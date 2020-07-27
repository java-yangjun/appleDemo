package com.jung.channel.api.test.design.factory.abstractFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 7:06 下午
 **/
public class TestFactory {
    public static void main(String[] args) {
        AbstractFactory iPhoneFactory = new IPhoneFactory();
        iPhoneFactory.whoMake();
        AbstractFactory miPhoneFactory = new MiPhoneFactory();
        miPhoneFactory.whoMake();
    }
}
