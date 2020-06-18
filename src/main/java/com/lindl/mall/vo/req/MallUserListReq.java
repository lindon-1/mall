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

    /**
     * 分页默认一页10条数据
     */
    public Integer pageSize = PagerConstant.PAGE_SIZE;

    /**
     * 分页默认第一页
     */
    public Integer pageNum = PagerConstant.PAGE_NUM;


}
