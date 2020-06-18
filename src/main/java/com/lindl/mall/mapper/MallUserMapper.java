package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.pojo.MallUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/6/10 15:48
 */
@Mapper
public interface MallUserMapper {

    @Insert({
            "<script>",
            "insert into mall_user(`username`,`password`,`gender`,`birthday`,`last_login_time`,`last_login_ip`,`nickname`,`mobile`,`avatar`,`openid`,`session_key`) ",
            "values(#{username},#{password},#{gender},#{birthday},#{lastLoginTime},#{lastLoginIp},#{nickname},#{mobile},#{avatar},#{openid},#{sessionKey})",
            "</script>"
    })
    @Results(value = {
            @Result(column="birthday", property="birthday", jdbcType = JdbcType.DATE)
    })
    int insert(MallUser mallUser);

    @Select("select * from mall_user where id = #{id}")
    @Results({
            @Result(column="create_time", property="createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column="birthday", property="birthday", jdbcType = JdbcType.DATE)
    })
    MallUser findById(@Param("id")Long id);

    @Select({
            "<script>",
            "select * from mall_user where deleted = 0",
            "<if test=\"username !=null and username != ''\">",
            "and username = #{username}",
            "</if>",
            "</script>"
    })
    List<MallUser> findList(@Param("username")String username);

    @Select("select * from mall_user where username=#{username} and password=#{password}")
    MallUser findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);


    @Delete({
            "update mall_user set deleted = 1 where id = #{id}"
    })
    int delete(@Param("id")Long id);

    @Update({
            "update mall_user set username = #{username},password = #{password},gender=#{gender},birthday=#{birthday},nickname=#{nickname},mobile=#{mobile} where id = #{id}"
    })
    int modify(@Param("mallUser") MallUser mallUser);

    @Update("update mall_user set last_login_ip=#{lastLoginIp}, last_login_time=now() where id =#{id}")
    int modifyLoginIp(@Param("id")Long id, @Param("lastLoginIp")String lastLoginIp);

    @Select({
            "<script>",
            "select role_id from mall_user_role r, mall_user u where r.user_id = u.id ",
            "</script>"
    })
    Long findRoleIdByUserId(@Param("id")Long id);
}
