package com.lindl.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:43
 */
@Data
public class MallResource {

    private Long id;

    private String permission;

    private String methodType;

    /**
     * 是否逻辑删除 0-否  1-是  默认0
     */
    private byte deleted;

    private Date createTime;

    private Date updateTime;
}
