package com.jung.channel.api.test.redisLock.utils;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil implements ICacheManager{

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String get(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] data = connection.get(serializer.serialize(key));
                    connection.close();
                    if (data == null) {
                        return null;
                    }
                    return serializer.deserialize(data);
                }
            });
        } catch (Exception e) {
            LOGGER.error("get redis error, key : {}", key);
        }
        return obj != null ? obj.toString() : null;
    }

    @Override
    public boolean set(String key, String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    connection.set(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return true;
                }
            });
        } catch (Exception e) {
            LOGGER.error("set redis error, key : {}", key);
        }
        return obj != null ? (Boolean) obj : false;
    }

    @Override
    public boolean setNX(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            LOGGER.error("setNX redis error, key : {}", key);
        }
        return obj != null ? (Boolean) obj : false;
    }

    @Override
    public boolean setNX(String key, String value, long time) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, "NX", "PX", time);
            };
            String result = redisTemplate.execute(callback);
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            LOGGER.error("setNX redis error, key : {}", key);
        }
        return false;
    }

    @Override
    public String getSet(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (Exception e) {
            LOGGER.error("getSet redis error, key : {}", key);
        }
        return obj != null ? (String) obj : null;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Map setHashObject(String key,Object obj){
        try {
            if(obj instanceof Map){
                redisTemplate.opsForHash().putAll(key, (Map)obj);
                return (Map)obj;
            }
            Map map = BeanToMapUtil.convertBean(obj);
            redisTemplate.opsForHash().putAll(key, map);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("setHashObject redis error, key : {}", key);
        }
        return null;
    }

    @Override
    public List<Object> pipeline(RedisCallback<List<Object>> pipelineCallback) {
        return (List<Object>)redisTemplate.execute(pipelineCallback);
    }

    @Override
    public boolean zadd(String key,String value,double score) {
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    public void batchDel(String... pattern){
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    @Override
    public String hget(String key, String hash) {
        return (String) redisTemplate.opsForHash().get(key,hash);
    }

    @Override
    public Object hmget(String s,Class clazz) {
        boolean hasKey = redisTemplate.hasKey(s);
        if(!hasKey){
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        Collection<Object> fieldNames = new ArrayList<>();
        for(Field field:fields){
            fieldNames.add(field.getName());
        }
        List<Object> values = redisTemplate.opsForHash().multiGet(s,fieldNames);
        if(values==null||values.isEmpty()){
            return null;
        }
        try {
            Object obj = clazz.newInstance();
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                field.setAccessible(true);
                String type = field.getGenericType().toString();
                String value = (String) values.get(i);
                if(StringUtils.isBlank(value)){
                    if(type.equals("class java.lang.String")){
                        field.set(obj, value);
                    }
                    continue;
                }
                if(type.equals("int")){
                    field.set(obj,Integer.valueOf(value));
                }else if(type.equals("class java.math.BigDecimal")){
                    field.set(obj, new BigDecimal(value));
                }else if(type.equals("class java.util.Date")){
                    field.set(obj, new Date(Long.parseLong(value)));
                }else{
                    field.set(obj,values.get(i));
                }
            }
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void hset(String key, String hash, String value) {
        redisTemplate.opsForHash().put(key,hash,value);
    }

    public Set<String> getAllSet(String key) {
        Set<String> values = redisTemplate.opsForZSet().range(key,0,-1);
        return values;
    }

    @Override
    public Set<String> getSet(String key, long start, long end) {
        LOGGER.info("start:{} end{}",start,end);
        Set<String> values = redisTemplate.opsForZSet().range(key,start,end);
        return values;
    }

    @Override
    public Long getSetNum(String key) {
        Long count = redisTemplate.opsForZSet().zCard(key);
        return count;
    }

    @Override
    public Long getListNum(String key) {
        Long count = redisTemplate.opsForList().size(key);
        return count;
    }

    @Override
    public Object execute(RedisCallback<Object> calback) {
        return redisTemplate.execute(calback);
    }

    @Override
    public List<String> getList(String key, long start, long end) {
        LOGGER.info("start:{} end{}",start,end);
        List<String> values = redisTemplate.opsForList().range(key,start,end);
        return values;
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Object execute(SessionCallback<Object> calback) {
        return redisTemplate.execute(calback);
    }

    @Override
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public boolean hExist(String key,String hash) {
        return redisTemplate.opsForHash().hasKey(key,hash);
    }

    @Override
    public void rPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public void hDel(String key, Object... value) {
        redisTemplate.opsForHash().delete(key,value);
    }

    @Override
    public void expire(String key, Long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }
}
