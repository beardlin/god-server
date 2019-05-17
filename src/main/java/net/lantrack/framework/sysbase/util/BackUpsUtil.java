package net.lantrack.framework.sysbase.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lantrack.framework.core.config.Config;
import net.lantrack.project.search.util.JDBCUtils;
/**
 * 
 * 数据备份工具类
 * 数据库+文件，系统统一定义的目录下
 * 
 * @author liumy
 *
 */
public class BackUpsUtil { 
	private static Logger logger = LoggerFactory.getLogger(BackUpsUtil.class);

	/** MySQL安装目录的Bin目录的绝对路径 */  
//	private static String mysqlBinPath = MySqlPath.getMysqlPath();
	private static String mysqlBinPath;
	/** 访问MySQL数据库的用户名 */  
	private static String username = JDBCUtils.user;  
	/** 访问MySQL数据库的密码 */  
	private static String password = JDBCUtils.password;  
	/** 访问MySQL数据库的库名*/
	private static String dbName;
	/** 访问MySQL数据库的库名*/
	private static String ip;

	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * methodName: restoreStart
	 * date: 2018年2月5日 上午10:34:57 
	 * @param :  
	 * @author: liumy
	 * @param fileName 要恢复的文件夹名字
	 * @param request
	 */
	public static void restoreStart(String fileName,HttpServletRequest request) {  
		try {  
			//获得系统的备份路径
//			String rootPathTemp = request.getSession().getServletContext().getRealPath("/");
//			String rootPath = rootPathTemp.replace("\\", "/").substring(0, rootPathTemp.length()-1);

			String rootPath =Config.backupsPath;
			//判断当前操作系统的名称
			String osNameType = System.getProperty("os.name").toLowerCase();
			rootPath = osNameType.contains("window") ? rootPath : File.separator+rootPath;

			// 拼接获得待还原的sql文件    -----   还原数据库  
			restoreSql(rootPath+File.separator+Config.backupsPath
			           +File.separator+fileName+File.separator+dbName+".sql", osNameType);
			//还原文件
			String mobileFile=request.getSession().getServletContext().getRealPath("/")+File.separator+"mobile";
			//rootPath+File.separator+Config.uploadPath
			restoreFile(mobileFile, 
					rootPath+File.separator+Config.backupsPath+File.separator+fileName+File.separator+Config.uploadPath);
			System.out.println("还原成功");
		} catch (Exception e) {  
			logger.error("数据还原时出现异常，异常信息为："+e.getMessage()); 
		}  
	} 

	/**
	 * 
	 * methodName: restoreSql
	 * 恢复指定的数据库文件
	 * date: 2018年1月24日 上午11:13:56 
	 * @param :  
	 * @author: liumy
	 * @param sqlFile 之前备份的sql文件全路径
	 * @param osName 系统的类型
	 */
	public static void restoreSql(String sqlPath,String osNameType) {  
		//判断当前操作系统的名称
		if(osNameType.contains("window")){
			recoverWindows(sqlPath); 
		}else {
			recoverLinux(sqlPath);
		}
	}
	/**
	 * 
	 * methodName: recoverWindows
	 * 恢复数据库  (Windows操作系统时)
	 * date: 2018年1月24日 上午11:20:08 
	 * @param :  
	 * @author: liumy
	 * @param sqlPath 之前备份的sql文件全路径
	 * @return
	 */
	public static boolean recoverWindows(String sqlPath) {
		if (!mysqlBinPath.endsWith(File.separator)) {  
			mysqlBinPath = mysqlBinPath + File.separator;  
		}
		// String command = "cmd /c \"" +mysqlBinPath + "mysql\" -u" + username  
		//          + " -p" + password + " -D" + dbname + "<"; 
		String command = "cmd /c \"" +mysqlBinPath + "mysql\" -h "+ip+" -u" + username  
				+ " -p" + password + " --default-character-set=utf8 -D" + dbName + "<";
		if(command.indexOf("\\")!=-1) {
			command = command.replace("\\", "/");
		}
		String tempPath = "C:/temp/";
		String newPath = tempPath+dbName+".sql";
		if(sqlPath.indexOf(" ")!=-1) {
			File dempDir = new File(tempPath);
			if(!dempDir.exists()) {
				dempDir.mkdirs();
			}
			command += newPath;
		}else {
			command += sqlPath;
		}
		File binDir = new File(mysqlBinPath);
		if(!binDir.exists()) {
			return false;
		}
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			File newfile = new File(newPath);
			if(newfile.isFile()) {
				newfile.delete();
			}
			File dempDir = new File(tempPath);
			if(dempDir.exists()) {
				dempDir.delete();
			}
		} catch (IOException e1) {
			logger.error("恢复数据库时出现IO异常，异常信息为："+e1.getMessage()); 
			return false;
		} catch (InterruptedException e) {
			logger.error("恢复数据库时出现中断异常，异常信息为："+e.getMessage());
			return false;
		} 
		return true;
	}

	/**
	 * 
	 * methodName: recoverLinux
	 * 恢复数据库  (Linux操作系统时)
	 * date: 2018年1月24日 上午11:20:08 
	 * @param sqlPath 之前备份的sql文件全路径
	 * @return
	 */
	public static boolean recoverLinux(String sqlPath) {
		String command = "mysql -h " + ip + " -u" + username  
				+ " -p" + password + " --default-character-set=utf8 -D" + dbName +"<"+sqlPath;  
		try {
			Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",command});
			//Process process = Runtime.getRuntime().exec("sh/ "+command);//实际测试失败
			process.waitFor();
		} catch (IOException e1) {
			logger.error("Linux系统上执行恢复数据库时出现IO异常，异常信息为："+e1.getMessage()); 
			return false;
		} catch (InterruptedException e) {
			logger.error("Linux系统上执行恢复数据库时出现中断异常，异常信息为："+e.getMessage());
			return false;
		} 
		return true;
	}

	/**
	 * 
	 * methodName: backups
	 * 手动备份统一人口
	 * date: 2018年1月24日 上午9:58:49 
	 * @param delTime  保存的天数后自动删除
	 * @param rootPath 
	 * @author: liumy
	 */
	public static void backups(String delTime,String rootPath,String upfilePath) {  
		try {
			//String rootPathTemp = request.getSession().getServletContext().getRealPath("/");
			//String rootPath = rootPathTemp.replace("\\", "/").substring(0, rootPathTemp.length()-1);
			//判断当前操作系统的名称 D:/apache-tomcat-8.0.33/webapps/god-server
			String osName = System.getProperty("os.name").toLowerCase();
			rootPath = osName.contains("window") ? rootPath : File.separator+rootPath;
			//系统的统一备份文件夹;装文件备份的路径根路径
			String backupPathTemp = rootPath+File.separator+Config.backupsPath;
			//String backupPathTemp = rootPath+"-file";//装文件备份的路径根路径
			File file = new File(backupPathTemp);
			//当目录不存在则新建目录文件夹
			if(!file.exists()){
				file.mkdirs();
			}
			//当天日期，作为存放数据库的名字
			String newDate = format.format(new Date());
			//当天最新的文件夹
			//D:**\2017-06-06
			File folder = new File(backupPathTemp+File.separator+newDate);//在装文件的路径下在创建以时间为目录的文件夹
			if(!folder.exists()){
				folder.mkdir();
			}
			//进行数据备份
			if(osName.contains("window")){
				backupSql(folder.getPath());
			}else {
				backupLinux(folder.getPath(), dbName);
			}
			//系统的上传文件，进行备份操作
			//附件路径
			String mobileFile=upfilePath+File.separator+"mobile";
			//rootPath+File.separator+Config.uploadPath
			backUpFile(mobileFile, folder.getPath()+File.separator+Config.uploadPath);
			//如果没有时间就直接跳过，不在执行删除操作
			if(!"".equals(delTime) && null!=delTime){
				/*
				 * 删除保留天数之外的文件
				 * 获得备份下的文件夹下文件夹的数量
				 */
				String[] listfolder = file.list();
				//文件夹以时间命名；判断使用要删除文件夹的数量，大于预期时间
				if(listfolder.length>Integer.parseInt(delTime)){
					String min=listfolder[0];
					for(int i=1;i<listfolder.length;i++){
						if(compareDateStr(min,listfolder[i])){
							min=listfolder[i];
						}
					}
					listfolder = sortFromSmall(listfolder);
					//删除不符合时间文件夹
					for(int i=0;i<listfolder.length-Integer.parseInt(delTime);i++){
						//System.out.println(rootPath+"-file"+File.separator+listfolder[i]);
						File delFile = new File(rootPath+"-file"+File.separator+listfolder[i]);
						//System.out.println(delFile);
						if(delFile.exists()){
							delFile(delFile);
							delFile.delete();
						}	
					}

				}
			}
		} catch (Exception e) {  
			e.printStackTrace();
			logger.error("数据备份时出现异常，异常信息为："+e.getMessage());   
		}  
	}

	/**
	 * 
	 * methodName: backUpFile
	 * 备份系统上传过的文件
	 * date: 2018年1月23日 下午9:04:11 
	 * @param :  
	 * @author: liumy
	 * @param sourcePath  系统的上传文件地址  
	 * D:/apache-tomcat-8.0.33/webapps/god-server-uploadfile
	 * @param destinationPath  系统备份后的目标地址   
	 * D:\apache-tomcat-8.0.33\webapps\\god-server-file\\2018-02-05\\upload
	 * @throws FileNotFoundException
	 */
	public static void backUpFile(String sourcePath, String destinationPath) throws FileNotFoundException{  
		File sourceFile = new File(sourcePath);
		if(!sourceFile.exists()){
			sourceFile.mkdirs();
		}
		File deFile = new File(destinationPath);
		if(!deFile.exists()){
			deFile.mkdirs();
		}
		File[] fs=sourceFile.listFiles();  
		// 循环拷贝文件
		for (File f : fs) {  
			if(f.isFile()){  
				//调用文件拷贝的方法  
				copyFile(f,new File(destinationPath+File.separator+f.getName())); 
			}else if(f.isDirectory()){  
				//递归调用  创建文件夹
				backUpFile(f.getPath(),destinationPath+File.separator+f.getName());
			}  
		}
	}

	/**
	 * 
	 * methodName: restoreFile
	 * 系统文件还原
	 * date: 2018年1月24日 下午3:33:00 
	 * @param :  
	 * @param sourcePath 原系统路径
	 * @param destinationPath  备份后的路径
	 * @throws FileNotFoundException
	 * @author: liumy
	 */
	public static void restoreFile(String sourcePath, String destinationPath) throws FileNotFoundException{  
		File sourceFile = new File(sourcePath);
		if(!sourceFile.exists()){
			sourceFile.mkdirs();
			// throw new FileNotFoundException("文件不存在："+source);
		}
		File deFile = new File(destinationPath);
		if(!deFile.exists()){
			deFile.mkdirs();
		}
		File[] fs=deFile.listFiles(); 
		// 循环拷贝文件
		for (File f : fs) {  
			if(f.isFile()){  
				//调用文件拷贝的方法  
				copyFile(f,new File(sourcePath+File.separator+f.getName())); 
			}else if(f.isDirectory()){  
				//递归调用  创建文件夹
				restoreFile(sourcePath+File.separator+f.getName(),destinationPath+File.separator+f.getName());
			}  
		}
	}

	/**
	 * 
	 * methodName: backUpSql
	 * 备份数据库   执行的导出备份等
	 * date: 2018年1月23日 下午2:56:04 
	 * @param :  
	 * @author: liumy
	 * @param filePath  存放的路径
	 */
	public static void backupSql(String filePath) {  
		// D://MySQL Server 5.1//bin\
		if (!mysqlBinPath.endsWith(File.separator)) {  
			mysqlBinPath = mysqlBinPath + File.separator; 
		}
		//System.out.println(ip);
		String command = mysqlBinPath + "mysqldump -h "+ip+" -u" + username  
				+ " -p" + password + " --set-charset=utf8 " + dbName ;  
		OutputStream os=null ;
		PrintWriter pw = null ;  
		BufferedReader reader = null ;  
		try {  
			//拼成一个新的sql文件名字 ；
			File backuFile = new File(filePath+File.separator+dbName+".sql");
			//创建文件
			if(!backuFile.exists()){
				backuFile.createNewFile();
			}
			os = new FileOutputStream(backuFile);
			pw = new PrintWriter(new OutputStreamWriter(os, "utf8"));  
			Process process = Runtime.getRuntime().exec(command);  
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");  
			reader = new BufferedReader(inputStreamReader);  
			String line = null;  
			while ((line = reader.readLine()) != null) {  
				pw.println(line);  
			}  
			pw.flush();  
		} catch (UnsupportedEncodingException e) {  
			logger.error("备份数据库时出现不支持的编码格式异常，异常信息为："+e.getMessage());  
			throw new RuntimeException(e);
		} catch (IOException e) {  
			logger.error("备份数据库时出现IO异常，异常信息为："+e.getMessage());  
			throw new RuntimeException(e);
		} finally {  
			try {  
				if (reader != null) {  
					reader.close();  
				} 
				if (os != null) {  
					os.close();  
				} 
				if (pw != null) {  
					pw.close();  
				}  
			} catch (IOException e) {  
				logger.error("备份数据库时出现IO异常，异常信息为："+e.getMessage());  
			}  
		}  
	}  

	/** 
	 * 备份数据库 (Linux操作系统时)
	 *  
	 * @param output  输出流 
	 * @param dbname  要备份的数据库名 
	 */  
	public static boolean backupLinux(String dest, String dbname){
		File todayFolder = new File(dest);
		if(!todayFolder.exists()) {
			todayFolder.mkdirs();
		}

		String command = "mysqldump -h " + ip + " -u" + username  
				+ " -p" + password + " --set-charset=utf8 " + dbname + " > "+dest+File.separator+dbname+".sql"; 
		try {  
			//Process process = Runtime.getRuntime().exec(command); //实际在linux系统下执行失败 
			Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",command}); 
			process.waitFor();

		} catch (UnsupportedEncodingException e) { 
			logger.error("Linux系统上执行备份数据库时出现不支持的编码格式异常，异常信息为："+e.getMessage());  
			return false;
		} catch (IOException e) {  
			logger.error("Linux系统上执行备份数据库时出现IO异常，异常信息为："+e.getMessage());
			return false;
		} catch (InterruptedException e) {
			logger.error("Linux系统上执行备份数据库时出现中断异常，异常信息为："+e.getMessage());
			return false;
		} 
		return true;
	} 	

	/**
	 * 
	 * methodName: copyFile
	 * date: 2018年1月24日 下午3:40:55 
	 * @param :  
	 * @author: liumy
	 * @param source 文件原
	 * @param dest 目的地路径
	 */
	public static void copyFile(File source,File dest ){
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			if(!dest.exists()){
				dest.createNewFile();
			}
			//读取文件
			fi = new FileInputStream(source);
			//写文件
			fo = new FileOutputStream(dest);
			//获取通道
			in = fi.getChannel();
			//获取通道
			out = fo.getChannel();
			//连接2个通道，从in中读取写入out中
			in.transferTo(0, in.size(),out);
		} catch (FileNotFoundException e) {
			logger.error("复制文件时出现找不到指定文件的异常，异常信息为："+e.getMessage());
		} catch (IOException e) {
			logger.error("复制文件时出现IO异常，异常信息为："+e.getMessage());
		} finally {
			try {  
				if (fi != null) {  
					fi.close();  
				} 
				if (in != null) {  
					in.close();  
				} 
				if (fo != null) {  
					fo.close();  
				}  
				if (out != null) {  
					out.close();  
				}  
			} catch (IOException e) {  
				logger.error("备份数据库时出现IO异常，异常信息为："+e.getMessage());  
			} 
		}
	}

	/**
	 * 比较俩个日期格式字符串大小
	 * @param:
	 * @param:
	 * @throws ParseException 
	 * @author:louxiaolin
	 */
	public static boolean compareDateStr(String str1,String str2) throws ParseException{
		Date date1 = format.parse(str1);
		Date date2 = format.parse(str2);
		//date1在date2以后
		return date1.after(date2);
	}

	/**
	 * 
	 * methodName: sortFromSmall
	 * 时间小到大排序
	 * date: 2018年1月24日 下午6:14:03 
	 * @param :  
	 * @author: liumy
	 * @param args
	 * @return
	 * @throws ParseException
	 */
	public static String[] sortFromSmall(String[] args) throws ParseException{
		for(int i=0;i<args.length;i++){
			for(int j=i+1;j<args.length;j++){
				if(compareDateStr(args[i],args[j])){
					String str=args[i];
					args[i]=args[j];
					args[j]=str;
				}
			}
		}
		return args;
	}

	/**
	 * methodName: delFile
	 * 删除文件夹
	 * 
	 * @param:delFile
	 * @author:luoxiaolin
	 */
	public static void delFile(File delFile) {
		if(delFile.isDirectory()){
			File[] listFiles = delFile.listFiles();
			for(File file:listFiles){
				delFile(file);
			}
		}
		delFile.delete();
	}

}  