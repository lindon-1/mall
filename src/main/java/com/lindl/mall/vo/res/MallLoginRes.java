package com.lindl.mall.vo.res;

import com.lindl.mall.common.Model;
import lombok.Builder;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 11:12
 */
@Data
@Builder
public class MallLoginRes extends Model {
    private String username;

    private String avatar;

    private String token;
}
