package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.sysbase.entity.SysConfig;

/**
 * 系统配置变量
 *       
 * @date 2019年1月18日
 */
public interface SysConfigService {
	

	/**
	 * 更新配置信息批量
	 * @param config
	 * @date 2019年1月18日
	 */
	void updateConfigList(List<SysConfig> configs);
	/**
	 * 更新配置信息
	 * @param config
	 * @date 2019年1月18日
	 */
	void updateConfig(SysConfig config);
	
	/**
	 * 查看指定配置项
	 * @param name
	 * @return
	 * @date 2019年1月18日
	 */
	SysConfig getConfigByName(String name);

	/**
	 * 查看系统配置变量数据
	 * @return
	 * @date 2019年1月18日
	 */
	List<SysConfig> getConfigList();
}
