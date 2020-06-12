package com.lindl.mall.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import com.lindl.mall.mapper.MallResourceMapper;
import com.lindl.mall.mapper.MallUserMapper;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.pojo.MallUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：用户token校验
 * @Author: ldl
 * @CreateDate: 2020/6/11 16:15
 */
@Order(2)
@Aspect
@Component
public class TokenCheck {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ExceptionFactory exceptionFactory;

    @Resource
    private MallUserMapper mallUserMapper;

    @Resource
    private MallResourceMapper mallResourceMapper;

    @Before("execution(* com.lindl..*Controller.*(..)) && !execution(* com.lindl..LoginController.*(..))")
    public void before(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ERROR, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ERROR));
        }
        String userInfoString = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(userInfoString)) {
            throw exceptionFactory.create(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE, ExceptionMsg.msg.get(ExceptionMsg.ERR_AUTHENCATION_ISUNVALIDATE));
        }

        String requestUrl = request.getRequestURL().toString();
        //获取请求方式
        String methodType = request.getMethod().toUpperCase();
        //获取根路径
        String path = ResourceUtils.getURL(requestUrl).getPath();
        MallUser mallUser = JSONObject.parseObject(userInfoString, MallUser.class);
        Long roleId = mallUserMapper.findRoleIdByUserId(mallUser.getId());
        List<MallResource> mallResources = mallResourceMapper.findByRoleId(roleId);
        if (CollectionUtils.isEmpty(mallResources)) {
            throw exceptionFactory.create(ExceptionMsg.UNAUTHORTY_UNLESS, ExceptionMsg.msg.get(ExceptionMsg.UNAUTHORTY_UNLESS));
        }
        List<MallResource> list = mallResources.stream().filter(e -> e.getPermission().equals(path) && e.getMethodType().equals(methodType)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            throw exceptionFactory.create(ExceptionMsg.UNAUTHORTY_UNLESS, ExceptionMsg.msg.get(ExceptionMsg.UNAUTHORTY_UNLESS));
        }

    }
}
