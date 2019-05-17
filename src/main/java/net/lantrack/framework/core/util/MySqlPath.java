package net.lantrack.framework.core.util;

import java.io.File;
import java.util.Map;

public class MySqlPath {

	/**
	 * 
	 * methodName: getMysqlPath
	 * 获取mysql安装路径
	 * date: 2018年1月23日 上午11:28:17 
	 * @param :  
	 * @author: liumy
	 * @return
	 */
	public static String getMysqlPath(){
		String mySqlPath = null;
		Map<String, String> map = System.getenv();
		if(map != null) {
			//获取本计算机环境变量中Path的内容
			String pathTemp = (String) map.get("PATH");
			if(pathTemp!=null && pathTemp.indexOf("MySQL Server")!=-1) {
				//截取索引从"0"到"MySQL Server"字符串
				String mySQLServerPtathTemp = pathTemp.substring(0, pathTemp.indexOf("MySQL Server"));
				if(mySQLServerPtathTemp!=null) {
					//获取S1字符串中最后一个":"的索引，然后截取从":"-1 索引处到最后的字符串
					String binPathTemp = pathTemp.substring(mySQLServerPtathTemp.lastIndexOf(":")-1, pathTemp.length());
					if(binPathTemp!=null) {
						//截取字符串S3 从索引"0" 到"bin"的字符串。获取完整的mysql安装路径
						mySqlPath = binPathTemp.substring(0,binPathTemp.indexOf("bin")+3);
						if(mySqlPath.indexOf("\\")!=-1) {
							mySqlPath = mySqlPath.replace("\\", "/");
						}
					}
				}
			}else {
				//获取本计算机环境变量中MYSQL_HOME的内容
				String mysqlhome = (String) map.get("MYSQL_HOME");
				mySqlPath = mysqlhome+File.separator+"bin";
				if(mySqlPath.indexOf("\\")!=-1) {
					mySqlPath = mySqlPath.replace("\\", "/");
				}
			}
		}
		return mySqlPath;
	}
}
