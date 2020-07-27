package com.jung.channel.api.test.design.proxy.staticProxy;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/7/1 5:43 下午
 **/
public class StarProxy implements Star{

    private Star star;

    public StarProxy(Star star){
        this.star = star;
    }

    @Override
    public void autoGraph() {
        doBefore();
        star.autoGraph();
        doAfter();
    }

    private void doBefore(){
        System.out.println("送过来纸");
    }
    private void doAfter(){
        System.out.println("送回去");
    }
}
