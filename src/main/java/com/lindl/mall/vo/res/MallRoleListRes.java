package com.lindl.mall.vo.res;

import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.Model;
import com.lindl.mall.pojo.MallRole;
import lombok.Builder;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/18 16:21
 */
@Data
@Builder
public class MallRoleListRes extends Model {
    private PageInfo<MallRole> pageInfo;
}
