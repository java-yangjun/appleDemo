package com.jung.channel.api.test.design.factory.methodFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 7:05 下午
 **/
public class IPhoneFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new IPhone();
    }
}
