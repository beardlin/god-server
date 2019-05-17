package net.lantrack.framework.sysbase.entity;


import net.lantrack.framework.core.entity.BaseEntity;

public class SysOfficeTree extends BaseEntity<SysOfficeTree> {
	
	private String id;
	/**
	 * 父名称
	 */
	private String pName;
	/**
	 * 全名称
	 */
	private String fullName;
	/**
	 * 父id
	 */
	private String pId;
	/**
	 * 父ids
	 */
	private String pIds;
	/**
	 * 节点名称
	 */
	private String tName;
	/**
	 * 节点排序
	 */
	private String oSort;
	/**
	 * 区域ID
	 */
	private String areaId;
	/**
	 * 区域编码
	 */
	private String oCode;
	/**
	 * 机构类型
	 */
	private String oType;
	/**
	 * 机构等级
	 */
	private String oGrade;
	/**
	 * 所在地址
	 */
	private String oAddress;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 负责人
	 */
	private String master;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 主职
	 */
	private String primaryPerson;
	/**
	 * 副负责人
	 */
	private String deputyPerson;

	/**
	 * 同步标记
	 */
	private String stand1;

	private String stand2;

	private String stand3;

	private static final long serialVersionUID = 1L;
	
	

	public SysOfficeTree(String id, String pId, String tName) {
		super();
		this.id = id;
		this.pId = pId;
		this.tName = tName;
	}

	public SysOfficeTree(String id, String pName, String fullName, String pId, String pIds, String tName, String oSort,
			String areaId, String oCode, String oType, String oGrade, String oAddress, String zipCode, String master,
			String phone, String fax, String email, String primaryPerson, String deputyPerson, String stand1,
			String stand2, String stand3, String createBy, String createDate, String updateBy, String updateDate,
			String remarks, String delFlag) {
		super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
		this.id = id;
		this.pName = pName;
		this.fullName = fullName;
		this.pId = pId;
		this.pIds = pIds;
		this.tName = tName;
		this.oSort = oSort;
		this.areaId = areaId;
		this.oCode = oCode;
		this.oType = oType;
		this.oGrade = oGrade;
		this.oAddress = oAddress;
		this.zipCode = zipCode;
		this.master = master;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.primaryPerson = primaryPerson;
		this.deputyPerson = deputyPerson;
		this.stand1 = stand1;
		this.stand2 = stand2;
		this.stand3 = stand3;
	}

	public SysOfficeTree() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName == null ? null : pName.trim();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName == null ? null : fullName.trim();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getpIds() {
		return pIds;
	}

	public void setpIds(String pIds) {
		this.pIds = pIds == null ? null : pIds.trim();
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName == null ? null : tName.trim();
	}

	public String getoSort() {
		return oSort;
	}

	public void setoSort(String oSort) {
		this.oSort = oSort;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId == null ? null : areaId.trim();
	}

	public String getoCode() {
		return oCode;
	}

	public void setoCode(String oCode) {
		this.oCode = oCode == null ? null : oCode.trim();
	}

	public String getoType() {
		return oType;
	}

	public void setoType(String oType) {
		this.oType = oType == null ? null : oType.trim();
	}

	public String getoGrade() {
		return oGrade;
	}

	public void setoGrade(String oGrade) {
		this.oGrade = oGrade == null ? null : oGrade.trim();
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress == null ? null : oAddress.trim();
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode == null ? null : zipCode.trim();
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master == null ? null : master.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax == null ? null : fax.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson == null ? null : primaryPerson.trim();
	}

	public String getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(String deputyPerson) {
		this.deputyPerson = deputyPerson == null ? null : deputyPerson.trim();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", pName=").append(pName);
		sb.append(", fullName=").append(fullName);
		sb.append(", pId=").append(pId);
		sb.append(", pIds=").append(pIds);
		sb.append(", tName=").append(tName);
		sb.append(", oSort=").append(oSort);
		sb.append(", areaId=").append(areaId);
		sb.append(", oCode=").append(oCode);
		sb.append(", oType=").append(oType);
		sb.append(", oGrade=").append(oGrade);
		sb.append(", oAddress=").append(oAddress);
		sb.append(", zipCode=").append(zipCode);
		sb.append(", master=").append(master);
		sb.append(", phone=").append(phone);
		sb.append(", fax=").append(fax);
		sb.append(", email=").append(email);
		sb.append(", primaryPerson=").append(primaryPerson);
		sb.append(", deputyPerson=").append(deputyPerson);
		sb.append(", stand1=").append(stand1);
		sb.append(", stand2=").append(stand2);
		sb.append(", stand3=").append(stand3);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}