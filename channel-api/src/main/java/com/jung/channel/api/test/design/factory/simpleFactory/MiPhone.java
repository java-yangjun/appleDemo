package com.jung.channel.api.test.design.factory.simpleFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 6:55 下午
 **/
public class MiPhone implements Phone{
    @Override
    public void make() {
        System.out.println("小米手机");
    }
}
