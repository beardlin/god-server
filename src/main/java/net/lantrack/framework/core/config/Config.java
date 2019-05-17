package net.lantrack.framework.core.config;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lantrack
 */
public class Config {

	private static Logger logger = LoggerFactory.getLogger(Config.class);
    /**
     * 显示
     */
    public static final String SHOW = "1";
    /**
     * 隐藏
     */
    public static final String HIDE = "0";
    /**
     * 是
     */
    public static final String YES = "1";
    /**
     * 否
     */
    public static final String NO = "0";
    /**
     * 对
     */
    public static final String TRUE = "true";
    /**
     * 错
     */
    public static final String FALSE = "false";
    
    /**
     * 系统文件保存根路径 部署时根据config.properties配置修改
     */
    public static String redirectPath = "D:\\lantrack\\sysfile";

    /**
     * webAppName
     */
    public static String webAppName = "god";
    /**
     * 附件上传保存地址
     */
    public static String uploadPath = "upload";
    
    /**
     * 资料库上传保存地址
     */
    public static String resourcePath = "filelib";
    
    /**
     * 系统临时文件地址
     */
    public static String tempPath = "temp";
    
    /**
     * 系统 备份 路径
     */
    public static String backupsPath = "backups";
   
    /**
     * 编码类型
     */
    public static String defaultTemplateEncoding = "UTF-8";
   
    /**
     * 系统静态资源
     */
    public static String staticFile = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.htm,.html,.crx,.xpi," +
            ".exe,.ipa,.apk";
    /**
     * 系统上传要求最大值
     */
    public static long maxUploadSize = 10 * 1024 * 1024;
    /**
     * 系统禁止上传的文件
     */
    public static String forbiddenUpfile = ".sh,.bat,.dll,.exe";
    /**
     * 是否允许用户同设备同时登录,
     */
    public static String usermultiAccountLogin = "true";
    /**
     * 动态映射URL后缀
     */
    public static String suffix = ".jsp";
    /**
     * 前缀
     */
    public static String prefix = "/";
    
    /**
     * sql注入拦截字段
     */
    public static String sqlInject = "or,',\",*,%,select,update,delete,insert,alter,and,table,show";
    

    
    static {

        try {
            Properties props = new Properties();
//            props.load(Config.class.getResourceAsStream("../../../../../properties/config.properties"));
            props.load(Config.class.getClassLoader().getResourceAsStream("properties/config.properties"));
            //系统常量
            if (props.containsKey("redirectPath")) {
            	redirectPath = props.getProperty("redirectPath");
            }
            if (props.containsKey("webAppName")) {
            	webAppName = props.getProperty("webAppName");
            }
            if (props.containsKey("uploadPath")) {
            	uploadPath = props.getProperty("uploadPath");
            }
            if (props.containsKey("resourcePath")) {
            	resourcePath = props.getProperty("resourcePath");
            }
            if (props.containsKey("backupsPath")) {
            	backupsPath = props.getProperty("backupsPath");
            }
            if (props.containsKey("staticFile")) {
                staticFile = props.getProperty("staticFile");
            }
            if (props.containsKey("maxUploadSize")) {
                maxUploadSize = Long.parseLong(props.getProperty("maxUploadSize"));
            }
            if (props.containsKey("usermultiAccountLogin")) {
                usermultiAccountLogin = props.getProperty("usermultiAccountLogin");
            }
            if (props.containsKey("forbidden_upfile")) {
            	forbiddenUpfile = props.getProperty("forbidden_upfile");
            }
            

            //spring 视图前缀与后缀
            if (props.containsKey("suffix")) {
                suffix = props.getProperty("suffix");
            }
            if (props.containsKey("prefix")) {
                prefix = props.getProperty("prefix");
            }
            
            if (props.containsKey("sqlInject")) {
            	sqlInject = props.getProperty("sqlInject");
            }
            uploadPath = redirectPath+File.separator+webAppName+File.separator+uploadPath;
            resourcePath = redirectPath+File.separator+webAppName+File.separator+resourcePath;
            backupsPath = redirectPath+File.separator+webAppName+File.separator+backupsPath;
            tempPath = redirectPath+File.separator+webAppName+File.separator+tempPath;
        } catch (IOException e) {
        	logger.error("获取Config配置信息失败："+e.getMessage());
        }
    }

}