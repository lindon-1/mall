package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 17:22
 */
@Mapper
public interface MallUserRoleMapper {

    @Insert({
            "insert into mall_user_role(role_id, user_id) values(#{roleId},#{userId})"
    })
    int insert(MallUserRole mallUserRole);

    @Insert({
            "<script> ",
            "insert into mall_user_role(role_id, user_id) values",
            "<foreach item = 'mallUserRole' collection ='mallUserRoles'  separator= ','> ",
            "(#{mallUserRole.roleId}, #{mallUserRole.userId})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("mallUserRoles") List<MallUserRole> mallUserRoles);

    @Select("select role_id from mall_user_role where user_id = #{userId}")
    Long findRoleIdByUserId(@Param("userId")Long userId);
}
