package net.lantrack.framework.core.util;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class GsonUtil {
	/**
     * @param json
     * @param clazz
     * @return
     */
    public static synchronized <T> List<T> jsonToList(String json, Class<T[]> clazz) throws Exception{
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    public static <T> T getSingleBean(String jsonData, Class<T> clazz) throws Exception {
        return new Gson().fromJson(jsonData, clazz);
    }
    /**
     * json转对象
     * @param <T>
     */
    public static <T> T toObject(String json,Class<T> cla){
        Gson gson = new Gson();
        try {
           return gson.fromJson(json, cla);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   /**
    * Object 转 json
    * @Return String
    */
    public static String toJson(Object obj){
        Gson gson = new Gson();
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取json串里的字段值
     * @Return String
     */
    public static String getFieldValue(String fieldName,String jsonStr){
        if(fieldName==null||jsonStr==null||"".equals(fieldName)
                ||"".equals(jsonStr)){
            return "";
        }
        try {
            JsonParser parser = new JsonParser();
            JsonObject jo = (JsonObject) parser.parse(jsonStr);
            return jo.get(fieldName).getAsString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args) {
        
    }
}
