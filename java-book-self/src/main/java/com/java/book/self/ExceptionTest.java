package com.java.book.self;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 17:23
 */
public class ExceptionTest {

    public static void main(String[] args) throws IOException {
        try (
            // 声明、初始化两个可关闭的资源
            // try语句会自动关闭这两个资源。
            BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
            PrintStream ps = new PrintStream(new FileOutputStream("a.txt"))) {
            // 使用两个资源
            System.out.println(br.readLine());
            ps.println("miss my mentor...");
        }
    }
}
