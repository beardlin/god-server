package net.lantrack.framework.sysbase.controller;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.Duty;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.DutyService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 职务管理Controller
 * 2018年1月17日
 * @author lin
 */
@Controller
@RequestMapping("duty")
public class DutyController extends BaseController{

	@Autowired
	DutyService dutyService; 


	/**
	 * 
	 * methodName: getPage
	 * 职务管理列表
	 * date: 2018年1月31日 下午3:47:04 
	 * @param :  
	 * @author: liumy
	 * @param entity
	 * @param info
	 * @param rt
	 * @return
	 */
	@RequestMapping(value = "getPage")
	public String getPage(Duty entity, PageEntity info, ReturnPage rt) {
		try {
			this.dutyService.getPage(entity, info);
			rt.setResult(info);
			rt.success("查询成功");
		} catch (Exception e) {
			rt.failed("查看失败："+e.getMessage());
		}
		return "";
	}


	//添加职务  duty/save.json?表单参数
	@RequestMapping("save")
	@LogDesc(value="添加职务",type=LogType.ADD,modelClass=Duty.class)
	public String save(Duty entity, String menus, ReturnEntity info) {
		if (!authPermission(info,"sys:duty:save")) {
            return "";    
        }
		try {
			entity = this.dutyService.save(entity, menus);
			info.setResult(entity);
		} catch (Exception e) {
			info.failed("添加失败："+e.getMessage());
		}
		return "";
	}
	//修改职务  duty/update.json?表单参数
	@RequestMapping("update")
	@LogDesc(value="修改职务",type=LogType.UPDATE,modelClass=Duty.class)
	public String update(Duty entity, String menus, ReturnEntity info) {
		if (!authPermission(info,"sys:duty:update")) {
            return "";    
        }
		if (entity.getId() == null) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return "";
		}
		try {
			this.dutyService.update(entity, menus);
		} catch (Exception e) {
			info.failed("修改失败："+e.getMessage());
		}
		return "";
	}
	/**
	 * 
	 * methodName: delete
	 * 删除职务
	 * duty/delete.json?id=1,2,3
	 * @param :  
	 * @param id
	 * @param info
	 * @return
	 * date: 2018年2月1日 上午10:40:01 
	 */
	@RequestMapping("delete")
	@LogDesc(value="删除职务",type=LogType.DELETE)
	public String delete(String id,ReturnEntity info) {
		if (!authPermission(info,"sys:duty:delete")) {
            return "";    
        }
		if(StringUtils.isBlank(id)){
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return "";
		}
		try {
			this.dutyService.deleteByIds(id, UserUtil.getCurrentUser(), false);
		} catch (Exception e) {
			info.failed("删除失败："+e.getMessage());
		}
		return "";
	}

	//配置权限   duty/addPermission.json?id=1&menus=1,2,3,4,5
	@RequestMapping("addPermission")
	@LogDesc(value="配置职务权限",type=LogType.UPDATE)
	public String addPermission(Integer id,String menus,ReturnEntity info){
		if(StringUtils.isBlank(menus)){
			info.failed("请勾选要配置的权限");
			return "";
		}
		try {
			this.dutyService.addPermission(id, menus);
		} catch (Exception e) {
			info.failed("配置失败："+e.getMessage());
		}
		return "";
	}
	//查看当前部门下职务   duty/queryDutyByOffice.json?id=7
	@RequestMapping("queryDutyByOffice")
	public String queryDutyByOffice(String id,ReturnEntity info){
		if(StringUtils.isBlank(id)){
			info.failed("id 不能为空");
			return "";
		}
		try {
			List<Duty> dutys = this.dutyService.getDutyByOfficeId(id);
			info.setResult(dutys);
		} catch (Exception e) {
			info.failed("查看失败："+e.getMessage());
		}
		return "";
	}
	//查看当前职务的权限   duty/queryMenuByDuty.json?id=7
	@RequestMapping("queryMenuByDuty")
	public String queryMenuByDuty(Integer id,ReturnEntity info){
		if(id==null){
			info.failed("id 不能为空");
			return "";
		}
		try {
			Map<String, Object> list = this.dutyService.getMenuTreeByDutyId(id);
			info.setResult(list);
		} catch (Exception e) {
			info.failed("查看失败："+e.getMessage());
		}
		return "";
	}
	/**
	 * 
	 * methodName: dutyDetail
	 * 查看职务详情
	 * duty/detail.json?id=10
	 * date: 2018年1月31日 下午3:47:49 
	 * @param :  
	 * @author: liumy
	 * @param id 
	 * @param info
	 * @return
	 */
	@RequestMapping("dutyDetail")
	public String dutyDetail(Integer id, ReturnEntity info) {
		if (!authPermission(info,"sys:duty:view")) {
            return "";    
        }
		if(id == null){
			info.failed("id 不能为空");
			return "";
		}
		try {
			Duty duty = this.dutyService.queryById(id);
			Map<String, Object> map = this.dutyService.getMenuTreeByDutyId(id);
			if (duty != null && map != null) {
				map.put("duty", duty);
				info.setResult(map);
				info.success("查询成功");
			} else {
				info.failed("暂无数据");
			}
		} catch (Exception e) {
			info.failed("查看失败："+e.getMessage());
		}
		return "";
	}


}
