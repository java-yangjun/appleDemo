package com.jung.channel.api.test.design.factory.abstractFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 7:04 下午
 **/
public abstract class AbstractFactory {

    public void whoMake(){
        System.out.println("抽象工厂生产");
        Phone phone = makePhone();
        phone.make();
    }

    public abstract Phone makePhone();
}
