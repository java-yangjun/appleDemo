package com.jung.channel.api.test.redisLock;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String prefix() default "";//redis 锁key的前缀
    String key() default "";//锁key
    long timeOut() default 1000;//轮询锁的时间
    int expireTime() default 1000;//key在redis里存在的时间，1000mS
    String failMessage() default "请勿重复提交";
}
