package com.google.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author dongzonglei
 * @description
 * @date 2020/1/31 下午5:14
 */
public class OtherHandler {
    
    @Subscribe
    public void mq(MQEvent mq) {
        System.out.println("OtherHandler work");
    }
}
