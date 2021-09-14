package redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2021/7/18 6:52 下午
 */
public class TestRedis {

    public static void main(String[] args) throws Exception {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        for (int i = 0; i < 514; i++) {
            //jedis.hset("test", String.valueOf(i), String.valueOf(i));
            jedis.lpush("test1", String.valueOf(i));
        }
    }
}
