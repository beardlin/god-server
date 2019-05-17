package net.lantrack.framework.core.util;


import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * 格式化url      
 * @date 2018年8月10日
 */
public class PathUtil {
	
	public static void main(String[] args) {
		String path="/lantrack\\hrms";
		System.out.println(formatFilePath(path));
		System.out.println(formatUrlPath(path));
	}
	
	
   /**
     * 如果文件夹开头path不为/,则拼接上
     * @param path
     */
    public static String formatPrefix(String path){
        if (!path.startsWith(File.separator)) {  
            path = File.separator + path;  
        }
        return path;
    }
    /**
     * 如果文件夹结尾不为/,则拼接上
     * @param path
     */
    public static String formatsuffix(String path){
        if (!path.endsWith(File.separator)) {  
            path = path + File.separator;  
        }
        return path;
    }
	
	/**
	 * 获取系统绝对路径
	 * @param request
	 */
	public static String getSysRootPath() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = applicationContext.getServletContext();
		String ctxpath = servletContext.getContextPath();
		String realPath = servletContext.getRealPath("/");
		return realPath.replace(ctxpath, "");
	}
	
	/**
	 * 格式化浏览器url路径将'\'格式化为'/'
	 * @param path
	 * @return
	 * @date 2018年8月10日
	 */
	public static String formatUrlPath(String path) {
		if(path!=null) {
			path = path.replace("\\", "/");
		}
		return path;
	}

	/**
	 * 格式化文件路径将'/'格式化为'\'
	 * @param path
	 * @return
	 * @date 2018年8月10日
	 */
	public static String formatFilePath(String path) {
		if(path!=null) {
			if(ifWindows()) {
				path = path.replace("/", "\\");
			}else {
				path = path.replace("\\", "/");
			}
		}
		return path;
	}
	
	
	public static boolean ifWindows() {
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("window")) {
			return true;
		}else {
			return false;
		}
	}
}
