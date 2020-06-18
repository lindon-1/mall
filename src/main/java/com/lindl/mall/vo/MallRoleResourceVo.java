package com.lindl.mall.vo;

import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.pojo.MallRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 16:23
 */
@Data
public class MallRoleResourceVo implements Serializable {
    private MallRole mallRole;
    private List<MallResource> resources;
}
