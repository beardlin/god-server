package net.lantrack.framework.sysbase.entity;


import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.sysbase.util.UserUtil;

/**
 * 资料库管理
 * 
 * @date 2018年9月11日
 */
public class SysFile extends BaseEntity<SysFile> {

	private Integer id;
	/**
	 * 文件新名称
	 */
	private String newName;
	/**
	 * 文件旧名称
	 */
	private String oldName;
	/**
	 * 父id
	 */
	private Integer pId;
	/**
	 * 所有父id 1,2,3,4
	 */
	private String pIds;
	/**
	 * 文件类型 后缀 text doc 等
	 */
	private String fileType;
	/**
	 * 是否为文件夹 0否1是
	 */
	private Integer ifDirect;
	/**
	 * 当前文件夹层级
	 */
	private Integer directLevel;
	/**
	 * 文件大小 Byte
	 */
	private Long target = 0L;
	/**
	 * 是否隐藏 0否1是
	 */
	private Integer isShow = 0;
	/**
	 * 是否允许下载 （0否1是）
	 */
	private Integer allowDownload = 1;
	/**
	 * 文件存放地址
	 */
	private String fileUrl;

	/**
	 * 以下为预留字段
	 */
	private String stand1;

	private String stand2;

	private String stand3;

	private String stand4;

	private String stand5;

	private String stand6;
	
	private String currentUser;

	private static final long serialVersionUID = 1L;

	public SysFile(Integer id, String newName, String oldName, Integer pId, String pIds, String fileType,
			Integer ifDirect, Integer directLevel, Long target, Integer isShow, Integer allowDownload, String fileUrl,
			String stand1, String stand2, String stand3, String stand4, String stand5, String stand6, String createBy,
			String createDate, String updateBy, String updateDate, String remarks, String delFlag) {
		super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.pId = pId;
		this.pIds = pIds;
		this.fileType = fileType;
		this.ifDirect = ifDirect;
		this.directLevel = directLevel;
		this.target = target;
		this.isShow = isShow;
		this.allowDownload = allowDownload;
		this.fileUrl = fileUrl;
		this.stand1 = stand1;
		this.stand2 = stand2;
		this.stand3 = stand3;
		this.stand4 = stand4;
		this.stand5 = stand5;
		this.stand6 = stand6;
	}
	
	

	public String getCurrentUser() {
		currentUser = UserUtil.getCurrentUser();
		return currentUser;
	}



	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}



	public SysFile() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName == null ? null : newName.trim();
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName == null ? null : oldName.trim();
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpIds() {
		return pIds;
	}

	public void setpIds(String pIds) {
		this.pIds = pIds == null ? null : pIds.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public Integer getIfDirect() {
		return ifDirect;
	}

	public void setIfDirect(Integer ifDirect) {
		this.ifDirect = ifDirect;
	}

	public Integer getDirectLevel() {
		return directLevel;
	}

	public void setDirectLevel(Integer directLevel) {
		this.directLevel = directLevel;
	}



	public Long getTarget() {
		return target;
	}



	public void setTarget(Long target) {
		this.target = target;
	}



	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getAllowDownload() {
		return allowDownload;
	}

	public void setAllowDownload(Integer allowDownload) {
		this.allowDownload = allowDownload;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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

	public String getStand4() {
		return stand4;
	}

	public void setStand4(String stand4) {
		this.stand4 = stand4 == null ? null : stand4.trim();
	}

	public String getStand5() {
		return stand5;
	}

	public void setStand5(String stand5) {
		this.stand5 = stand5 == null ? null : stand5.trim();
	}

	public String getStand6() {
		return stand6;
	}

	public void setStand6(String stand6) {
		this.stand6 = stand6 == null ? null : stand6.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", newName=").append(newName);
		sb.append(", oldName=").append(oldName);
		sb.append(", pId=").append(pId);
		sb.append(", pIds=").append(pIds);
		sb.append(", fileType=").append(fileType);
		sb.append(", ifDirect=").append(ifDirect);
		sb.append(", directLevel=").append(directLevel);
		sb.append(", target=").append(target);
		sb.append(", isShow=").append(isShow);
		sb.append(", allowDownload=").append(allowDownload);
		sb.append(", fileUrl=").append(fileUrl);
		sb.append(", stand1=").append(stand1);
		sb.append(", stand2=").append(stand2);
		sb.append(", stand3=").append(stand3);
		sb.append(", stand4=").append(stand4);
		sb.append(", stand5=").append(stand5);
		sb.append(", stand6=").append(stand6);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}