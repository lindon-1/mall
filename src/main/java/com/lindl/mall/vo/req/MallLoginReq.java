package com.lindl.mall.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:09
 */
@Data
public class MallLoginReq {


    @NotNull
    private String username;

    @NotNull
    private String password;
}
