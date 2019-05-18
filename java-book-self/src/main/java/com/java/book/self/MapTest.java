package com.java.book.self;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dongzonglei
 * @description https://www.cnblogs.com/coder-yoyo/p/6215175.html
 * @date 2019-05-16 18:01
 */
public class MapTest {

    public static void main(String[] args) {
        System.out.println("Before allocate map, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
        Map<String, String> map = new ConcurrentHashMap<String, String>(2000000000);
        System.out.println("After allocate map, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");

        int i = 0;
        try {
            while (i < 1000000) {
                System.out.println("Before put the " + (i + 1) + " element, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
                map.put(String.valueOf(i), String.valueOf(i));
                System.out.println("After put the " + (i + 1) + " element, free memory is " + Runtime.getRuntime().freeMemory()/(1024*1024) + "M");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            System.out.println("map size is " + map.size());
        }
    }

}