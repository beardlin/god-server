package net.lantrack.framework.sysbase.controller;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.core.util.FormatHandler;
import net.lantrack.framework.shiro.Principal;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysLog;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.log.LogIdsModel;
import net.lantrack.framework.sysbase.model.log.LogModel;
import net.lantrack.framework.sysbase.service.SysLogService;
import net.lantrack.framework.sysbase.util.UserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 操作日志Controller
 * 2018年1月12日
 * @author hww
 */
@RestController
@RequestMapping(value = "/sysLog")
public class SysLogController extends BaseController {
	
	@Autowired
	private SysLogService sysLogService;
	
	 
	//获取日志类型   sysLog/logtype
	@RequestMapping("/logtype")
	public ReturnEntity getLogType(ReturnEntity info) {
		LogType[] values = LogType.values();
		List<MapEntity> list = new ArrayList<>(values.length);
		for(LogType type:values) {
			MapEntity element = new MapEntity();
			element.setK(type.getType());
			element.setV(type.getRemarks());
			list.add(element);
		}
		info.setResult(list);
		return info;
	}
	
	/**
	 * 分页查询操作日志列表  
	 * @param entity SysLog类型
	 * @param pageentity PageEntity类型
	 * @return
	 * sysLog/getPage
	 */
	@RequestMapping("/getPage")
	public ReturnPage getPage(@RequestBody String json,ReturnPage rt ){
//		if(!authPermission("menu:sys:log:page")) {
//			rt.setStatus(StatusCode.NOPERMISS_ERROR);
//			return rt;
//		}
		try {
			SysLog entity = toObject(json, SysLog.class);
			PageEntity info = toObject(json, PageEntity.class);
			if (StringUtils.isBlank(info.getOrderField())) {
				info.setOrderField("create_date");
			}
			if(StringUtils.isNotBlank(entity.getUserId())) {
				SysUser sysUser = UserUtil.get(entity.getUserId());
				if(sysUser!=null) {
					entity.setCreateBy(sysUser.getLoginName());
				}
			}
			this.sysLogService.getPage(entity, info);
			rt.setResult(info);
		} catch (Exception e) {
			e.printStackTrace();
			rt.setStatus(StatusCode.SERVER_ERROR);
		}
		return rt;
	}
	
	/**
	 * 根据id删除当前节点区域
	 * @param id 当前节点id Integer类型
	 * @param info ReturnEntity类型
	 * @return
	 * sysLog/deleteByIds
	 */
	@LogDesc(value ="删除日志", modelClass=LogIdsModel.class, type=LogType.DELETE)
	@RequestMapping(value = "deleteByIds")
	public ReturnEntity deleteByIds(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:log:delete")) {
//            return info;    
//        }
		String ids = parms.get("ids");
		if (StringUtils.isNotBlank(ids)) {
			ids = FormatHandler.formatIds(ids);
			this.sysLogService.deleteByIds(ids, UserUtil.getCurrentUser(), true);
			info.success("删除成功");
		} else {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return info;
	}
	
	
	/**
	 * 查看一个操作日志详情
	 * @param entity SysLog类型
	 * @param info 返回的结果对象
	 * sysLog/detail.json?id=
	 * @return
	 */
	@RequestMapping(value ="detail")
	public ReturnEntity detail(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:log:view")) {
//            return info;    
//        }
		String id = parms.get("id");
		if (StringUtils.isNotBlank(id)) {
			LogModel model = this.sysLogService.queryModelById(Long.valueOf(id));
			String userName = UserUtil.getByLoginName(model.getCreateBy()).getUserName();
			model.setCreateUser(userName);
			info.setResult(model);
		}else{
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return info;
	}
	/**
	 * 清空日志           sysLog/clear.json
	 * @param info
	 * @return
	 * 2018年1月24日
	 * @author lin
	 */
	@RequestMapping("clear")
	public ReturnEntity clearLog(ReturnEntity info) {
//		if (!authPermission(info, "btn:sys:log:clear")) {
//			return info;
//		}
		Principal principal = UserUtil.getPrincipal();
	    if (UserUtil.currentUserIsAdmin.equals(principal.getIfAdmin())) {
	        try {
	            sysLogService.clearLog();
            } catch (Exception e) {
                info.failed("清空失败："+e.getMessage());
            }
	    } else {
	        info.setStatus(StatusCode.NOPERMISS_ERROR);
	    }
	    return info;
	}
	
	
	
}
