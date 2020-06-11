package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 16:23
 */
@Mapper
public interface MallRoleMapper {

    @Insert({
            "insert into mall_role(`name`,`desc`) values(#{name},#{desc})"
    })
    int insert(MallRole mallRole);

    @Select({
            "select * from mall_role where id =#{id}"
    })
    @Results({
            @Result(column="create_time", property="createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    MallRole findById(@Param("id") Long id);

}
