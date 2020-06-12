package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.vo.req.MallResourceListReq;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:20
 */
@Mapper
public interface MallResourceMapper {

    @Select({
            "<script> ",
            "select permission,method_type from mall_resource where id in ",
            "<foreach item = 'id' collection ='ids' open='(' close=')' separator= ','> ",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<MallResource> findByIds(@Param("ids") List<Long> ids);

    @Select({
            "<script> ",
            "select r.permission,r.method_type from mall_role_resource rr  left join mall_resource r on rr.resource_id = r.id where rr.role_id = #{roleId}",

            "</script>"
    })
    @Results({
            @Result(column="method_type", property="methodType", jdbcType = JdbcType.VARCHAR),

    })
    List<MallResource> findByRoleId(@Param("roleId")Long roleId);

    @Insert({
            "insert into mall_resource(permission,method_type) values(#{permission},#{methodType})"
    })
    int insert(MallResource mallResource);

    @Insert({
            "<script> ",
            "insert into mall_resource(permission,method_type) values",
            "<foreach item = 'mallResource' collection ='mallResources'  separator= ','> ",
            "(#{mallResource.permission},#{mallResource.methodType})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("mallResources") List<MallResource> mallResources);

    @Update({
            "update mall_resource set deleted = 1 where id = #{id}"
    })
    int delete(@Param("id")Long id);

    @Select({
            "<script> ",
            "select * from mall_resource where 1=1 ",
            "<if test =\"mallResourceListReq.permission !=null and mallResourceListReq.permission !='' \">",
            " and permission =#{mallResourceListReq.permission}",
            "</if>",
            "<if test =\"mallResourceListReq.methodType !=null and mallResourceListReq.methodType !='' \">",
            " and method_type =#{mallResourceListReq.methodType}",
            "</if>",
            "<if test =\"mallResourceListReq.startTime !=null and mallResourceListReq.endTime !=null \">",
//            <![CDATA[ >= ]]> #{startTime} and create_time  <![CDATA[ <= ]]> #{endTime}
            " and create_time  <![CDATA[ >= ]]> #{mallResourceListReq.startTime} and create_time  <![CDATA[ <= ]]> #{mallResourceListReq.endTime}",
            "</if>",
            " order by  create_time desc",
            "</script>"
    })
    @Results({
            @Result(column="method_type", property="methodType", jdbcType = JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType = JdbcType.TIMESTAMP)

    })
    List<MallResource> findList(@Param("mallResourceListReq")MallResourceListReq mallResourceListReq);

    @Update({
            "update mall_resource set permission =#{permission},method_type=#{methodType} where id = #{id}"
    })
    int update(MallResource mallResource);

}
