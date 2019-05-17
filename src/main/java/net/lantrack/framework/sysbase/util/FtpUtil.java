package net.lantrack.framework.sysbase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lantrack.framework.core.util.PathUtil;


/**
 * FTP工具类
 * @date 2019年4月17日
 */
public class FtpUtil {
    
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    
    private volatile static FTPClient ftp;
    
	private static String ipAddr;//ip地址
    
    private static Integer port;//端口号
    
    private static String userName;//用户名
    
    private static String password;//密码
    
    static {
    	Properties pro = new Properties();
    	try {
    		pro.load(FtpUtil.class.getClassLoader().getResourceAsStream("properties/config.properties"));
			ipAddr = pro.getProperty("FTPServer_ip");
			String property = pro.getProperty("FTPServer_port","21");
			port = Integer.valueOf(property);
			userName = pro.getProperty("FTPServer_userName");
			password = pro.getProperty("FTPServer_password");
		} catch (IOException e) {
			logger.error("加载FTP配置文件失败");
		}
    }
    
    private FtpUtil() {
    	
    }
    /**
     * 获取FTP服务器客户端
     * @return
     * @throws SocketException
     * @throws IOException
     * @date 2019年4月17日
     */
    private  static FTPClient getFTPClient() throws SocketException, IOException {
    	if(ftp==null) {
    		synchronized (FtpUtil.class) {
        		if(ftp==null) {
            		ftp = new FTPClient();
            	}
    		}
    	}
    	return ftp;
    }
    
    /**
     * 获取ftp连接
     * @param f
     * @return
     * @throws Exception
     */
    private static boolean connect() throws Exception{
    	getFTPClient();
        boolean flag=false;
        int reply;
        ftp.connect(ipAddr,port);
        ftp.login(userName, password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();      
        if (!FTPReply.isPositiveCompletion(reply)) {      
              ftp.disconnect();      
              return flag;      
        }      
        flag = true;      
        return flag;
    }
    
    /**
     * 关闭ftp连接
     */
    public static void closeFtp(){
        if (ftp!=null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

    
    /**
     * 文件删除
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean delete(String fileName) throws Exception {
    	if(!connect() || StringUtils.isBlank(fileName)) {
    		return false;
    	}
    	fileName = PathUtil.formatUrlPath(fileName);
    	if(!fileName.startsWith("/")) {
    		fileName = "/"+fileName;
    	}
    	return ftp.deleteFile(fileName);
    }
    /**
     * FTP文件下载
     * @param fileName  文件地址
     * @param local    输出流
     * @return
     * @throws IOException
     */
    public static boolean download(String fileName,OutputStream local) throws Exception {
    	if(!connect() || StringUtils.isBlank(fileName)) {
    		return false;
    	}
    	fileName = PathUtil.formatUrlPath(fileName);
    	if(!fileName.startsWith("/")) {
    		fileName = "/"+fileName;
    	}
    	return ftp.retrieveFile(fileName, local);
    }
    
    /**
     * 获取文件输入流
     * @param fileName
     * @return
     * @throws Exception
     * @date 2019年4月18日
     */
    public static InputStream getStream(String fileName) throws Exception {
    	if(!connect() || StringUtils.isBlank(fileName)) {
    		return null;
    	}
    	fileName = PathUtil.formatUrlPath(fileName);
    	if(!fileName.startsWith("/")) {
    		fileName = "/"+fileName;
    	}
    	return ftp.retrieveFileStream(fileName);
    }
    
    /**
     * FTP文件上传
     * @param uploadPath  上传地址 
     * @param inputStream 输入流
     * @return
     * @throws Exception 
     * @date 2019年4月17日
     */
    public static boolean upload(String uploadPath,InputStream input) throws Exception {
    	if(!connect() || StringUtils.isBlank(uploadPath)) {
    		return false;
    	}
    	uploadPath = PathUtil.formatUrlPath(uploadPath);
    	if(uploadPath.startsWith("/")) {
    		uploadPath = uploadPath.replaceFirst("/", "");
    	}
    	String[] split = uploadPath.split("/");
    	int last = split.length-1;
    	for(int i=0;i<last;i++) {
    		ftp.makeDirectory(split[i]);
    		ftp.changeWorkingDirectory(split[i]);
    	}
    	return ftp.storeFile(split[last], input);
    }
    /**
     * 在FTP指定位置建立文件夹
     * @param dirParh
     * @return
     * @throws Exception
     * @date 2019年4月20日
     */
    public static boolean mkdir(String dirParh) throws Exception {
    	if(!connect() || StringUtils.isBlank(dirParh)) {
    		return false;
    	}
    	dirParh = PathUtil.formatUrlPath(dirParh);
    	if(dirParh.startsWith("/")) {
    		dirParh = dirParh.replaceFirst("/", "");
    	}
    	String[] split = dirParh.split("/");
    	for(int i=0;i<split.length;i++) {
    		ftp.makeDirectory(split[i]);
    		boolean directory = ftp.changeWorkingDirectory(split[i]);
    		if(!directory) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * 文件重命名
     * @param from
     * @param to
     * @return
     * @throws Exception
     * @date 2019年4月20日
     */
    public static boolean rename(String from,String to) throws Exception {
    	if(!connect() || StringUtils.isBlank(from) 
    			||StringUtils.isBlank(to)) {
    		return false;
    	}
    	return ftp.rename(from, to);
    }

    /**
     * 删除文件夹
     * @param dir
     * @throws Exception 
     * @date 2019年4月20日
     */
    public static void deleteDir(String dir) throws Exception {
    	if(!connect() || StringUtils.isBlank(dir)) {
    		return;
    	}
    	dir = PathUtil.formatUrlPath(dir);
    	if(dir.endsWith("/")) {
    		dir = dir.substring(0, dir.length()-1);
    	}
    	FTPFile[] listFiles = ftp.listFiles(dir);
    	for(FTPFile ftpFile:listFiles) {
    		if(ftpFile.isDirectory()) {
    			deleteDir(dir+"/"+ftpFile.getName());
    		}else {
    			delete(dir+"/"+ftpFile.getName());
    		}
    	}
    	ftp.removeDirectory(dir);
    	
    }
    
    
    
    public static void main(String[] args) throws Exception{  
    	
//    	deleteDir("lantrack/god");
//    	System.out.println(mkdir("lantrack/god/filelib"));
//    	File file = new File("D:/阿里巴巴Java开发手册终极版v1.3.0.pdf");
    	File file = new File("D:/api.html");
//    	boolean upload = upload("/lantrack/god/Java-Codind-Guidelines.pdf", new FileInputStream(file));
    	boolean upload = upload("/lantrack/god/godapi.html", new FileInputStream(file));
    	System.out.println(upload);
//    	download("lantrack/god/upload/hello.txt", new FileOutputStream(file));
//    	delete("lantrack/god/upload/hello.txt");
//    	boolean removeDirectory = ftp.removeDirectory("/lantrack/god/upload");
//    	System.out.println(removeDirectory);
//            File dir = new File("D:/lxl/test/hello");
//            dir.mkdirs();
//            File file = new File("D:/lxl/test/hello/aaa.docx");
//            file.createNewFile();
//            System.out.println(file.getPath());
//            System.out.println(file.getParent());
//            String path = File.separator+"hello"+File.separator+"aaa";
//            ftp.changeWorkingDirectory(PathUtil.formatUrlPath(path));
//            System.out.println(PathUtil.formatUrlPath(path)+"---"+ftp.printWorkingDirectory());
//            File file = new File("D:/ftp.txt");  
//            ftp.storeFile("aaa.txt", new FileInputStream(file));
//          	OutputStream out = new FileOutputStream(new File("D:/hello1.txt"));
//          	boolean retrieveFile = ftp.retrieveFile("/hello/aaa/ftp.txt", out);
//          	System.out.println(retrieveFile);
//          	FTPFile mdtmFile = ftp.mdtmFile("/hello/aaa/ftp.txt");
//          	System.out.println(mdtmFile);
//            boolean deleteFile = ftp.deleteFile("/hello/test.txt");
//            System.out.println(deleteFile);
           
    }  
    
    
}