package com.google.guava.eventbus.test;

import com.google.common.eventbus.EventBus;

/**
 * @author dongzonglei
 * @description
 * @date 2020/1/31 下午5:14
 */
public class DeadEventTest {
    
    public static void main(String[] args) {
        //初始化消息总线
        EventBus eventBus = new EventBus();
        // 注册订阅者
        eventBus.register(new DeadEventHandler());
        //MqEvent推送给订阅者
        MQEvent mqEvent = new MQEvent();
        //发布消息
        eventBus.post(mqEvent);
    }
}
