package com.lindl.mall.service;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.vo.req.MallResourceListReq;
import com.lindl.mall.vo.res.MallResourceListRes;
import org.springframework.validation.BindingResult;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/12 14:20
 */
public interface ResourceService {

    HttpResponse add(HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception;

    HttpResponse delete(Long id) throws Exception;

    HttpResponse<MallResourceListRes> findList(HttpRequest<MallResourceListReq> request);

    HttpResponse modify(HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception;
}
