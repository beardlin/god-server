/*
 * Copyright 2017 Lantrack Corporation All Rights Reserved.
 *
 * The source code contained or described herein and all documents related to
 * the source code ("Material") are owned by Lantrack Corporation or its suppliers
 * or licensors. Title to the Material remains with Lantrack Corporation or its
 * suppliers and licensors. The Material contains trade secrets and proprietary
 * and confidential information of Lantrack or its suppliers and licensors. The
 * Material is protected by worldwide copyright and trade secret laws and
 * treaty provisions.
 * No part of the Material may be used, copied, reproduced, modified, published
 * , uploaded, posted, transmitted, distributed, or disclosed in any way
 * without Lantrack's prior express written permission.
 *
 * No license under any patent, copyright, trade secret or other intellectual
 * property right is granted to or conferred upon you by disclosure or delivery
 * of the Materials, either expressly, by implication, inducement, estoppel or
 * otherwise. Any license under such intellectual property rights must be
 * express and approved by Intel in writing.
 *
 */
package net.lantrack.framework.sysbase.model.menu;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

/**
 * Detailed description
 * 2018年1月11日
 * @author lin
 */
public class MenuTreeModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer pid;
    private String name;
    private Integer sort;
    
    public MenuTreeModel(){
        
    }
    public MenuTreeModel(Integer id, Integer pid, String name,Integer sort) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.sort = sort;
    }
    
    
    
    public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Description("编号")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Description("父级编号")
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    @Description("菜单名称")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
}
