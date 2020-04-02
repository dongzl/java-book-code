package com;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author dongzonglei
 * @description
 * @date 2020/3/21 下午9:57
 */
public class BloomFilterTest {
    
    public static void main(String[] args) {
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        filter.put("java");
        filter.put("c++");
        filter.put("python");
        System.out.println(filter.mightContain("php"));
        BloomFilter<String> filter2 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        filter2.put("go");
        filter2.put("rust");
        filter2.put("c");
        filter2.putAll(filter);
        System.out.println(filter2.mightContain("java"));
    }
}
