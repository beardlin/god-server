package net.lantrack.framework.core.datasource;

import com.alibaba.druid.pool.DruidDataSource;

import net.lantrack.framework.sysbase.util.AESUtils;

/**
 *   自己的DataSource    
 * @date 2019年1月9日
 */
@SuppressWarnings("serial")
public class CustomDataSource extends DruidDataSource{

	@Override
	public void setPassword(String password) {
		try {
			password = AESUtils.aesDecrypt(password);
		} catch (Exception e) {
			System.err.println("数据库密码解析失败");
		}		
		super.setPassword(password);
	}
}
