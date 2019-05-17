package net.lantrack.framework.sysbase.entity;


/**
 * 用户角色表
 * 2018年1月18日
 * @author hww
 */
public class UserRole {

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 角色id
	 */
	private Integer roleId;
	
	
	public UserRole(String userId, Integer roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
}
