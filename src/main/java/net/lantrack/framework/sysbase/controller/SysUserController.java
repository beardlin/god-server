package net.lantrack.framework.sysbase.controller;


import java.util.Map;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户管理      
 * @date 2019年3月6日
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {


	@Autowired
	private SysUserService sysUserService;
	
//	@Autowired
//	private SysRoleService sysRoleService;
//	
//	@Autowired
//	private SysMenuService sysMenuService;
	
//	@Autowired
//	private SysOfficeTreeService sysOfficeTreeService;
	
	//获取当前用户可见的用户下拉列表   sysUser/select
	@RequestMapping("/select")
	public ReturnEntity getUserSelect(ReturnEntity info) {
		info.setResult(UserUtil.getUserSelect());
		return info;
	}

	//解锁账户  sysUser/unlock
	@RequestMapping("/unlock")
	@LogDesc(value="解锁账户", type=LogType.UPDATE)
	public ReturnEntity unlock(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:unlock")) {
//            return info;    
//        }
		try {
			String ids = parms.get("ids");
			if(StringUtils.isBlank(ids)) {
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			this.sysUserService.unlockUser(ids);
			info.success("解锁成功");
        } catch (Exception e) {
            info.failed("解锁失败");
        }
        return info;
	}

	//恢复账户  sysUser/enable
	@RequestMapping("/enable")
	@LogDesc(value="恢复账户",  type=LogType.UPDATE)
	public ReturnEntity enable(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:enable")) {
//            return info;    
//        }
		try {
			String ids = parms.get("ids");
			if(StringUtils.isBlank(ids)) {
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			this.sysUserService.enableUser(ids);
			info.success("恢复成功");
        } catch (Exception e) {
            info.failed("恢复失败");
        }
        return info;
	}
	
	//禁用账户  sysUser/disable
	@RequestMapping("/disable")
	@LogDesc(value="禁用账户", modelClass=SysUser.class, type=LogType.UPDATE)
	public ReturnEntity disable(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:disable")) {
//            return info;    
//        }
		try {
			String ids = parms.get("ids");
			if(StringUtils.isBlank(ids)) {
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			this.sysUserService.disableUser(ids);
			info.success("禁用成功");
        } catch (Exception e) {
            info.failed("禁用失败");
        }
        return info;
	}
	
	//删除用户  sysUser/delete
	@RequestMapping("/delete")
	@LogDesc(value="删除用户", modelClass=SysUser.class, type=LogType.DELETE)
	public ReturnEntity delete(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:delete")) {
//            return info;    
//        }
		try {
			String ids = parms.get("ids");
			if(StringUtils.isBlank(ids)) {
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			this.sysUserService.deleteByIds(ids, UserUtil.getCurrentUser(), true);
			info.success("删除成功");
        } catch (Exception e) {
            info.failed("删除失败");
        }
        return info;
	}
	
	//添加用户  sysUser/add
	@RequestMapping("/add")
	@LogDesc(value="添加用户", modelClass=SysUser.class, type=LogType.ADD)
	public ReturnEntity add(@RequestBody String json, ReturnEntity info) {
		if (!authPermission(info,"btn:sys:user:save")) {
            return info;    
        }
		SysUser entity = null;
		try {
			entity = toObject(json, SysUser.class);
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		if(!beanValidator(info, entity)) {
			return info;
		}
		try {
            entity = this.sysUserService.save(entity);
            info.setResult(entity.getId());
        } catch (Exception e) {
            info.failed("添加失败："+e.getMessage());
        }
        return info;
	}
	

	//修改用户  sysUser/update
	@RequestMapping("/update")
	@LogDesc(value="修改用户", modelClass=SysUser.class, type=LogType.UPDATE)
	public ReturnEntity update(@RequestBody String json, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:update")) {
//            return info;    
//        }
		SysUser entity = null;
		try {
			entity = toObject(json, SysUser.class);
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		if(!beanValidator(info, entity)) {
			return info;
		}
		try {
            entity = this.sysUserService.update(entity);
            info.success("修改成功");
        } catch (Exception e) {
            info.failed("修改失败:",e.getMessage());
        }
        return info;
	}
	
	
	//用户详情  sysUser/detail
	@RequestMapping("/detail")
	public ReturnEntity detail(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:detail")) {
//            return info;    
//        }
		try {
			String id = parms.get("id");
	    	if (StringUtils.isBlank(id)) {
	    		info.setStatus(StatusCode.PARAMETER_ERROR);
	    		return info;
			}
	    	SysUser user = this.sysUserService.queryById(id);
	    	if (user == null) {
	    		info.failed("数据不存在");
	    		return info;
			}
	        info.setResult(user);
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
        return info;
	}


	/**
	 * 分页获取用户列表
	 * @param entity
	 * @param pageentity
	 * @param rt
	 * @return
	 * @throws Exception
	 * sysUyser/getPage
	 */
	@RequestMapping("/getPage")
	public ReturnPage getPage(@RequestBody String json,ReturnPage rt){
//		if (!authPermission("menu:sys:user:page")) {
//			rt.setStatus(StatusCode.NOPERMISS_ERROR);
//            return rt;    
//        }
		try {
			PageEntity pageentity = toObject(json, PageEntity.class);
			SysUser entity = toObject(json, SysUser.class);
			if (StringUtils.isBlank(pageentity.getOrderField())) {
				pageentity.setOrderField("o.update_date");
				pageentity.setOrderSort("asc");
			}
			this.sysUserService.getPage(entity, pageentity);
			rt.setResult(pageentity);
		} catch (Exception e) {
		    rt.setStatus(StatusCode.SERVER_ERROR);
		}
		return rt;
	}

	/**
	 * 开始从单点登录系统同步用户的数据
	 * @param info ReturnEntity类型
	 * @return
	 */
//	@RequestMapping("extractUserFromSso")
//	@LogDesc(value="同步用户数据", type=LogType.SYNCH)
//	public ReturnEntity extractOfficeFromSso(ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:synch")) {
//            return info;    
//        }
//		try {
//			String rmg = this.sysUserService.extractUserFromSso("");
//			info.success(rmg);
//		} catch (Exception e) {
//			info.setStatus(StatusCode.SERVER_ERROR);
//		}
//		return info;
//	}
	/**
	 * 根据用户ID获取能给该用户分配的角色，以及该用户目前已有的权限
	 * @param id 用户id
	 * @param info
	 * @return map
	 */
//	@RequestMapping("openAuthorizationById")
//	public ReturnEntity openAuthorizationById(String id, ReturnEntity info) {
//		if (!authPermission(info,"btn:sys:user:view")) {
//            return info;    
//        }
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map<Integer, String> currentRoleMap = new HashMap<Integer, String>();
//		List<MenuTreeModel> currentMenuList = new ArrayList<MenuTreeModel>();
//		Map<Integer, String> roleMap = new HashMap<Integer, String>();
//		List<MenuTreeModel> menuList = new ArrayList<MenuTreeModel>();
//		
//		String currentUserId = UserUtil.getCurrentUserId();
//    	if (StringUtils.isNotBlank(id)) {
//    		UserModel userModel = this.sysUserService.queryByPk(id);
//    		map.put("user", userModel);
//    		map.put("ifAdmin", userModel.getIfAdmin());
//    		if ("1".equals(userModel.getIfAdmin())) {
//    			roleMap = this.sysRoleService.getAll(currentUserId);
//    			menuList = sysMenuService.getAllMenuTree();
//    		} else {
//    			roleMap = this.sysRoleService.getAll(id);
//    			menuList = this.sysUserService.getUserMenus(id);
//    		}
//    		List<Integer> menuIds = new ArrayList<Integer>();
//    		if (menuList != null && menuList.size() > 0) {
//    			for (MenuTreeModel mtd : menuList) {
//    				menuIds.add(mtd.getId());
//    			}
//    		}
//    		map.put("roleMap", roleMap != null ? roleMap : "");
//    		map.put("menuList", menuIds.size()>0 ? menuIds : "");
//		} else {
//    		map.put("roleMap", "");
//    		map.put("menuList", "");
//		}
//		currentRoleMap = this.sysRoleService.getAll(currentUserId);
//		if (UserUtil.ifAdmin()) {
//			currentMenuList = sysMenuService.getAllMenuTree();
//		} else {
//			Set<Integer> userMenuIds = sysMenuService.getUserMenus(UserUtil.getUser());
//			currentMenuList = sysMenuService.getUserMenuTreeModels(userMenuIds);
//		}
//		
//		map.put("currentRoleMap", currentRoleMap != null ? currentRoleMap : "");
//		map.put("currentMenuList", currentMenuList.size()>0 ? currentMenuList : "");
//			
//		info.setResult(map);
//        return info;
//	}
	
	/**
	 * 根据勾选的角色ids获取对应权限id的并集
	 * @param ids 勾选的角色ids
	 * @return
	 */
//	@RequestMapping("getMenuListByRoleIds")
//	public ReturnEntity getMenuListByRoleIds(String ids, ReturnEntity info) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (StringUtils.isBlank(ids)) {
//			map.put("menuList", "");
//		} else {
//			List<MenuTreeModel> currentMenuList = new ArrayList<MenuTreeModel>();
//			if (UserUtil.ifAdmin()) {
//				currentMenuList = sysMenuService.getAllMenuTree();
//			} else {
//				Set<Integer> userMenuIds = sysMenuService.getUserMenus(UserUtil.getUser());
//				currentMenuList = sysMenuService.getUserMenuTreeModels(userMenuIds);
//			}
//			map.put("currentMenuList", currentMenuList.size()>0 ? currentMenuList : "");
//			List<Integer> menuIds = new ArrayList<Integer>();
//			menuIds = this.sysRoleService.getMenuListByRoleIds(ids);
//			map.put("menuList", menuIds.size()>0 ? menuIds : "");
//		}
//		info.setResult(map);
//		return info;
//	}
	

	/**
	 * 给单个用户指派好职务或角色之后将对应关系保存入库
	 * @param model
	 * @param info
	 * @return
	 */
//	@RequestMapping("authorizeDutyOrRole")
//	@LogDesc(value="给用户授权",modelClass=UserAuthModel.class,type=LogType.UPDATE)
//	public ReturnEntity authorizeRoleOrMenus(UserAuthModel model, ReturnEntity info) {
//		if (!authPermission(info,"sys:user:authorize")) {
//            return info;    
//        }
//		if (!StringUtils.isNotBlank(model.getUserids())) {
//			info.setStatus(StatusCode.PARAMETER_ERROR);
//		} else {
//			this.sysUserService.saveUserDutyRoleMenu(model);
//		}
//		return info;
//	}
//	
	
	/**
	 * 获取有权查看的所有用户列表
	 * @param info
	 * @return Map形式
	 */
//	@RequestMapping("getAllMap")
//	public String getAllMap(ReturnEntity info){
//		List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
//		List<SysUser> list = new ArrayList<SysUser>();
//		SysUser entity = new SysUser();
//		SysUser currentUser = UserUtil.getUser();
//		if (UserUtil.ifAdmin()) {
//			list = this.sysUserService.getAll(entity);
//		} else {
//			if (StringUtils.isNotBlank(currentUser.getOfficeId())) {
//				String currentOfficeId = currentUser.getOfficeId();
//				String officeIds = this.sysOfficeTreeService.getAllSubOfficeids(currentOfficeId, false);
//				SysUserExample example = new SysUserExample();
//				SysUserExample.Criteria cr = example.createCriteria();
//				cr.andDelFlagEqualTo("0");
//				if (StringUtils.isNotBlank(officeIds)) {
//					List<String> oidList = new ArrayList<String>(Arrays.asList(officeIds.split(",")));
//					cr.andOfficeIdIn(oidList);
//				}
//				list = this.sysUserDao.selectByExample(example);
//			}
//			
//		}
//		if (list != null && list.size() > 0) {
//			for (SysUser user : list) {
//				if (StringUtils.isNotBlank(user.getId()) && StringUtils.isNotBlank(user.getUserName())) {
//					Map<String, String> map = new HashMap<String, String>();
//					map.put("userId", user.getId());
//					map.put("userName", user.getUserName());
//					userList.add(map);
//				}
//			}
//		}
//        info.setResult(userList);
//        return "";
//	}
	/**
	 * sysUser/getPageByOfficeId.json?officeId=bfea9b4a-3a89-4fb6-9c2a-68deb696bdba
	 * 根据选择的officeId，分页查询其下所有子孙组织机构的所有用户列表
	 * @param officeId
	 * @param pageentity
	 * @param rt
	 * @return
	 */
//	@RequestMapping("getPageByOfficeId")
//	public ReturnPage getPageByOfficeId(String officeId, String userName, PageEntity pageentity,
//	        ReturnPage rt){
//		if (StringUtils.isNotBlank(officeId)) {
//			String officeIds = this.sysOfficeTreeService.getAllSubOfficeids(officeId, false);
//			String[] officeIdArray = officeIds.split(",");
//			UserOfficeIdsModel entity = new UserOfficeIdsModel();
//			if (StringUtils.isNotBlank(officeIds)) {
//				List<String> officeIdList = new ArrayList<String>(Arrays.asList(officeIdArray));
//				entity.setOfficeIdList(officeIdList);
//			}
//			if (StringUtils.isNotBlank(userName)) {
//				entity.setUserName(userName);
//			}
//			this.sysUserService.getUserByOfficeIdListPage(entity, pageentity);
//			rt.setResult(pageentity);
//		} else {
//			SysUser entity = new SysUser();
//			if (StringUtils.isNotBlank(userName)) {
//				entity.setUserName(userName);
//			}
//			this.sysUserService.getPage(entity, pageentity);
//			rt.setResult(pageentity);
//		}
//		
//		return rt;
//	}
	
	/**
	 * 获取当前登录用户的基本信息和对应的权限信息
	 * @param info
	 * @return
	 */
//	@RequestMapping("getCurrentUser")
//	public ReturnEntity getCurrentUser(ReturnEntity info) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		SysUser currentUser = UserUtil.getUser();
//		map.put("currentUser", currentUser);
//		map.put("ifAdmin", currentUser.getIfAdmin());
//		map.put("accreditFlag", currentUser.getAccreditFlag());
//		map.put("permissions", UserUtil.getPermissionList());
//        info.setResult(map);
//        return info;
//	}


	
}
