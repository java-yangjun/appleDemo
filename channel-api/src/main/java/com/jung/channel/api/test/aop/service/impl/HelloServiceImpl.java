package com.jung.channel.api.test.aop.service.impl;

import com.jung.channel.api.test.aop.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/9 11:11 上午
 **/
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void printHello() {
        System.out.println("Hello, 我是被aop的方法，printHello!");
    }

    @Override
    public String returnHello(Integer mark) throws Exception {
        System.out.println("Hello, 我是被aop的方法，returnHello!");
        if(mark > 10){
            System.out.println("我是 return Hello，异常了..");
            throw new Exception(" too big");
        }
        System.out.println("我是 return Hello！正常返回..");
        return "return Hello";
    }
}
