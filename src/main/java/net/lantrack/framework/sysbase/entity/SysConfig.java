package net.lantrack.framework.sysbase.entity;

import java.io.Serializable;

/**
 * 系统变量配置
 * @date 2019年1月17日
 */
public class SysConfig implements Serializable {
	/**
	 * 存放系统变量名称
	 */
    private String confName;
    /**
	 * 存放系统变量值
	 */
    private String confValue;
    /**
	 * 描述
	 */
    private String confRemarks;
    
    /**
	 * 描述
	 */
    private String label;
    
    private static final long serialVersionUID = 1L;

    public SysConfig(String confName, String confValue, String confRemarks, String label) {
        this.confName = confName;
        this.confValue = confValue;
        this.confRemarks = confRemarks;
        this.label = label;
    }

    public SysConfig() {
        super();
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
    }

    public String getConfValue() {
        return confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue == null ? null : confValue.trim();
    }

    public String getConfRemarks() {
        return confRemarks;
    }

    public void setConfRemarks(String confRemarks) {
        this.confRemarks = confRemarks == null ? null : confRemarks.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", confName=").append(confName);
        sb.append(", confValue=").append(confValue);
        sb.append(", confRemarks=").append(confRemarks);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}