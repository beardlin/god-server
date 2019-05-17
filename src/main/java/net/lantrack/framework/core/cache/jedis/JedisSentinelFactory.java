package net.lantrack.framework.core.cache.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

public class JedisSentinelFactory {

    private volatile static JedisSentinelPool jedisSentinelPool = null;

    public static JedisSentinelPool getJedisPool(String masterName,
                                                 Set<String> sentinels,
                                                 final GenericObjectPoolConfig poolConfig,
                                                 int timeout,
                                                 final String password) {
        if (jedisSentinelPool == null) {
            synchronized (JedisSingleFactory.class) {
                if (jedisSentinelPool == null) {
                    jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig, timeout, password);
                }
            }
        }

        return jedisSentinelPool;
    }

    public static Jedis createResources() {
        if (jedisSentinelPool == null) {
            throw new RuntimeException("Jedis Sentinel Pool is not initialized.");
        }
        return jedisSentinelPool.getResource();
    }

}
