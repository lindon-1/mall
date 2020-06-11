package com.lindl.mall.vo.res;

import com.lindl.mall.common.Model;
import lombok.Builder;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 16:09
 */
@Data
@Builder
public class MallBashbordRes extends Model {

    private Long userTotal;

    private Long goodsTotal;

    private Long productTotal;

    private Long orderTotal;
}
