package net.lantrack.framework.sysbase.enm;
/**
 * 系统变量参数      
 * @date 2019年1月17日
 */
public enum SysConfigEnum {
	LOCKHOUR("lockHour"),//用户名密码登录失败达到上限锁定时长
	LOCKCOUNT("lockCount"),//用户名密码登录失败锁定次数
	LOGINTYPE("loginType"),//系统登录方式1用户名密码2短信验证码3二维码
	OPENVCODE("openVcode"),//是否开启登录图片验证码0否1是
	SMSSIGN("smsSign"),//腾讯短信服务签名
	SMSCONTENTID("smsContentID"),//腾讯短信内容模板ID
	SMSCONTENTID_LOGIN_ERROR("smsContentID_login_error"),//腾讯短信内容模板ID
	SMSAPPID("smsAppID"),//腾讯短信服务AppID
	SMSAPPKEY("smsAppKey"),//腾讯短信服务AppKey
	SMSMINUT("smsMinut"),//短信验证码有效时间（分钟）
	DEFAULT_PASS("defaulPass"),//新增用户默认密码
	REMBER_TIME("remberTime"),//用户记住密码时长（天）
	OPEN_LOGIN_ERROR_TIPS("openLoginErrorCountTips");//是否开启登录失败次数限制提醒 0否1是
	
	private String confName;

	private SysConfigEnum(String confName) {
		this.confName = confName;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}
	
}
