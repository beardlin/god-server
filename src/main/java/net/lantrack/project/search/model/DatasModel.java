package net.lantrack.project.search.model;

import java.util.List;
/**
 * 数据检索列信息和查询条件
 * @Description:      
 * @author lin
 * @date 2018年6月25日
 */
public class DatasModel {
	
	private String ids;
	private List<CustomCondtion> conds;
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public List<CustomCondtion> getConds() {
		return conds;
	}


	public void setConds(List<CustomCondtion> conds) {
		this.conds = conds;
	}


	@Override
	public String toString() {
		return "DatasModel [ids=" + ids + ", conds=" + conds + "]";
	}
	
	

}
