package com.lindl.mall.vo.res;

import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.Model;
import com.lindl.mall.pojo.MallResource;
import lombok.Builder;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/12 14:25
 */
@Data
@Builder
public class MallResourceListRes extends Model {

    private PageInfo<MallResource> pageInfo;
}
