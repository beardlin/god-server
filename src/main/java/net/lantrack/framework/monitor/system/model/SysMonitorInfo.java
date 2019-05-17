package net.lantrack.framework.monitor.system.model;

public class SysMonitorInfo {

	//操作系统的名称
	private String osName;
	//操作系统的构架
	private String osArch;
	//操作系统的版本
	private String osVersion;
	//计算机域名
	private String userDomain;
	//主机名
	private String hostName;
	//ip地址
	private String ipAddress;
	//jvm可用处理器个数
	private String availableProcessors;
	//jvm版本
	private String jvmVersion;
	//jvm名称
	private String jvmName;
	//jvm分配最大内存
	private String jvmMaxMemory;
	//jvm已使用内存
	private String jvmTotalMemory;
	//jvm可用内存
	private String jvmFreeMemory;
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getOsArch() {
		return osArch;
	}
	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getAvailableProcessors() {
		return availableProcessors;
	}
	public void setAvailableProcessors(String availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}
	public String getJvmName() {
		return jvmName;
	}
	public void setJvmName(String jvmName) {
		this.jvmName = jvmName;
	}
	public String getJvmMaxMemory() {
		return jvmMaxMemory;
	}
	public void setJvmMaxMemory(String jvmMaxMemory) {
		this.jvmMaxMemory = jvmMaxMemory;
	}
	public String getJvmTotalMemory() {
		return jvmTotalMemory;
	}
	public void setJvmTotalMemory(String jvmTotalMemory) {
		this.jvmTotalMemory = jvmTotalMemory;
	}
	public String getJvmFreeMemory() {
		return jvmFreeMemory;
	}
	public void setJvmFreeMemory(String jvmFreeMemory) {
		this.jvmFreeMemory = jvmFreeMemory;
	}
	@Override
	public String toString() {
		return "SysMonitorInfo [osName=" + osName + ", osArch=" + osArch + ", osVersion=" + osVersion + ", userDomain="
				+ userDomain + ", hostName=" + hostName + ", ipAddress=" + ipAddress + ", availableProcessors="
				+ availableProcessors + ", jvmVersion=" + jvmVersion + ", jvmName=" + jvmName + ", jvmMaxMemory="
				+ jvmMaxMemory + ", jvmTotalMemory=" + jvmTotalMemory + ", jvmFreeMemory=" + jvmFreeMemory + "]";
	}
	
	
	
}
