package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/9/14 7:59 下午
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = 1L;

    public HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return String.format("Hello %s", name);
    }
}
