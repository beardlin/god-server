package net.lantrack.framework.sysbase.model.user;


public class UserAuthModel {


	/**
	 * 角色ids
	 */
	private String roles;
	/**
	 * 批量授权的用户id
	 */
	private String userids;
	/**
	 * 是否可授权
	 */
//	private String accreditFlag;
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	
	
}
