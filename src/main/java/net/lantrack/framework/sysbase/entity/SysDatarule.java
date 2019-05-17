package net.lantrack.framework.sysbase.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

/**
 * 数据规则
 * @date 2019年3月21日
 */
public class SysDatarule implements Serializable {
    private Integer id;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则字段
     */
    private String tField;
    /**
     * 规则条件
     */
    private String tExpress;
    /**
     * 规则值
     */
    private String tValue;
    /**
     * 自定义SQL
     */
    private String sqlSegment;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除标记(0否1是)
     */
    private String delFlag;
    /**
     * 过滤实体类名（列表查询条件中的类名）
     */
    private String classname;

    private static final long serialVersionUID = 1L;

    public SysDatarule(Integer id, Integer menuId, String ruleName, String tField, String tExpress, String tValue, String sqlSegment, String remarks, String delFlag, String classname) {
        this.id = id;
        this.menuId = menuId;
        this.ruleName = ruleName;
        this.tField = tField;
        this.tExpress = tExpress;
        this.tValue = tValue;
        this.sqlSegment = sqlSegment;
        this.remarks = remarks;
        this.delFlag = delFlag;
        this.classname = classname;
    }

    public SysDatarule() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    @Length(min=1,max=64,message="规则名称应在1~64字符之间")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }
    @Length(min=0,max=64,message="规则字段不能超过64个字符")
    public String gettField() {
        return tField;
    }

    public void settField(String tField) {
        this.tField = tField == null ? null : tField.trim();
    }
    @Length(min=0,max=64,message="规则条件不能超过64个字符")
    public String gettExpress() {
        return tExpress;
    }

    public void settExpress(String tExpress) {
        this.tExpress = tExpress == null ? null : tExpress.trim();
    }
    @Length(min=0,max=64,message="规则值不能超过64个字符")
    public String gettValue() {
        return tValue;
    }

    public void settValue(String tValue) {
        this.tValue = tValue == null ? null : tValue.trim();
    }
    @Length(min=0,max=1000,message="自定义sql规则值不能超过1000个字符")
    public String getSqlSegment() {
        return sqlSegment;
    }

    public void setSqlSegment(String sqlSegment) {
        this.sqlSegment = sqlSegment == null ? null : sqlSegment.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }
    /**
     * 获取数据过滤权限
     * @return
     * @date 2019年3月21日
     */
    public String getDataScopeSql(){
		StringBuffer sqlBuffer = new StringBuffer();
		if(StringUtils.isNotBlank(tField)&&StringUtils.isNotBlank(tValue)){
			sqlBuffer.append(" AND " +tField+" "+StringEscapeUtils.unescapeHtml4(tExpress)+" "+tValue+" ");
		}
		if(StringUtils.isNotBlank(sqlSegment)){
			sqlBuffer.append(" AND "+StringEscapeUtils.unescapeHtml4(sqlSegment)+" ");
		}
		return sqlBuffer.toString();
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuId=").append(menuId);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", tField=").append(tField);
        sb.append(", tExpress=").append(tExpress);
        sb.append(", tValue=").append(tValue);
        sb.append(", sqlSegment=").append(sqlSegment);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", classname=").append(classname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}