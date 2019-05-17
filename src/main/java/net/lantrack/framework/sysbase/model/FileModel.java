package net.lantrack.framework.sysbase.model;

import java.io.Serializable;
/**
 * 系统文件管理Model
 * @Description:      
 * @author lin
 * @date 2018年5月21日
 */
public class FileModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 是否为文件夹0否1是
	 */
	private Integer ifDir;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 文件路径
	 */
	private String path;
	/**
	 * 文件大小
	 */
	private String fileCount;
	
	
	
	
	public FileModel() {
		super();
	}
	public FileModel(String fileName, Integer ifDir, String fileType, String path, String fileCount) {
		super();
		this.fileName = fileName;
		this.ifDir = ifDir;
		this.fileType = fileType;
		this.path = path;
		this.fileCount = fileCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getIfDir() {
		return ifDir;
	}
	public void setIfDir(Integer ifDir) {
		this.ifDir = ifDir;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileCount() {
		return fileCount;
	}
	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}
	@Override
	public String toString() {
		return "FileModel [fileName=" + fileName + ", ifDir=" + ifDir + ", fileType=" + fileType + ", path=" + path
				+ ", fileCount=" + fileCount + "]";
	}
	
	
}
