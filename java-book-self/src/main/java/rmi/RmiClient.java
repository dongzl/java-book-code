package rmi;

import java.rmi.Naming;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/9/14 8:02 下午
 */
public class RmiClient {

    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost:1099/demo.zookeeper.remoting.server.HelloServiceImpl";
        HelloService helloService = (HelloService) Naming.lookup(url);
        String result = helloService.sayHello("Jack");
        System.out.println(result);
    }
}
