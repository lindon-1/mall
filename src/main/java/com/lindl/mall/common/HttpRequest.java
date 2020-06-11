package com.lindl.mall.common;

import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:43
 */
@Data
public class HttpRequest<T> extends Model   {
    /**
     * 客户端的版本
     */
    private String version;
    /**
     * 客户端的渠道编号
     */

    private String channelId;
    /**
     * 客户端的应用渠道
     */

    private String useChannel;
    /**
     * 客户端发起请求的时间
     */

    private String time;
    /**
     * 请求的数据对象
     */

    private T data;

    /**
     * 请求的密文，如果该接口需要加密上送，
     * 则将sdt的密文反序化到data，
     * sdt和action至少有一个为空
     */

    private String sdt;

    /**
     * 签名
     **/

    private String sign;
}
