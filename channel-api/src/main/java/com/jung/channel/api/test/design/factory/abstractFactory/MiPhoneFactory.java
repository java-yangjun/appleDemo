package com.jung.channel.api.test.design.factory.abstractFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 7:06 下午
 **/
public class MiPhoneFactory extends AbstractFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}
