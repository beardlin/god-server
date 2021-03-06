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
package net.lantrack.framework.sysbase.entity;

/**
 * 职务权限表
 * 2018年1月17日
 * @author lin
 */
public class DutyMenu {
    
    /**
     * 职务id
     */
    private Integer dutyId;
    /**
     * 权限id
     */
    private Integer menuId;
    
    
    
    public DutyMenu(Integer dutyId, Integer menuId) {
        super();
        this.dutyId = dutyId;
        this.menuId = menuId;
    }
    public Integer getDutyId() {
        return dutyId;
    }
    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    
}
