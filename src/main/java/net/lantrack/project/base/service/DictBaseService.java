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
package net.lantrack.project.base.service;


import java.util.List;
import java.util.Map;

import net.lantrack.framework.sysbase.model.dict.DictModel;

/**
 * 基础字典等接口的管理
 * 2018年3月27日
 * @author lin
 */
public interface DictBaseService {
    
    /**
     * 根据字典类型和所属科室查询对应字典
     * @param dictType 字典类型
     * @param depart （0通用1环卫2食品3放射4职业5学校）
     * @return
     * 2018年3月27日
     * @author lin
     */
    List<DictModel> queryDictByTypeAndDepart(
            String dictType,Integer depart);
    
    Map<String, String> queryDictMapByTypeAndDepart(
            String dictType,Integer depart);
}
