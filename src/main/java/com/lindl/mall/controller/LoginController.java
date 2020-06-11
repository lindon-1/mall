package com.lindl.mall.controller;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.service.LoginService;
import com.lindl.mall.vo.req.MallLoginReq;
import com.lindl.mall.vo.res.MallInfoRes;
import com.lindl.mall.vo.res.MallLoginRes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:08
 */
@RestController()
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping(value = "/login")
    public HttpResponse<MallLoginRes> login(@RequestBody@Validated HttpRequest<MallLoginReq> request, BindingResult bindingResult) throws Exception{
        return loginService.login(request, bindingResult);
    }

    @PostMapping(value = "/info")
    public HttpResponse<MallInfoRes> info(@RequestParam("token") String token) throws Exception {
        return loginService.info(token);
    }

    @PostMapping
    public HttpResponse loginOut() throws Exception {
        return loginService.loginOut();
    }

}
