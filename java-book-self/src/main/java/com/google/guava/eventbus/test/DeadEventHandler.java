package com.google.guava.eventbus.test;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author dongzonglei
 * @description
 * @date 2020/2/2 上午10:04
 */
public class DeadEventHandler {
    
    /**
     * 没有订阅者时被触发
     */
    @Subscribe
    public void deadEvent(DeadEvent event){
        System.out.println("Receive a DeadEvent");
    }
}
