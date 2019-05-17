package net.lantrack.framework.core.cache.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;

public class JedisUtilsTest {

    @Test
    public void testGetKeyByJedisIsRight() {
        // 生成当前时间后缀的key和value，避免以前测试影响
        long currentTimeMills = System.currentTimeMillis();
        String key = "testKey" + currentTimeMills;
        String value = "testValue" + currentTimeMills;

        Jedis jedis = JedisUtils.getResource();
        jedis.set(key, value);
        assertEquals(value, jedis.get(key));
    }

    @Test
    public void testGetClusterKeyByLettuceIsRight() {
        // 生成当前时间后缀的key和value，避免以前测试影响
        long currentTimeMills = System.currentTimeMillis();
        String key = "testKeyCluster" + currentTimeMills;
        String value = "testValueCluster" + currentTimeMills;

        LettuceUtils.syncSet(key, value);
        assertEquals(value, LettuceUtils.syncGet(key));
    }

}
