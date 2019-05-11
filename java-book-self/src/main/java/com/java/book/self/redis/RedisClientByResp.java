package com.java.book.self.redis;

import java.io.IOException;
import java.net.Socket;

/**
 * @author dongzonglei
 * @description
 * @date 2019-05-09 20:19
 */
public class RedisClientByResp {

    private Socket socket;

    public RedisClientByResp() {
        try {
            socket = new Socket("127.0.0.1", 6379);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接失败：" + e.getMessage());
        }
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    public String set(String key, String value) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*3").append("\r\n");
        builder.append("$").append(CommandRedis.SET.name().length()).append("\r\n");
        builder.append(CommandRedis.SET.name()).append("\r\n");
        builder.append("$").append(key.getBytes().length).append("\r\n");
        builder.append(key).append("\r\n");
        builder.append("$").append(value.getBytes().length).append("\r\n");
        builder.append(value).append("\r\n");
        System.out.println(builder.toString());

        socket.getOutputStream().write(builder.toString().getBytes());
        byte[] bytes = new byte[2048];
        socket.getInputStream().read(bytes);
        return new String(bytes);
    }

    /**
     * 获取值
     * @param key
     * @return
     * @throws Exception
     */
    public String get(String key) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append("\r\n");
        builder.append("$").append(CommandRedis.GET.name().length()).append("\r\n");
        builder.append(CommandRedis.GET.name()).append("\r\n");
        builder.append("$").append(key.getBytes().length).append("\r\n");
        builder.append(key).append("\r\n");
        System.out.println(builder.toString());

        socket.getOutputStream().write(builder.toString().getBytes());
        byte[] bytes = new byte[2048];
        socket.getInputStream().read(bytes);
        return new String(bytes);
    }

    /**
     * 设置值：不会覆盖存在的值
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public String setnx(String key, String value) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("*3").append("\r\n");
        builder.append("$").append(CommandRedis.SETNX.name().length()).append("\r\n");
        builder.append(CommandRedis.SETNX.name()).append("\r\n");
        builder.append("$").append(key.getBytes().length).append("\r\n");
        builder.append(key).append("\r\n");
        builder.append("$").append(value.getBytes().length).append("\r\n");
        builder.append(value).append("\r\n");
        System.out.println(builder.toString());

        socket.getOutputStream().write(builder.toString().getBytes());
        byte[] b = new byte[2048];
        socket.getInputStream().read(b);
        return new String(b);
    }

    /**
     * echo
     * @param value
     * @return
     * @throws Exception
     */
    public String echo(String value) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append("\r\n");
        builder.append("$").append(CommandRedis.ECHO.name().length()).append("\r\n");
        builder.append(CommandRedis.ECHO.name()).append("\r\n");
        builder.append("$").append(value.getBytes().length).append("\r\n");
        builder.append(value).append("\r\n");
        System.out.println(builder.toString());

        socket.getOutputStream().write(builder.toString().getBytes());
        byte[] b = new byte[2048];
        socket.getInputStream().read(b);
        return new String(b);
    }

    public static void main(String args[]) throws Exception {
        System.out.println(new RedisClientByResp().set("mykey", "myvalue1"));
        System.out.println(new RedisClientByResp().get("mykey"));
        System.out.println(new RedisClientByResp().setnx("mykey1", "myvalue2"));
        System.out.println(new RedisClientByResp().get("mykey"));
        System.out.println(new RedisClientByResp().echo("Hello World!"));
    }
}
