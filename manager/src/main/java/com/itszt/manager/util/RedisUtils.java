package com.itszt.manager.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
//			redisTemplate.opsForValue().set(key, value);
            System.out.println(new RedisTemplate<>()+"+++++++++++___________");
            System.out.println(key+"key   key");
            System.out.println(value+"value    value");
            System.out.println(redisTemplate+"  redistamplate  redisTemplate");
            redisTemplate.opsForValue().set(key, value);
//			new RedisTemplate<>().opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
