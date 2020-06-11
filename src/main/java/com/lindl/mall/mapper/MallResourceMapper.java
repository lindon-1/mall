package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallResource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
