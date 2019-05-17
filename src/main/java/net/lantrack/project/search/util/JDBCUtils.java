package net.lantrack.project.search.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lantrack.framework.sysbase.util.AESUtils;


public class JDBCUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
	
	/**
	 * 驱动
	 */
	public static String driver;
	/**
	 * 数据库url
	 */
	public static String url;
	/**
	 * 用户
	 */
	public static String user;
	/**
	 * 密码
	 */
	public static String password;
	/**
	 * 数据库名称
	 */
//	private static String dbName;
	
	static {
		try {
			Properties props = new Properties();
			props.load(JDBCUtils.class.getClassLoader().getResourceAsStream("properties/dbjdbc.properties"));
			if (props.containsKey("jdbc.driverClassName")) {
				driver = props.getProperty("jdbc.driverClassName");
            }
			if (props.containsKey("jdbc.url")) {
				url = props.getProperty("jdbc.url");
			}
			if (props.containsKey("jdbc.username")) {
				user = props.getProperty("jdbc.username");
			}
			if (props.containsKey("jdbc.password")) {
				password = AESUtils.aesDecrypt(props.getProperty("jdbc.password"));
				System.out.println("数据库密码为:"+password);
			}
//			if (props.containsKey("jdbc.dbName")) {
//				dbName = props.getProperty("jdbc.dbName");
//			}
		} catch (Exception e) {
			logger.error("读取数据库配置文件失败："+e.getMessage());
		}
	}
	
 
	private static Connection conn;
 
	/**
	 * 私有构造方法，防止用户创建对象，浪费内存空间
	 */
	private JDBCUtils() {
 
	}
 
 
	/**
	 * 定义一个静态方法,用于获取数据库连接对象Connection
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			/**
			 * 如果数据库连接失败，则不应该继续往下，抛出运行时异常给虚拟机，终止程序
			 */
			throw new RuntimeException("数据库连接失败！");
		}
		return conn;
	}
 
	/**
	 * 定义一个静态方法,用于释放资源
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 
}

