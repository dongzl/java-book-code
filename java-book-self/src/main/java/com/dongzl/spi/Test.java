package com.dongzl.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/1/18 9:04 下午
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ServiceLoader<IRegistry> serviceLoader = ServiceLoader.loadInstalled(IRegistry.class);
        Iterator<IRegistry> iterator = serviceLoader.iterator();
        while (iterator != null && iterator.hasNext()){
            IRegistry registry = iterator.next();
            System.out.println("class: " + registry.getClass().getName());
            registry.register("SPI");
        }
    }
}
