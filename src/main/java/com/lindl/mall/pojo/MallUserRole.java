package com.lindl.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:45
 */
@Data
public class MallUserRole {

    private Long id;

    private Long userId;

    private Long roleId;

    private Date createTime;

    private Date updateTime;
}
