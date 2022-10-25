package com.github.binarywang.demo.wx.cp.config.mutil;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean(value = "redissonClient", destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws Exception{

        Config config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%s", this.host, this.port));
        if (!this.password.isEmpty()) {
            config.useSingleServer().setPassword(this.password);
        }
        config.useSingleServer().setDatabase(this.database);

        StringCodec codec = new StringCodec();
        config.setCodec(codec);
        return Redisson.create(config);
    }

}
