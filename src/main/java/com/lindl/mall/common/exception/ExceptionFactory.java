package com.lindl.mall.common.exception;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:53
 */
/**
 * @Description TODO异常工厂，各个模块交给Spring管理，无须每次新增实例
 * @Author Lindonglin
 * @date 2020/3/17 15:09
 **/
public class ExceptionFactory {

    /**
     * 具体的异常类
     */
    public ExceptionMsg msg = null;

    public static ExceptionFactory exceptionFatory = null;

    /**
     * 该实例为单例
     */
    public ExceptionFactory(ExceptionMsg msg) {
        this.msg = msg;
    }

    /**
     * @Author Lindonglin
     * @Description //TODO获取对象
     * @Date 2020/3/17
     * @Param [msg]
     * @return com.lindl.common.exception.ExceptionFactory
     **/
    public static ExceptionFactory getInstance(ExceptionMsg msg) {
        synchronized (ExceptionFactory.class) {
            if (exceptionFatory == null) {
                exceptionFatory = new ExceptionFactory(msg);
            }
            return exceptionFatory;
        }
    }

    public ExceptionHandler create(String code, String log, String... args) throws Exception {
        return msg.build(code, log, args);
    }
}
