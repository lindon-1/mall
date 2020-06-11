package com.lindl.mall.vo.req;

import com.lindl.mall.common.constant.PagerConstant;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 18:13
 */
@Data
public class MallUserListReq extends PagerConstant {

    private String username;


}
