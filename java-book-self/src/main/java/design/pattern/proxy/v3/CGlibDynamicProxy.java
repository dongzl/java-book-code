package design.pattern.proxy.v3;

/**
 * @author dongzonglei
 * @description
 * @date 2020/2/29 下午5:02
 */
public class CGlibDynamicProxy {
    
//    public static void main(String[] args) throws Exception {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(RemoteExecute.class);
//        enhancer.setCallback(new ExecuteMethodInterceptor());
//        RemoteExecute execute = (RemoteExecute)enhancer.create();
//        execute.execute();
//    }
//}
//
//class ExecuteMethodInterceptor implements MethodInterceptor {
//    
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println(o.getClass().getSuperclass().getName());
//        long start = System.currentTimeMillis();
//        Object result = methodProxy.invokeSuper(o, objects);
//        System.out.println("Time: " + (System.currentTimeMillis() - start));
//        return result;
//    }
//}
//
//class RemoteExecute {
//    
//    public void execute() throws Exception {
//        Thread.sleep(1000L);
//        System.out.println("Remote execute ......");
//    }
}