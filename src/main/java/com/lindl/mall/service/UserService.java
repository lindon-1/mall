package com.lindl.mall.service;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallUser;
import com.lindl.mall.vo.req.MallUserListReq;
import com.lindl.mall.vo.res.MallUserListRes;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:37
 */
public interface UserService {

    HttpResponse add(HttpRequest<MallUser> request, BindingResult bindingResult)  throws Exception;

    HttpResponse delete(Long id)  throws Exception;

    HttpResponse<MallUserListRes> findList(HttpRequest<MallUserListReq> request);

    MallUser findById(Long id);

    HttpResponse modify(HttpRequest<MallUser> request, BindingResult bindingResult) throws Exception;
}
