package com.jung.channel.api.test.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Description 切面配置类(如果@Aspect声明的切面逻辑类，也声明了@Component，则不需要此切面配置类。
 * 该配置类使用场景：需要指定生成代理类的方式JdkDynamicProxy或CglibProxy，
 * proxyTargetClass默认false，被代理类有实现的接口优先用jdk，没有实现接口用CglibProxy
 * proxyTargetClass设置为true，统一使用cglib动态代理)
 * Author yangjun
 * Date 2020/7/9 2:02 下午
 **/
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.jung.channel.api.test.aop.*")
public class AopConfig {

    /*@Bean
    public HelloServiceAspect getAspect(){
        return new HelloServiceAspect();
    }*/
}
