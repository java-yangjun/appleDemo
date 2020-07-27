package com.jung.channel.api.test.design.prototype;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 5:07 下午
 **/
public class PhoneDemo implements Cloneable{
    private int phone = 100;

    @Override
    protected PhoneDemo clone() throws CloneNotSupportedException {
        return (PhoneDemo)super.clone();
    }
}
