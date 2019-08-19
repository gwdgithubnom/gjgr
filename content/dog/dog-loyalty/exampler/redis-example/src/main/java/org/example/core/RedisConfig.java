package org.example.core;

/**
 * File Name : essen-apps - com.essen.apps.moniter.core
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/2/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/*@Configuration
@EnableAutoConfiguration*/
public class RedisConfig {
/*    @Bean*/
    public RedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        JedisConnectionFactory ob = new JedisConnectionFactory(poolConfig);
        ob.setUsePool(true);
        ob.setHostName("localhost");
        ob.setPort(6379);
        return ob;
    }
/*    @Bean*/
    public StringRedisTemplate  stringRedisTemplate(){
        return new StringRedisTemplate(jedisConnectionFactory());
    }
}
