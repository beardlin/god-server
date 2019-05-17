
package net.lantrack.framework.sysbase.entity;

import net.lantrack.framework.core.entity.BaseEntity;

/**
 * 系统导入数据日志记录 2018年2月28日
 * 
 * @author lin
 */
public class ImportLog extends BaseEntity<ImportLog> {
    
    private Integer id;
    /**
     * 日志记录所属model(Student,Report等)
     */
    private String modelType;
    /**
     * 导入文件名称
     */
    private String fileName;
    /**
     * 数据总条数
     */
    private Integer totalCount;
    /**
     * 成功条数
     */
    private Integer sucCount;
    /**
     * 错误条数
     */
    private Integer errCount;

    private static final long serialVersionUID = 1L;

    public ImportLog(Integer id, String modelType, String fileName, Integer totalCount,
            Integer sucCount, Integer errCount, String createBy, String createDate, String updateBy,
            String updateDate, String remarks, String delFlag) {
        super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
        this.id = id;
        this.modelType = modelType;
        this.fileName = fileName;
        this.totalCount = totalCount;
        this.sucCount = sucCount;
        this.errCount = errCount;
    }

    public ImportLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType == null ? null : modelType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSucCount() {
        return sucCount;
    }

    public void setSucCount(Integer sucCount) {
        this.sucCount = sucCount;
    }

    public Integer getErrCount() {
        return errCount;
    }

    public void setErrCount(Integer errCount) {
        this.errCount = errCount;
    }

    @Override
    public String toString() {
        return "ImportLog [id=" + id + ", modelType=" + modelType + ", fileName=" + fileName
                + ", totalCount=" + totalCount + ", sucCount=" + sucCount + ", errCount="
                + errCount + "]";
    }

    
}
