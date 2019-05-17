package net.lantrack.framework.sysbase.model.log;

import org.springframework.context.annotation.Description;

public class LogIdsModel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3402697047990867097L;
	/**
     * 日志编号
     */
    private String ids;
    
    @Description("日志编号")
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
    
    
    
	
}
