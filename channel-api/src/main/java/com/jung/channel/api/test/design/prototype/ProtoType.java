package com.jung.channel.api.test.design.prototype;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 5:06 下午
 **/
public class ProtoType implements Cloneable {

    public Integer a = new Integer(129);

    public String b = "b";

    public PhoneDemo phoneDemo = new PhoneDemo();

    public ProtoType(){
        System.out.println("调用ProtoType构造");
    }


    @Override
    protected ProtoType clone() throws CloneNotSupportedException {
        return (ProtoType)super.clone();
    }
}
