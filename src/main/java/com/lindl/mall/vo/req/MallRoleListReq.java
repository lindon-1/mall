package com.lindl.mall.vo.req;

import com.lindl.mall.common.constant.PagerConstant;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 16:28
 */
@Data
public class MallRoleListReq {
    private String name;

    /**
     * 分页默认一页10条数据
     */
    public Integer pageSize = PagerConstant.PAGE_SIZE;

    /**
     * 分页默认第一页
     */
    public Integer pageNum = PagerConstant.PAGE_NUM;

}
