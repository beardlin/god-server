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
package net.lantrack.framework.core.entity;

import net.lantrack.framework.sysbase.util.EntityUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 不带分页返回的结果
 * 2018年1月12日
 * @author lin
 */
public class ReturnResult implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作结果状态码 默认为1 成功
     */
    private Integer rst=1;
    /**
     * 操作结果描述  默认为'操作成功'
     */
    private String rmg="操作成功";
    /**
     * 返回结果内容,此处new HashMap防止null出现
     * 如果想返回自定义结果则需new HashMap(),然后将各个值put进去，然后setResult
     */
    private Object content = new HashMap();
    
    public Integer getRst() {
        return rst;
    }
    public void setRst(Integer rst) {
        this.rst = rst;
    }
    public String getRmg() {
        return rmg;
    }
    public void setRmg(String rmg) {
        this.rmg = rmg;
    }
    public Object getContent() {
        return content;
    }
    public synchronized void setContent(Object content) {
        if(content==null){
            return;
        }
        if(content instanceof java.util.List){
            content = EntityUtil.turnListNullToString((List) content);
        }else if(content instanceof java.util.Map){
            Set set = ((java.util.Map) content).keySet();
            for(Object key:set){
                Object val = ((java.util.Map) content).get(key);
                if(val==null){
                    ((java.util.Map) content).replace(key, "");
                }
            }
        }else{
            content = EntityUtil.turnNullToString(content);
        }
        this.content = content;
    } 
    
    
}
