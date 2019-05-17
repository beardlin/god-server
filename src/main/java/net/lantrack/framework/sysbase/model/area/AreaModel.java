package net.lantrack.framework.sysbase.model.area;

/**
 * 向前台返回数据时的行政区域model
 * @author hww
 */
public class AreaModel {

	/**
     * 主键id 自增
     */
    private Integer id;

    /**
     * 父名称  like
     */
    private String parentName;

    /**
     * 全名称  like
     */
    private String fullName;

    /**
     * 父级编号  ==
     */
    private String parentId;

    /**
     * 所有父级编号  ==
     */
    private String parentIds;

    /**
     * 名称  like
     */
    private String name;

    /**
     * 排序  ==
     */
    private Long sort;

    /**
     * 区域编码  ==
     */
    private String code;

    /**
     * 区域类型  ==
     */
    private String type;

    /**
     * 经度  ==
     */
    private String longitude;

    /**
     * 纬度  ==
     */
    private String latitude;

    /**
     * 地址  like
     */
    private String address;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
