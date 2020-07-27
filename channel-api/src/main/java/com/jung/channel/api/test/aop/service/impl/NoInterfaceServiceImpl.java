package com.jung.channel.api.test.aop.service.impl;

import org.springframework.stereotype.Service;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/9 3:14 下午
 **/
@Service
public class NoInterfaceServiceImpl {

    public void sayName(String str){
        System.out.println("我是没有实现任何接口的service.sayName" + str);
    }

    public void sayName2(){
        System.out.println("我是没有实现任何接口的service.sayName2");
    }

}
