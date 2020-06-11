package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallRoleResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:31
 */
@Mapper
public interface MallRoleResourceMapper {

    @Insert({
            "insert into mall_role_resource(role_id,resource_id) values(#{roleId},#{resourceId})"
    })
    int insert(MallRoleResource mallRoleResource);

    @Insert({
            "<script> ",
            "insert into mall_role_resource(role_id,resource_id) values",
            "<foreach item = 'mallRoleResource' collection ='mallRoleResources'  separator= ','> ",
            "(#{mallRoleResource.roleId},#{mallRoleResource.resourceId})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("mallRoleResources") List<MallRoleResource> mallRoleResources);

    @Select("select resourceId from mall_role_resource where role_id = #{roleId}")
    List<Long> findResourceIdsByRoleId(@Param("roleId")Long roleId);
}
