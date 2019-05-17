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
import net.lantrack.framework.core.importexport.util.ExcleModel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.date.EasterSundayRule;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 导入Excel工具类 2018年2月24日
 * 
 * @author lin
 */
public class ImportExcelUtil {
	/**
	 * excel文件类型  目前按照07版以后的做
	 */
	public static String type07 = ".xlsx";
	public static String type03 = ".xls";
	
	/**
	 * 通过列
	 * @Description: 
	 * @author lin
	 * @date 2018年5月22日
	 */
	public static List importExcelByColumnIndex(Class modelClass, File excel,
			int dataStart) throws Exception {
		//存放列的索引和字段名的映射
		Map<String, String> fieldIndex = getClassFieldAnnoMapper(modelClass);
		//存放字段名对应的类型
		Map<String, Class<?>> fieldTypeMap = new HashMap<String, Class<?>>();
		Field[] fields = modelClass.getDeclaredFields();
		for(Field f:fields){
			String name = f.getName();
			if(fieldIndex.containsValue(name)){
				fieldTypeMap.put(name, f.getType());
			}
		}
		//excel数据
		List<List<Object>> datalist = read2007Excel(excel, dataStart);
		//返回数据结果集
		List datas = new ArrayList<>();
		for(List<Object> data:datalist){
			Object temp = modelClass.newInstance();
			boolean emp = false;
			for(int i = 0; i < data.size(); i++){
				Object obj = data.get(i);
				if(obj==null||"".equals(obj)){
					continue;
				}
				emp = true;
				String fName = fieldIndex.get(i+"");
				if(fName==null||!fieldTypeMap.containsKey(fName)) continue;
				Class<?> fieldType = fieldTypeMap.get(fName);
				String methodName = "set"+fName.substring(0, 1).toUpperCase()+fName.substring(1);
				Method method = modelClass.getMethod(methodName, fieldType);
				if(method==null) continue;
				switch (fieldType.getName()) {
				case "java.lang.Integer":
					Integer integer = Integer.valueOf(obj.toString());
					method.invoke(temp, integer);break;
				case "int":
					Integer intVal = Integer.valueOf(obj.toString());
					method.invoke(temp, intVal);break;
				case "java.lang.Double":
					Double doub = Double.valueOf(obj.toString());
					method.invoke(temp, doub);break;
				case "double":
					Double doubVal = Double.valueOf(obj.toString());
					method.invoke(temp, doubVal);break;
				case "java.lang.Float":
					Float flo = Float.valueOf(obj.toString());
					method.invoke(temp, flo);break;
				case "float":
					float floVal = Float.valueOf(obj.toString());
					method.invoke(temp, floVal);break;
				case "java.lang.Long":
					Long l = Long.valueOf(obj.toString());
					method.invoke(temp, l);break;
				case "long":
					long lval = Long.valueOf(obj.toString());
					method.invoke(temp, lval);break;
				case "java.util.Date":
					Date date = (Date) obj;
					method.invoke(temp, date);break;
				default:
					method.invoke(temp, obj.toString());
					break;
				}
			}
			if(emp) {
				datas.add(temp);
			}
		}
		return datas;
	}
	/**
	 * 
	 * metoodName: importExcel
	 * @date:  2018年4月19日 下午3:01:39
	 * @param modelClass
	 * @param excel
	 * @param header 行头位置
	 * @param dataStart 读数据位置
	 * @return
	 * @throws Exception List
	 */
	public static List importExcel(Class modelClass, File excel,
			int header,int dataStart) throws Exception {
		//获取excel列头
		Map<Integer, String> excelHeader = getExcelHeader(excel, header);
		//获取modelClass中的字段名与中文名的描述
		Map<String, String> fieldMap = getClassFieldAnnoMapper(modelClass);
		if(excelHeader==null||fieldMap==null){
			return null;
		}
		//存放列的索引和字段名的映射
		Map<Integer, String> fieldIndex = new HashMap<Integer, String>();
		for(Map.Entry<Integer, String> entry:excelHeader.entrySet()){
			Integer colindex = entry.getKey();
			String headerName = entry.getValue();
			if(fieldMap.containsKey(headerName)){
				fieldIndex.put(colindex, fieldMap.get(headerName));
			}
		}
		Map<String, Class<?>> fieldTypeMap = new HashMap<String, Class<?>>();
		Field[] fields = modelClass.getDeclaredFields();
		for(Field f:fields){
			String name = f.getName();
			if(fieldIndex.containsValue(name)){
				fieldTypeMap.put(name, f.getType());
			}
		}
		//excel数据
		List<List<Object>> datalist = read2007Excel(excel, dataStart);
		//返回数据结果集
		List<Object> datas = new ArrayList<>();
		for(List<Object> data:datalist){
			Object temp = modelClass.newInstance();
			boolean empty = false;
			for(int i = 0; i < data.size(); i++){
				Object obj = data.get(i);
				if(obj==null||"".equals(obj)){
					continue;
				}
				empty = true;
				String fName = fieldIndex.get(i);
				if(fName==null||!fieldTypeMap.containsKey(fName)) continue;
				Class<?> fieldType = fieldTypeMap.get(fName);
				String methodName = "set"+fName.substring(0, 1).toUpperCase()+fName.substring(1);
				Method method = modelClass.getMethod(methodName, fieldType);
//				System.out.println("obj="+obj+"======fName="+fName+"=====method="+method);
				if(method==null) continue;
				switch (fieldType.getName()) {
				case "java.lang.Integer":
					Integer integer = Integer.valueOf(obj.toString());
					method.invoke(temp, integer);break;
				case "int":
					Integer intVal = Integer.valueOf(obj.toString());
					method.invoke(temp, intVal);break;
				case "java.lang.Double":
					Double doub = Double.valueOf(obj.toString());
					method.invoke(temp, doub);break;
				case "double":
					Double doubVal = Double.valueOf(obj.toString());
					method.invoke(temp, doubVal);break;
				case "java.lang.Float":
					Float flo = Float.valueOf(obj.toString());
					method.invoke(temp, flo);break;
				case "float":
					float floVal = Float.valueOf(obj.toString());
					method.invoke(temp, floVal);break;
				case "java.lang.Long":
					Long l = Long.valueOf(obj.toString());
					method.invoke(temp, l);break;
				case "long":
					long lval = Long.valueOf(obj.toString());
					method.invoke(temp, lval);break;
				case "java.util.Date":
					Date date = (Date) obj;
					method.invoke(temp, date);break;
				default:
					method.invoke(temp, obj.toString());
					break;
				}
			}
			if(empty) {
				datas.add(temp);
			}
		}
		return datas;
	}
	/**
	 * 读取Excel
	 * @param modelClass  excel对应的model
	 * @param excel 导入文件
	 * @param header 列头所在行
	 * @param dataStart 数据起始行
	 * @return
	 * @throws Exception
	 * 2018年2月26日
	 * @author lin
	 */
	public static <T> List<T> bigExcelReader(Class<T> modelClass, File excel,
			int header,int dataStart) throws Exception {
		if(header<0||dataStart<0) {
			throw new RuntimeException("heardr or dataStart Index Out Of header:"+header+",dataStart:"+dataStart);
		}
		//解析数据
		InputStream inputStream = new FileInputStream(excel);
        List<Object> datalist = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
		//获取excel列头
        Map<Integer, String> excelHeader = null;
//		 excelHeader = getExcelHeader(excel, header);
        if(datalist.size()>header) {
        	List<Object> headerList = (List<Object>) datalist.get(header);
        	excelHeader = new LinkedHashMap<>(headerList.size());
        	for(int i=0;i<headerList.size();i++) {
        		excelHeader.put(i, toStr(headerList.get(i)));
        	}
        }
        
		//获取modelClass中的字段名与中文名的描述
		Map<String, String> fieldMap = getClassFieldAnnoMapper(modelClass);
		if(excelHeader==null||fieldMap==null){
			return null;
		}
		//存放列的索引和字段名的映射
		Map<Integer, String> fieldIndex = new HashMap<Integer, String>();
		for(Map.Entry<Integer, String> entry:excelHeader.entrySet()){
			Integer colindex = entry.getKey();
			String headerName = entry.getValue();
			if(fieldMap.containsKey(headerName)){
				fieldIndex.put(colindex, fieldMap.get(headerName));
			}
		}
		Map<String, Class<?>> fieldTypeMap = new HashMap<String, Class<?>>();
		Field[] fields = modelClass.getDeclaredFields();
		for(Field f:fields){
			String name = f.getName();
			if(fieldIndex.containsValue(name)){
				fieldTypeMap.put(name, f.getType());
			}
		}
		//excel读取数据
//		ExcelReader reader = new ExcelReader(excel,dataStart);
//		@SuppressWarnings("unchecked")
//		List<List<Object>> datalist = reader.getRowList();
		
		//返回数据结果集,从开始数据解析
		datalist = datalist.subList(dataStart, datalist.size());
		List<T> datas = new ArrayList<>();
		for(Object data:datalist){
			T temp = modelClass.newInstance();
			List<Object> rows  = (List<Object>)data;
			for(Object obj:rows){
				int indexOf = rows.indexOf(obj);
				String fName = fieldIndex.get(indexOf);
				if(fName==null||!fieldTypeMap.containsKey(fName)) continue;
				Class<?> fieldType = fieldTypeMap.get(fName);
				String methodName = "set"+fName.substring(0, 1).toUpperCase()+fName.substring(1);
				Method method = modelClass.getMethod(methodName, fieldType);
				if(method==null) continue;
				switch (fieldType.getName()) {
				case "java.lang.Integer":
					Integer integer = Integer.valueOf(obj.toString());
					method.invoke(temp, integer);break;
				case "int":
					Integer intVal = Integer.valueOf(obj.toString());
					method.invoke(temp, intVal);break;
				case "java.lang.Double":
					Double doub = Double.valueOf(obj.toString());
					method.invoke(temp, doub);break;
				case "double":
					Double doubVal = Double.valueOf(obj.toString());
					method.invoke(temp, doubVal);break;
				case "java.lang.Float":
					Float flo = Float.valueOf(obj.toString());
					method.invoke(temp, flo);break;
				case "float":
					float floVal = Float.valueOf(obj.toString());
					method.invoke(temp, floVal);break;
				case "java.lang.Long":
					Long l = Long.valueOf(obj.toString());
					method.invoke(temp, l);break;
				case "long":
					long lval = Long.valueOf(obj.toString());
					method.invoke(temp, lval);break;
				case "java.util.Date":
					Date date = (Date) obj;
					method.invoke(temp, date);break;
				default:
					method.invoke(temp, obj.toString());
					break;
				}
			}
			datas.add(temp);
		}
		return datas;
	}
	private static String toStr(Object obj) {
		return obj==null?"":obj.toString();
	}
	
	/**
	 * 获取modelClass的英文字段与中文字段对应值
	 * @param modelClass
	 * @return
	 * 2018年2月26日
	 * @author lin
	 */
	public static Map<String, String> getClassFieldAnnoMapper(Class modelClass){
		Field[] fields = modelClass.getDeclaredFields();
		// 将对象中的字段名称和中文名存放起来 ---<字段名,字段中文名>
		Map<String, String> headMap = new LinkedHashMap<String, String>();
		// 获取字段注解的表头
		for(Field field:fields){
			ExcelAnno anno = field.getAnnotation(ExcelAnno.class);
			if (anno == null) continue;
			headMap.put(anno.value(), field.getName());
		}
		return headMap;
	}
	/**
	 * 模板文件与上传文件校验，根据表头校验
	 * @param modelClass
	 * @param excel
	 * @param header 从哪一行开始检查
	 * @return boolean
	 * 2018年2月26日
	 * @author lin
	 */
	public static boolean validateExcel(Class modelClass, File excel,int header) {
		if (excel.getName().indexOf(type07) == -1) {
			return false;
		}
		ExcleModel modelAnno = (ExcleModel) modelClass.getAnnotation(ExcleModel.class);
		if (modelAnno == null) {
			return false;
		}
		String path = modelAnno.path();
		URL url = modelClass.getResource(path);
		if (url == null) {
			return false;
		}
		File file = new File(url.getFile());
		if (!file.exists()) {
			return false;
		}
		Map<Integer, String> modelHeader = null;
		Map<Integer, String> excelHeader = null;
		try {
			modelHeader = getExcelHeader(file, header);
			excelHeader = getExcelHeader(excel, header);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(modelHeader==null||excelHeader==null){
			return false;
		}
		for(Map.Entry<Integer, String> entry:modelHeader.entrySet()){
			Integer key = entry.getKey();
			String value = entry.getValue();
			String excelVal = excelHeader.get(key);
			//            System.out.println(key+"--"+value+"--"+excelVal);
			if(excelVal==null||!excelVal.equals(value)){
				return false;
			}
		}
		return true;
	}
	
   
    
    /**
     * 根据指定模板Model下载模板文件
     * @param modelClass
     * @return File
     * 2018年2月26日
     * @author lin
     * @throws IOException 
     */
    @SuppressWarnings("rawtypes")
    public static void downExcelTemplate(HttpServletResponse response,
    		Class modelClass,String fileName) throws Exception{
        @SuppressWarnings("unchecked")
		ExcleModel modelAnno = (ExcleModel) modelClass.getAnnotation(ExcleModel.class);
        if (modelAnno == null) {
            throw new Exception("模板注解缺失!");
        }
        String path = modelAnno.path();
        URL url = modelClass.getResource(path);
        if (url == null) {
        	throw new Exception("模板文件路径错误!");
        }
        File file = new File(url.getFile());
        if (!file.exists()) {
        	throw new Exception("模板文件不存在或丢失!");
        }
        response.reset();
		OutputStream out = null;
		try {
			// 设置response的Header
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			//为了解决中文名称乱码问题
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(),"iso-8859-1"));
			out = new BufferedOutputStream(response.getOutputStream());
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
		} catch (IOException e) {
			IOUtils.closeQuietly(out);
			throw new Exception(e.getMessage());
		}
		
    }
    
    /**
     * 根据指定模板Model下载模板文件
     * @param modelClass
     * @return File
     * 2018年2月26日
     * @author lin
     */
    public static File downExcelTemplate(@SuppressWarnings("rawtypes") Class modelClass) {
        @SuppressWarnings("unchecked")
		ExcleModel modelAnno = (ExcleModel) modelClass.getAnnotation(ExcleModel.class);
        if (modelAnno == null) {
            return null;
        }
        String path = modelAnno.path();
        URL url = modelClass.getResource(path);
        if (url == null) {
            return null;
        }
        File file = new File(url.getFile());
        if (file.exists()) {
            return file;
        }
        return null;
    }

	/**
	 * 获取excel的制定列头
	 * 
	 * @param excle
	 * @param header
	 * @return <columnIndex--columnName>
	 * @throws FileNotFoundException
	 * @throws IOException 2018年2月25日
	 * @author lin
	 */
	@SuppressWarnings("resource")
	public static Map<Integer, String> getExcelHeader(File excel, int header)
			throws FileNotFoundException, IOException {
		if (excel.getName().indexOf(type07) == -1) {
			return null;
		}
		Map<Integer, String> headerMap = new HashMap();
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excel));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow rowHeader = sheet.getRow(header);
		short first = rowHeader.getFirstCellNum();
		short last = rowHeader.getLastCellNum();
		for (int i = first; i < last; i++) {
			XSSFCell cell = rowHeader.getCell(i);
			headerMap.put(i, cell.getStringCellValue());
		}
		return headerMap;
	}

	public static List<List<Object>> importExcel(File file,int start) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName
				.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file,start);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		@SuppressWarnings("resource")
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					// System.out.println(i+"行"+j+" 列 is String type");
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					// System.out.println(i+"行"+j+" 列 is Boolean type");
					value = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					// System.out.println(i+"行"+j+" 列 is Blank type");
					value = "";
					break;
				default:
					// System.out.println(i+"行"+j+" 列 is default type");
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
	/**
	 * 读取指定起止行/起止列的值
	 * @Description: 
	 * @author lin
	 * @date 2018年5月22日
	 */
	private static List<List<Object>> read2007ExcelByStartEnd(File file,int startColumn,
			int endColumn,int start) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = start; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = startColumn; j <= endColumn; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add("");
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					// System.out.println(i+"行"+j+" 列 is String type");
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else if ("0_ ".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					// System.out.println(i+"行"+j+" 列 is Boolean type");
					value = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					// System.out.println(i+"行"+j+" 列 is Blank type");
					value = "";
					break;
				default:
					// System.out.println(i+"行"+j+" 列 is default type");
					value = cell.toString();
				}
				//                if (value == null || "".equals(value)) {
				//                    continue;
				//                }
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

	/**
	 * 读取Office 2007 excel
	 */
	public static List<List<Object>> read2007Excel(File file,int start) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = start; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					linked.add("");
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					// System.out.println(i+"行"+j+" 列 is String type");
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else if ("0_ ".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					// System.out.println(i+"行"+j+" 列 is Boolean type");
					value = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					// System.out.println(i+"行"+j+" 列 is Blank type");
					value = "";
					break;
				default:
					// System.out.println(i+"行"+j+" 列 is default type");
					value = cell.toString();
				}
				//                if (value == null || "".equals(value)) {
				//                    continue;
				//                }
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

}
