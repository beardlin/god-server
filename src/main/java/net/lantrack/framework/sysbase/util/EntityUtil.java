
package net.lantrack.framework.sysbase.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 对象中的null处理以及属性值的HTML转义字符转换为原字符的工具类
 * @author lin
 */
public class EntityUtil {

    /**
     * 将对象中的null转换为""字符串
     * @Return T
     */
    public static  <T> T turnNullToString(T entity) {
        if(entity ==null){
            return entity;
        }
        Field[] fields = entity.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            String typeName = f.getGenericType().getTypeName();
            try {
                Object value = f.get(entity);
                if (value!=null) {
                    continue;
                }
                switch (typeName) {
                    case "java.lang.String":
                        f.set(entity,"");
                        break;
                    case "java.lang.Integer":
                        f.set(entity,0);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
    
    /**
     * 将数据中的null转换为""
     * @Return List<T>
     */
    public static <T> List<T> turnListNullToString(List<T> entitys) {
        List<T> newList = new ArrayList<>();
        if(entitys==null){
            return newList;
        }
        for (T t : entitys) {
            newList.add(turnNullToString(t));
        }
        return newList;
    }
    
    /**
     * 将对象中String类型的属性值的HTML转义字符转换为原字符
     * @Return T
     */
    public static  <T> T turnHtmlEscapeToString(T entity) {
        if(entity == null){
            return entity;
        }
        Field[] selfFields = entity.getClass().getDeclaredFields();
        Field[] superFields = entity.getClass().getSuperclass() != null ? entity.getClass().getSuperclass().getDeclaredFields() : null;
        Field[] grandFields = entity.getClass().getSuperclass()!=null && entity.getClass().getSuperclass().getSuperclass()!=null ? entity.getClass().getSuperclass().getSuperclass().getDeclaredFields() : null;
        Field[] fields = selfFields;
        if (superFields != null && superFields.length > 0) {
        	fields = ArrayUtils.addAll(selfFields, superFields);
        }
        if (grandFields != null && grandFields.length > 0) {
        	fields = ArrayUtils.addAll(fields, grandFields);
        }
        for (Field f : fields) {
        	String modifier = Modifier.toString(f.getModifiers());
        	if (StringUtils.isBlank(modifier) || modifier.contains("static") || modifier.contains("final")) {
        		continue;
        	}
            f.setAccessible(true);
            String typeName = f.getGenericType().getTypeName();
            try {
                Object value = f.get(entity);
                if (value == null) {
                	continue;
                }
                switch (typeName) {
                    case "java.lang.String":
                    	String valueStr = StringEscapeUtils.unescapeHtml4(value.toString());
                        f.set(entity, valueStr);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

}
