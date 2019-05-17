package net.lantrack.framework.sysbase.controller;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.core.service.MapEntityService;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysRole;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.SysRoleService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 角色管理Controller
 * 2018年1月19日
 * @author lin
 */
@RestController
@RequestMapping(value = "sysRole")
public class SysRoleController extends BaseController {


	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	@Qualifier("sysRoleServiceImp")
	private MapEntityService roleSelect;
	
	

	
	//获取所有角色复选框  sysRole/list
	@RequestMapping("list")
	private ReturnEntity listRole(ReturnEntity info) {
	    try {
	    	List<SysRole> list = this.sysRoleService.getRoleListByUserId(UserUtil.getCurrentUserId());
            info.setResult(list);
        } catch (Exception e) {
            info.failed("获取失败");
        }
	    return info;
	}
	
	//添加角色  sysRole/save
	@RequestMapping("save")
	@LogDesc(value="添加角色", modelClass=SysRole.class, type=LogType.ADD)
	private ReturnEntity addRole(@RequestBody SysRole role, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:role:save")) {
//            return info;    
//        }
	    try {
	        role = sysRoleService.save(role);
            info.success("添加成功");
            info.setResult(role);
        } catch (Exception e) {
            info.failed("添加失败:"+e.getMessage());
        }
	    return info;
	}
	//查看角色详情    sysRole/sysRoleDetail
    @RequestMapping(value = "sysRoleDetail")
    public ReturnEntity sysRoleDetail(@RequestBody Map<String, String> prams, ReturnEntity info) {
//    	if (!authPermission(info,"btn:sys:role:view")) {
//            return info;    
//        }
    	try {
    		String id = prams.get("id");
            if (id != null) {
                SysRole role = this.sysRoleService.queryById(id);
                if (role != null) {
                    info.setResult(role);
                    info.success("查询成功");
                } else {
                    info.failed("暂无数据");
                }
            } else {
                info.setStatus(StatusCode.PARAMETER_ERROR);
                info.appendMessage("id不能为空");
            }
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
        return info;
    }
    
    //角色修改    sysRole/sysRoleUpdate
    @RequestMapping(value = "sysRoleUpdate")
    @LogDesc(value="修改角色", modelClass=SysRole.class, type=LogType.UPDATE)
    public ReturnEntity update(@RequestBody SysRole entity, ReturnEntity info) {
//    	if (!authPermission(info,"btn:sys:role:update")) {
//            return info;    
//        }
        try {
            this.sysRoleService.update(entity);
            info.success("修改成功");
        } catch (Exception e) {
            info.failed("修改失败："+e.getMessage());
        }
        return info;
    }
    
    //角色列表    sysRole/getPage
    @RequestMapping(value = "getPage")
    public ReturnPage getPage(@RequestBody String json, ReturnPage rt) {
//    	if(!authPermission("menu:sys:role:page")) {
//    		rt.setStatus(StatusCode.NOPERMISS_ERROR);
//    		return rt;
//    	}
        try {
        	SysRole entity = toObject(json, SysRole.class);
        	PageEntity info = toObject(json, PageEntity.class);
            this.sysRoleService.getPage(entity, info);
            rt.setResult(info);
            rt.success("查询成功");
        } catch (Exception e) {
            rt.failed("查看失败："+e.getMessage());
        }
        return rt;
    }
    
    //批量删除    sysRole/delete.json
    @RequestMapping(value = "delete")
    @LogDesc(value="删除角色", type=LogType.DELETE)
    public ReturnEntity deleteByIds(@RequestBody Map<String, String> map, ReturnEntity info) {
//    	if (!authPermission(info,"btn:sys:role:delete")) {
//            return info;    
//        }
        try {
        	String ids = map.get("ids");
        	if (!StringUtils.isNotBlank(ids)) {
                info.setStatus(StatusCode.PARAMETER_ERROR);
            } else {
            	this.sysRoleService.deleteByIds(ids, UserUtil.getCurrentUser(),false);
            }
		} catch (Exception e) {
			info.failed("删除失败");
		}
        return info;
    }
    
//    查看当前角色下的权限   sysRole/queryMenuByRole.json?id=7
//    @RequestMapping("queryMenuByRole")
//    public ReturnEntity queryMenuByRole(Integer id,ReturnEntity info) {
//        if (id == null) {
//            info.failed("id 不能为空");
//            return info;
//        }
//  		Map<String, Object> map = this.sysRoleService.getMenuMapByRoleId(id);
//        info.setResult(map);
//        return info;
//    }
    //获取当前登录用户所拥有的权限   sysRole/queryMenuByRole.json?id=7
//    @RequestMapping("queryMenuTree")
//    public String queryMenuTree(ReturnEntity info) {
//    	List<MenuTreeModel> currentMenuList = new ArrayList<MenuTreeModel>();
//    	String currentUserId = UserUtil.getCurrentUserId();
//    	if (StringUtils.isNotBlank(currentUserId)) {
//    		if (UserUtil.ifAdmin()) {
//    			currentMenuList = sysMenuService.getAllMenuTree();
//    		} else {
//    			currentMenuList = this.userMenuDao.getMenuByUserId(currentUserId);
//    		}
//		}
//    	info.setResult(currentMenuList);
//    	return "";
//    }
    //配置权限   sysRole/addPermission.json?id=1&menus=1,2,3,4,5
//    @RequestMapping("addPermission")
//    @LogDesc(value="配置角色权限", modelClass=RoleMenusModel.class, type=LogType.UPDATE)
//    public ReturnEntity addPermission(Integer id,String menus,ReturnEntity info) {
//    	if (!authPermission(info,"sys:role:authorize")) {
//            return info;    
//        }
//        if(StringUtils.isBlank(menus)){
//            info.failed("请勾选要配置的权限");
//            return info;
//        }
//        try {
//            this.sysRoleService.addPermission(id, menus);
//        } catch (Exception e) {
//            info.failed("配置失败："+e.getMessage());
//        }
//        return info;
//    }

}
