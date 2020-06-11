package com.lindl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import com.lindl.mall.mapper.*;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.pojo.MallUser;
import com.lindl.mall.service.LoginService;
import com.lindl.mall.utils.MD5Utils;
import com.lindl.mall.vo.req.MallLoginReq;
import com.lindl.mall.vo.res.MallInfoRes;
import com.lindl.mall.vo.res.MallLoginRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:11
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private MallUserMapper mallUserMapper;

    @Resource
    private MallUserRoleMapper mallUserRoleMapper;
    @Resource
    private MallRoleMapper mallRoleMapper;

    @Resource
    private MallRoleResourceMapper mallRoleResourceMapper;

    @Resource
    private MallResourceMapper mallResourceMapper;
    @Resource
    private ExceptionFactory exceptionFactory;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private HttpServletRequest request;

    @Value("${auth.key:auth_key_pre_}")
    public  String key;

    @Value("${auth.effectiveTime}")
    public Long effectiveTime;


    @Override
    public HttpResponse<MallLoginRes> login(HttpRequest<MallLoginReq> request, BindingResult bindingResult) throws Exception {
        MallLoginReq data = request.getData();
        if (bindingResult.hasErrors()) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        String password = data.getPassword();
        String username = data.getUsername();
        String md5DigestAsHexByPassWord = DigestUtils.md5DigestAsHex(password.getBytes());
        MallUser mallUser = mallUserMapper.findByUsernameAndPassword(username, md5DigestAsHexByPassWord);
        if(Objects.isNull(mallUser)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_USER_ISEXIST, ExceptionMsg.msg.get(ExceptionMsg.ERR_USER_ISEXIST));
        }
        Long id = mallUser.getId();
        String token = MD5Utils.encode(String.valueOf(id));
        String userInfo = JSONObject.toJSONString(mallUser);
        redisTemplate.opsForValue().set(token, userInfo, effectiveTime, TimeUnit.SECONDS);

        log.info("用户：{} 登录成功 token：{},userInfo:{}", id, token, userInfo);
        return HttpResponse.build(MallLoginRes.builder().avatar(mallUser.getAvatar()).token(token).username(username).build());
    }

    @Override
    public HttpResponse loginOut() {
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)) {
            redisTemplate.delete(token);
        }
        return null;
    }

    @Override
    public HttpResponse<MallInfoRes> info(String token) throws Exception{
        if(StringUtils.isEmpty(token)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ERROR, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ERROR));
        }
        String userInfoString = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoString)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE));
        }

        List<String> roles = new ArrayList<>(1);
        List<String> perms = new ArrayList<>();
        MallUser mallUser = JSONObject.parseObject(userInfoString, MallUser.class);
        Long roleId = mallUserRoleMapper.findRoleIdByUserId(mallUser.getId());
        if (!StringUtils.isEmpty(roleId)) {
            MallRole mallRole = mallRoleMapper.findById(roleId);
            roles.add(mallRole.getName());
            List<Long> resourceIds = mallRoleResourceMapper.findResourceIdsByRoleId(roleId);
            List<MallResource> mallResources = null;
            if (!CollectionUtils.isEmpty(resourceIds)) {
                mallResources = mallResourceMapper.findByIds(resourceIds);
            }
            if (!CollectionUtils.isEmpty(mallResources)) {
                perms = mallResources.stream().map(e -> e.getMethodType() + " " + e.getPermission()).collect(Collectors.toList());
            }
        }
        MallInfoRes mallInfoRes = MallInfoRes.builder().avatar(mallUser.getAvatar()).name(mallUser.getUsername()).perms(perms).roles(roles).build();
        log.info("用户信息：{}", mallInfoRes);
        return HttpResponse.build(mallInfoRes);
    }
}
