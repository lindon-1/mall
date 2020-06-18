package com.lindl.mall.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 15:45
 */
@Data
public class MallRoleAddReq {
    @NotNull
    private String name;

    private String desc;

    /**
     * 是否启用 1-是 0-否 默认1
     */
    private byte enabled;

    @NotNull(message = "请绑定对应的资源")
    private List<Long> resourceIds;
}
