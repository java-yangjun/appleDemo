package com.jung.channel.api.test.redisLock.interceptor;

import com.jung.channel.api.test.redisLock.CacheLock;
import com.jung.channel.api.test.redisLock.LockComplexObject;
import com.jung.channel.api.test.redisLock.LockObject;
import com.jung.channel.api.test.redisLock.exception.CacheLockException;
import com.jung.channel.api.test.redisLock.utils.ICacheManager;
import com.jung.channel.api.test.redisLock.utils.RedisLock;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class LockAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockAspect.class);

    @Resource
    private ICacheManager cacheManager;

    @Pointcut("@annotation(com.jung.channel.api.test.redisLock.CacheLock)")
    public void cacheLockPointcut(){}

    @Around("cacheLockPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        if(null == cacheLock){
            return pjp.proceed();
        }
        String key = cacheLock.key();
        if(StringUtils.isBlank(key)) {
            //获得方法中参数的注解
            Annotation[][] annotations = method.getParameterAnnotations();
            Object[] args = pjp.getArgs();
            Object lockedObject = getLockedObject(annotations, args);
            key = lockedObject.toString();
        }
        //新建一个锁
        RedisLock lock = new RedisLock(cacheManager,cacheLock.prefix(), key);
        //加锁
        boolean result = lock.lock(cacheLock.timeOut(), cacheLock.expireTime());
        LOGGER.info("加锁结果:{},LOCK KEY:{}",result,lock.getLockKey());
        if(!result){//取锁失败
            throw new CacheLockException(cacheLock.failMessage());
        }
        try{
            //加锁成功，执行方法
            return pjp.proceed();
        }finally{
            lock.unlock(System.currentTimeMillis());//释放锁
        }
    }

    private Object getLockedObject(Annotation[][] annotations,Object[] args){
        if(null == args || args.length == 0){
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }

        if(null == annotations || annotations.length == 0){
            throw new CacheLockException("没有被注解的参数");
        }
        int index = -1;
        for(int i = 0;i < annotations.length;i++){
            for(int j = 0;j < annotations[i].length;j++){
                if(annotations[i][j] instanceof LockComplexObject){
                    index = i;
                    try {
                        Field field = args[i].getClass().getDeclaredField(((LockComplexObject)annotations[i][j]).field());
                        field.setAccessible(true);
                        return field.get(args[index]);
                    } catch (NoSuchFieldException | SecurityException e) {
                        throw new CacheLockException("注解对象中没有该属性" + ((LockComplexObject)annotations[i][j]).field());
                    } catch (IllegalAccessException e) {
                        throw new CacheLockException("获取所对象失败" + ((LockComplexObject)annotations[i][j]).field());
                    }
                }
                if(annotations[i][j] instanceof LockObject){
                    index = i;
                    break;
                }
            }
            if(index != -1){
                break;
            }
        }

        if(index == -1){
            throw new CacheLockException("请指定被锁定参数");
        }
        return args[index];
    }
}
