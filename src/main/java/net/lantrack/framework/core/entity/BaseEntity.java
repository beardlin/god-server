package net.lantrack.framework.core.entity;

import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.util.UserUtil;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 基类
 * 2018年1月05日
 * @author lin
 */
public abstract class BaseEntity<T> implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    /**
     * 是
     */
    public static final String YES="1";
    /**
     * 否
     */
    public static final String NO="0";
    
    /**
     * 创建人
     */
    private String createBy=UserUtil.getCurrentUser();
    /**
     * 创建日期
     */
    private String createDate=DateUtil.getDateTime();
    /**
     * 修改人
     */
    private String updateBy=UserUtil.getCurrentUser();
    /**
     * 修改日期 
     */
    private String updateDate=DateUtil.getDateTime();
    /**
     * 备注
     */
    private String remarks="";
    /**
     * 删除标记
     */
    private String delFlag=NO;
    
    /**
     * 数据过滤
     */
    private String dataFilter;
    
    private SysUser user;
    
    public BaseEntity() {
        
    }
    

    public BaseEntity(String createBy, String createDate, String updateBy, String updateDate,
            String remarks, String delFlag) {
        super();
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.remarks = remarks;
        this.delFlag = delFlag;
    }


    
    @JsonIgnore
    public SysUser getUser() {
		return user;
	}


	public void setUser(SysUser user) {
		this.user = user;
	}


	
   


	public String getCreateBy() {
        return createBy==null?"":createBy;
    }
	
	@JsonIgnore
	@XmlTransient
    public String getDataFilter() {
		return dataFilter;
	}


	public void setDataFilter(String dataFilter) {
		this.dataFilter = dataFilter;
	}


	public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public String getCreateDate() {
        if(createDate!=null){
            createDate = DateUtil.formatDate(createDate);
        }
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy==null?"":updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public String getUpdateDate() {
        if(updateDate!=null){
            updateDate = DateUtil.formatDate(updateDate);
        }
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks==null?"":remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag==null?"":delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    
}
