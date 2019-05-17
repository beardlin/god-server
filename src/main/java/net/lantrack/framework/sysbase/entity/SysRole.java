package net.lantrack.framework.sysbase.entity;

import net.lantrack.framework.core.entity.BaseEntity;

public class SysRole extends BaseEntity<SysRole> {

	private Integer id;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 机构名称
	 */
	private String officeName;
	/**
	 * 机构id
	 */
	private String officeId;
	/**
	 * 数据范围
	 */
	private String dataScope;
	/**
	 * 是否可用（0否1是）
	 */
	private String useAble;
	/**
	 * 是否为管理员（0否1是）
	 */
	private String ifAdmin;

	/**
	 * 权限集合“1,2,3”
	 */
	private String permission;
	
	private String stand1;

	private String stand2;

	private String stand3;

	private static final long serialVersionUID = 1L;
	
	public SysRole(Integer id, String roleName, String officeName, String officeId, String useAble,
			String ifAdmin, String stand1, String stand2, String stand3, String createBy, String createDate,
			String updateBy, String updateDate, String remarks, String delFlag) {
		super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
		this.id = id;
		this.roleName = roleName;
		this.officeName = officeName;
		this.officeId = officeId;
		this.useAble = useAble;
		this.ifAdmin = ifAdmin;
		this.stand1 = stand1;
		this.stand2 = stand2;
		this.stand3 = stand3;
	}

	public SysRole(Integer id, String roleName, String officeName, String officeId, String useAble,
			String ifAdmin, String stand1, String stand2, String stand3, String createBy, String createDate,
			String updateBy, String updateDate, String remarks, String delFlag, String permission,String dataScope) {
		super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
		this.id = id;
		this.roleName = roleName;
		this.officeName = officeName;
		this.officeId = officeId;
		this.dataScope = dataScope;
		this.useAble = useAble;
		this.ifAdmin = ifAdmin;
		this.stand1 = stand1;
		this.stand2 = stand2;
		this.stand3 = stand3;
		this.permission = permission;
		this.dataScope = dataScope;
	}

	public SysRole() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName == null ? null : officeName.trim();
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId == null ? null : officeId.trim();
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope == null ? null : dataScope.trim();
	}

	public String getUseAble() {
		return useAble;
	}

	public void setUseAble(String useAble) {
		this.useAble = useAble == null ? null : useAble.trim();
	}

	public String getIfAdmin() {
		return ifAdmin;
	}

	public void setIfAdmin(String ifAdmin) {
		this.ifAdmin = ifAdmin == null ? null : ifAdmin.trim();
	}

	public String getStand1() {
		return stand1;
	}

	public void setStand1(String stand1) {
		this.stand1 = stand1 == null ? null : stand1.trim();
	}

	public String getStand2() {
		return stand2;
	}

	public void setStand2(String stand2) {
		this.stand2 = stand2 == null ? null : stand2.trim();
	}

	public String getStand3() {
		return stand3;
	}

	public void setStand3(String stand3) {
		this.stand3 = stand3 == null ? null : stand3.trim();
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", roleName=").append(roleName);
		sb.append(", officeName=").append(officeName);
		sb.append(", officeId=").append(officeId);
		sb.append(", dataScope=").append(dataScope);
		sb.append(", useAble=").append(useAble);
		sb.append(", ifAdmin=").append(ifAdmin);
		sb.append(", stand1=").append(stand1);
		sb.append(", stand2=").append(stand2);
		sb.append(", stand3=").append(stand3);
		sb.append(", permission=").append(permission);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}