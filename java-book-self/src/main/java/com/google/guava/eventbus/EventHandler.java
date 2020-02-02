package com.google.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author dongzonglei
 * @description
 * @date 2020/1/31 下午5:13
 */
public class EventHandler {
    
    @Subscribe
    public void mq(MQEvent mq) {
        System.out.println(mq.getClass().getCanonicalName() + " work");
    }
    
    @Subscribe
    public void status(StatusEvent statusEvent) {
        System.out.println(statusEvent.getClass().getCanonicalName() + " work");
    }
}
