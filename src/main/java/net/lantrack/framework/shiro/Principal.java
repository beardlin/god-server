package net.lantrack.framework.shiro;

import java.io.Serializable;


/**
 * 系统当前登录的用户主体
 * @author hww
 */
public class Principal implements Serializable {
	
	public static final String LOGIN_TYPE_PC = "pc";
	public static final String LOGIN_TYPE_APP = "app";

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    
    /**
     * 登录名
     */
    private String loginName;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 登录的设备类型
     */
    private String deviceType;
    
    /**
     * 是否由单点登录任命的管理员
     */
    private String ifAdmin;
    
    /**
     * Token
     */
    private String sn;


    /**
     * @param id
     * @param loginName
     * @param name
     * @param deviceType
     */
    public Principal(String id, String loginName, String name, String deviceType, String ifAdmin, String sn) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.deviceType = deviceType;
        this.ifAdmin = ifAdmin;
        this.sn = sn;
    }
    
    

    public Principal(String id, String loginName, String name, String deviceType, String ifAdmin) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.deviceType = deviceType;
		this.ifAdmin = ifAdmin;
	}



	public String getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getIfAdmin() {
		return ifAdmin;
	}

	public String getSn() {
		return sn;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
		if(deviceType!=null&&!"".equals(deviceType.trim())) {
			deviceType = deviceType.toLowerCase();
		}
        return id+deviceType;
    }

}
