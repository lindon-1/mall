package com.lindl.mall.controller;

import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.vo.res.MallBashbordRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 16:08
 */
@RestController
public class DashbordController {

    @GetMapping("/bashbord")
    public HttpResponse<MallBashbordRes> info() {
        return HttpResponse.build(MallBashbordRes.builder().build());
    }
}
