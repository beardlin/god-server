package net.lantrack.framework.sysbase.entity;

import java.io.Serializable;
import java.util.Date;

public class IdUser implements Serializable {
    private Integer id;

    private String uName;

    private Integer uAge;

    private String uLike;

    private String uSex;

    private Date uBirthday;

    private String uPhone;

    private String uEmail;

    private String uSchool;

    private String uCode;

    private String uCompany;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String remarks;

    private String delFlag;

    private static final long serialVersionUID = 1L;

    public IdUser(Integer id, String uName, Integer uAge, String uLike, String uSex, Date uBirthday, String uPhone, String uEmail, String uSchool, String uCode, String uCompany, Date createDate, String createBy, Date updateDate, String updateBy, String remarks, String delFlag) {
        this.id = id;
        this.uName = uName;
        this.uAge = uAge;
        this.uLike = uLike;
        this.uSex = uSex;
        this.uBirthday = uBirthday;
        this.uPhone = uPhone;
        this.uEmail = uEmail;
        this.uSchool = uSchool;
        this.uCode = uCode;
        this.uCompany = uCompany;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.remarks = remarks;
        this.delFlag = delFlag;
    }
    

    public IdUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public Integer getuAge() {
        return uAge;
    }

    public void setuAge(Integer uAge) {
        this.uAge = uAge;
    }

    public String getuLike() {
        return uLike;
    }

    public void setuLike(String uLike) {
        this.uLike = uLike == null ? null : uLike.trim();
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex == null ? null : uSex.trim();
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail == null ? null : uEmail.trim();
    }

    public String getuSchool() {
        return uSchool;
    }

    public void setuSchool(String uSchool) {
        this.uSchool = uSchool == null ? null : uSchool.trim();
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode == null ? null : uCode.trim();
    }

    public String getuCompany() {
        return uCompany;
    }

    public void setuCompany(String uCompany) {
        this.uCompany = uCompany == null ? null : uCompany.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uName=").append(uName);
        sb.append(", uAge=").append(uAge);
        sb.append(", uLike=").append(uLike);
        sb.append(", uSex=").append(uSex);
        sb.append(", uBirthday=").append(uBirthday);
        sb.append(", uPhone=").append(uPhone);
        sb.append(", uEmail=").append(uEmail);
        sb.append(", uSchool=").append(uSchool);
        sb.append(", uCode=").append(uCode);
        sb.append(", uCompany=").append(uCompany);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    
    
}