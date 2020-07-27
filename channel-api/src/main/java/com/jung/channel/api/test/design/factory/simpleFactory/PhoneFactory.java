package com.jung.channel.api.test.design.factory.simpleFactory;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/30 6:56 下午
 **/
public class PhoneFactory {

    public Phone makePhone(String type){
        Phone phone = null;
        if(type.equals("apple")){
            phone = new IPhone();
        } else if("mi".equals(type)){
            phone = new MiPhone();
        }
        return phone;
    }
}
