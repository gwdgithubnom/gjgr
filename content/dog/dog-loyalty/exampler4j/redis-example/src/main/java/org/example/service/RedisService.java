package org.example.service;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * File Name : example - org.example.service
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
public interface RedisService {
    public RedisTemplate<Serializable, Serializable> getRedisTemplate();
    public Jedis getJedis();
    public void set(final Serializable key, final Serializable value);
    public void set(final Serializable key, final Serializable value, final long liveTime);
    public void del(final Serializable... keys);
    public boolean is(final Serializable key);
    public void flush();
    public Serializable get(final Serializable key);
}
