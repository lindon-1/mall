package com.lindl.mall.mapper;

import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.pojo.MallUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;


@SpringBootTest
class MallUserMapperTest {

    @Resource
    private MallUserMapper mallUserMapper;

    @Resource
    private MallRoleMapper mallRoleMapper;

    @Test
    public void test1() {
        MallUser mallUser = new MallUser();
        mallUser.setUsername("youxiang");
        mallUser.setPassword("123456");
        mallUser.setBirthday(new Date());
        mallUser.setMobile("13433836156");
        mallUser.setLastLoginIp("192.168.44.128");
        mallUserMapper.insert(mallUser);

        MallRole mallRole = new MallRole();
        mallRole.setDesc("添加");
        mallRole.setName("/admin/delete");
        System.out.println(mallRole.getDesc());
        mallRoleMapper.insert(mallRole);
        MallRole byId = mallRoleMapper.findById(1L);
        System.out.println(byId);
        MallUser byId1 = mallUserMapper.findById(1L);
        System.out.println(byId1);
    }
}
