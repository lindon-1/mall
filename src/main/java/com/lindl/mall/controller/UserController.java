package com.lindl.mall.controller;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallUser;
import com.lindl.mall.service.UserService;
import com.lindl.mall.vo.req.MallUserListReq;
import com.lindl.mall.vo.res.MallUserListRes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:35
 */

@RestController()
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "user/list")
    public HttpResponse<MallUserListRes> list(@RequestBody HttpRequest<MallUserListReq> request) {
        return userService.findList(request);
    }

    @PostMapping("user/add")
    public HttpResponse add(@RequestBody@Validated HttpRequest<MallUser> request, BindingResult bindingResult) throws Exception {
        return userService.add(request, bindingResult);
    }

    @DeleteMapping("user/delete")
    public HttpResponse delete(@RequestParam("id") Long id) throws Exception {
        return userService.delete(id);
    }

    @PostMapping("user/update")
    public HttpResponse update(@RequestBody@Validated HttpRequest<MallUser> request, BindingResult bindingResult) throws Exception {
        return userService.modify(request, bindingResult);
    }
}
