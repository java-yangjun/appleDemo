package com.jung.channel.api.test.redisLock.utils;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface ICacheManager {

    String get(final String key);

    boolean set(final String key, final String value);

    boolean setNX(final String key, final String value);

    boolean setNX(final String key, final String value, final long time);

    String getSet(final String key, final String value);

    void delete(final String key);

    public Map setHashObject(String key, Object obj);

    List<Object> pipeline(RedisCallback<List<Object>> Object);

    boolean zadd(String key, String value, double score);

    void batchDel(String... pattern);

    String hget(String key, String value);

    Object hmget(String s, Class clazz);

    void hset(String s, String bidStatus, String value);

    Set<String> getAllSet(String key);

    Set<String> getSet(String key, long start, long end);

    List<String> getList(String key, long start, long end);

    Long getSetNum(String key);

    Long getListNum(String key);

    Object execute(RedisCallback<Object> calback);

    Object execute(SessionCallback<Object> calback);

    boolean hasKey(String key);

    Object lPop(String key);

    boolean hExist(String key, String hash);

    void rPush(String key, String value);

    void hDel(String key, Object... value);

    void expire(String key, Long time, TimeUnit timeUnit);


}
