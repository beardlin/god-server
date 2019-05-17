package net.lantrack.framework.springplugin.progress;

/**
 * 上传进度时临时entityModel
 */
public class ProgressEntityModel {
	/**
	 * 到目前为止读取文件的比特数  
	 */
	private String pBytesRead ;   
	/**
	 * 文件总大小    
	 */
	private String pContentLength ;   
	/**
	 *目前正在读取第几个文件  
	 */
	private int pItems;
	public String getpBytesRead() {
		return pBytesRead;
	}
	public void setpBytesRead(String pBytesRead) {
		this.pBytesRead = pBytesRead;
	}
	public String getpContentLength() {
		return pContentLength;
	}
	public void setpContentLength(String pContentLength) {
		this.pContentLength = pContentLength;
	}
	public int getpItems() {
		return pItems;
	}
	public void setpItems(int pItems) {
		this.pItems = pItems;
	}               

	
}