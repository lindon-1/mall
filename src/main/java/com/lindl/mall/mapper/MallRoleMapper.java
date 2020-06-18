package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.vo.MallRoleResourceVo;
import com.lindl.mall.vo.req.MallRoleListReq;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 16:23
 */
@Mapper
public interface MallRoleMapper {

    @Insert(value = "insert into mall_role(`name`,`desc`) values(#{name},#{desc})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(MallRole mallRole);

    @Select({
            "select * from mall_role where id =#{id}"
    })
    @Results({
            @Result(column="create_time", property="createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    MallRole findById(@Param("id") Long id);

    @Select({
            "<script>",
            "select * from mall_role where deleted = 0",
            "<if test=\"name !=null and name != ''\">",
            "and name = #{name}",
            "</if>",
            "</script>"
    })

    @Results({
            @Result(column="create_time", property="createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType = JdbcType.TIMESTAMP)

    })
    List<MallRole> findList(@Param("name")String name);


    @Update("update mall_role set deleted = 1 where id = #{id}")
    int delete(Long id);
}
