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

package net.lantrack.project.base.controller;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.model.dict.DictModel;
import net.lantrack.project.base.service.DictBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 字典信息的共用接口basedict/不拦截 2018年3月27日
 * 
 * @author lin
 */
@RequestMapping("base/dict")
@Controller
public class BaseDictController extends BaseController {
    
    @Autowired
    private DictBaseService dictBaseService;
    //获取对应类型字典  base/dict/getDict.json
    @RequestMapping("getDict")
    public String getDict(String type, Integer pid, ReturnEntity info) {
        List<DictModel> list = this.dictBaseService.queryDictByTypeAndDepart(type, pid);
        info.setResult(list);
        info.success("suc");
        return "";
    }
    
    
}
