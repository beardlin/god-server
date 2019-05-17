package net.lantrack.framework.sysbase.util.http;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author lihuadong
 * @Date 2017/7/24 17:58
 */
public class ModelUtil {

    public static <T> MultiValueMap<String, Object> convertModel2Map(T obj){
        Class clazz = obj.getClass();
        Field[] fields= clazz.getDeclaredFields();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        for (Field f: fields) {
            body.add(f.getName(), invokeGet(obj,f.getName()));
        }
        return body;
    }
    public static <T> MultiValueMap<String, String> convertModel2StrMap(T obj){
        Class clazz = obj.getClass();
        Field[] fields= clazz.getDeclaredFields();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        for (Field f: fields) {
            body.add(f.getName(), invokeGet(obj,f.getName()) == null?"":invokeGet(obj,f.getName()).toString());
        }
        return body;
    }
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[] { value });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            try {
                Class[] parameterTypes = new Class[1];
                Field field = objectClass.getDeclaredField(fieldName);
                parameterTypes[0] = field.getType();
                StringBuffer sb = new StringBuffer();
                sb.append("set").append(fieldName);
                Method method = objectClass.getMethod(sb.toString(), parameterTypes);
                return method;
            } catch (Exception e1) {
            }
        }
        return null;
    }

    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            try {

                sb.delete(0,sb.length());
                sb.append("get").append(fieldName);
                return objectClass.getMethod(sb.toString());
            } catch (Exception e2) {
            }
        }
        return null;
    }
}
