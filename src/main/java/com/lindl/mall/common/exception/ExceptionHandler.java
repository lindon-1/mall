package com.lindl.mall.common.exception;

import lombok.Data;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:54
 */
@Data
public class ExceptionHandler extends Exception implements Serializable, Supplier<ExceptionHandler> {
    private static final long serialVersionUID = 7250629556180017484L;
    /**
     * 错误响应码
     */
    private String code;
    /**
     * 日志信息（统一异常处理答应）
     */
    private String log;

    public ExceptionHandler(String message, String code, String log) {
        super(message);
        this.code = code;
        this.log = log;
    }

    @Override
    public ExceptionHandler get() {
        return this;
    }
}
