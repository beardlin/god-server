package net.lantrack.framework.sysbase.entity;


import net.lantrack.framework.core.entity.BaseEntity;
/**
 * 文件上传entity
 * @author liumy
 *
 */
public class SysUpDownFile  extends BaseEntity<SysUpDownFile> {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 文件说明（自定义如）
	 */
	private String fileContent;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	/**
	 * 文件类型(后缀类型)
	 */
	private String fileType;

	/**
	 * 文件日志
	 */
	private String loginfo;

	/**
	 * 文件新名字
	 */
	private String nname;

	/**
	 * 文件旧名字
	 */
	private String oname;

	/**
	 * 文件所属子系统字典id或表名
	 */
	private String sid;

	/**
	 * 文件保存路径
	 */
	private String uploadpath;
	
	/**
	 * 文件上传时的状态，0为临时状态；1为永久状态
	 */
	private String yn = "0";

	/**
	 * 文件所属对象id
	 */
	private String tableid;

	/**
	 * 文件所属表名称
	 */
	private String tablename="defaultTable";

	//文件相对保存路径
	private String relativepath;
	
	
	

	/**
	 * 文件类型
	 */
	public SysUpDownFile(Integer id, String fileContent, Long fileSize, String fileType, String loginfo, String nname, 
			String oname, String sid, String uploadpath, String yn, String tableid, String tablename, 
			String createBy, String createDate,String updateBy, String updateDate, String remarks, String delFlag) {
        super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
		this.id = id;
		this.fileContent = fileContent;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.loginfo = loginfo;
		this.nname = nname;
		this.oname = oname;
		this.sid = sid;
		this.uploadpath = uploadpath;
		this.yn = yn;
		this.tableid = tableid;
		this.tablename = tablename;
	}

	public SysUpDownFile() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent == null ? null : fileContent.trim();
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}

	public String getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo == null ? null : loginfo.trim();
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname == null ? null : nname.trim();
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname == null ? null : oname.trim();
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getUploadpath() {
		return uploadpath;
	}

	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath == null ? null : uploadpath.trim();
	}
	
	public String getRelativepath() {
		return relativepath;
	}

	public void setRelativepath(String relativepath) {
		this.relativepath = relativepath;
	}

	public String getYn() {
		return yn;
	}

	public void setYn(String yn) {
		this.yn = yn == null ? null : yn.trim();
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid == null ? null : tableid.trim();
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename == null ? null : tablename.trim();
	}
	
	

	@Override
	public  Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", fileContent=").append(fileContent);
		sb.append(", fileSize=").append(fileSize);
		sb.append(", fileType=").append(fileType);
		sb.append(", loginfo=").append(loginfo);
		sb.append(", nname=").append(nname);
		sb.append(", oname=").append(oname);
		sb.append(", sid=").append(sid);
		sb.append(", uploadpath=").append(uploadpath);
		sb.append(", relativepath=").append(relativepath);
		sb.append(", yn=").append(yn);
		sb.append(", tableid=").append(tableid);
		sb.append(", tablename=").append(tablename);
		sb.append("]");
		return sb.toString();
	}
}