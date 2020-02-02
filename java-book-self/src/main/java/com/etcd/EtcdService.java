package com.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Lease;
import io.etcd.jetcd.Lock;
import io.etcd.jetcd.Observers;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.kv.DeleteResponse;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.lease.LeaseKeepAliveResponse;
import io.etcd.jetcd.lock.LockResponse;
import io.etcd.jetcd.lock.UnlockResponse;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.PutOption;
import io.etcd.jetcd.watch.WatchResponse;
import io.grpc.stub.StreamObserver;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author dongzonglei
 * @description
 * @date 2020/2/1 上午11:19
 */
public class EtcdService {
    
    private static String localHost = "http://127.0.0.1:2379";
    private static String username = "root";
    private static String password = "12345";
    
    //etcl客户端链接
    private static Client client = null;
    
    //链接初始化 单例模式
    private static synchronized Client getEtclClient() {
        if (null == client) {
            // create client
            client = Client.builder().endpoints(localHost).build();
        }
        return client;
    }
    
    private static KV getKVClient() {
        return getEtclClient().getKVClient();
    }
    
    private static Watch getWatchClient() {
        return getEtclClient().getWatchClient();
    }
    
    private static Lease getLeaseClient() {
        return getEtclClient().getLeaseClient();
    }
    
    private static Lock getLockClient() {
        return getEtclClient().getLockClient();
    }
    
    private static ByteSequence convert2ByteSequence(String obj) {
        return ByteSequence.from(obj.getBytes());
    }
    
    /**
     * 根据指定的配置名称获取对应的value
     *
     * @param key 配置项
     * @return
     * @throws Exception
     */
    public String get(String key) throws Exception {
        KV kvClient = getKVClient();
        // get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(convert2ByteSequence(key));
        // get the value from CompletableFuture
        GetResponse response = getFuture.get();
        List<KeyValue> kvs = response.getKvs();
        if (kvs.size() > 0) {
            String value = kvs.get(0).getValue().toString(Charset.defaultCharset());
            return value;
        } else {
            return null;
        }
    }
    
    /**
     * 根据前缀获取子key
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> getChildren(String path) throws Exception {
        KV kvClient = getKVClient();
        return kvClient.get(ByteSequence.from(path, Charset.defaultCharset()), 
                GetOption.newBuilder().withPrefix(ByteSequence.from(path, Charset.defaultCharset())).build())
                .get().getKvs().stream().parallel().filter(pair -> {
                    String key = pair.getKey().toString(Charset.defaultCharset());
                    int index = path.length(), count = 0;
                    if (key.length() > path.length()) {
                        for (; (index = key.indexOf("/", index)) != -1; ++index) {
                            if (count++ > 1) {
                                break;
                            }
                        }
                    }
                    return count == 1;
                }).map(pair -> pair.getKey().toString(Charset.defaultCharset())).collect(Collectors.toList());
    }
    
    /**
     * 新增或者修改指定的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void put(String key, String value) throws Exception {
        KV kvClient = getKVClient();
        // put the key-value
        PutResponse response = kvClient.put(convert2ByteSequence(key), convert2ByteSequence(value)).get();
    }
    
    public void watch(String key) throws Exception {
        Watch watch = getWatchClient();
        watch.watch(convert2ByteSequence(key), new Watch.Listener() {
            @Override
            public void onNext(WatchResponse response) {
                System.out.println(response.getEvents().get(0).getEventType());
                System.out.println(response.getEvents().get(0).getKeyValue().getKey().toString(Charset.defaultCharset()));
                System.out.println(response.getEvents().get(0).getKeyValue().getValue().toString(Charset.defaultCharset()));
                System.out.println("onNext========");
            }
    
            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError========");
            }
    
            @Override
            public void onCompleted() {
                System.out.println("onCompleted========");
            }
        });
    }
    
    public void lock(String key, long leaseId) throws Exception {
        LockResponse lockResponse = getLockClient().lock(convert2ByteSequence(key), leaseId).get();
        System.out.println(System.currentTimeMillis() / 1000 + "_" + leaseId);
        System.out.println("lockResponse=====" + lockResponse.getKey().toString(Charset.defaultCharset()));
        Thread.sleep(2000);
        UnlockResponse unlockResponse = getLockClient().unlock(lockResponse.getKey()).get();
        System.out.println("unlockResponse=====" + unlockResponse.toString());
    }
    
    public void putEphemeral(String key, String value) throws Exception {
        KV kvClient = getKVClient();
        // put the key-value
        Lease lease = getLeaseClient();
        long leaseId = lease.grant(5).get().getID();
        final StreamObserver<LeaseKeepAliveResponse> observer = new Observers.Builder().build();
        lease.keepAlive(leaseId, observer);
        PutResponse response = kvClient.put(convert2ByteSequence(key), convert2ByteSequence(value), 
                PutOption.newBuilder().withLeaseId(leaseId).build()).get();
    }
    
    /**
     * 删除指定的配置
     *
     * @param key
     * @return
     */
    public void delete(String key) {
        KV kvClient = getKVClient();
        // delete the key
        CompletableFuture<DeleteResponse> response = kvClient.delete(convert2ByteSequence(key));
    }
    
    public static void main(String[] args) {
        EtcdService etcdService = new EtcdService();
        try {
            //etcdService.put("/orchestration_ds/config/schema/test/rule", "rule");
            //etcdService.put("/orchestration_ds/config/schema/test/datasource", "datasource");
            //List<String> s = etcdService.getChildren("/orchestration_ds/config/schema/test");
            //System.out.println(s);
            //etcdService.watch("/orchestration_ds/config/schema/test/rule");
            
            //Thread.sleep(30000000);
            
//            System.out.println("0." + etcdService.get("test_ephemeral"));
//            etcdService.putEphemeral("test_ephemeral", "test_ephemeral");
//            System.out.println("1." + etcdService.get("test_ephemeral"));
//            Thread.sleep(3000);
//            System.out.println("2." + etcdService.get("test_ephemeral"));
//            Thread.sleep(3000);
//            System.out.println("3." + etcdService.get("test_ephemeral"));
            
            //long leaseId = getLeaseClient().grant(10).get().getID();
            //long leaseId1 = getLeaseClient().grant(8).get().getID();
            //etcdService.lock("test_lock11", leaseId);
            //etcdService.lock("test_lock11", leaseId1);
            
            System.out.println(getEtclClient().getKVClient());
            System.out.println(getEtclClient().getKVClient());
            
            getEtclClient().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
