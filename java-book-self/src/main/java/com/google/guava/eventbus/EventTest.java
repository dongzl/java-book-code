package com.google.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author dongzonglei
 * @description
 * @date 2020/1/31 下午5:14
 */
public class EventTest {
    
    public static void main(String[] args) {
        //初始化消息总线
        EventBus eventBus = new EventBus();
        // 注册订阅者
        eventBus.register(new EventHandler());
        eventBus.register(new OtherHandler());
        //MqEvent推送给了两个订阅者
        MQEvent mqEvent = new MQEvent();
        StatusEvent statusEvent = new StatusEvent();
        //发布消息
        eventBus.post(mqEvent);
        eventBus.post(statusEvent);
    }
}
