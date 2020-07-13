package com.lindl.mall.dbcache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Description： 封装获取数据先从redis读取，没有再去数据库拿数据
 * @Author: ldl
 * @CreateDate: 2020/7/13 15:14
 */
@Component
public class DbcacheByRedis{

    @Resource
    private RedisTemplate<Long, Object> redisTemplate;

    public  <R> R  getData(Long id, Function<Long, R> function) {
        long start = System.currentTimeMillis();
        if (id == null) {
            return null;
        }
        R r = (R)redisTemplate.opsForValue().get(id);
        if (!Objects.isNull(r)) {
            System.out.println("缓存命中。。");
            System.out.println("时长： " + (System.currentTimeMillis() - start));
            return r;
        }
        r = function.apply(id);
        redisTemplate.opsForValue().set(id, r);
        System.out.println("时长： " + (System.currentTimeMillis() - start));
        return r;
    }
}
