package com.lindl.mall.service;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.vo.req.MallLoginReq;
import com.lindl.mall.vo.res.MallInfoRes;
import com.lindl.mall.vo.res.MallLoginRes;
import org.springframework.validation.BindingResult;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:09
 */
public interface LoginService {

    HttpResponse<MallLoginRes> login(HttpRequest<MallLoginReq> request, BindingResult bindingResult) throws Exception;

    HttpResponse loginOut();

    HttpResponse<MallInfoRes> info(String token)  throws Exception;
}
