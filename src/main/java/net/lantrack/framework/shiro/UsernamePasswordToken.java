/**
 *
 */
package net.lantrack.framework.shiro;


/**
 * 用户和密码（包含验证码）令牌类
 *
 * @author 大法师
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private String captcha;
    private String deviceType;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, char[] password,
                                 boolean rememberMe, String host, String captcha, String deviceType) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.deviceType = deviceType;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getDeviceType() {
        return deviceType;
    }

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public Object getPrincipal() {
//		return this.getUsername()+this.deviceType;
		return this.getUsername();
	}
    
	
    

}