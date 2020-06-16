package com.lindl.mall.config;

import com.lindl.mall.common.HttpResponse;
import com.lindl.mall.common.constant.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description：异常统一处理
 * @Author: ldl
 * @CreateDate: 2020/6/16 14:28
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

    @ExceptionHandler
    public HttpResponse handle(Exception ex) {
        log.error("异常信息：{}", ex.getCause().toString());
        return HttpResponse.build(ResultCode.EXCEPTION, ex.getCause().toString(), null);
    }
}
