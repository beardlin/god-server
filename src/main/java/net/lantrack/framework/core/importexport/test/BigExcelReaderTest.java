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
package net.lantrack.framework.core.importexport.test;


import net.lantrack.framework.core.importexport.excel.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

/**
 * Detailed description
 * 2018年2月24日
 * @author lin
 */
public class BigExcelReaderTest {

    public static void main(String[] args) throws Exception{
    	long start = System.currentTimeMillis();
        File file = new File("D:\\student.xlsx");
//        1
//        ExcelReader reader = new ExcelReader(file,1);
//        List list = reader.getRowList();
        
        
//        2
//        InputStream inputStream = new FileInputStream(file);
//        List<Object> datalist = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
//        System.out.println(datalist.size());
        
        
//      3
      InputStream inputStream = new FileInputStream(file);
      ExcelListener listener = new ExcelListener();
      EasyExcelFactory.readBySax(inputStream,  new Sheet(1, 0), listener);
      List<List<Object>> list = listener.getList();
      System.out.println(list.size()+"---"+list.get(0));
        long end = System.currentTimeMillis();
        System.out.println("用时:"+(end-start)/1000+"s");
        
    }
}
