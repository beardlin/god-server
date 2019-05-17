
package net.lantrack.framework.sysbase.entity;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Description;


public class SysDict extends BaseEntity<SysDict> {
    /**
     * id
     */
    private Integer id;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典类别
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Long sort=0L;
    /**
     * 父id 
     */
    private String parentId;

    private static final long serialVersionUID = 1L;

    public SysDict(Integer id, String value, String label, String type, String description,
            Long sort, String parentId, String createBy, String createDate, String updateBy,
            String updateDate, String remarks, String delFlag) {
        super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
        this.id = id;
        this.value = value;
        this.label = label;
        this.type = type;
        this.description = description;
        this.sort = sort;
        this.parentId = parentId;
    }

    public SysDict() {
        super();
    }
    /**
     * 将排序字段中不对应的列转换为数据库字段
     * @param page
     * 2018年1月25日
     * @author lin
     */
    public static void turnOrderField(PageEntity page){
        String field = page.getOrderField();
        if(field==null||"".equals(field.trim())){
            return;
        }
        switch (field) {
            case "Type":
                page.setOrderField("type");
                break;
            default:
                page.turnProToColumn();
                break;
        }
    }
    
    @Description("ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull
    @Length(max=32,message="字典值不能超过100个字符")
    @Description("字典值")
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
    @NotNull
    @Length(max=32,message="字典标签不能超过100个字符")
    @Description("标签")
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
    
    @NotNull
    @Length(max=32,message="字典类别不能超过64个字符")
    @Description("类别")
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    @NotNull
    @Length(max=32,message="标签值不能超过100个字符")
    @Description("描述")
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    @Description("排序")
    public Long getSort() {
        return sort;
    }
    
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    @Override
    public String toString() {
        return "SysDict [id=" + id + ", value=" + value + ", label=" + label + ", type=" + type
                + ", description=" + description + ", sort=" + sort + ", parentId=" + parentId
                + "]";
    }

}
