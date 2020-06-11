package com.lindl.mall.vo.res;

import com.lindl.mall.common.Model;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/11 15:13
 */
@Data
@Builder
public class MallInfoRes extends Model {

    private String avatar;

    private String name;

    private List<String> perms;

    private List<String> roles;
}
