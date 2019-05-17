
package net.lantrack.framework.sysbase.entity;

import net.lantrack.framework.core.entity.BaseEntity;

import org.springframework.context.annotation.Description;

/**
 * 用户职务表
 * 2018年1月16日
 * @author lin
 */
public class Duty extends BaseEntity<Duty> {
    /**
     * id
     */
    private Integer id;
    /**
     * 职务名称
     */
    private String dutyName;
    /**
     * 部门名称
     */
    private String officeName;
    /**
     * 部门id
     */
    private String officeId;
    /**
     * 是否可授权
     */
    private String ifAuth = BaseEntity.NO;

    private static final long serialVersionUID = 1L;

    public Duty(Integer id, String dutyName, String officeName, String officeId, String ifAuth,
            String createBy, String createDate, String updateBy, String updateDate, String remarks,
            String delFlag) {
        super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
        this.id = id;
        this.dutyName = dutyName;
        this.officeName = officeName;
        this.officeId = officeId;
        this.ifAuth = ifAuth;
    }

    public Duty() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Description("职务名称")
    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }
    @Description("部门名称")
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }
    @Description("部门id")
    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }
    @Description("是否可授权")
    public String getIfAuth() {
        return ifAuth;
    }

    public void setIfAuth(String ifAuth) {
        this.ifAuth = ifAuth == null ? null : ifAuth.trim();
    }

    @Override
    public String toString() {
        return "Duty [id=" + id + ", dutyName=" + dutyName + ", officeName=" + officeName
                + ", officeId=" + officeId + ", ifAuth=" + ifAuth + "]";
    }


}
