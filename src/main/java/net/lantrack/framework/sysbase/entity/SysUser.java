
package net.lantrack.framework.sysbase.entity;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import net.lantrack.framework.core.entity.BaseEntity;

/**
 * 用户表sys_user
 * Detailed description
 * 2018年1月17日
 * @author hww
 */
public class SysUser extends BaseEntity<SysUser> {
	/**
     * 主键id
     */
    private String id;

    /**
     * 所属机构id
     */
    private String officeId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 工号
     */
    private String userCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别 1男0女
     */
    private String sex;
    /**
     * 是否管理员
     */
    private String ifAdmin;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 职务
     */
    private Integer userDuty;
    
    /**
     * 职务名称
     */
    private String dutyName;
    
    /**
     * 是否可授权(0否1是)
     */
    private String accreditFlag="0";

    /**
     * 同步标记
     */
    private String syncFlag;
    /**
     * 生日
     */
    private String birthday;
    


	private static final long serialVersionUID = 1L;

    public SysUser(String id, String officeId, String loginName, String userCode, String password,
            String userName, String sex, String ifAdmin, String email, String mobile, Integer userDuty, String dutyName, 
            String accreditFlag, String syncFlag, String birthday,String createBy, String createDate, String updateBy, String updateDate,
            String remarks, String delFlag) {
    	super(createBy,createDate,updateBy,updateDate,remarks,delFlag);
        this.id = id;
        this.officeId = officeId;
        this.loginName = loginName;
        this.userCode = userCode;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.ifAdmin = ifAdmin;
        this.email = email;
        this.mobile = mobile;
        this.userDuty = userDuty;
        this.dutyName = dutyName;
        this.accreditFlag = accreditFlag;
        this.syncFlag = syncFlag;
        this.birthday = birthday;
    }

    public SysUser() {
        super();
    }
    @Length(max=19,message="生日数据格式错误")
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
        this.id = id == null ? null : id.trim();
    }
    @NotNull
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }
    @Length(min=1,max=64,message="登录名应在1~64字符之间")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    @Length(min=1,max=64,message="用户名应在1~64字符之间")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIfAdmin() {
		return ifAdmin;
	}

	public void setIfAdmin(String ifAdmin) {
		this.ifAdmin = ifAdmin == null ? null : ifAdmin.trim();
	}
	@Length(max=64,message="邮箱长度不能超过64个字符")
	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    @Length(max=20,message="电话长度不能超过20个字符")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(Integer userDuty) {
        this.userDuty = userDuty;
    }

    public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName == null ? null : dutyName.trim();
	}

	public String getAccreditFlag() {
		return accreditFlag;
	}

	public void setAccreditFlag(String accreditFlag) {
		this.accreditFlag = accreditFlag == null ? null : accreditFlag.trim();
	}

	public String getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag == null ? null : syncFlag.trim();
    }
    

}
