
package net.lantrack.framework.sysbase.controller;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysMenu;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.Tree;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.SysMenuService;
import net.lantrack.framework.sysbase.util.TreeUtil;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 菜单管理Controller 2018年1月11日
 * 
 * @author lin
 */
@RestController
@RequestMapping(value = "sysMenu")
public class SysMenuController extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 获取完整菜单树 <br/>
	 * sysMenu/tree
	 * 
	 * @param info
	 * @return
	 * @date 2019年3月13日
	 */
	@RequestMapping("/tree")
	public ReturnEntity getAllTree(ReturnEntity info) {
		List<MenuTreeModel> allMenuTree = sysMenuService.getAllMenuTree();
		info.setResult(allMenuTree);
		return info;
	}
	/**
	 * 获取完整菜单树,多层级 <br/>
	 * sysMenu/treeChild
	 * @param info
	 * @return
	 * @date 2019年3月13日
	 */
	@RequestMapping("/treeChild")
	public ReturnEntity getAllTreeHasChild(ReturnEntity info) {
		List<MenuTreeModel> allMenuTree = sysMenuService.getAllMenuTree();
		if(allMenuTree==null||allMenuTree.size()==0) {
			return info;
		}
		List<Tree> list = new ArrayList<>(allMenuTree.size());
		for(MenuTreeModel menu:allMenuTree) {
			Tree tree = new Tree(menu.getId().toString(), menu.getPid().toString(), menu.getName(),menu.getSort());
			list.add(tree);
		}
		info.setResult(TreeUtil.converhandle(list));
		return info;
	}

	/**
	 * 获取左树
	 * sysMenu/userTree
	 * @param info
	 * @return 
	 * @date 2019年3月14日
	 */
	@RequestMapping("/userTreeChild")
	public ReturnEntity getMenuTreeChild(ReturnEntity info) {
//		if(authPermission(info, "menu:sys:menu:tabletree")) {
//			return info;
//		}
		List<MenuTreeModel> menuTree = UserUtil.getMenuTree();
		if(menuTree==null||menuTree.size()==0) {
			return info;
		}
		List<Tree> list = new ArrayList<>(menuTree.size());
		for(MenuTreeModel menu:menuTree) {
			Tree tree = new Tree(menu.getId().toString(), menu.getPid().toString(), menu.getName());
			list.add(tree);
		}
		info.setResult(TreeUtil.converhandle(list));
		return info;
	}

	/**
	 * 获取左树
	 * sysMenu/userTree
	 * @param info
	 * @return 
	 * @date 2019年3月14日
	 */
	@RequestMapping("/userTree")
	public ReturnEntity getMenuTree(ReturnEntity info) {
//		if(authPermission(info, "menu:sys:menu:tabletree")) {
//			return info;
//		}
		List<MenuTreeModel> menuTree = UserUtil.getMenuTree();
		info.setResult(menuTree);
		return info;
	}

	/**
	 * 获取菜单右表,根据所传id查找自己以及下一级菜单列表 sysMenu/getTable.json?pid=1 2018年1月11日
	 * 
	 * @author lin
	 */
	@RequestMapping("getTable")
	public ReturnEntity getTable(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		if(authPermission(info, "menu:sys:menu:tabletree")) {
//			return info;
//		}
		String pid = parms.get("pid");
		if (StringUtils.isBlank(pid)) {
			pid = "0";
		}
		SysMenu menu = new SysMenu();
		try {
			menu.setId(Integer.valueOf(pid));
			List<SysMenu> list = this.sysMenuService.getAll(menu);
			info.setResult(list);
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
			info.appendMessage(e.getMessage());
		}
		return info;
	}

	/**
	 * 获取菜详情 sysMenu/getDetail.json?id=0 2018年1月11日
	 * 
	 * @author lin
	 */
	@RequestMapping("getDetail")
	public ReturnEntity getDetail(@RequestBody Map<String, String> parms, ReturnEntity info) {
		String id = parms.get("id");
		if (StringUtils.isBlank(id)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		SysMenu menu = this.sysMenuService.queryById(id);
		if (menu == null) {
			info.setStatus(StatusCode.RESOURCE_NOTFIND);
		}
		info.setResult(menu);
		return info;
	}

	/**
	 * 根据Id删除菜单，父节点有子菜单的不可删除 sysMenu/deleteById.json?id=0 2018年1月11日
	 * 
	 * @author lin
	 */
	@LogDesc(value = "删除菜单", type = LogType.DELETE)
	@RequestMapping("deleteById")
	public ReturnEntity deleteById(@RequestBody Map<String, String> parms, ReturnEntity info) {
		// if (!authPermission(info,"btn:sys:menu:delete")) {
		// 		return "";
		// }
		String id = parms.get("id");
		if (StringUtils.isBlank(id)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			this.sysMenuService.deleteById(id, "", false);
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
		return info;
	}

	/**
	 * 在树结构父节点下添加子菜单 sysMenu/addMenuTree.json?pid=0&name=菜单名称 2018年1月11日
	 * 
	 * @author lin
	 */
	@LogDesc(value = "添加菜单树", type = LogType.ADD)
	@RequestMapping("addMenuTree")
	public ReturnEntity addMenu(@RequestBody Map<String, String> parms, ReturnEntity info) {
		// if (!authPermission(info,"btn:sys:menu:save")) {
		// 		return info;
		// }
		String pid = parms.get("pid");
		String name = parms.get("name");
		SysMenu menu = new SysMenu();
		menu.setParentId(Integer.valueOf(pid));
		menu.setMenuName(name);
		try {
			menu = this.sysMenuService.save(menu);
			info.setResult(menu);
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
		return info;
	}

	/**
	 * 在树结构父节点下修改子菜单 sysMenu/updateMenuTree.json?id=3&name=修改后名称 2018年1月11日
	 * 
	 * @author lin
	 */
	@LogDesc(value = "修改树菜单", type = LogType.UPDATE)
	@RequestMapping("updateMenuTree")
	public ReturnEntity updateMenuTree(@RequestBody Map<String, String> parms, ReturnEntity info) {
//		 if (!authPermission(info,"btn:sys:menu:update")) {
//			 return "";
//		 }
		try {
			SysMenu menu = new SysMenu();
			menu.setId(Integer.valueOf( parms.get("id")));
			menu.setMenuName(parms.get("name"));
			menu.setCreateDate(null);
			menu.setIsShow(null);
			this.sysMenuService.update(menu);
			info.success("修改成功");
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
		return info;
	}

	/**
	 * 修改菜单 sysMenu/updateMenu.json 2018年1月11日
	 * 
	 * @author lin
	 */
	@RequestMapping(value = "updateMenu")
	@LogDesc(value = "修改菜单", type = LogType.UPDATE)
	public ReturnEntity update(@RequestBody String json, ReturnEntity info) {
//		if (!authPermission(info, "btn:sys:menu:update")) {
//			return info;
//		}
		try {
			SysMenu menu = toObject(json, SysMenu.class);
			this.sysMenuService.updateMenu(menu);
		} catch (Exception e) {
			info.failed("修改失败" );
		}
		return info;
	}
	// sysMenu/addMenu
	@RequestMapping(value = "addMenu")
	@LogDesc(value = "添加菜单", type = LogType.ADD)
	public ReturnEntity save(@RequestBody String json, ReturnEntity info) {
//		if (!authPermission(info, "btn:sys:menu:save")) {
//			return info;
//		}
		try {
			SysMenu menu = toObject(json, SysMenu.class);
			this.sysMenuService.save(menu);
			info.setResult(menu);
		} catch (Exception e) {
			info.failed("添加失败" );
		}
		return info;
	}


}
