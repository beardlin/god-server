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
package net.lantrack.project.demo.dao;

import net.lantrack.BaseJunitTest;
import net.lantrack.framework.sysbase.dao.IdUserDao;
import net.lantrack.framework.sysbase.entity.IdUser;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Detailed description
 * 2018年1月6日
 * @author lin
 */
public class TestIdAndUUid extends BaseJunitTest{
    
    @Autowired
    IdUserDao idUserDao;
    
    @Test
    public void testByIdAuto(){
//        IdUser u = new IdUser();
//        u.setuLike("test");
//        u.setuAge(10);
//        u.setuName("张三");
//        idUserDao.insert(u);
//        System.out.println(u);
//        Assert.assertNotNull(u.getId());
    }
}
