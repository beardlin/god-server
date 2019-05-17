package net.lantrack.framework.springplugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurablePropertyResolver;

import java.util.Properties;

/**
 * 在spring配置文件中直接读取对应的属性信息的工具类，参考bean id =propertyConfigurer
 * @author : lihuadong@lantrack.net
 * @description :
 * @date : 2017/12/29 16:03
 */
public class PropertyPlaceholder extends PropertySourcesPlaceholderConfigurer {
    private static ConfigurablePropertyResolver property;
    
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, ConfigurablePropertyResolver propertyResolver) throws BeansException {
        super.processProperties(beanFactoryToProcess, propertyResolver);
        PropertyPlaceholder.property =  propertyResolver;
    }
    
    public static String getProperty(String name) {
        return property.getProperty(name)==null?null:property.getProperty(name).toString();
    }
    
}
