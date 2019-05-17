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

package net.lantrack.framework.sysbase.util;

import net.lantrack.framework.core.config.Config;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.PathUtil;
import net.lantrack.framework.sysbase.entity.SysFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 系统资料管理工具类 2018年3月10日
 * 
 * @author lin
 */
public class SysFileUtils extends FileUtils {
    protected static    Logger logger = LoggerFactory.getLogger(SysFileUtils.class);
    public static void main(String[] args) throws IOException {
    	
//        5将文件或文件夹从命名
//        File dir = new File("D:/mdap/person/");
//        System.out.println(reNameFile(dir,"car"));
        //4在指定目录下新建文件或文件夹
//        createFile("D:/mdap/person/",new File("D:/KuGou/test"));
        //3删除文件
//        System.out.println(deleteFile("d:/mdap/press/"));
//        //1获取子文件信息
//        List<SysFile> files = getFiles("D:/mdap");
//        for(SysFile sysfile:files){
//          //2打印所有文件信息
//            printFileInfo(sysfile);
//        }
    }
    /**
     * 文件或文件夹的重命名
     * @param file
     * @param newName
     * @return
     * 2018年3月11日
     * @author lin
     */
    public static boolean reNameFile(File file,String newName){
        if(!file.exists()){
            return false;
        }
        String parent = formatDirPath(file.getParent());
        String newPath =parent+newName;
        if(file.isFile()){
            String fileType = getFileType(file.getName());
            if(!"".equals(fileType)){
                newPath = newPath+"."+fileType;
            }
        }
        File newfile = new File(newPath);
        return file.renameTo(newfile);
    }
    /**
     * 在指定目录下添加文件或文件夹
     * @param parent
     * @param file
     * 2018年3月11日
     * @author lin
     * @throws IOException 
     */
    public static void createFile(String parentPath,File file) throws IOException{
        File dir = new File(parentPath);
        if(!dir.isDirectory()){
            return;
        }
        moveToDirectory(file, dir, true);
    }
    /**
     * 如果文件夹path不为/,则拼接上
     * @param path
     * @return
     * 2018年3月11日
     * @author lin
     */
    public static String formatDirPath(String path){
        if (!path.endsWith(File.separator)) {  
            path = path + File.separator;  
        }
        return path;
    }
    /**
     * 删除文件或目录
     * @param file
     * @return
     * 2018年3月11日
     * @author lin
     * @throws IOException 
     */
    public static boolean deleteFile(String file) throws IOException{
        File f = new File(file);
        if (!f.exists()) {
            return false;
        }
        if(f.isDirectory()){
            deleteDirectory(f);
            return true;
        }else{
            return f.delete();
        }
    }
    /**
     * 根据文件名称匹配指定文件夹下的文件
     * @param sf
     * 2018年3月10日
     * @author lin
     */
    public static List<SysFile> getFileByInfo(SysFile sf,String fName){
    	List<SysFile> list = new ArrayList<>();
        if(sf.getIfDirect()==1){
            List<SysFile> files = getFiles(sf.getOldName());
            for(SysFile sFile:files){
            	List<SysFile> chilList = getFileByInfo(sFile,fName);
            	list.addAll(chilList);
            }
        }else {
        	if(sf.getOldName().contains(fName)) {
        		list.add(sf);
        	}
        }
        return list;
    }
    /**
     * 查看当前文件夹下资源
     * @param dir
     * @return 2018年3月10日
     * @author lin
     */
    public static List<SysFile> getFiles(String dir) {
        File file = new File(dir);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        List<SysFile> list = new ArrayList<SysFile>(listFiles.length);
        for (File f : listFiles) {
            SysFile sysFile = turnFileToSysFile(f);
            if (sysFile != null) {
                list.add(sysFile);
            }
        }
        return list;
    }
    /**
     * 文件夹类型
     */
    public static final int DIR_YES=1;
    /**
     * 文件类型
     */
    public static final int DIR_NO=0;
    /**
     * 将文件转换为系统资源对象
     * @param file
     * @return 2018年3月10日
     * @author lin
     */
    public static SysFile turnFileToSysFile(File file) {
        if (!file.exists()) {
            return null;
        }
        SysFile sf = new SysFile();
        sf.setOldName(file.getName());
        sf.setIfDirect(file.isDirectory() ? DIR_YES:DIR_NO);
        sf.setIsShow(file.isHidden() ? DIR_YES:DIR_NO);
        sf.setTarget(sizeOf(file));
        if (file.isFile()) {
            sf.setFileType(getFileType(file.getPath()));
        }
        return sf;
    }
    /**
     * 获取文件类型，后缀
     * @param filePath
     * @return
     * 2018年3月11日
     * @author lin
     */
    public static String getFileType(String filePath){
        String type = "";
        int indexOf = filePath.lastIndexOf(".");
        if(indexOf>0){
            type = filePath.substring(indexOf+1);
        }
        return type;
    }
    /**
     * 下载文件多个 
     * @Description: 
     * @author lin
     * @date 2018年5月23日
     */
    public static void downloadMultiple(HttpServletResponse response,List<File> files) throws Exception{
    	//则使用当前时间为临时压缩文件名称
    	String zipName = PathUtil.getSysRootPath() + DateUtil.getNumDateFormat() + ".zip";
    	File zip = new File(PathUtil.formatFilePath(zipName));
    	if(!zip.exists()) {
    		zip.createNewFile();
    	}
    	try {
    		//将多个文件加入到压缩文件夹
    		ZipUtils.zipFiles(files, zip);
    		//下载文件
    		downLoadSingle(response, zip);
		} catch (Exception e) {
			throw e;
		}finally {
			if(zip.exists()) {
				zip.delete();
			}
		}
    }
    
    public static void downloadMultipleZip(HttpServletResponse response,List<File> files,String zipName) throws Exception{
    	//如果没有指定压缩文件名称则使用当前时间
    	if(StringUtils.isBlank(zipName)) {
    		zipName = DateUtil.getNumDateFormat();
    	}
    	if(!zipName.endsWith("zip") && !zipName.endsWith("rar")) {
    		zipName = zipName + ".zip";
    	}
    	//创建临时文件夹
    	File tempDir = new File(Config.tempPath);
    	if(!tempDir.exists()) {
    		tempDir.mkdirs();
    	}
    	File zip = new File(Config.tempPath+File.separator+zipName);
    	if(!zip.exists()) {
    		zip.createNewFile();
    	}
    	try {
    		//将多个文件加入到压缩文件夹
    		ZipUtils.zipFiles(files, zip);
    		//下载文件
    		downLoadSingle(response, zip);
		} catch (Exception e) {
			throw e;
		}finally {
			if(zip.exists()) {
				System.out.println(zip.getAbsolutePath());
				zip.delete();
			}
		}
    }
    
    /**
     * 下载单个文件
     * @param response
     * @param file
     * @throws IOException
     * @date 2019年4月15日
     */
    public static void downLoadSingle(HttpServletResponse response,File file) throws IOException {
    	OutputStream out = null;
		try {
			// 设置response的Header
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			//为了解决中文名称乱码问题 URLEncoder
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
			out = new BufferedOutputStream(response.getOutputStream());
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
		} catch (IOException e) {
			throw e;
		}finally {
			IOUtils.closeQuietly(out);
		}
    }
}
