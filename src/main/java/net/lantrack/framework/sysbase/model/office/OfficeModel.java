package net.lantrack.framework.sysbase.model.office;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

public class OfficeModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;

	/**
	 * 父id
	 */
    private String parentId;
    
    /**
     * 父名称
     */
    private String parentName;

    /**
     * 机构名称
     */
    private String officeName;
    
    /**
     * 同步标记
     */
    private String syncFlag;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 排序
     */
    private String sort;
    

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Description("机构名称")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@Description("上级名称")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Description("同步标记")
	public String getSyncFlag() {
		return syncFlag;
	}

	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}

	@Description("地址")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Description("备注")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
