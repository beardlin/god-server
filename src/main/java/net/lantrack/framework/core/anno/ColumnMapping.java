package net.lantrack.framework.core.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 结果集到对象映射注解，加到属性上	
 * 例：
 * @ColumnMapping("user_name")
 * private String uName;
 * @author lin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnMapping {
    String value();
}