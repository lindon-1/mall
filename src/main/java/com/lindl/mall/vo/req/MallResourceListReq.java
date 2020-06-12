package com.lindl.mall.vo.req;

import com.lindl.mall.common.constant.PagerConstant;
import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/12 14:23
 */
@Data
public class MallResourceListReq extends PagerConstant {

    private String permission;

    private String methodType;

    private Date startTime;

    private Date endTime;
}
