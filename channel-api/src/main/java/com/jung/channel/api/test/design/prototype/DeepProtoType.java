package com.jung.channel.api.test.design.prototype;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 5:06 下午
 **/
public class DeepProtoType implements Cloneable {

    public Integer a = 10;

    public String b = "b";

    public PhoneDemo phoneDemo = new PhoneDemo();


    @Override
    protected DeepProtoType clone() throws CloneNotSupportedException {
        DeepProtoType d = (DeepProtoType)super.clone();
        d.phoneDemo = this.phoneDemo.clone();
        return d;
    }
}
