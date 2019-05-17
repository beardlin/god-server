package net.lantrack.framework.sysbase.util.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.lantrack.framework.springplugin.PropertyPlaceholder;


/**
 * @Description: 调用单点登录系统接口获取数据的工具类
 * @Author lihuadong
 * @Date 2018/1/12 16:25
 */
public class SsoApiUtil {
    public static String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
    private ClientRequst clientRequst;
    private static SsoApiUtil ourInstance;
    
    public static SsoApiUtil getInstance() {
        if (ourInstance == null) {
            ourInstance = new SsoApiUtil();
        }
        return ourInstance;
    }
    
    private SsoApiUtil() {
        clientRequst = new ClientRequst();
        clientRequst.setAppId(PropertyPlaceholder.getProperty("sso.appId"));
        clientRequst.setAppSecret(PropertyPlaceholder.getProperty("sso.appSecret"));
        clientRequst.setService(PropertyPlaceholder.getProperty("sso.service"));
    }
    /**
     * 从单点登录系统获取全部组织机构的数据
     * @param url
     * @param map
     * @return
     */
    public String getBasicDataList(String url) {
        HttpClientService httpClientService = new HttpClientService(new ConnectParams("", SsoApiUtil.FORM_CONTENT_TYPE));
        String responseString = httpClientService.get(url, String.class);
        return responseString;
    }
    
    public String getSn(String username, String password) {
    	String sn = null;
    	HttpClientService httpClientService = new HttpClientService(new ConnectParams("", SsoApiUtil.FORM_CONTENT_TYPE));
        String url = PropertyPlaceholder.getProperty("sso.service")+"/getSn?username="+username+"&password="+password;
        String responseStr = httpClientService.get(url, String.class);
        JsonParser parser = new JsonParser();
        JsonObject jo = (JsonObject) parser.parse(responseStr);
        JsonElement je = jo.get("result");
        if (je!=null && je.getAsJsonObject()!=null && je.getAsJsonObject().get("sn")!=null) {
        	sn = je.getAsJsonObject().get("sn").getAsString();
        }
        return sn;
    }
    
}
