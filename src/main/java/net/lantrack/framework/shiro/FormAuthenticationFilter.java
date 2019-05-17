/**
 *
 */
package net.lantrack.framework.shiro;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.core.util.StrUtil;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.interceptor.LogUtil;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 表单验证（包含验证码）过滤类
 *
 * @version 2017-2-9
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "deviceType";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	
	@Autowired
	private LoginInfoService loginInfoService;
	

	// 该方法为是否有效请求，如果有效直接放过拦截器链
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		boolean accessAllowed = super.isAccessAllowed(request, response, mappedValue);
		// 当访问有效时，再次校验是否为重复登录
		if (accessAllowed) {
			// String requestPath =
			// WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
			// System.out.println("访问地址:"+requestPath+",是否为登录url："+loginRequest);
			boolean loginRequest = isLoginRequest(request, response);
			// 如果已经登录，再次登录时则重新校验，不直接放过
			if (loginRequest) {
				// 重新生成一个session
				SecurityUtils.getSubject().logout();
				accessAllowed = false;
			}

		}
		return accessAllowed;
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StrUtil.getRemoteAddr((HttpServletRequest) request);
		String captcha = getCaptcha(request);
		String deviceType = getDeviceType(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, deviceType);
	}
	
	

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)&&
				isLoginSubmission(request, response)) {
             return executeLogin(request, response);
        } 
		ReturnEntity info = new ReturnEntity();
        info.setStatus(StatusCode.AUTHENTICATION_ERROR);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(GsonUtil.toJson(info));
		return false;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}

	protected String getDeviceType(ServletRequest request) {
		return WebUtils.getCleanParam(request, getMobileLoginParam());
	}

	public String getMessageParam() {
		return messageParam;
	}

	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Principal principal = (Principal) subject.getPrincipal();
		String loginName = principal.getLoginName();
		ReturnEntity info = new ReturnEntity();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentUser", loginName);
		map.put("sn", subject.getSession().getId());
		info.setResult(map);
    	info.success("登录成功");
		//通过用户名密码登录成功后将重置登录失败次数
		LoginInfo loginInfo = this.loginInfoService.getInfo(UserUtil.getUser().getLoginName());
		if(loginInfo!=null) {
			this.loginInfoService.deleteSession(loginInfo.getStand2());
			loginInfo.errCount(0);
			loginInfo.setLastSucTime(new Date());
			loginInfo.setLockStartTime(null);
			loginInfo.setLockEndTime(null);
			loginInfo.setLastSucIp(request.getRemoteAddr());
			loginInfo.setLastDevice(principal.getDeviceType());
			loginInfo.setStand2(subject.getSession().getId().toString());
			this.loginInfoService.updateInfo(loginInfo);
		}
		System.out.println("登录成功");
		UserUtil.getSession().setAttribute("test", UserUtil.getCurrentUser());
		LogUtil.saveLog("登录", LogType.LOGIN.getType(), "1");
		response.getWriter().write(GsonUtil.toJson(info));
		// we handled the success redirect directly, prevent the chain from continuing:
		return false;
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);

	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)
				|| AuthenticationException.class.getName().equals(className)) {
			message = "用户或密码错误, 请重试!";// 用户或密码错误, 请重试.
		} else if (e.getMessage() != null) {
			message = e.getMessage();
		} else {
			message = "系统出现点问题，请稍后再试！";// 系统出现点问题，请稍后再试！
			e.printStackTrace(); // 输出到控制台
		}
		// UsernamePasswordToken tk = (UsernamePasswordToken) token;
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		request.setAttribute(getUsernameParam(),token.getPrincipal());
		return true;
	}

}