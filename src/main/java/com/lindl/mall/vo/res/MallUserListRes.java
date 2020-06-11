package com.lindl.mall.vo.res;

import com.github.pagehelper.PageInfo;
import com.lindl.mall.common.Model;
import com.lindl.mall.pojo.MallUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:59
 */
@Data
@Builder
public class MallUserListRes extends Model {

    private PageInfo<MallUser> pageInfo;

}
