package com.lindl.mall.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/7/6 10:32
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
//        // 默认连接地址 127.0.0.1:6379
//        RedissonClient redisson = Redisson.create();

        Config config = new Config();
        //单redis节点配置
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //主从节点配置
//        config.useMasterSlaveServers()
//                //可以用"rediss://"来启用SSL连接
//                .setMasterAddress("redis://192.168.44.128:6379")
//                .addSlaveAddress("redis://192.168.44.128:6380");
//                .addSlaveAddress("redis://192.168.44.128:6381");

        return Redisson.create(config);
    }
}
