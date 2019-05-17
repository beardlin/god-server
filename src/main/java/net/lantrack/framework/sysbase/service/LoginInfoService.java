package net.lantrack.framework.sysbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import net.lantrack.framework.sysbase.entity.LoginInfo;

/**
 * 用户登录信息
 *       
 * @date 2019年1月18日
 */
public interface LoginInfoService {
	
	
	/**
	 * 删除失效session
	 * @param sessionID
	 */
	void deleteSession(String sessionID);
	
	/**
	 * 刷脸登录后获取当前用户Token
	 * @param userId
	 * @return
	 */
	Map<String, Object> faceLogin(String userId);
	
	/**
	 * 执行伪登录，使Subject认证成功
	 * @param loginName
	 * @param phone
	 * @param msgCode
	 * @param deviceType
	 * @date 2019年2月13日
	 */
	void excuteLoginByPhoneMsgCode(String loginName,String phone,String msgCode,String deviceType);
	
	/**
	 * 查看APP是否已将authCode经点击确认登录
	 * @param authCode
	 * @date 2019年1月22日
	 */
	Map<String, String> hasAllowLogin(HttpServletRequest req,String authCode);

	/**
	 * APP点击确认登录,将authCode的Attirbute=0改为1
	 * @param code
	 * @date 2019年1月21日
	 */
	void allowAppScanCode(String loginName,String authCode);
	
	/**
	 * 获取二维码扫信息
	 * @param code
	 * @date 2019年1月21日
	 */
	String getAppScanCode();
	
	/**
	 * 校验短信验证码有后返回登录信息
	 * @param code
	 * @param phone
	 */
	Map<String, Object> validMsgCodeToLogin(String code,String phone,String deviceType);
	
	/**
	 * 校验短信验证码有效性
	 * @param code
	 * @param phone
	 * @date 2019年2月14日
	 */
	void validMsgCode(String code,String phone);
	
	/**
	 * 获取手机短信验证码
	 * @param phone
	 */
	void getMsgCodeByPhone(String phone);
	
	/**
	 * 校验图片验证码
	 * @param token
	 * @param imgCode
	 * @date 2019年1月25日
	 */
	void validImgCode(String token,String imgCode);
	
	
	/**
	 * 更新用户登录信息
	 * @param info
	 * @date 2019年1月18日
	 */
	void updateInfo(LoginInfo info);

	/**
	 * 查看账户是否被禁用
	 * @param loginName
	 * @return
	 * @date 2019年1月18日
	 */
	LoginInfo getInfo(String loginName);
	
	/**
	 * 插入用户登录信息
	 * @param info
	 * @date 2019年1月18日
	 */
	void insertInfo(LoginInfo info);
	
	
	/**
	 * 手机APP登录
	 * @param username
	 * @param password
	 * @param deviceType
	 * @return
	 * @date 2019年1月21日
	 */
	Map<String, Object> appLogin(HttpServletRequest req,String username,String password,String deviceType);
	
	/**
	 * 通过账户名密码登录失败信息记录，返回剩余次数
	 * @param loginInfo
	 * @return
	 */
	int loginErrorInfo(LoginInfo loginInfo);
	
	/**
	 * 校验用户禁用状态
	 * @param loginName
	 * @throws Exception
	 * @date 2019年1月21日
	 */
	void validUserStatus(String loginName);
	
	 
}
