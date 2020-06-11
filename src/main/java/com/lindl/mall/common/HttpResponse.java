package com.lindl.mall.common;

import com.lindl.mall.common.constant.ResultCode;
import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:44
 */
@Data
public class HttpResponse<T extends Model> extends Model {
    private String code;
    private String msg;
    private T data;
    private Date time;

    public HttpResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = new Date();
    }
    /**
     * @Author Lindonglin
     * @Description //TODO构建一个响应
     * @Date 2020/3/10
     * @Param [code, msg, data]
     * @return com.lindl.base.HttpResponse<T>
     **/
    public static <T extends Model> HttpResponse<T> build(String code, String msg, T data) {
        return new HttpResponse<T>(code, msg, data);
    }

    /**
     * @Author Lindonglin
     * @Description //TODO构建一个成功带数据和msg的响应
     * @Date 2020/3/10
     * @Param [msg, data]
     * @return com.lindl.base.HttpResponse<T>
     **/
    public static <T extends Model> HttpResponse<T> build(String msg, T data) {
        return build(ResultCode.SUCCESS, msg, data);
    }

    /**
     * @Author Lindonglin
     * @Description //TODO构建一个成功带响应数据的响应（不带msg）
     * @Date 2020/3/10
     * @Param [data]
     * @return com.lindl.base.HttpResponse<T>
     **/
    public static <T extends Model> HttpResponse<T> build(T data) {
        return build(null, data);
    }
}
