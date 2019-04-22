package com.java.book.self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-22 17:37
 */
public class SystemInTest {

    public static void main(String args[]) throws Exception {
        //testScanner();
        testBufferedReader();
    }

    public static void testScanner() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println("========" + s);
        input.close();
    }

    public static void testBufferedReader() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();
        System.out.println("AAAAAAAAAA" + s);
        input.close();
    }
}
