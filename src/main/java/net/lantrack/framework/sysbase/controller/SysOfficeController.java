package net.lantrack.framework.sysbase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysOfficeTree;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.Tree;
import net.lantrack.framework.sysbase.model.office.OfficeModel;
import net.lantrack.framework.sysbase.service.SysOfficeTreeService;
import net.lantrack.framework.sysbase.service.SysUserService;
import net.lantrack.framework.sysbase.service.TreeService;
import net.lantrack.framework.sysbase.util.TreeUtil;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织机构Controller
 */
@RestController
@RequestMapping(value = "/sysOffice")
public class SysOfficeController extends BaseController {


	@Autowired
	private SysOfficeTreeService sysOfficeService;
	
	@Autowired
	@Qualifier("sysOfficeTreeServiceImpl")
	private TreeService officeTreeService;
	
	@Autowired
	private SysUserService sysUserService;
	


	//  sysOffice/update
	//  修改机构
	@RequestMapping("/update")
	@LogDesc(value="修改组织机构",type=LogType.UPDATE)
	public ReturnEntity update(@RequestBody String json,ReturnEntity info) {
//		if(authPermission(info, "btn:sys:office:update")) {
//			return info;
//		}
		try {
			SysOfficeTree office = toObject(json, SysOfficeTree.class);
			this.sysOfficeService.update(office);
			this.officeTreeService.updateTreeNode(office.getId(),office.gettName());
			info.success("修改成功");
			info.setResult(office.getId());
		} catch (Exception e) {
			info.failed("修改失败");
		}
		return info;
	}
	
	//  sysOffice/save
	//  添加机构
	@RequestMapping("/save")
	@LogDesc(value="添加组织机构",type=LogType.ADD)
	public ReturnEntity save(@RequestBody String json,ReturnEntity info) {
//		if(authPermission(info, "btn:sys:office:save")) {
//			return info;
//		}
		try {
			SysOfficeTree office = toObject(json, SysOfficeTree.class);
			this.sysOfficeService.save(office);
			info.success("添加成功");
			info.setResult(office.getId());
		} catch (Exception e) {
			info.failed("添加失败");
		}
		return info;
	}
	

	//  sysOffice/treeDelete
	//  删除机构树节点
	@RequestMapping("/treeDelete")
	public ReturnEntity treeDelete(@RequestBody Map<String, String> parms,ReturnEntity info) {
		String ids = parms.get("ids");
		if(StringUtils.isBlank(ids)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			String[] split = ids.split(",");
			for(String id:split) {
				this.officeTreeService.deleteTreeNode(id);
			}
		} catch (Exception e) {
			info.setStatus(StatusCode.SERVER_ERROR);
		}
		return info;
	}

	//  sysOffice/treeUpdate
	//  修改机构树节点
	@RequestMapping("/treeUpdate")
	public ReturnEntity treeUpdate(@RequestBody Map<String, String> parms,ReturnEntity info) {
		String id = parms.get("id");
		String nodename = parms.get("nodename");
		if(StringUtils.isBlank(id)||StringUtils.isBlank(nodename)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			this.officeTreeService.updateTreeNode(id, nodename);
		} catch (Exception e) {
			info.failed("修改失败:",e.getMessage());
		}
		return info;
	}

	//   sysOffice/treeSave
	//  添加机构树节点
	@RequestMapping("/treeSave")
	public ReturnEntity treeSave(@RequestBody Map<String, String> parms,ReturnEntity info) {
		String pid = parms.get("pid");
		String nodename = parms.get("nodename");
		if(StringUtils.isBlank(pid)||StringUtils.isBlank(nodename)) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return info;
		}
		try {
			String id = this.officeTreeService.addTreeNode(pid, nodename);
			info.setResult(id);
		} catch (Exception e) {
			info.failed("添加失败:"+e.getMessage());
		}
		return info;
	}
	
	/**
	 * 分页查询组织机构列表
	 * @param entity SysOffice类型
	 * @param pageentity PageEntity类型
	 * @return
	 * sysOffice/getPage
	 */
	@RequestMapping(value ="getPage")
	public ReturnPage getPage(@RequestBody String json,ReturnPage rt) {
//		if(!authPermission("menu:sys:offcie:pagetree")) {
//			rt.setStatus(StatusCode.NOPERMISS_ERROR);
//			return rt;
//		}
		try {
			SysOfficeTree entity = toObject(json, SysOfficeTree.class);
			PageEntity pageentity = toObject(json, PageEntity.class);
//			if (StringUtils.isBlank(pageentity.getOrderField())) {
//				pageentity.setOrderField("sync_flag");
//				pageentity.setOrderSort("asc");
//			}
			this.sysOfficeService.getPage(entity, pageentity);
			rt.setResult(pageentity);
		} catch (Exception e) {
		    rt.setStatus(StatusCode.SERVER_ERROR);
		}
		return rt;
	}
	
	/**
	 * 开始从单点登录系统同步组织机构的数据
	 * @param info ReturnEntity类型
	 * @return
	 */
	@RequestMapping(value ="extractOfficeFromSso")
	@LogDesc(value="同步数据",type=LogType.SYNCH)
	public String extractOfficeFromSso(ReturnEntity info) {
		if (!authPermission(info,"sys:office:synch")) {
            return "";    
        }
		try {
			 this.sysOfficeService.extractOfficeFromSso("");
			 this.sysUserService.extractUserFromSso("");
			 info.success("同步完成");
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "";
	}


	/**
     * 获取组织机构树分层级版本   sysOffice/getTreeChild
     */
    @RequestMapping("getTreeChild")
    public ReturnEntity getOfficeTreeChild(ReturnEntity info){
//    	if(!authPermission(info,"menu:sys:offcie:pagetree")) {
//			return info;
//		}
        List<OfficeModel> officeModelList = new ArrayList<OfficeModel>();
        if (!UserUtil.ifAdmin()) {
        	officeModelList = this.sysOfficeService.getCurrentOfficeTree();
        } else {
        	SysOfficeTree entity = new SysOfficeTree();
        	List<SysOfficeTree> sysOffices = this.sysOfficeService.getAll(entity);
        	if (sysOffices != null && sysOffices.size() > 0) {
        		for (SysOfficeTree sysOffice : sysOffices) {
        			OfficeModel model = new OfficeModel();
        			model.setId(sysOffice.getId());
        			model.setParentId(sysOffice.getpId());
        			model.setOfficeName(sysOffice.gettName());
        			officeModelList.add(model);
        		}
        	}
        }
        List<Tree> list = new ArrayList<>(officeModelList.size());
		for(OfficeModel office:officeModelList) {
			String sort = office.getSort();
			Tree tree = new Tree(office.getId(), office.getParentId(), 
					office.getOfficeName(),Integer.valueOf(sort==null?"0":sort));
			list.add(tree);
		}
		info.setResult(TreeUtil.converhandle(list));
        return info;
    }
	
	/**
     * 获取组织机构树  sysOffice/getTree
     */
    @RequestMapping("getTree")
    public ReturnEntity getOfficeTree(ReturnEntity info){
//    	if(!authPermission(info,"menu:sys:offcie:pagetree")) {
//			return info;
//		}
        List<OfficeModel> officeModelList = new ArrayList<OfficeModel>();
        if (!UserUtil.ifAdmin()) {
        	officeModelList = this.sysOfficeService.getCurrentOfficeTree();
        } else {
        	SysOfficeTree entity = new SysOfficeTree();
        	List<SysOfficeTree> sysOffices = this.sysOfficeService.getAll(entity);
        	if (sysOffices != null && sysOffices.size() > 0) {
        		for (SysOfficeTree sysOffice : sysOffices) {
        			OfficeModel model = new OfficeModel();
        			model.setId(sysOffice.getId());
        			model.setParentId(sysOffice.getpId());
        			model.setOfficeName(sysOffice.gettName());
        			officeModelList.add(model);
        		}
        	}
        }
        info.setResult(officeModelList);
        return info;
    }
    
    /**
     * 查看一个组织机构的详情
     * sysOffice/detail.json?id=area
     * 2018年1月17日
     * @author hww
     */
    @RequestMapping("detail")
    public ReturnEntity detail( @RequestBody Map<String, String> parms, ReturnEntity info) {
//    	if (!authPermission(info,"btn:sys:office:view")) {
//            return info;    
//        }
    	String id = parms.get("id");
    	SysOfficeTree entity = null;
    	if (StringUtils.isNotBlank(id)) {
			entity = this.sysOfficeService.queryById(id);
		}
    	if (entity == null) {
			entity = new SysOfficeTree();
		}
        info.setResult(entity);
        return info;
    }
    
    /**
     * 获取所有部门的下拉框数据
     * @param entity
     * @param info
     * @return
     */
    @RequestMapping("getAllMap")
    public String getAllMap(SysOfficeTree entity, ReturnEntity info){
        List<SysOfficeTree> list = this.sysOfficeService.getAll(entity);
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        for (SysOfficeTree office : list) {
        	if (StringUtils.isNotBlank(office.getId())) {
        		Map<String, String> map = new HashMap<String, String>();
        		map.put("id", office.getId());
        		map.put("name", office.gettName());
        		mapList.add(map);
        	}
        }
        info.setResult(mapList);
        return "";
    }
}
