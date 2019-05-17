package net.lantrack.framework.core.cache.lettuce;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import net.lantrack.framework.core.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Lettuce Factory
 *
 * @author XC
 */
public class LettuceFactory {

    private static Logger logger = LoggerFactory.getLogger(LettuceFactory.class);

    /**
     * key for cluster host
     */
    private static final String KEY_CLUSTER_HOST = "redis.cluster.host";
    private static String clusterHost;

    /**
     * key for cluster password
     */
    private static final String KEY_CLUSTER_PWD = "redis.cluster.password";
    private static String clusterPassword;

    /**
     * Redis URI Array List
     */
    private static List<RedisURI> redisURIList = new ArrayList<>(6);


    static {
        try {
            Properties props = new Properties();
            props.load(Config.class.getClassLoader().getResourceAsStream("properties/redis.properties"));

            if (props.containsKey(KEY_CLUSTER_HOST)) {
                clusterHost = props.getProperty(KEY_CLUSTER_HOST);
            }

            if (props.containsKey(KEY_CLUSTER_PWD)) {
                clusterPassword = props.getProperty(KEY_CLUSTER_PWD);
            }

            if (clusterHost != null) {
                String[] hostArray = clusterHost.split(",");
                if (hostArray.length > 0) {
                    for (String hostAndPort : hostArray) {
                        String[] hostAndPortArray = hostAndPort.split(":");
                        if (hostAndPortArray.length != 2) {
                            throw new RuntimeException("配置错误");
                        }
                        // 添加host
                        RedisURI redisURI = RedisURI.create(hostAndPortArray[0], Integer.parseInt(hostAndPortArray[1]));
                        redisURI.setPassword(clusterPassword);

                        redisURIList.add(redisURI);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("properties load failed.........");
        }
    }

    public static RedisClusterClient createRedisClusterClient() {
        return RedisClusterClient.create(redisURIList);
    }


}

