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
        // error NEP
        //map2.put(null, null);
        map2.put("a", null);
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

        // 二进制：01111111111111111111111111111111
        final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
        System.out.println(HASH_BITS);
        System.out.println("  0" + Integer.toBinaryString(Integer.valueOf(HASH_BITS)));

        int h = Integer.parseInt("01010101010101010101010100001111", 2);
        System.out.println("a 0" + Integer.toBinaryString(h));
        int temp = h >>> 16;
        System.out.println("b 00000000000000000" + Integer.toBinaryString(temp));
        temp = h ^ temp;
        System.out.println("c 0" + Integer.toBinaryString(temp));
        temp = temp & HASH_BITS;
        System.out.println("d 0" + Integer.toBinaryString(temp));
        System.out.println("e 0" + Integer.toBinaryString((h ^ (h >>> 16)) & HASH_BITS));
    }
}
