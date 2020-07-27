package com.jung.channel.api.test.design.factory.methodFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 7:06 下午
 **/
public class TestFactory {
    public static void main(String[] args) {
        AbstractFactory iPhoneFactory = new IPhoneFactory();
        AbstractFactory miPhoneFactory = new MiPhoneFactory();
        Phone apple = iPhoneFactory.makePhone();
        apple.make();
        Phone mi = miPhoneFactory.makePhone();
        mi.make();
    }
}
