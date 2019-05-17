package net.lantrack.framework.core.cache.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSingleFactory {

    private volatile static JedisPool jedisPool = null;

    public static JedisPool getJedisPool(final GenericObjectPoolConfig c,
                                         final String host, int port,
                                         int timeout,
                                         final String password,
                                         final int database) {
        if (jedisPool == null) {
            synchronized (JedisSingleFactory.class) {
                if (jedisPool == null) {
                    jedisPool = new JedisPool(c, host, port, timeout, password, database);
                }
            }
        }

        return jedisPool;
    }

    public static Jedis createResources() {
        if (jedisPool == null) {
            throw new RuntimeException("Jedis Pool is not initialized.");
        }
        return jedisPool.getResource();
    }


}
