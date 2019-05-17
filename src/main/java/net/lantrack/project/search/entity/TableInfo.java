package net.lantrack.project.search.entity;

import java.io.Serializable;
/**
 * 元数据表基本信息
 * @Description:      
 * @author lin
 * @date 2018年6月13日
 */
public class TableInfo implements Serializable {
    private Integer id;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表中文名称
     */
    private String zhName;
    /**
     * 全名称
     */
    private String fullName;
    /**
     * 注释
     */
    private String tableRemark;
    /**
     * 字段数
     */
    private Integer columnNum;
    /**
     * 引擎
     */
    private String tableEngine;
    /**
     * 表分组
     */
    private String tableGroup;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 父id
     */
    private Integer pId;

    private String standby1;

    private String standby2;

    private String standby3;

    private static final long serialVersionUID = 1L;

    public TableInfo(Integer id, String tableName, String zhName, String fullName, String tableRemark, Integer columnNum, String tableEngine, String tableGroup, String dbName, Integer pId, String standby1, String standby2, String standby3) {
        this.id = id;
        this.tableName = tableName;
        this.zhName = zhName;
        this.fullName = fullName;
        this.tableRemark = tableRemark;
        this.columnNum = columnNum;
        this.tableEngine = tableEngine;
        this.tableGroup = tableGroup;
        this.dbName = dbName;
        this.pId = pId;
        this.standby1 = standby1;
        this.standby2 = standby2;
        this.standby3 = standby3;
    }

    public TableInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark == null ? null : tableRemark.trim();
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public String getTableEngine() {
        return tableEngine;
    }

    public void setTableEngine(String tableEngine) {
        this.tableEngine = tableEngine == null ? null : tableEngine.trim();
    }

    public String getTableGroup() {
        return tableGroup;
    }

    public void setTableGroup(String tableGroup) {
        this.tableGroup = tableGroup == null ? null : tableGroup.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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
        sb.append(", tableName=").append(tableName);
        sb.append(", zhName=").append(zhName);
        sb.append(", fullName=").append(fullName);
        sb.append(", tableRemark=").append(tableRemark);
        sb.append(", columnNum=").append(columnNum);
        sb.append(", tableEngine=").append(tableEngine);
        sb.append(", tableGroup=").append(tableGroup);
        sb.append(", dbName=").append(dbName);
        sb.append(", pId=").append(pId);
        sb.append(", standby1=").append(standby1);
        sb.append(", standby2=").append(standby2);
        sb.append(", standby3=").append(standby3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}