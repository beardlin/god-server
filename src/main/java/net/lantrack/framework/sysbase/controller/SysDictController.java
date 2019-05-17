package net.lantrack.framework.sysbase.controller;


import java.util.Map;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysDict;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.SysDictService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典管理Controller
 * @author lin
 */
@RestController
@RequestMapping(value = "sysDict")
public class SysDictController extends BaseController {

	@Autowired
	private SysDictService sysDictService;

	//查看字典详情    sysDict/sysDictDetail.json?id=3
	@RequestMapping(value = "sysDictDetail")
	public ReturnEntity sysDictDetail(@RequestBody Map<String, String> parms,  ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:dict:view")) {
//            return info;    
//        }
		String id = parms.get("id");
		if (StringUtils.isBlank(id)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);   
			return info;
		}
		try {
			SysDict dict = this.sysDictService.queryById(id);
		    if(dict!=null){
		        info.setResult(dict);
		        info.success("查询成功");
		    }else{
		        info.failed("暂无数据");
		    }
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
		return info;
	}

	//添加字典    sysDict/sysDictSave.json?entity=3
	@RequestMapping(value = "sysDictSave")
	@LogDesc(value="添加字典", type=LogType.ADD)
	public ReturnEntity save(@RequestBody String json, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:dict:save")) {
//            return info;    
//        }
	    try {
	    	SysDict entity = toObject(json, SysDict.class);
			if(!beanValidator(info, entity)) {
				return info;
			}
			entity = this.sysDictService.save(entity);
	        info.success("添加成功");
	        info.setResult(entity);
        } catch (Exception e) {
            info.failed("添加失败:"+e.getMessage());
        }
		return  info;
	}
	
	//字典修改    sysDict/sysDictUpdate.json?entity=3
	@RequestMapping(value = "sysDictUpdate")
	@LogDesc(value="修改字典", type=LogType.UPDATE)
	public ReturnEntity update(@RequestBody String json, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:dict:update")) {
//            return info;    
//        }
	    try {
	    	SysDict entity = toObject(json, SysDict.class);
	    	if(!beanValidator(info, entity)) {
				return info;
			}
	        this.sysDictService.update(entity);
	        info.success("修改成功");
        } catch (Exception e) {
            info.failed("修改失败:"+e.getMessage());
        }
		return info;
	}
	
	//字典列表    sysDict/getPage.json?entity=3
	@RequestMapping(value = "getPage")
	public ReturnPage getPage(@RequestBody String json,ReturnPage rt) {
//		if(!authPermission("menu:sys:dict:page")) {
//			rt.setStatus(StatusCode.NOPERMISS_ERROR);
//			return rt;
//		}
		try {
			SysDict entity = toObject(json, SysDict.class);
			PageEntity page = toObject(json, PageEntity.class);
		    this.sysDictService.getPage(entity, page);
		    rt.setResult(page);
        } catch (Exception e) {
            rt.failed("查看失败!");
        }
		return rt;
	}

	//批量删除    sysDict/sysDictDelete.json?id=4,5,6
	@RequestMapping(value = "sysDictDelete")
	@LogDesc(value="删除字典", type=LogType.DELETE)
	public ReturnEntity deleteByIds(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:dict:delete")) {
//            return info;    
//        }
		String ids = parms.get("ids");
		if (StringUtils.isBlank(ids)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			this.sysDictService.deleteByIds(ids, UserUtil.getCurrentUser(),false);
		} catch (Exception e) {
			info.failed("删除失败");
		}
		return info;
	}
	

	//获取所有字典类型   sysDict/getDictType.json
	@RequestMapping(value = "getDictType")
    public ReturnEntity getDictType(ReturnEntity info) {
		Map<String, String> map = this.sysDictService.getDictTypeAll();
        info.setResult(map);
        return info;
    }
	
	//一个页面里所有下拉框字典集合 sysDict/getDictMapByTypes.json?datajson={"type":"area_type,sex_type"}
//	@RequestMapping(value = "getDictMapByTypes")
//	public ReturnEntity getDictMapByTypes(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		String types = parms.get("type");
//		if(StringUtils.isBlank(types)) {
//			info.setStatus(StatusCode.PARAMETER_ERROR);
//			return info;
//		}
//		
//		try {
//			Map<String, Map<String, String>> map = new LinkedHashMap<String, Map<String,String>>();
//			String[] typeArr = types.split(",");
//			for (int i=0; i<typeArr.length; i++) {
//				String type = typeArr[i];
//				SysDict entity = new SysDict();
//				entity.setType(type);
//				Map<String, String> dictMap = this.sysDictService.getDictMap(entity, 1);
//				map.put(type, dictMap);
//			}
//			info.setResult(map);
//		} catch (Exception e) {
//			info.setResult(StatusCode.SERVER_ERROR);
//		}
//		return info;
//	}

}
