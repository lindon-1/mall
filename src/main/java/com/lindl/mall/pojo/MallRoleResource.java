package com.lindl.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:46
 */
@Data
public class MallRoleResource {
    private Long id;

    private Long roleId;

    private Long resourceId;

    private Date createTime;

    private Date updateTime;
}
