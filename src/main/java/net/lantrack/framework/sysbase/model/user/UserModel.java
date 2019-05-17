package net.lantrack.framework.sysbase.model.user;


import org.springframework.context.annotation.Description;

public class UserModel  {
	

	/**
     * 主键id
     */
    private String id;


    /**
     * 登录名
     */
    private String loginName;

    /**
     * 工号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;
    

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;


    /**
    * 机构名称
    */
	private String officeName;
	
	/**
	 * 职务名称
	 */
	private String dutyName;
	/**
	 * 生日
	 */
	private String birthday;
	
	//是否锁定  0否1是
	private String lock;
	//是否禁用  0否1是
	private String forbidden;
	
	
	
	
	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	

	public String getForbidden() {
		return forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Description("机构名称")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@Description("职务名称")
	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

    
	
}
