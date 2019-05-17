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
package net.lantrack.project.base.service.impl;

import net.lantrack.framework.sysbase.dao.SysDictDao;
import net.lantrack.framework.sysbase.model.dict.DictModel;
import net.lantrack.project.base.service.DictBaseService;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础字典等接口的管理
 * 2018年3月27日
 * @author lin
 */
@Service
public class DictBaseServiceImpl implements DictBaseService {
    
    protected Logger logger = (Logger) LogManager.getLogger(DictBaseServiceImpl.class);

    
    
    @Autowired
    private SysDictDao sysDictDao;
    
    @Override
    public List<DictModel> queryDictByTypeAndDepart(String dictType, Integer depart) {
        if(StringUtils.isBlank(dictType)||depart==null){
            return null;
        }
        try {
            return this.sysDictDao.getDictByTypeAndPid(dictType, depart);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("queryDictByTypeAndDepart ERROR:dictType:{}，depart:{}，errMsg:{}"
                    ,dictType,depart,e.getMessage());
            return null;
        }
    }

	@Override
	public Map<String, String> queryDictMapByTypeAndDepart(String dictType, Integer depart) {
		List<DictModel> list = queryDictByTypeAndDepart(dictType,depart);
		Map<String, String> map = new HashMap<>();
		if(list==null) {
			return map;
		}
		for(DictModel dict:list) {
			map.put(dict.getId(), dict.getValue());
		}
		return map;
	}
    
}
