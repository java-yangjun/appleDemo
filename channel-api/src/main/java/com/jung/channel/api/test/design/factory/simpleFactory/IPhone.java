package com.jung.channel.api.test.design.factory.simpleFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 6:54 下午
 **/
public class IPhone implements Phone {
    @Override
    public void make() {
        System.out.println("苹果手机");
    }
}
