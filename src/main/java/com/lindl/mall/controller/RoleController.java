package com.lindl.mall.controller;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.service.RoleService;
import com.lindl.mall.vo.req.MallRoleAddReq;
import com.lindl.mall.vo.req.MallRoleListReq;
import com.lindl.mall.vo.res.MallRoleListRes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 15:40
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    public HttpResponse add(@RequestBody@Validated HttpRequest<MallRoleAddReq> request, BindingResult bindingResult) throws Exception {
        return roleService.add(request, bindingResult);
    }

    @GetMapping("/findList")
    public HttpResponse<MallRoleListRes> findList(@RequestBody HttpRequest<MallRoleListReq> request) throws Exception {
        return roleService.findList(request);
    }

    @DeleteMapping("/delete")
    public HttpResponse delete(@RequestParam Long id) throws Exception {
        return roleService.delete(id);
    }
}
