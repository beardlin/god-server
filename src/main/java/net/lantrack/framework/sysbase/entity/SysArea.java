package net.lantrack.framework.sysbase.entity;


import org.springframework.context.annotation.Description;

import net.lantrack.framework.core.entity.BaseEntity;

/**
 * 区域表sys_area
 * Detailed description
 * 2018年1月6日
 * @author hww
 */
public class SysArea extends BaseEntity<SysArea> {
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

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     * @param id
     * @param parentName
     * @param fullName
     * @param parentId
     * @param parentIds
     * @param name
     * @param sort
     * @param code
     * @param type
     * @param longitude
     * @param latitude
     * @param address
     * @param createBy
     * @param createDate
     * @param updateBy
     * @param updateDate
     * @param remarks
     * @param delFlag
     */
    public SysArea(Integer id, String parentName, String fullName, String parentId, String parentIds, String name, Long sort, String code, String type, String longitude, String latitude, String address, String createBy, String createDate, String updateBy, String updateDate, String remarks, String delFlag) {
    	super(createBy,createDate,updateBy,updateDate,remarks,delFlag);
        this.id = id;
        this.parentName = parentName;
        this.fullName = fullName;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.name = name;
        this.sort = sort;
        this.code = code;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public SysArea() {
        super();
    }

    @Description("编号")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Description("上级区域名称")
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    @Description("全名称")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    @Description("上级区域ID")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    @Description("所有上级区域IDs")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    @Description("区域名称")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Description("排序")
    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Description("区域编码")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Description("区域类型")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    @Description("经度")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    @Description("纬度")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    @Description("地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentName=").append(parentName);
        sb.append(", fullName=").append(fullName);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", address=").append(address);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}