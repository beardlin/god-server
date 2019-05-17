package net.lantrack.framework.core.entity;

import java.io.Serializable;

/**
 * 专门用来存放key-value的，一般给下拉，单选，复选框用
 *       
 * @date 2019年3月7日
 */
public class MapEntity implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * key
     */
    private String k;
    /**
     * value
     */
    private String v;
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
    
}
