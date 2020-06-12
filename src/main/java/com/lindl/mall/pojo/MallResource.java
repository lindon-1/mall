package com.lindl.mall.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:43
 */
@Data
public class MallResource {

    private Long id;

    @NotNull
    private String permission;

    @NotNull
    private String methodType;

    /**
     * 是否逻辑删除 0-否  1-是  默认0
     */
    private byte deleted;

    private Date createTime;

    private Date updateTime;
}
