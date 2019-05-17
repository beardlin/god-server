package net.lantrack.framework.sysbase.entity;


import net.lantrack.framework.core.entity.BaseEntity;

public class SysTask extends BaseEntity<SysTask> {
	private Integer id;

	private String taskName;

	private String taskStatus;

	private String jobName;

	private String triggerName;

	private String className;

	private String fieldJson;

	private String cronExps;

	private static final long serialVersionUID = 1L;

	public SysTask(Integer id, String taskName, String taskStatus, String jobName, String triggerName, String className,
			String fieldJson, String cronExps, String createBy, String createDate, String updateBy, String updateDate,
			String remarks, String delFlag) {
		this.id = id;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.jobName = jobName;
		this.triggerName = triggerName;
		this.className = className;
		this.fieldJson = fieldJson;
		this.cronExps = cronExps;
	}

	public SysTask() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus == null ? null : taskStatus.trim();
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName == null ? null : jobName.trim();
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName == null ? null : triggerName.trim();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getFieldJson() {
		return fieldJson;
	}

	public void setFieldJson(String fieldJson) {
		this.fieldJson = fieldJson == null ? null : fieldJson.trim();
	}

	public String getCronExps() {
		return cronExps;
	}

	public void setCronExps(String cronExps) {
		this.cronExps = cronExps == null ? null : cronExps.trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SysTask ");
		sb.append(" [");
		sb.append("id=").append(id);
		sb.append(", taskName=").append(taskName);
		sb.append(", taskStatus=").append(taskStatus);
		sb.append(", jobName=").append(jobName);
		sb.append(", triggerName=").append(triggerName);
		sb.append(", className=").append(className);
		sb.append(", fieldJson=").append(fieldJson);
		sb.append(", cronExps=").append(cronExps);
		sb.append("]");
		return sb.toString();
	}
}