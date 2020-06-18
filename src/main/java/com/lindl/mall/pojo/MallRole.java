package com.lindl.mall.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:41
 */
@Data
public class MallRole {
    private Long id;

    private String name;

    private String desc;

    /**
     * 是否启用 1-是 0-否 默认1
     */
    private byte enabled;

    /**
     * 是否逻辑删除 0-否  1-是  默认0
     */
    private byte deleted;

    private Date createTime;

    private Date updateTime;
}
