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

import net.lantrack.framework.core.importexport.Student;
import net.lantrack.framework.core.importexport.excel.ExportExcelUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Detailed description
 * 2018年2月24日
 * @author lin
 */
public class ExportUtilTest {
    
    public static void main(String[] args) throws Exception {
        // 1导出字段
        List<String> heads = new ArrayList<String>();
        // heads.add("stuName");
        // heads.add("stuAge");
        // 2数据集合
        List<Student> dataList = new ArrayList<Student>();
        for (int i = 0; i < 100000; i++) {
            Student stu = new Student("jack"+i, i, "1992-09-03");
            dataList.add(stu);
        }
        // 3输出
        File file = new File("D:\\student.xlsx");
        FileOutputStream outputStream = FileUtils.openOutputStream(file);
//        ExportExcelUtil.exportExcel97(heads, dataList, outputStream, "yyyy-MM-dd");
        ExportExcelUtil.exportExcel2007(heads, dataList, outputStream, "yyyy-MM-dd");
        outputStream.close();
        System.out.println("完成");
        
    	//导出字段
//    	long start = System.currentTimeMillis();
//        Map<String, String> exportFields = new LinkedHashMap<String, String>();
//        Map<String, Object> dataMap = new HashMap<>();
//        for(int i=0;i<20;i++) {
//        	exportFields.put("field"+i, "第"+i+"列");
//        	dataMap.put("field"+i, "data"+i);
//        }
//        //导出数据
//        List<Map<String, Object>> dataList = new ArrayList<>(1000000);
//        for(int i=0;i<500000;i++) {
//        	dataList.add(dataMap);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//        //输出流
//        File file = new File("D:\\student.xlsx");
//        FileOutputStream outputStream = FileUtils.openOutputStream(file);
//    	ExportExcelUtil.exportExcelByColumnMap(exportFields, dataList, outputStream, "yyyy-MM-dd");
//        System.out.println("success");
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1-end);
    }
}
