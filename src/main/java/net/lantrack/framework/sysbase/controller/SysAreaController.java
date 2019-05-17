package net.lantrack.framework.sysbase.controller;

import java.util.List;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysArea;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.SysAreaService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "sysArea")
public class SysAreaController extends BaseController {
	@Autowired
	private SysAreaService sysAreaService;

	
	/**
	 * 添加一个行政区域 ，调用save方法
	 * @param entity SysArea类型
	 * @param parentId 父级区域的id
	 * @param info 返回的结果对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	@LogDesc(value="添加行政区域", modelClass=SysArea.class, type=LogType.ADD)
	public String save(SysArea entity, String parentId, ReturnEntity info) throws Exception {
		if (!authPermission(info,"sys:area:save")) {
            return "";    
        }
		if(StringUtils.isBlank(parentId)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return "";
		}
		entity.setParentId(parentId);
		SysArea parentArea = this.sysAreaService.queryById(Integer.valueOf(parentId));
		if ("1".equals(parentId)) {
			entity.setParentIds("1");
		} else {
			entity.setParentIds(parentArea.getParentIds() + "," + parentId);
		}
		entity.setParentName(parentArea.getName());
		entity.setFullName(parentArea.getFullName() + "_" + entity.getName());
		this.sysAreaService.save(entity);
		info.setResult(entity);
		info.success("保存成功");
		return "sys/sysAreaSave";
	}

	/**
	 * 查看一个行政区域详情
	 * @param entity SysArea类型
	 * @param info 返回的结果对象
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String detail(SysArea entity, ReturnEntity info) {
		if (!authPermission(info,"sys:area:view")) {
            return "";    
        }
		if (entity.getId() != null) {
			entity = this.sysAreaService.queryById(entity.getId());
			info.setResult(entity);
		} else {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "sys/sysAreaDetail";
	}

	/**
	 * 添加一个行政区域 ，调用save方法
	 * @param entity SysArea类型
	 * @param parentId 父级区域的id
	 * @param info 返回的结果对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	@LogDesc(value="修改行政区域", modelClass=SysArea.class, type=LogType.UPDATE)
	public String update(SysArea entity, String parentId, ReturnEntity info) throws Exception {
		if (!authPermission(info,"sys:area:update")) {
            return "";    
        }
		//主键id不能为空
		if (entity.getId() == null) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		} else {
			SysArea area = this.sysAreaService.queryById(entity.getId());
			if (StringUtils.isBlank(parentId)) {
				//还是等于自己
				parentId = area.getParentId();
			}
			SysArea parentArea = new SysArea();
			entity.setParentId(parentId);
			// 根节点的parentId始终为0，名称始终为世界
			if ("0".equals(parentId)) {
				entity.setParentId("0");
				entity.setParentIds("0");
				entity.setParentName("世界");
				entity.setFullName(entity.getName());
			} else {
				parentArea = this.sysAreaService.queryById(Integer.valueOf(parentId));
				entity.setParentIds(parentArea.getParentIds() + "," + parentId);
				entity.setParentName(parentArea.getName());
				entity.setFullName(parentArea.getFullName() + "_" + entity.getName());
			}
			this.sysAreaService.update(entity);
			info.setResult(entity);
			info.success("更新成功");
		}
		return "sys/sysAreaUpdate";
	}

	/**
	 * 分页查询行政区域列表
	 * @param entity SysArea类型
	 * @param pageentity PageEntity类型
	 * @return
	 */
	@RequestMapping(value ="getPage")
	public String getPage(SysArea entity, PageEntity pageentity,
	        ReturnPage rt) {
		try {
			this.sysAreaService.getPage(entity, pageentity);
			rt.setResult(pageentity);
		} catch (Exception e) {
		    rt.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "sys/sysAreaList";
	}

	/**
	 * 获取全部的行政区域集合,给前台用以组装树
	 * @param entity
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "getAll")
	public String getAll(SysArea entity, ReturnEntity info) {
		try {
			List<SysArea> list = this.sysAreaService.getAll(entity);
			info.setResult(list);
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "sys/sysAreaListTree";
	}
	
	/**
	 * 根据id删除当前节点区域
	 * @param id 当前节点id Integer类型
	 * @param info ReturnEntity类型
	 * @return
	 */
	@RequestMapping(value = "deleteById")
	@LogDesc(value="删除行政区域", modelClass=SysArea.class, type=LogType.DELETE)
	public String deleteById(Integer id, ReturnEntity info) {
		if (!authPermission(info,"sys:area:delete")) {
            return "";    
        }
		try {
			List<SysArea> children = this.sysAreaService.getChildrenById(id+"");
			if (id == 1) {
				info.failed("根节点不允许删除");
				return "";
			}
			if (children != null && children.size() > 0) {
				info.failed("当前节点下面还有子节点，请先删除子节点");
				return "";
			}
			// 参数：当前节点id，修改者update_by，是否逻辑删除：true逻辑，false物理
			this.sysAreaService.deleteById(id, UserUtil.getCurrentUser(), true);
			info.success("删除成功");
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "sys/sysAreaListTree";
	}
	
	/**
	 * 根据parentId获取其所有子节点（只到儿子一级）
	 * @param entity
	 * @param info
	 * @return
	 */
    //@RequiresPermissions("sys:sysArea:view")
	@RequestMapping(value = "getChildrenById")
	public String getChildrenById(Integer id, ReturnEntity info) {
		try {
			List<SysArea> list = this.sysAreaService.getChildrenById(id+"");
			info.setResult(list);
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "sys/sysAreaListTree";
	}
	
}
