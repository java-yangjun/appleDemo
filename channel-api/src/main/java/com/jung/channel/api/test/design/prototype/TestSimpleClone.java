package com.jung.channel.api.test.design.prototype;

import io.swagger.models.auth.In;

/**
 * Description
 * Author yangjun
 * Date 2020/6/30 5:12 下午
 **/
public class TestSimpleClone {

    /**
     * 浅克隆:浅克隆，只能克隆8种基本类型、String、对于引用类型克隆的是引用内存地址
     * @param args
     * @throws CloneNotSupportedException
     */
    /*public static void main(String[] args) throws CloneNotSupportedException {

        ConcreteProtoType c = new ConcreteProtoType();
        c.laugh();
        ProtoType p =  c.clone();
        System.out.println("对象对比："+ (c==p));
        Integer ca = c.a;
        Integer pa = p.a;
        System.out.println("Integer对象c："+ ca);
        System.out.println("Integer对象p："+ pa);
        System.out.println("Integer对比："+ (c.a==p.a));
        System.out.println("String对比："+ (c.b==p.b));
        System.out.println("引用类对比："+(c.phoneDemo == p.phoneDemo));
        System.out.println("对象c："+c);
        System.out.println("对象p："+p);
        System.out.println("引用类c："+c.phoneDemo);
        System.out.println("引用类p："+p.phoneDemo);
    }*/

    /**
     * 深克隆:深克隆，不仅能克隆8种基本类型、String、对于引用类型实现了克隆的，也能克隆
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcreteDeepProtoType c = new ConcreteDeepProtoType();
        c.laugh();
        DeepProtoType p = c.clone();
        System.out.println("深克隆对象对比："+ (c==p));
        System.out.println("深克隆Integer对比："+ (c.a==p.a));
        System.out.println("深克隆String对比："+ (c.b==p.b));
        System.out.println("深克隆引用类对比："+(c.phoneDemo == p.phoneDemo));
        System.out.println("对象c："+c);
        System.out.println("对象p："+p);
        System.out.println("引用类c："+c.phoneDemo);
        System.out.println("引用类p："+p.phoneDemo);
    }

    /*public static void main(String[] args) {
        Integer a = 128;
        Integer b = new Integer(128);
        Integer c = new Integer(128);
        Integer d = Integer.valueOf(128);
        int e = 128;

        Integer a1 = 126;
        Integer b1 = new Integer(126);
        Integer c1 = new Integer(126);
        Integer d1 = Integer.valueOf(126);
        int e1 = 126;
        System.out.println("===");
    }*/

}
