package net.lantrack.framework.sysbase.model.user;

import java.util.List;

public class UserOfficeIdsModel {
	
	private String userName;
	
	private List<String> officeIdList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getOfficeIdList() {
		return officeIdList;
	}

	public void setOfficeIdList(List<String> officeIdList) {
		this.officeIdList = officeIdList;
	}
	
	
}