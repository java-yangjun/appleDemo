package com.jung.channel.api.test.redisLock;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockObject {
    //不需要值
}
