package net.lantrack.framework.sysbase.util.http;

/**
 * @author : lihuadong@lantrack.net
 * @description :
 * @date : 2018/1/8 13:58
 */
public class ClientRequst {
    private String appId;
    private String appSecret;
    private String service;
    private String loginType;
    
    public ClientRequst(String appId, String appSecret, String service,String loginType) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.service = service;
        this.loginType = loginType;
    }
    
    
    public String getLoginType() {
        return loginType;
    }
    
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    
    public String getService() {
        return service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
    
    public String getAppId() {
        return appId;
    }
    
    
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    public String getAppSecret() {
        return appSecret;
    }
    
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
    
    
    public ClientRequst(){
    
    }
}
