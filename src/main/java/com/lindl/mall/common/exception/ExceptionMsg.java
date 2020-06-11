package com.lindl.mall.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:53
 */
public class ExceptionMsg {

    /**
     * 异常Map,key是异常码，value是异常信息
     */
    public static Map<String, String> msg = new HashMap<String, String>();

    /**
     * @Author Lindonglin
     * @Description //TODO异常构建
     * @Date 2020/3/17
     * @Param [code, log, args]  错误码,错误日志,错误参数
     * @return com.lindl.common.exception.ExceptionHandler
     **/
    ExceptionHandler build(String code, String log, String... args) throws Exception {
        String message = msg.get(code);
        if (args != null && args.length != 0) {
            String result = new String(message);
            for (int i = 0; i < args.length; i++) {
                result = result.replace("{" + i + "}", args[i]);
            }
            message = result;
        }
        return new ExceptionHandler(message, code, log);
    }

    /**
     * 渠道编号不能为空
     **/
    public static final String ERR_CHANNEL_ISNULL = "100001";
    /**
     * 渠道编号不存在
     **/
    public static final String ERR_CHANNEL_ISNOTEXIST = "100002";
    /**
     * 签名异常
     **/
    public static final String ERR_SIGN_EXCEPTION = "100003";

    public static final String ERROR_PARAMTER_MISS = "100004";

    /**
     * 参数校验异常
     */
    public static final String ERR_PARA_VALIDATION_EXCEPTION = "100004";

    public static final String ERR_AUTHENTICATION_ISNULL = "100005";

    public static final String ERR_AUTHENCATION_ISUNVALIDATE = "100006";

    public static final String ERR_AUTHENCATION_ERROR = "100007";

    public static final String UNAUTHORTY_UNLESS = "100008";

    public static final String ERR_USER_ISEXIST = "100009";

    public static final String ERR_INSERT = "100010";

    public static final String ERR_DELETE = "100011";

    public static final String ERR_UPDATE = "100012";



    /** ========== ERROR MSG =========== **/
    static {
        msg.put(ERR_CHANNEL_ISNULL, "渠道编号不能为空");
        msg.put(ERR_CHANNEL_ISNOTEXIST, "渠道编号不存在");
        msg.put(ERR_SIGN_EXCEPTION, "签名异常");
        msg.put(ERR_PARA_VALIDATION_EXCEPTION, "参数校验异常");
        msg.put(ERR_AUTHENTICATION_ISNULL, "缺少认证参数");
        msg.put(ERR_AUTHENCATION_ISUNVALIDATE, "登录已过期，请重新登录");
        msg.put(ERR_AUTHENCATION_ERROR, "认证失败，请重新登录");
        msg.put(ERROR_PARAMTER_MISS, "缺少必要参数");
        msg.put(UNAUTHORTY_UNLESS, "权限不足");
        msg.put(ERR_USER_ISEXIST, "用户不存在");
        msg.put(ERR_INSERT, "新增操作失败");
        msg.put(ERR_DELETE, "删除操作失败");
        msg.put(ERR_UPDATE, "更新操作失败");
    }
}
