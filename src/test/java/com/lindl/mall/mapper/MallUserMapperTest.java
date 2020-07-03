package com.lindl.mall.mapper;

import com.alibaba.fastjson.JSONArray;
import com.lindl.mall.pojo.MallResource;
import com.lindl.mall.pojo.MallRole;
import com.lindl.mall.pojo.MallUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
class MallUserMapperTest {

    @Resource
    private MallUserMapper mallUserMapper;

    @Resource
    private MallRoleMapper mallRoleMapper;

    @Resource
    private MallResourceMapper mallResourceMapper;

    @Resource
    private RedisTemplate<String, List<MallUser>> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() throws Exception{
        List<MallUser> list = mallUserMapper.findList(null);
        Long user_key = stringRedisTemplate.opsForList().leftPush("USER_KEY", JSONArray.toJSONString(list));
        System.out.println(user_key);
//        System.out.println(redisTemplate.opsForList().leftPop("USER_KEY"));
//        System.out.println(redisTemplate.opsForValue().get("ddd "));
//        stringRedisTemplate.opsForValue().set("nums", "0L");
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<CompletableFuture<Void>> nums = IntStream.rangeClosed(0, 1000).boxed().parallel().map(e -> {
//            return CompletableFuture.runAsync(() -> {
//                System.out.println(Thread.currentThread().getName() + ":  ");
//                stringRedisTemplate.opsForValue().increment("agcd");
//            }, executorService);
//        }).collect(Collectors.toList());
//
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(nums.toArray(new CompletableFuture[]{}));
//        voidCompletableFuture.get();
//        System.out.println(redisTemplate.opsForValue().get("numbb "));
        

    }

    @Test
    public void test1() {
        MallUser mallUser = new MallUser();
        mallUser.setUsername("hongsheng");
        mallUser.setPassword("123456");
        mallUser.setBirthday(new Date());
        mallUser.setMobile("13433836156");
        mallUser.setLastLoginIp("192.168.44.128");
        mallUserMapper.insert(mallUser);
        System.out.println(mallUser);

//        MallRole mallRole = new MallRole();
//        mallRole.setDesc("添加");
//        mallRole.setName("/admin/delete");
//        System.out.println(mallRole.getDesc());
//        mallRoleMapper.insert(mallRole);
//        MallRole byId = mallRoleMapper.findById(1L);
//        System.out.println(byId);
//        MallUser byId1 = mallUserMapper.findById(1L);
//        System.out.println(byId1);

    }

    @Test
    public void test2() {
        List<MallResource> byRoleId = mallResourceMapper.findByRoleId(1L);
        System.out.println(byRoleId);
    }
}
