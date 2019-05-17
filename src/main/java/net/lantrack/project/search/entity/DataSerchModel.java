package net.lantrack.project.search.entity;


import net.lantrack.framework.core.entity.BaseEntity;

/**
 * 数据检索model
 * 
 * @Description:
 * @author lin
 * @date 2018年6月18日
 */
public class DataSerchModel extends BaseEntity<DataSerchModel> {
	private Integer id;
	/**
	 * 模板名称
	 */
	private String modelName;
	/**
	 * 查询条件
	 */
	private String modelCond;
	/**
	 * 字段id
	 */
	private String modelField;
	/**
	 * 条件中文拼接
	 */
	private String modelCondZh;
	/**
	 * 字段中文拼接
	 */
	private String modelFieldZh;
	/**
	 * 对谁可见（1自己2部门3全部）
	 */
	private String showWho;
	/**
	 * 部门编号
	 */
	private String officeCode;

	private static final long serialVersionUID = 1L;

	public DataSerchModel(Integer id, String modelName, String modelCond, String modelField, String modelCondZh,
			String modelFieldZh, String showWho, String officeCode, String createBy, String createDate, String updateBy,
			String updateDate, String delFlag, String remarks) {
		this.id = id;
		this.modelName = modelName;
		this.modelCond = modelCond;
		this.modelField = modelField;
		this.modelCondZh = modelCondZh;
		this.modelFieldZh = modelFieldZh;
		this.showWho = showWho;
		this.officeCode = officeCode;
	}

	public DataSerchModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName == null ? null : modelName.trim();
	}

	public String getModelCond() {
		return modelCond;
	}

	public void setModelCond(String modelCond) {
		this.modelCond = modelCond == null ? null : modelCond.trim();
	}

	public String getModelField() {
		return modelField;
	}

	public void setModelField(String modelField) {
		this.modelField = modelField == null ? null : modelField.trim();
	}

	public String getModelCondZh() {
		return modelCondZh;
	}

	public void setModelCondZh(String modelCondZh) {
		this.modelCondZh = modelCondZh == null ? null : modelCondZh.trim();
	}

	public String getModelFieldZh() {
		return modelFieldZh;
	}

	public void setModelFieldZh(String modelFieldZh) {
		this.modelFieldZh = modelFieldZh == null ? null : modelFieldZh.trim();
	}

	public String getShowWho() {
		return showWho;
	}

	public void setShowWho(String showWho) {
		this.showWho = showWho == null ? null : showWho.trim();
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode == null ? null : officeCode.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", modelName=").append(modelName);
		sb.append(", modelCond=").append(modelCond);
		sb.append(", modelField=").append(modelField);
		sb.append(", modelCondZh=").append(modelCondZh);
		sb.append(", modelFieldZh=").append(modelFieldZh);
		sb.append(", showWho=").append(showWho);
		sb.append(", officeCode=").append(officeCode);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}