package com.lindl.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.common.exception.ExceptionFactory;
import com.lindl.mall.common.exception.ExceptionMsg;
import com.lindl.mall.mapper.MallUserMapper;
import com.lindl.mall.pojo.MallUser;
import com.lindl.mall.service.UserService;
import com.lindl.mall.utils.IpUtils;
import com.lindl.mall.vo.req.MallUserListReq;
import com.lindl.mall.vo.res.MallUserListRes;
import jdk.jfr.DataAmount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:37
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MallUserMapper mallUserMapper;

    @Resource
    private ExceptionFactory exceptionFactory;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public HttpResponse add(HttpRequest<MallUser> request, BindingResult bindingResult) throws Exception{

        if (bindingResult.hasErrors()) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        MallUser data = request.getData();
        int count = mallUserMapper.insert(data);
        if (count != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_INSERT, ExceptionMsg.msg.get(ExceptionMsg.ERR_INSERT));
        }
        return HttpResponse.build(null);
    }

    @Override
    public HttpResponse delete(Long id) throws Exception{
        if (Objects.isNull(id)) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        int count = mallUserMapper.delete(id);
        if (count != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_DELETE, ExceptionMsg.msg.get(ExceptionMsg.ERR_DELETE));
        }

        return HttpResponse.build(null);
    }

    @Override
    public HttpResponse<MallUserListRes> findList(HttpRequest<MallUserListReq> request) {
        MallUserListReq mallUserListReq = request.getData();
        PageHelper.startPage(mallUserListReq.getPAGE_DEFAULT_NUM(), mallUserListReq.getPAGE_DEFAULT_SIZE());
        List<MallUser> list = mallUserMapper.findList(mallUserListReq.getUsername());
        PageInfo<MallUser> pageInfo = PageInfo.of(list);
        return HttpResponse.build(MallUserListRes.builder().pageInfo(pageInfo).build());
    }

    @Override
    public MallUser findById(Long id) {
        return null;
    }

    @Override
    public  HttpResponse modify(HttpRequest<MallUser> request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw exceptionFactory.create(ExceptionMsg.ERROR_PARAMTER_MISS, ExceptionMsg.msg.get(ExceptionMsg.ERROR_PARAMTER_MISS));
        }
        MallUser data = request.getData();
        int modify = mallUserMapper.modify(data);
        if (modify != 1) {
            throw exceptionFactory.create(ExceptionMsg.ERR_DELETE, ExceptionMsg.msg.get(ExceptionMsg.ERR_DELETE));
        }
        return HttpResponse.build(null);
    }
}
