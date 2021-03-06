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

    /**
     * 分页默认一页10条数据
     */
    public Integer pageSize = PagerConstant.PAGE_SIZE;

    /**
     * 分页默认第一页
     */
    public Integer pageNum = PagerConstant.PAGE_NUM;
}
