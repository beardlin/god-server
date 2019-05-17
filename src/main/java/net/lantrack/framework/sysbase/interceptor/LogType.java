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

package net.lantrack.framework.sysbase.interceptor;


/**
 * 日志类型 2018年1月25日
    <p>
    LOGIN("1","登录"),
    LOGOUT("2","退出"),
    ADD("3","添加"),
    DELETE("4","删除"),
    UPDATE("5","修改"),
    LOOK("6","查看");
    </p>
    @author lin
 */
public enum LogType {
    
    LOGIN("1","登录"),
    LOGOUT("2","退出"),
    ADD("3","添加"),
    DELETE("4","删除"),
    UPDATE("5","修改"),
    LOOK("6","查看"),
    SYNCH("7","同步");
    
    private String type;
    private String remarks;
    
    private LogType(String type,String remarks) {
        this.type = type;
        this.remarks = remarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public static String getValueByKey(String key) {
    	String value = "";
    	switch (key) {
			case "1" : value="登录";
				break;
			case "2" : value="退出";
				break;
			case "3" : value="添加";
				break;
			case "4" : value="删除";
				break;
			case "5" : value="修改";
				break;
			case "6" : value="查看";
				break;
			case "7" : value="同步";
				break;
	
			default:
				break;
		}
    	return value;
    }
    
}
