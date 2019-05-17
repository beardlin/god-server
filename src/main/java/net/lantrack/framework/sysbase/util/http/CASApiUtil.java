package net.lantrack.framework.sysbase.util.http;

import net.lantrack.framework.springplugin.PropertyPlaceholder;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @Description:
 * @Author lihuadong
 * @Date 2017/7/24 14:34
 */
public class CASApiUtil {
    public static String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
    private ClientRequst clientRequst;
    private static CASApiUtil ourInstance;
    
    public static CASApiUtil getInstance() {
        if (ourInstance == null) {
            ourInstance = new CASApiUtil();
        }
        return ourInstance;
    }
    
    private CASApiUtil() {
        clientRequst = new ClientRequst();
        clientRequst.setAppId(PropertyPlaceholder.getProperty("sso.appId"));
        clientRequst.setAppSecret(PropertyPlaceholder.getProperty("sso.appSecret"));
        clientRequst.setService(PropertyPlaceholder.getProperty("sso.service"));
    }
    
    public String getTGT(String url, MultiValueMap<String, String> map) {
        HttpClientService httpClientService = new HttpClientService(new ConnectParams("", CASApiUtil.FORM_CONTENT_TYPE));
        MultiValueMap<String, String> body = ModelUtil.convertModel2StrMap(clientRequst);
        body.putAll(map);
        ResponseEntity<String> responseEntity = httpClientService.postForEntity(url, body, String.class);
        return responseEntity.getBody();
    }
    
    public String getST(String url, String TGT) {
        HttpClientService httpClientService = new HttpClientService(new ConnectParams("", CASApiUtil.FORM_CONTENT_TYPE));
        MultiValueMap<String, String> body = ModelUtil.convertModel2StrMap(clientRequst);
        ResponseEntity<String> responseEntity = httpClientService.postForEntity(url + "/" + TGT, body, String.class);
        return responseEntity.getBody();
    }
    
    
    
}
