package net.lantrack.framework.sysbase.model.role;

import org.springframework.context.annotation.Description;

public class RoleMenusModel {

	/**
	 * 角色编号
	 */
	private Integer id;
	/**
	 * 所配权限编号
	 */
	private String menus;
	
	@Description("角色编号")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Description("所配权限编号")
	public String getMenus() {
		return menus;
	}
	public void setMenus(String menus) {
		this.menus = menus;
	}
	
	
	
	
}
