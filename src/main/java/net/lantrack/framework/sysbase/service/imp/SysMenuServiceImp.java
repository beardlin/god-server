package net.lantrack.framework.sysbase.service.imp;

import com.google.common.collect.Lists;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.DutyMenuDao;
import net.lantrack.framework.sysbase.dao.RoleMenuDao;
import net.lantrack.framework.sysbase.dao.SysMenuDao;
import net.lantrack.framework.sysbase.dao.UserMenuDao;
import net.lantrack.framework.sysbase.dao.UserRoleDao;
import net.lantrack.framework.sysbase.entity.SysMenu;
import net.lantrack.framework.sysbase.entity.SysMenuExample;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.SysMenuService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 菜单管理Service
 * 2018年1月10日
 * @author lin
 */
@Service
public class SysMenuServiceImp extends BaseService implements SysMenuService {
    /**
     * 菜单根节点
     */
    private static final Integer ROOT_ID = 1;
    
	@Autowired
	protected PageService pageService;
	@Autowired
	protected SysMenuDao sysMenuDao;
	@Autowired
	protected DutyMenuDao dutyMenuDao;
	@Autowired
	protected UserMenuDao userMenuDao;
	@Autowired
	protected RoleMenuDao roleMenuDao;
	@Autowired
    protected UserRoleDao userRoleDao;
	@Autowired
    protected BaseDao baseDao;
	
	@Override
	public void deleteById(Object id, String update_by, boolean ifLogic) 
	        throws ServiceException {
		    if (id==null) {
	            throw new ServiceException("id不能为空");
	        }
		    try {
		    	StringBuffer delSql = new StringBuffer("delete from ").append(" sys_menu ");
				delSql.append(" where id = '").append(id).append("' or parent_ids like '%");
				delSql.append(id).append("%'");
				this.baseDao.deleteSql(delSql.toString());
			} catch (Exception e) {
				throw printException(e);
			}

	}
		@Override
	    public void deleteByIds(String ids, String update_by, boolean ifLogic) throws ServiceException {
	        if (StringUtils.isBlank(ids)) {
	            throw new ServiceException("id can not be null or empty!");
	        }
	        String[] split = ids.split(",");
	        // 添加条件
	        SysMenuExample menuExample = new SysMenuExample();
	        SysMenuExample.Criteria cr = menuExample.createCriteria();
	        List<String> asList = Arrays.asList(split);
	        List<Integer> intList = new ArrayList<>(asList.size());
	        for(String id:asList){
	            intList.add(Integer.valueOf(id));
	        }
	        cr.andIdIn(intList);
	        if (!ifLogic) {
	            // 物理删除
	            this.sysMenuDao.deleteByExample(menuExample);
	        } else {
	            // 此处用逻辑删除
	            SysMenu menu = new SysMenu();
	            menu.setCreateDate(null);
	            menu.setUpdateBy(update_by);
	            menu.setDelFlag(BaseEntity.YES);
	            this.sysMenuDao.updateByExampleSelective(menu, menuExample);
	        }
	    }
        @Override
        @Transactional
        public SysMenu update(SysMenu entity) throws ServiceException {
        	
            //修改自己名字
            this.sysMenuDao.updateByPrimaryKeySelective(entity);
            //修改儿子们的名字
            this.sysMenuDao.updateChildParentName(entity);
            return entity;
        }
        @Override
        public SysMenu save(SysMenu entity) throws ServiceException {
            //查找父节点
            SysMenu parentMenu = this.sysMenuDao.selectByPrimaryKey(entity.getParentId());
            if(parentMenu==null){
                return entity;
            }
            //排序默认往当前最下面节点插入
            Integer maxSort = this.sysMenuDao.getMaxSortByPid(parentMenu.getId());
            if(maxSort!=null){
                entity.setSort(maxSort+1);
            }else{
                entity.setSort(1);
            }
            entity.setParentName(parentMenu.getMenuName());
            entity.setParentIds(parentMenu.getParentIds()+","+parentMenu.getId());
            this.sysMenuDao.insert(entity);
            return entity;
        }
        /*
         *  通过id查找
         */
        @Override
        public SysMenu queryById(Object id) {
            if(id==null){
                throw new ServiceException("Menu id can not be null");
            }
            return this.sysMenuDao.
                    selectByPrimaryKey(Integer.valueOf(id.toString()));
        }
        /* (non-Javadoc)
         * @see net.lantrack.framework.core.service.CrudService#getPage(net.lantrack.framework.core.entity.BaseEntity, net.lantrack.framework.core.entity.PageEntity)
         */
        @Override
        public void getPage(SysMenu entity, PageEntity pageentity) {
            
        }
        /* (non-Javadoc)
         * @see net.lantrack.framework.core.service.CrudService#getPageRe(net.lantrack.framework.core.entity.BaseEntity, net.lantrack.framework.core.entity.PageEntity)
         */
        @Override
        public void getPageRe(SysMenu entity, PageEntity pageentity) {
            
        }
        /* (non-Javadoc)
         * @see net.lantrack.framework.core.service.CrudService#deleteByIdsRe(java.lang.String, java.lang.String)
         */
        @Override
        public void deleteByIdsRe(String ids, String update_by) throws ServiceException {
            
        }
        /**
         * 根据id查本身和下一级列表
         */
        @Override
        public List<SysMenu> getAll(SysMenu entity) throws ServiceException{
            SysMenuExample menuExample = new SysMenuExample();
            SysMenuExample.Criteria cr = menuExample.createCriteria();
            if(entity.getId()!=null) {
            	cr.andParentIdEqualTo(entity.getId());
            }
            cr.andDelFlagEqualTo(BaseEntity.NO);
            menuExample.setOrderByClause("sort");
            try {
                List<SysMenu> list = this.sysMenuDao.selectByExample(menuExample);
                return list;
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }
        /* 根据父节点获取子节点
         * @see net.lantrack.framework.sysbase.service.SysMenuService#getMenuTest(java.lang.Integer)
         */
        @Override
        public List<SysMenuModel> getMenuAdmin(Integer parentId) {
            List<SysMenuModel> list = this.sysMenuDao.getIndexMenuByParentId(parentId);
            return list;
        }
        
        /* 获取树结构根据父id
         * @see net.lantrack.framework.sysbase.service.SysMenuService#getTreeByPid(java.lang.Integer)
         */
        @Override
        public List<MenuTreeModel> getTreeByPid(Integer pid) throws ServiceException {
            List<SysMenu> list = sysMenuDao.queryByPid(pid);
            if(list!=null){
                ArrayList<MenuTreeModel> tree = Lists.newArrayListWithExpectedSize(list.size());
                for(SysMenu menu:list){
                    MenuTreeModel m = new MenuTreeModel(
                            menu.getId(),menu.getParentId(),menu.getMenuName(),menu.getSort());
                    tree.add(m);
                }
                return tree;
            }
            return new ArrayList<MenuTreeModel>();
        }
        
        
        
        @Override
        public List<SysMenuModel> getMeunIndex(SysUser user) {
            //1先获取用户的所有菜单
            Set<Integer> menus = getUserMenus(user);
            //2查找拥有权限的以一级菜单
            List<SysMenuModel> list = this.sysMenuDao.getIndexMenuByParentId(ROOT_ID);
            removeNoAuth(menus,list);
//            Iterator<SysMenuModel> iterator = list.iterator();
//            while(iterator.hasNext()){
//                SysMenuModel next = iterator.next();
//                if(!menus.contains(next.getId())){
//                    iterator.remove();
//                }
//            }
            return list;
        }
        /**
         * 去除没有权限的菜单
         * @param menus
         * @param list
         * 2018年2月27日
         * @author lin
         */
        public void removeNoAuth(Set<Integer> menus,List<SysMenuModel> list){
            if(list==null||list.size()==0){
                return;
            }
            Iterator<SysMenuModel> iterator = list.iterator();
            while(iterator.hasNext()){
                SysMenuModel next = iterator.next();
                if(!menus.contains(next.getId())){
                    iterator.remove();
                }else{
                	 List<SysMenuModel> children = next.getChildren();
                	if(children!=null&&children.size()>0) {
                    	removeNoAuth(menus, next.getChildren());
                    }
                }
            }
        }
        @Override
        public List<String> getUserPermission(SysUser user) {
            // 1先获取用户的所有菜单id
            Set<Integer> menus = getUserMenus(user);
            if (menus != null && menus.size() > 0) {
                // 2在去菜单表中查找权限
            	List<Integer> menuIdList = new ArrayList<Integer>();
            	Iterator<Integer> iterator = menus.iterator();
            	while (iterator.hasNext()) {
            		menuIdList.add(iterator.next());
            	}
                List<String> permission = this.sysMenuDao.queryPermissionByidList(menuIdList);
                return permission;
            }
            return null;
        }
        
        @Override
        public Set<Integer> getUserMenus(SysUser user) {
            Set<Integer> menus = new HashSet<Integer>();
            //1查询拥有职务的权限
            /*if(user.getUserDuty()!=null){
                List<Integer> listByDuty = 
                        this.dutyMenuDao.queryMenuListByDuty(user.getUserDuty());
                if(listByDuty!=null){
                    for(Integer menuid:listByDuty){
                        menus.add(menuid);
                    }
                }
            }*/
            if (!StringUtils.isNotBlank(user.getId())) {
                return menus;
            }
            //2查询是否拥有角色的权限
            List<Integer> rolelist = this.userRoleDao.queryRoleListByUser(user.getId());
            if (rolelist != null && rolelist.size() > 0) {
                List<Integer> listByRole = this.roleMenuDao.queryMenuListByRoles(rolelist);
                if(listByRole!=null){
                    for(Integer menuid:listByRole){
                        menus.add(menuid);
                    }
                }
            } else {
                //3查询是否拥有直接配置的权限
                List<Integer> listByUser = this.userMenuDao.queryMenuListByUser(user.getId());
                if(listByUser!=null){
                    for(Integer menuid:listByUser){
                        menus.add(menuid);
                    }
                }
            }
            return menus;
        }
		@Override
		public List<MenuTreeModel> getAllMenuTree() {
			List<MenuTreeModel> modelList = new ArrayList<MenuTreeModel>();
			SysMenuExample menuExample = new SysMenuExample();
            SysMenuExample.Criteria cr = menuExample.createCriteria();
            cr.andDelFlagEqualTo("0");
            menuExample.setOrderByClause("sort");
            try {
                List<SysMenu> list = this.sysMenuDao.selectByExample(menuExample);
                if (list!=null && list.size()>0) {
                	for(SysMenu menu:list) {
                		MenuTreeModel model = new MenuTreeModel(menu.getId(), 
                				menu.getParentId(), menu.getMenuName(),menu.getSort());
                		modelList.add(model);
                	}
                }
                return modelList;
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
		}
		/**
		 * 获取系统管理员或者是拥有管理员角色的用户的所有权限标识集合
		 */
        @Override
        public List<String> getAdminPermission(Integer id) {
        	List<Integer> menuIdList = new ArrayList<Integer>();
        	// 系统管理员
        	if (UserUtil.ifAdmin()) {
        		menuIdList = this.roleMenuDao.queryMenuListByRole(ROOT_ID);
        	} else {
        		// 非系统管理员但是拥有其他角色的用户
        		menuIdList = this.roleMenuDao.queryMenuListByRole(id);
        	}
            List<String> permission = this.sysMenuDao.queryPermissionByidList(menuIdList);
            return permission;
        }
        
        /**
		 * 根据用户所拥有的menuIds获取对应的权限树
		 */
        @Override
        public List<MenuTreeModel> getUserMenuTreeModels(Set<Integer> menuIds) {
        	List<Integer> idList = new ArrayList<Integer>();
        	Iterator<Integer> iterator = menuIds.iterator();
        	while (iterator.hasNext()) {
        		idList.add(iterator.next());
        	}
        	List<SysMenuModel> sysMenuModelList = new ArrayList<SysMenuModel>();
        	List<MenuTreeModel> menuTreeModelList = new ArrayList<MenuTreeModel>();
        	try {
        		sysMenuModelList = this.sysMenuDao.queryModelListByidList(idList);
        		if (sysMenuModelList != null && sysMenuModelList.size() > 0) {
        			for (SysMenuModel sysMenuModel : sysMenuModelList) {
        				MenuTreeModel treeModel = new MenuTreeModel();
        				treeModel.setId(sysMenuModel.getId());
        				treeModel.setPid(sysMenuModel.getParentId());
        				treeModel.setName(sysMenuModel.getMenuName());
        				menuTreeModelList.add(treeModel);
        			}
        		}
				
			} catch (Exception e) {
				logger.error("SysMenuServiceImp getUserMenuTreeModels failed");
			}
        	return menuTreeModelList;
        }
		@Override
		public void updateMenu(SysMenu entity) {
			try {
				//查找父节点
	            SysMenu parentMenu = this.sysMenuDao.selectByPrimaryKey(entity.getParentId());
	            if(parentMenu==null){
	                return;
	            }
	            entity.setParentName(parentMenu.getMenuName());
	            entity.setParentIds(parentMenu.getParentIds()+","+parentMenu.getId());
	            this.sysMenuDao.updateByPrimaryKeySelective(entity);
			} catch (Exception e) {
				throw printException(e);
			}
			
		}
	

}