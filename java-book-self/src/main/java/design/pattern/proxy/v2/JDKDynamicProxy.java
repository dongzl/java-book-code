package design.pattern.proxy.v2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dongzonglei
 * @description
 * @date 2020/2/29 下午3:45
 */
public class JDKDynamicProxy {
    
    public static void main(String[] args) throws Exception {
        Executable remote = new RemoteExecute();
        
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        
        Executable executable = (Executable)Proxy.newProxyInstance(JDKDynamicProxy.class.getClassLoader(), new Class[]{Executable.class}, new ExecuteInvocationHandler(remote));
        executable.execute();
    }
}

class ExecuteInvocationHandler implements InvocationHandler {
    
    private Executable executable;
    
    public ExecuteInvocationHandler(Executable executable) {
        this.executable = executable;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = method.invoke(executable, args);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        return result;
    }
}

interface Executable {
    
    void execute() throws Exception;
}

class RemoteExecute implements Executable {
    
    @Override
    public void execute() throws Exception {
        Thread.sleep(1000L);
        System.out.println("Remote execute ......");
    }
}