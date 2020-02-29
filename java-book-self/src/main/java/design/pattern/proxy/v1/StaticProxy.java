package design.pattern.proxy.v1;

/**
 * @author dongzonglei
 * @description
 * @date 2020/2/29 下午3:28
 */
public class StaticProxy {
    
    public static void main(String[] args) throws Exception {
        new ProxyExecute(new RemoteExecute()).execute();
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

class ProxyExecute implements Executable {
    
    private Executable executable;
    
    public ProxyExecute(Executable executable) {
        this.executable = executable;
    }
    
    @Override
    public void execute() throws Exception {
        long start = System.currentTimeMillis();
        executable.execute();
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}
