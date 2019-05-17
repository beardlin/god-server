package net.lantrack.project.search.entity;

import java.io.Serializable;
/**
 * 元数据字段信息
 * @Description:      
 * @author lin
 * @date 2018年6月13日
 */
public class ColumnInfo implements Serializable {
    private Integer id;
    /**
     * 所属表id
     */
    private Integer tableId;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 中文名称
     */
    private String zhName;
    /**
     * 注释
     */
    private String columnRemarks;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 长度
     */
    private Integer dataLength;
    /**
     * 是否为主键 0否1是
     */
    private Integer ifPri;
    /**
     * 数据分组
     */
    private String dataGroup;

    private String standby1;

    private String standby2;

    private String standby3;

    private static final long serialVersionUID = 1L;

    public ColumnInfo(Integer id, Integer tableId, String tableName, String columnName, String zhName, String columnRemarks, String dataType, Integer dataLength, Integer ifPri, String dataGroup, String standby1, String standby2, String standby3) {
        this.id = id;
        this.tableId = tableId;
        this.tableName = tableName;
        this.columnName = columnName;
        this.zhName = zhName;
        this.columnRemarks = columnRemarks;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.ifPri = ifPri;
        this.dataGroup = dataGroup;
        this.standby1 = standby1;
        this.standby2 = standby2;
        this.standby3 = standby3;
    }

    public ColumnInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks == null ? null : columnRemarks.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getIfPri() {
        return ifPri;
    }

    public void setIfPri(Integer ifPri) {
        this.ifPri = ifPri;
    }

    public String getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup == null ? null : dataGroup.trim();
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3 == null ? null : standby3.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tableId=").append(tableId);
        sb.append(", tableName=").append(tableName);
        sb.append(", columnName=").append(columnName);
        sb.append(", zhName=").append(zhName);
        sb.append(", columnRemarks=").append(columnRemarks);
        sb.append(", dataType=").append(dataType);
        sb.append(", dataLength=").append(dataLength);
        sb.append(", ifPri=").append(ifPri);
        sb.append(", dataGroup=").append(dataGroup);
        sb.append(", standby1=").append(standby1);
        sb.append(", standby2=").append(standby2);
        sb.append(", standby3=").append(standby3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}