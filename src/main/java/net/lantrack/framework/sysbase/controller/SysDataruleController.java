package net.lantrack.framework.sysbase.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysDatarule;
import net.lantrack.framework.sysbase.service.SysDataruleService;

@RestController
@RequestMapping("/datarule")
public class SysDataruleController extends BaseController{

	@Autowired
	SysDataruleService sysDataruleService;

	
	
	//  获取数据规则树 datarule/tree  
	@RequestMapping("/tree")
	private ReturnEntity tree(ReturnEntity info) {
		try {
			info.setResult(this.sysDataruleService.dataruleTree());
		} catch (Exception e) {
			info.failed("查询失败！",e.getMessage());
		}
		return info;
	}
	
	//  删除数据规则 datarule/delete  
	@RequestMapping("/delete")
	private ReturnEntity delete(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if(authPermission(info, "btn:sys:datarule:delete")) {
//			return info;
//		}
		String ids = parms.get("ids");
		try {
			this.sysDataruleService.delete(ids);
			info.success("删除成功");
		} catch (Exception e) {
			info.failed("删除失败:",e.getMessage());
		}
		return info;
	}
	
	//  查看数据规则 datarule/detial  
	@RequestMapping("/detial")
	private ReturnEntity detial(@RequestBody Map<String, String> parms, ReturnEntity info) {
		String id = parms.get("id");
		try {
			SysDatarule detial = this.sysDataruleService.detial(Integer.valueOf(id));
			if(detial==null) {
				throw new ServiceException("数据不存在");
			}
			info.setResult(detial);
		} catch (Exception e) {
			info.failed("查看失败:",e.getMessage());
		}
		return info;
	}
	
	//  修改数据规则 datarule/update  
	@RequestMapping("/update")
	private ReturnEntity update(@RequestBody String json, ReturnEntity info) {
//		if(authPermission(info, "btn:sys:datarule:update")) {
//			return info;
//		}
		try {
			SysDatarule rule = toObject(json, SysDatarule.class);
			this.sysDataruleService.update(rule);
			info.success("修改成功");
		} catch (Exception e) {
			info.failed("修改失败:",e.getMessage());
		}
		return info;
	}
	
	//  添加数据规则 datarule/save  
	@RequestMapping("/save")
	private ReturnEntity save(@RequestBody String json, ReturnEntity info) {
//		if(authPermission(info, "btn:sys:datarule:save")) {
//			return info;
//		}
		try {
			SysDatarule rule = toObject(json, SysDatarule.class);
			this.sysDataruleService.save(rule);
			info.setResult(rule.getId());
			info.success("添加成功");
		} catch (Exception e) {
			info.failed("添加失败:",e.getMessage());
		}
		return info;
	}
	

	//  数据规则列表  datarule/list  
	@RequestMapping("/list")
	private ReturnEntity list(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if(authPermission(info, "menu:sys:datarule:list")) {
//			return info;
//		}
		String menuid = parms.get("menuid");
		if(StringUtils.isBlank(menuid)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			List<SysDatarule> list = this.sysDataruleService.list(Integer.valueOf(menuid));
			info.setResult(list);
		} catch (Exception e) {
			info.failed("添加失败:",e.getMessage());
		}
		return info;
	}
	
}
