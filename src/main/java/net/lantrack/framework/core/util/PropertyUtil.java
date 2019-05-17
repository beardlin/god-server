package net.lantrack.framework.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            //　<!--第一种，通过类加载器进行获取properties文件流-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("properties/config.properties");
            // <!--第二种，通过类进行获取properties文件流-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("config.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("config.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static String getPropertyByPropertieFile(String propertiesFileName, String key) {
        logger.info("开始加载" + propertiesFileName + "文件内容.......");
        Properties propstemp = new Properties();
        InputStream in = null;
        try {
            //　<!--第一种，通过类加载器进行获取properties文件流-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
            // <!--第二种，通过类进行获取properties文件流-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            propstemp.load(in);
            return propstemp.getProperty(key);
        } catch (FileNotFoundException e) {
            logger.error(propertiesFileName + "文件未找到");
            return null;
        } catch (IOException e) {
            logger.error("出现IOException");
            return null;
        } finally {
            propstemp = null;
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(propertiesFileName + "文件流关闭出现异常");
                return null;
            }
        }

    }
}
