package net.lantrack.framework.core.cache.util;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import net.lantrack.framework.core.cache.lettuce.LettuceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Advanced Java Redis client Utils
 * 支持多线程下操作Redis，且高并发下不会产生阻塞
 * @author XC
 */
public class LettuceUtils {

    private static Logger logger = LoggerFactory.getLogger(LettuceUtils.class);

    /**
     * 同步设置 key value
     * @param key key
     * @param value value
     */
    public static void syncSet(String key, String value) {
        RedisClusterClient redisClusterClient = LettuceFactory.createRedisClusterClient();
        try (StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect()) {
            RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();

            syncCommands.set(key, value);
        } catch (Exception e) {
            logger.error("set key failed.........");
        } finally {
            redisClusterClient.shutdown();
        }
    }

    /**
     * 同步获取value
     * @param key key
     * @return value
     */
    public static String syncGet(String key) {
        RedisClusterClient redisClusterClient = LettuceFactory.createRedisClusterClient();
        String value = "";
        try (StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect()) {

            RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();

            value = syncCommands.get(key);
        } catch (Exception e) {
            logger.error("get key failed.........");
        } finally {
            redisClusterClient.shutdown();
        }

        return value;
    }

}
