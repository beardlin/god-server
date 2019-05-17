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
import net.lantrack.framework.core.importexport.excel.ImportExcelUtil;
import net.lantrack.framework.core.importexport.util.ExcelLogUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Detailed description 2018年2月24日
 * 
 * @author lin
 */
public class ImportExcelUtilTest {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		File file = new File("D:\\student.xlsx");
//		List<List<Object>> list = ImportExcelUtil.read2007Excel(file, 1);
		List<Student> list = ImportExcelUtil.bigExcelReader(Student.class, file, 0, 1);
		long end = System.currentTimeMillis();
		System.out.print(end-start);
		System.out.println("---"+list.size()+"---"+list.get(5));
		
	}
    
//    public static void main(String[] args) throws Exception {
//        // excel 导入数据demo
//        File file = new File("D:\\report.xlsx");
//        // 校验模板
//        boolean excel = ImportExcelUtil.validateExcel(Report.class, file, 0);
//        if (!excel) {
//            System.out.println("校验失败,请先下载模板,将数据填入模板后在提交!");
//            return;
//        }
//        List<Report> datas = (List<Report>) ImportExcelUtil
//                .importExcel(Report.class, file, 0, 1);
//        if (datas != null) {
//            for (Report rep : datas) {
//                System.out.println(rep);
//            }
//        }
//    }
    
//    public static void main(String[] args) throws Exception {
//        // excel 导入数据demo
//        File file = new File("D:\\student.xlsx");
//        long start = System.currentTimeMillis();
//        // 校验模板
//        boolean excel = ImportExcelUtil.validateExcel(Student.class, file, 0);
//        if (!excel) {
//            System.out.println("校验失败,请先下载模板,将数据填入模板后在提交!");
//            return;
//        }
//        List<Student> datas = (List<Student>) ImportExcelUtil
//                .importExcel(Student.class, file, 0, 1);
////        List<PhysicalBaseModel> datas = (List<PhysicalBaseModel>) ImportExcelUtil
////                .bigExcelReader(PhysicalBaseModel.class, file, 0, 1);
//        if (datas != null) {
//            long end = System.currentTimeMillis();
//            System.out.println(datas.size()+"---耗时："+(end-start));
//            for (Student stu : datas) {
//                System.out.println(stu);
//            }
//        }
//    }
    
}
