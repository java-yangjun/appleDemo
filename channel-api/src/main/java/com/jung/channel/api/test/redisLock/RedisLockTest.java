package com.jung.channel.api.test.redisLock;


import com.getsentry.raven.event.User;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/8 5:03 下午
 **/
public class RedisLockTest {

    @CacheLock
    public void testLock(@LockObject Integer id){
        //TODO
    }

    @CacheLock
    public void testLock(@LockComplexObject(field = "username") User id){
        //TODO
    }
}
