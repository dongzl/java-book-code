package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/9/14 8:00 下午
 */
public class RmiServer {

    public static void main(String[] args) throws Exception {
        int port = 1099;
        String url = "rmi://localhost:1099/demo.zookeeper.remoting.server.HelloServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.bind(url, new HelloServiceImpl());
    }
}
