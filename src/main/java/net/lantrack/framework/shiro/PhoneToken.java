package net.lantrack.framework.shiro;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * 通过手机短信验证码登录
 * @author lin
 */
public class PhoneToken implements AuthenticationToken{

	private static final long serialVersionUID = 1L;
	private String phone;//手机号
	private String msgcode;//短信验证码
	private String loginName;//登录名
	private String deviceType;//登录设备
	
	
	
	public PhoneToken() {
		super();
	}

	public PhoneToken(String phone, String msgcode) {
		super();
		this.phone = phone;
		this.msgcode = msgcode;
	}

	public PhoneToken(String phone, String msgcode, String loginName, String deviceType) {
		super();
		this.phone = phone;
		this.msgcode = msgcode;
		this.loginName = loginName;
		this.deviceType = deviceType;
	}

	public PhoneToken(String phone, String msgcode, String loginName) {
		super();
		this.phone = phone;
		this.msgcode = msgcode;
		this.loginName = loginName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	
	
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public Object getPrincipal() {
//		return loginName+deviceType;
		return loginName;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return msgcode;
	}

}
