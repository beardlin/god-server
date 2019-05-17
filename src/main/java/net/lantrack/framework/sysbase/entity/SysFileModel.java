package net.lantrack.framework.sysbase.entity;

public class SysFileModel {
	
	String file_name;
	String file_path;
	
	public SysFileModel(String file_name, String file_path) {
		this.file_name = file_name;
		this.file_path = file_path;
	}

	public SysFileModel() {
		
	}


	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

}
