package net.lantrack.framework.sysbase.model.updownfile;

/**
 * 文件对象
 * @date 2019年4月8日
 */
public class FileSample {

	private String id;
	private String name;
	
	
	public FileSample(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
