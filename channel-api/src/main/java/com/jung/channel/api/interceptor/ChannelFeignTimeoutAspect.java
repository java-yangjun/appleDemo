package com.jung.channel.api.interceptor;

import com.jung.channel.api.exception.ChannelTimeoutException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChannelFeignTimeoutAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelFeignTimeoutAspect.class);

    @Pointcut("@within(com.jung.channel.api.annotation.ChannelFeignTimerOutChecker)")
    public void timeoutPointcut(){}

    @Around("timeoutPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        try{
            Object b = pjp.proceed();
            return b;
        }catch (HystrixRuntimeException e){
            e.printStackTrace();;
            if(e.getFailureType()== HystrixRuntimeException.FailureType.TIMEOUT){
                throw new ChannelTimeoutException("渠道接口超时",e);
            }
        }
        return null;
    }

}
