package net.lantrack.framework.sysbase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.service.SysConfigService;

@RestController
@RequestMapping("/sysconfig")
public class SysConfigController extends BaseController {

	@Autowired
//	@Qualifier("sysConfigService")
	private SysConfigService sysConfigService;
	
	
	//获取配置信息   sysconfig/get
	@RequestMapping("/get")
	public ReturnEntity configGet(@RequestBody Map<String, String> parms,ReturnEntity info) {
//		if(!authPermission(info, "btn:sys:config:get")) {
//			return info;
//		}
		try {
			String name = parms.get("name");
			SysConfig sysConfig = this.sysConfigService.getConfigByName(name);
			info.setResult(sysConfig);
		} catch (Exception e) {
			info.failed("获取信息失败");
		}
		return info;
	}
	
	//获取配置列表  sysconfig/list
	@RequestMapping("/list")
	public ReturnEntity configList(ReturnEntity info) {
//		if(!authPermission(info, "menu:sys:config:list")) {
//			return info;
//		}
		try {
			List<SysConfig> configList = this.sysConfigService.getConfigList();
			info.setResult(configList);
		} catch (Exception e) {
			info.failed("获取信息失败");
		}
		return info;
	}
	
	//修改配置信息  sysconfig/update
	@RequestMapping("/update")
	public ReturnEntity configUpdate(@RequestBody List<SysConfig> conf,ReturnEntity info) {
//		if(!authPermission(info, "btn:sys:config:update")) {
//			return info;
//		}
		try {
			this.sysConfigService.updateConfigList(conf);
			info.success("修改成功");
		} catch (Exception e) {
			info.failed("修改失败");
		}
		return info;
	}
}
