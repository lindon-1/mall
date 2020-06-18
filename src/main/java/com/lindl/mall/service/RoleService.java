package com.lindl.mall.service;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.vo.req.MallRoleAddReq;
import com.lindl.mall.vo.req.MallRoleListReq;
import com.lindl.mall.vo.res.MallRoleListRes;
import org.springframework.validation.BindingResult;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 15:41
 */
public interface RoleService {
    HttpResponse add(HttpRequest<MallRoleAddReq> request, BindingResult bindingResult) throws Exception;

    HttpResponse<MallRoleListRes> findList(HttpRequest<MallRoleListReq> request) throws Exception;

    HttpResponse delete(Long id) throws Exception;
}
