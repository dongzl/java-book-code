package com.java.book.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-12 21:18
 */
public class Test {

    public static void main(String args[]) throws Exception {
        int a = 1 << 4;
        System.out.println(a);
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.get("a");
        Map<String, String> map1 = new LinkedHashMap<>(0);
        Map<String, String> map2 = new TreeMap<>();
        Hashtable<String, String> hashtable = new Hashtable<>();
        List<String> list = new ArrayList<>();
        List<String> lista = new LinkedList<>();
        Vector<String> vector = new Vector<>();
        Set<String> set = new HashSet<>();
        Set<String> set1 = new LinkedHashSet<>();
        Set<String> set2 = new TreeSet<>();
        System.out.println(-2 >>> 1);
        System.out.println(Integer.toHexString(-2));
        System.out.println(2 >>> 1);
        System.out.println(Integer.parseInt("0001111", 2) & 15);
        System.out.println(Integer.parseInt("0011111", 2) & 15);
        System.out.println(Integer.parseInt("0111111", 2) & 15);
        System.out.println(Integer.parseInt("1111111", 2) & 15);

        System.out.println(Integer.parseInt("01010101010101010101010100001111", 2) >> 16 & 15);
        System.out.println(Integer.parseInt("01011101110111010101010100011111", 2) >> 16 & 15);
        System.out.println(Integer.parseInt("01010101011100010101010100111111", 2) >> 16 & 15);
        System.out.println(Integer.parseInt("01110001010101010101010101111111", 2) >> 16 & 15);

        int initialCapacity = 10;
        int cap = initialCapacity + (initialCapacity >>> 1) + 1;
        System.out.println(cap);
        initialCapacity = 11;
        cap = initialCapacity + (initialCapacity >>> 1) + 1;
        System.out.println(cap);
    }
}
