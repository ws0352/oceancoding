package com.oceancoding.ws.ocean.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Redisutils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
    * 写入缓存
    * */
    public boolean set(final String key, Object value){
        boolean result = false;
        try{
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys){
        for(String key : keys){
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(final String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     */
    public void remove(final String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断是否存在对应的value
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key){
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * hash添加数据
     */
    public void hmset(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, hashKey, value);
    }

    /**
     * hash获取数据
     */
    public Object hmGet(String key,  Object hashKey){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }

    /**
     * 列表添加
     */
    public void lPush(String key, Object value){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.rightPush(key,value);
    }
    /**
     * 列表获取
     */
    public Object lPush(String key, long l,long l1){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, l, l1);
    }

    /**
     * 集合添加
     */
    public void add(String key, Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }
    /**
     * 集合获取
     */
    public Set<Object> setMembers(String key, long l, long l1){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }
    /**
     * 有序集合添加
     */
    public void zAdd(String key, Object value, double source){
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, source);
    }
    /**
     * 有序集合获取
     */
    public Object rangeByScore(String key, double source, double source1){
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, source, source1);
    }
}
