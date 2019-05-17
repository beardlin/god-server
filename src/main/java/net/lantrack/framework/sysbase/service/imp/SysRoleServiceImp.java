package net.lantrack.framework.sysbase.service.imp;


import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.MapEntityService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.RoleMenuDao;
import net.lantrack.framework.sysbase.dao.SysMenuDao;
import net.lantrack.framework.sysbase.dao.SysRoleDao;
import net.lantrack.framework.sysbase.dao.UserMenuDao;
import net.lantrack.framework.sysbase.dao.UserRoleDao;
import net.lantrack.framework.sysbase.entity.RoleMenu;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysRole;
import net.lantrack.framework.sysbase.entity.SysRoleExample;
import net.lantrack.framework.sysbase.entity.SysRoleExample.Criteria;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.SysMenuService;
import net.lantrack.framework.sysbase.service.SysRoleService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色管理Service
 * 2018年1月15日
 * @author lin
 */
@Service("sysRoleServiceImp")
public class SysRoleServiceImp extends BaseService implements SysRoleService,MapEntityService {
	
	/**
     * 菜单根节点
     */
    private static final Integer ROOT_ID = 1;
	
	@Autowired
	protected PageService pageService;
	
	@Autowired
	SysRoleDao sysRoleDao;
	
	@Autowired
    RoleMenuDao roleMenuDao;
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	SysMenuService sysMenuService;
	
	@Autowired
	UserMenuDao userMenuDao;
	
	@Autowired
	SysMenuDao sysMenuDao;
	
	@Autowired
	BaseDao baseDao;
	
	/**
	 * 修改角色
	 */
    @Override
    public SysRole update(SysRole entity) throws ServiceException {
        SysRoleExample roleExample = new SysRoleExample();
        SysRoleExample.Criteria cr = roleExample.createCriteria();
        cr.andRoleNameEqualTo(entity.getRoleName());
        try {
        	List<SysRole> list = this.sysRoleDao.selectByExample(roleExample);
            boolean up=false;
            if(list!=null&&list.size()==1){
                SysRole role = list.get(0);
                if(role.getId().equals(entity.getId())){
                    up = true;
                }
            }else{
                up=true;
            }
            if(up){
                this.sysRoleDao.updateByPrimaryKeySelective(entity);
            }else{
                throw new ServiceException("当前角色已存在");
            }
            
            return entity;
		} catch (Exception e) {
			throw printException(e);
		}
    }
    
    /**
     * 修改带权限参数的角色
     */
    @Override
    @Transactional
	public SysRole update(SysRole entity, String menus) throws ServiceException {
    	entity = this.update(entity);
    	if (StringUtils.isNotBlank(menus)) {
    		List<String> rList = new ArrayList<String>(1);
    		rList.add(entity.getId()+"");
    		this.roleMenuDao.deleteByRoleId(rList);
			List<RoleMenu> rmlist = new ArrayList<RoleMenu>();
			String[] menuIdArray = menus.split(",");
			for (int i=0; i<menuIdArray.length; i++) {
				Integer menuId = Integer.valueOf(menuIdArray[i]);
				RoleMenu userMenu = new RoleMenu(entity.getId(), menuId);
				rmlist.add(userMenu);
			}
			this.roleMenuDao.insertList(rmlist);
		}
		return entity;
	}
    
    /**
     * 添加角色
     */
    @Override
    public SysRole save(SysRole entity) throws ServiceException {
        SysRoleExample roleExample = new SysRoleExample();
        SysRoleExample.Criteria cr = roleExample.createCriteria();
        cr.andRoleNameEqualTo(entity.getRoleName());
        try {
        	List<SysRole> list = this.sysRoleDao.selectByExample(roleExample);
            boolean up=false;
            if(list==null||list.size()==0){
                up = true;
            }
            if(up){
                this.sysRoleDao.insertSelective(entity);
            }else{
                throw new ServiceException("当前角色已存在");
            }
            return entity;
		} catch (Exception e) {
			throw printException(e);
		}
        
    }
    /**
     * 保存带权限参数的角色
     */
    @Override
    @Transactional
	public SysRole save(SysRole entity, String menus) throws ServiceException {
    	try {
    		this.save(entity);
    		return entity;
		} catch (Exception e) {
			throw printException(e);
		}
	}
    
    @Override
    public SysRole queryById(Object id) {
        if (id==null) {
            this.logger.error("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        SysRole role = null;
        try {
            role = this.sysRoleDao.selectByPrimaryKey(pasId);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error(e);
        }
        return role;
    }
   
    @Override
    @Transactional
    public void deleteById(Object id, String upBy, boolean ifLogic) throws ServiceException {
        if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        List<String> roleIdList = new ArrayList<String>();
        roleIdList.add(id.toString());
        if (!ifLogic) {
            // 物理删除
            this.sysRoleDao.deleteByPrimaryKey(pasId);
            // 同时删除与角色对应的权限关系
            this.roleMenuDao.deleteByRoleId(roleIdList);
        } else {
            // 逻辑删除结束
            SysRole role = new SysRole();
            role.setCreateDate(null);
            role.setUpdateBy(upBy);
            role.setDelFlag(BaseEntity.YES);
            role.setId(pasId);
            this.sysRoleDao.updateByPrimaryKeySelective(role);
            // 同时删除与角色对应的权限关系
            this.roleMenuDao.deleteByRoleId(roleIdList);
        }
        
    }
    
    @Override
    @Transactional
    public void deleteByIds(String ids, String upBy, boolean ifLogic) throws ServiceException {
        if (StringUtils.isBlank(ids)) {
            this.logger.error("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        SysRoleExample dictExample = new SysRoleExample();
        SysRoleExample.Criteria cr = dictExample.createCriteria();
        cr.andIdIn(Arrays.asList(split));
        if (!ifLogic) {
            // 物理删除
            this.sysRoleDao.deleteByExample(dictExample);
            // 同时删除与角色对应的权限关系
//            this.roleMenuDao.deleteByRoleId(Arrays.asList(split));
        } else {
            // 此处用逻辑删除
            SysRole role = new SysRole();
            role.setCreateDate(null);
            role.setUpdateBy(upBy);
            role.setDelFlag(BaseEntity.YES);
            this.sysRoleDao.updateByExampleSelective(role, dictExample);
            // 同时删除与角色对应的权限关系
//            this.roleMenuDao.deleteByRoleId(Arrays.asList(split));
        }
    }
    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#getPage(net.lantrack.framework.core.entity.BaseEntity, net.lantrack.framework.core.entity.PageEntity)
     */
    @Override
    public void getPage(SysRole entity, PageEntity pageentity) {
        this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        if(StringUtils.isBlank(entity.getRoleName())){
            entity.setRoleName(null);
        }
        List<SysRole> result = this.sysRoleDao.getRoleListPage(pageentity, entity);
        pageentity.setContent(result);
        
    }
    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#getPageRe(net.lantrack.framework.core.entity.BaseEntity, net.lantrack.framework.core.entity.PageEntity)
     */
    @Override
    public void getPageRe(SysRole entity, PageEntity pageentity) {
        
    }
    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#deleteByIdsRe(java.lang.String, java.lang.String)
     */
    @Override
    public void deleteByIdsRe(String ids, String update_by) throws ServiceException {
        
    }
    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#getAll(net.lantrack.framework.core.entity.BaseEntity)
     */
    @Override
    public List<SysRole> getAll(SysRole entity) {
    	return this.sysRoleDao.selectByExample(new SysRoleExample());
    }
    
    @Override
    public List<MenuTreeModel> getMenuTreeByRoleId(Integer id) throws ServiceException{
        try {
            List<MenuTreeModel> list = this.roleMenuDao.getMenuByRoleId(id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public void addPermission(Integer id, String menus) {
        if(id==null||StringUtils.isBlank(menus)){
            throw new ServiceException("参数异常");
        }
        String[] split = menus.split(",");
        List<RoleMenu> dmList = new ArrayList<RoleMenu>(split.length);
        for(String menu:split){
            RoleMenu dm = new RoleMenu(id, Integer.valueOf(menu));
            dmList.add(dm);
        }
        List<String> roleList = new ArrayList<String>(1);
        roleList.add(id.toString());
        //先删除
        this.roleMenuDao.deleteByRoleId(roleList);
        //批量插入
        try {
            this.roleMenuDao.insertList(dmList);
        } catch (Exception e) {
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
    }
	
    /**
     * 获取当前登录用户所能见的角色集合(map<id,name>形式)
     */
    @Override
    public Map<Integer, String> getAll(String userId){
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	SysRoleExample example = new SysRoleExample();
    	SysRoleExample.Criteria cr = example.createCriteria();
    	cr.andDelFlagEqualTo("0");
    	List<SysRole> list = new ArrayList<SysRole>();
    	if (UserUtil.ifAdmin() && UserUtil.getCurrentUserId().equals(userId)) {
    		list = this.sysRoleDao.selectByExample(example);
    	} else {
    		List<Integer> idList = this.userRoleDao.queryRoleListByUser(userId);
    		if (idList!=null && idList.size()>0) {
    			for (int i=0; i<idList.size(); i++) {
    				SysRole sysRole = this.sysRoleDao.selectByPrimaryKey(idList.get(i));
    				if (sysRole != null && "0".equals(sysRole.getDelFlag())) {
    					list.add(sysRole);
    				}
    			}
    		}
    	}
    	if (list != null && list.size() > 0) {
    		for (int i=0; i < list.size(); i++) {
    			SysRole sysRole = list.get(i);
    			if (sysRole != null) {
    				map.put(sysRole.getId(), sysRole.getRoleName());
    			}
    		}
    		return map;
    	} else {
    		this.logger.warn("SysRoleServiceImp Map<Integer, String> getAll() return null");
    		return null;
    	}
    }
    
    /**
	 * 根据角色id获取全部权限列表和所操作角色目前已有的权限列表的map
	 * @return map
	 */
	@Override
	public Map<String, Object> getMenuMapByRoleId(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MenuTreeModel> currentMenuList = new ArrayList<MenuTreeModel>();
		List<MenuTreeModel> menuList = new ArrayList<MenuTreeModel>();
		List<Integer> checkedMenus = new ArrayList<Integer>();
    	// 获取当前登录用户所有的权限列表
    	// currentMenuList = this.userMenuDao.getMenuByUserId(currentUserId);
		currentMenuList = sysMenuService.getAllMenuTree();
    	map.put("menusAll", currentMenuList.size()>0 ? currentMenuList : "");
		// 再获取操作的角色目前已有的权限列表
		menuList = this.getMenuTreeByRoleId(id);
        if (menuList != null && menuList.size() > 0) {
        	for (MenuTreeModel menu : menuList) {
        		checkedMenus.add(menu.getId());
        	}
        }
        map.put("checkedMenus", checkedMenus);
		return map;
	}

	@Override
	public List<SysMenuModel> getSysMenuListByRoleId(Integer id) {
		List<SysMenuModel> list = new ArrayList<SysMenuModel>();
		List<Integer> menuIdList = this.roleMenuDao.queryMenuListByRole(id);
		// 1先查找当前用户所有的菜单
		Set<Integer> menus = new HashSet<Integer>();
		if (menuIdList != null && menuIdList.size() > 0) {
			for (Integer menuId : menuIdList) {
				menus.add(menuId);
			}
		}
		// 2再找所有的一级菜单
        list = this.sysMenuDao.getIndexMenuByParentId(ROOT_ID);
        Iterator<SysMenuModel> iterator = list.iterator();
        while(iterator.hasNext()){
            SysMenuModel next = iterator.next();
            if (!menus.contains(next.getId())) {
            	// 移除当前用户没有权限的一级菜单
                iterator.remove();
            }
        }
		return list;
	}
	

	/**
	 * 根据勾选的角色ids获取对应权限id的并集
	 * @param ids
	 * @return
	 */
	public List<Integer> getMenuListByRoleIds(String ids) {
		List<Integer> menuList = new ArrayList<Integer>();
		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			List<Integer> rolelist = new ArrayList<Integer>();
			for (int i=0; i<idArr.length; i++) {
				 rolelist.add(Integer.valueOf(idArr[i]));
			}
			Set<Integer> menus = new HashSet<Integer>();
			List<Integer> listByRole = this.roleMenuDao.queryMenuListByRoles(rolelist);
	        if (listByRole != null) {
	            for (Integer menuid : listByRole) {
	                menus.add(menuid);
	            }
	        }
	        if (menus.size() > 0) {
		        Iterator<Integer> iterator = menus.iterator();
		        while (iterator.hasNext()) {
		        	menuList.add(Integer.valueOf(iterator.next()));
		        }
	        }
		}
		return menuList;
	}

	@Override
	public List<MapEntity> getSelectMap() {
		List<MapEntity> list = null;
		try {
			String sql = "select id as k,role_name as v from sys_role where use_able = '"+BaseEntity.YES+"'";
			list = this.baseDao.queryList(sql, MapEntity.class);
		} catch (Exception e) {
			printException(e);
		}
		return list;
	}

	@Override
	public List<SysRole> getRoleListByUserId(String userId) {
		try {
			//获取当前用户拥有角色
			List<Integer> roleids = userRoleDao.queryRoleListByUser(userId);
			SysRoleExample example = new SysRoleExample();
			Criteria cr = example.createCriteria();
			cr.andUseAbleEqualTo("1");
			if(!UserUtil.ifAdmin()) {
				cr.andIdIntegerIn(roleids);
			}
			return this.sysRoleDao.selectByExampleWithBLOBs(example);
		} catch (Exception e) {
			printException(e);
		}
		return new ArrayList<>();
	}
}
