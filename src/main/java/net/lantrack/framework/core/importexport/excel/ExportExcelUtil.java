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

package net.lantrack.framework.core.importexport.excel;

import net.lantrack.framework.core.importexport.util.ExcelAnno;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出excel工具类 2018年2月6日
 * 
 * @author lin
 */
public class ExportExcelUtil {
    /**
     * 默认时间格式 为 yyyy-MM-dd
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATETIME_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
    /**
     * 默认列宽
     */
    public static final int DEFAULT_COLOUMN_WIDTH = 17;


    /**
     * 设置表头的样式
     * 
     * @param workbook 2018年2月6日
     * @author lin
     */
    public static CellStyle setHeadStyle(Workbook workbook) {
        // 设置列头样式
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColorPredefined.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置列头字体
        Font font = workbook.createFont();
        font.setColor(HSSFColorPredefined.VIOLET.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 设置表头的样式
     * 
     * @param workbook 2018年2月6日
     * @author lin
     */
    public static CellStyle setBodyStyle(Workbook workbook) {
        // 设置列头样式
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColorPredefined.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置内容字体
        Font font = workbook.createFont();
        // font.setBold(true);//不加粗
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 导出97版Excel(.xls)
     * @param exportFields  需要导出的字段，若全部导出则传null或者空
     * @param dataList  导出的数据结果集
     * @param out   输出流
     * @param datePattern 日期格式
     * @throws Exception
     * 2018年2月06日
     * @author lin
     */
    public static void exportExcel97(List<String> exportFields, List dataList, OutputStream out,
            String datePattern) throws Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为17个字节
        sheet.setDefaultColumnWidth(DEFAULT_COLOUMN_WIDTH);
        // 设置表头样式
        CellStyle headerStyle = setHeadStyle(workbook);
        // 设置内容样式
        CellStyle cellStyle = setBodyStyle(workbook);
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        Object tempT = dataList.get(0);
        Field[] fields = tempT.getClass().getDeclaredFields();
        // 将对象中的字段名称和中文名存放起来 ---<字段名,字段中文名>
        Map<String, String> headMap = new LinkedHashMap<String, String>();

        boolean exportAll = false;
        // 当传入列头list为空时，默认导出所有字段
        if (exportFields == null || exportFields.size() == 0) {
            exportAll = true;
        }
        // 获取字段注解的表头
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelAnno anno = field.getAnnotation(ExcelAnno.class);
            if (anno == null)
                continue;
            headMap.put(field.getName(), anno.value());
            // 将有注解的字段全部导出
            if (exportAll) {
                exportFields.add(field.getName());
            }
        }
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < exportFields.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            HSSFRichTextString text = new HSSFRichTextString(headMap.get(exportFields.get(i)));
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator it = dataList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            // 97版单个sheet最大容量为65536
            if (index >= 65500) {
                sheet = workbook.createSheet();
                sheet.setDefaultColumnWidth(DEFAULT_COLOUMN_WIDTH);
                // 产生表格标题行
                HSSFRow rw = sheet.createRow(0);
                for (int i = 0; i < exportFields.size(); i++) {
                    HSSFCell cell = rw.createCell(i);
                    cell.setCellStyle(headerStyle);
                    HSSFRichTextString text = new HSSFRichTextString(headMap.get(exportFields.get(i)));
                    cell.setCellValue(text);
                }
                index=0;
                continue;
            }
            row = sheet.createRow(index);
            Object temp = it.next();
            Class<? extends Object> tempClazz = temp.getClass();
            Field[] fieldsList = tempClazz.getDeclaredFields();
            for (Field field : fieldsList) {
                String fieldName = field.getName();
                int indexOf = exportFields.indexOf(fieldName);
                if (indexOf == -1) {
                    continue;
                }
                HSSFCell cell = row.createCell(indexOf);
                cell.setCellStyle(cellStyle);
                Method getMethod = null;
                //用来处理不规范的get方法
                try {
                	String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    getMethod = tempClazz.getMethod(getMethodName);
				} catch (Exception e) {
					String getMethodName = "get" + fieldName;
	                getMethod = tempClazz.getMethod(getMethodName);
				}
                if(getMethod==null) {
                	continue;
                }
                Object value = getMethod.invoke(temp);
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value == null) {
                    cell.setCellValue("");
                }
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    cell.setCellValue(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    cell.setCellValue(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    cell.setCellValue(dValue);
                } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    cell.setCellValue(longValue);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    if (datePattern == null) {
                        datePattern = DEFAULT_DATE_PATTERN;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                    textValue = sdf.format(date);
                    cell.setCellValue(textValue);
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value == null ? "" : value.toString();
                    cell.setCellValue(textValue);
                }
            }
        }
        workbook.write(out);

    }

    /**
     * 导出07版(.xlsx)
     * @param exportFields  需要导出的字段，若全部导出则传null或者空
     * @param dataList  导出的数据结果集
     * @param out  输出流
     * @param datePattern 日期时间格式
     * @throws Exception
     * 2018年2月06日
     * @author lin
     */
    public static void exportExcel2007(List<String> exportFields, List dataList,
            OutputStream out, String datePattern) throws Exception {
        if (datePattern == null)
            datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);// 缓存
        //压缩临时文件
        workbook.setCompressTempFiles(true);
        // 列头样式
        CellStyle headerStyle = setHeadStyle(workbook);
        // 单元格样式
        CellStyle cellStyle = setBodyStyle(workbook);
        // 生成一个表格
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
        sheet.setDefaultColumnWidth(DEFAULT_COLOUMN_WIDTH);
        
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        Object tempT = dataList.get(0);
        Field[] fields = tempT.getClass().getDeclaredFields();
        // 将对象中的字段名称和中文名存放起来 ---<字段名,字段中文名>
        Map<String, String> headMap = new LinkedHashMap<String, String>();
        boolean exportAll = false;
        // 当传入列头list为空时，默认导出所有字段
        if (exportFields == null || exportFields.size() == 0) {
            exportAll = true;
            if(exportFields == null){
                exportFields = new ArrayList<String>();
            }
        }
        // 获取字段注解的表头
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelAnno anno = field.getAnnotation(ExcelAnno.class);
            if (anno == null)
                continue;
            headMap.put(field.getName(), anno.value());
            // 将有注解的字段全部导出
            if (exportAll) {
                exportFields.add(field.getName());
            }
        }
        // 产生表格标题行
        SXSSFRow row = sheet.createRow(0);
        for (int i = 0; i < exportFields.size(); i++) {
            SXSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            String fieldName = exportFields.get(i);
            String colName = headMap.get(fieldName) == null?fieldName:headMap.get(fieldName);
            XSSFRichTextString text = new XSSFRichTextString(colName);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator it = dataList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object temp = it.next();
            Class<? extends Object> tempClazz = temp.getClass();
            Field[] fieldsList = tempClazz.getDeclaredFields();
            for (Field field : fieldsList) {
                String fieldName = field.getName();
                int indexOf = exportFields.indexOf(fieldName);
                if (indexOf == -1) {
                    continue;
                }
                SXSSFCell cell = row.createCell(indexOf);
                cell.setCellStyle(cellStyle);
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Method getMethod = tempClazz.getMethod(getMethodName);
                Object value = getMethod.invoke(temp);
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value == null) {
                    cell.setCellValue("");
                }
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    cell.setCellValue(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    cell.setCellValue(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    cell.setCellValue(dValue);
                } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    cell.setCellValue(longValue);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    if (datePattern == null) {
                        datePattern = DEFAULT_DATE_PATTERN;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                    textValue = sdf.format(date);
                    cell.setCellValue(textValue);
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value == null ? "" : value.toString();
                    cell.setCellValue(textValue);
                }
            }
        }
        try {
            workbook.write(out);
            workbook.dispose();
            workbook.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接下载生导出文件
     * @param fileName  下载文件名
     * @param exportFields  导出字段
     * @param dataList  导出数据结果集
     * @param response  
     * 2018年2月06日
     * @author lin
     */
    public static void downloadExcelFile(String fileName,List exportFields, List dataList,
            HttpServletResponse response) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            exportExcel2007(exportFields, dataList, os, null);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bufInput = new BufferedInputStream(is);
            BufferedOutputStream bufOutput = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[1024 * 8];
            int bytesRead;
            while (-1 != (bytesRead = bufInput.read(buff, 0, buff.length))) {
                bufOutput.write(buff, 0, bytesRead);
            }
            bufInput.close();
            bufOutput.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 导出任意字段到Excel，结合元数据，Mybatis
     * @Description: 
     * @author lin
     * @date 2018年6月19日
     */
    public static void exportExcelByColumnMap(Map<String, String> exportFields, List<Map<String, Object>> dataList,
            OutputStream out, String datePattern) throws Exception {
        if (datePattern == null)
            datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);// 缓存
        //压缩临时文件
        workbook.setCompressTempFiles(true);
        // 列头样式
        CellStyle headerStyle = setHeadStyle(workbook);
        // 单元格样式
        CellStyle cellStyle = setBodyStyle(workbook);
        // 生成一个表格
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
        sheet.setDefaultColumnWidth(DEFAULT_COLOUMN_WIDTH);
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        //  遍历<字段名,字段中文名>Map--->产生表格标题行
        SXSSFRow row = sheet.createRow(0);
        //  存放<index,column>对应关系
        Map<Integer, String> indexColumn = new HashMap<>(exportFields.size());
        int i=0;
        for(Map.Entry<String, String> entry:exportFields.entrySet()) {
        	SXSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            XSSFRichTextString text = new XSSFRichTextString(entry.getValue());
            cell.setCellValue(text);
            indexColumn.put(i, entry.getKey());
            i++;
        }
        // 遍历集合数据，产生数据行
        Iterator<Map<String, Object>> it = dataList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            //一条数据
            Map<String, Object> temp = it.next();
            for (int j = 0;j<i;j++) {
            	String columnName = indexColumn.get(j);
                if (columnName == null) {
                    continue;
                }
                Object value = temp.get(columnName);
                SXSSFCell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value == null) {
                    cell.setCellValue("");
                }
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    cell.setCellValue(intValue);
                } else if (value instanceof Float) {
                    float fValue = (Float) value;
                    cell.setCellValue(fValue);
                } else if (value instanceof Double) {
                    double dValue = (Double) value;
                    cell.setCellValue(dValue);
                } else if (value instanceof Long) {
                    long longValue = (Long) value;
                    cell.setCellValue(longValue);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    if (datePattern == null) {
                        datePattern = DEFAULT_DATE_PATTERN;
                    }
                    if(value.toString().length()>10) {
                    	datePattern = DATETIME_DATE_PATTERN;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                    textValue = sdf.format(date);
                    cell.setCellValue(textValue);
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value == null ? "" : value.toString();
                    cell.setCellValue(textValue);
                }
            }
        }
        try {
            workbook.write(out);
            workbook.dispose();
            workbook.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 将生成的Excel输出流写入response
     * @Description: 
     * @author lin
     * @date 2018年6月19日
     */
    public static void downloadExcel(String fileName,ByteArrayOutputStream os,HttpServletResponse response) {
        try {
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bufInput = new BufferedInputStream(is);
            BufferedOutputStream bufOutput = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[1024 * 8];
            int bytesRead;
            while (-1 != (bytesRead = bufInput.read(buff, 0, buff.length))) {
                bufOutput.write(buff, 0, bytesRead);
            }
            bufInput.close();
            bufOutput.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
