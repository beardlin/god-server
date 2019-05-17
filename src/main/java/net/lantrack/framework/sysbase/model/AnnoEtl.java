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
package net.lantrack.framework.sysbase.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Description;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取提交表单中的字段描述
 * 2018年1月12日
 * @author lin
 */
public class AnnoEtl {

    public static Map<String, String> getFieldDesc(Class<?> objectClass) {
        Map<String, String> fields = new HashMap<String, String>();
        for (Method m : objectClass.getMethods()) {
            if (StringUtils.startsWith(m.getName(), "get")) {
                try {
                    String field = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
                    Description fieldDesc = m.getAnnotation(Description.class);
                    if(fieldDesc!=null){
                        String desc = fieldDesc.value();
                        fields.put(field, desc);
                    }
                } catch (Exception e) {
                    // 忽略所有设置失败方法
                }
            }
        }
        return fields;
        
    }
    
    
    
    
    
    
}
