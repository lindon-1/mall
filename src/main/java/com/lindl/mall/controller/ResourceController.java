package com.lindl.mall.controller;

import com.lindl.mall.common.HttpRequest;
import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.service.ResourceService;
import com.lindl.mall.vo.req.MallResourceListReq;
import com.lindl.mall.vo.res.MallResourceListRes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/12 14:19
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @PostMapping("/add")
    public HttpResponse add(@RequestBody HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception {
        return resourceService.add(request, bindingResult);
    }

    @DeleteMapping("/delete")
    public HttpResponse delete(@RequestParam(value = "id", required = true) Long id) throws Exception {
        return resourceService.delete(id);
    }

    @PostMapping("/findList")
    public HttpResponse<MallResourceListRes> findList(@RequestBody HttpRequest<MallResourceListReq> request){
        return resourceService.findList(request);
    }

    @PostMapping("/update")
    public HttpResponse modify(@RequestBody HttpRequest<MallResource> request, BindingResult bindingResult) throws Exception {
        return resourceService.modify(request, bindingResult);
    }
}
