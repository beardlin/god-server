package net.lantrack.framework.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

//import net.lantrack.framework.sysbase.entity.SysUser;
//import net.lantrack.framework.sysbase.util.UserUtil;

/**
 * 通过手机验证码登录
 * @author lin
 *
 */
@Service
public class PhoneAuthenticationFilter extends AuthenticatingFilter{
	
	public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

	public static final String DEFAULT_PHONE_PARAM = "phoneNum";
    public static final String DEFAULT_MOBILE_PARAM = "deviceType";
    public static final String DEFAULT_MESSAGE_CODE_PARAM = "msgCode";
    public static final String DEFAULT_MESSAGE_PARAM = "message";
    public static final String DEFAULT_USERNAME_PARAM = "username";
    
    
    private String phoneNumParam = DEFAULT_PHONE_PARAM;
    private String msgCodeParam = DEFAULT_MESSAGE_CODE_PARAM;
    private String deviceTypeParam = DEFAULT_MOBILE_PARAM;
    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    private String messageParam = DEFAULT_MESSAGE_PARAM;
    private String usernameParam = DEFAULT_USERNAME_PARAM;

	
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		    String phone = getPhone(request);
	        String msgCode = getMsgCode(request);
	        if (msgCode == null) {
	        	msgCode = "";
	        }
	        String loginName = "";
//	        SysUser user = UserUtil.getByPhone(phone);
//	        if(user!=null) {
//	        	loginName = user.getLoginName();
//	        }
//	        String deviceType = getDeviceType(request);
	        return new PhoneToken(phone, msgCode, loginName);
	}

	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
		return true;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName()
				, message = "";        
        if (AuthenticationException.class.getName().equals(className)) {
            message = "手机号与验证码不匹配, 请重试!";
        } else if (e.getMessage() != null) {
            message = e.getMessage();
        } else {
            message = "系统出现点问题，请稍后再试！";//系统出现点问题，请稍后再试！
            e.printStackTrace(); // 输出到控制台
        }
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        request.setAttribute(getPhoneNumParam(), token.getPrincipal());
		return true;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// TODO Auto-generated method stub
		return super.isAccessAllowed(request, response, mappedValue);
	}

	
	public String getMessageParam() {
		return messageParam;
	}


	public void setMessageParam(String messageParam) {
		this.messageParam = messageParam;
	}


	public String getFailureKeyAttribute() {
		return failureKeyAttribute;
	}


	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}


	public String getDeviceTypeParam() {
		return deviceTypeParam;
	}


	public void setDeviceTypeParam(String deviceTypeParam) {
		this.deviceTypeParam = deviceTypeParam;
	}


	public String getPhoneNumParam() {
		return phoneNumParam;
	}

	public void setPhoneNumParam(String phoneNumParam) {
		this.phoneNumParam = phoneNumParam;
	}

	public String getMsgCodeParam() {
		return msgCodeParam;
	}

	public void setMsgCodeParam(String msgCodeParam) {
		this.msgCodeParam = msgCodeParam;
	}
	
	
	
	public String getUsernameParam() {
		return usernameParam;
	}


	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}


//	private String getDeviceType(ServletRequest request) {
//		return WebUtils.getCleanParam(request, getDeviceTypeParam());
//	}

	private String getMsgCode(ServletRequest request) {
		return WebUtils.getCleanParam(request, getMsgCodeParam());
	}

	private String getPhone(ServletRequest request) {
		return WebUtils.getCleanParam(request, getPhoneNumParam());
	}


}
