package com.alibaba.nacos;

import java.util.Properties;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

/**
 * @author nkorange
 */
public class NamingExample {
    
    public static void main(String[] args) throws Exception {
        
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
//        properties.setProperty("namespace", System.getProperty("namespace"));
        
        NamingService naming = NamingFactory.createNamingService(properties);
        
        naming.registerInstance(".orchestration_ds.state.instance", "11.11.11.11", 8888);
        
        naming.registerInstance(".orchestration_ds.state.instance", "2.2.2.2", 9999);
        
        //System.out.println(naming.getAllInstances(".orchestration_ds.state.instance"));
        
        //naming.deregisterInstance(".orchestration_ds.state.instance", "2.2.2.2", 9999);
        
        //System.out.println(naming.getAllInstances(".orchestration_ds.state.instance"));
        
        naming.subscribe(".orchestration_ds.state.instance", new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(((NamingEvent)event).getServiceName());
                System.out.println(((NamingEvent)event).getInstances());
            }
        });
        
        Thread.sleep(10000000);
    }
}
