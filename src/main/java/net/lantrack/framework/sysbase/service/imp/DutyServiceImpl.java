/*
 * Copyright 2017 Lantrack Corporation All Rights Reserved.
 *
 * The source code contained or described herein and all documents related to
 * the source code ("Material") are owned by Lantrack Corporation or its suppliers
 * or licensors. Title to the Material remains with Lantrack Corporation or its
 * suppliers and licensors. The Material contains trade secrets and proprietary
 * and confidential information of Lantrack or its suppliers and licensors. The
 * Material is protected by worldwide copyright and trade secret laws and
 * treaty provisions.
 * No part of the Material may be used, copied, reproduced, modified, published
 * , uploaded, posted, transmitted, distributed, or disclosed in any way
 * without Lantrack's prior express written permission.
 *
 * No license under any patent, copyright, trade secret or other intellectual
 * property right is granted to or conferred upon you by disclosure or delivery
 * of the Materials, either expressly, by implication, inducement, estoppel or
 * otherwise. Any license under such intellectual property rights must be
 * express and approved by Intel in writing.
 *
 */
package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.DutyDao;
import net.lantrack.framework.sysbase.dao.DutyMenuDao;
import net.lantrack.framework.sysbase.entity.Duty;
import net.lantrack.framework.sysbase.entity.DutyExample;
import net.lantrack.framework.sysbase.entity.DutyMenu;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.service.DutyService;
import net.lantrack.framework.sysbase.service.SysMenuService;
import net.lantrack.framework.sysbase.service.SysOfficeTreeService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职务管理service
 * 2018年1月16日
 * @author lin
 */
@Service
public class DutyServiceImpl extends BaseService implements DutyService{
	
    @Autowired()
    PageService pageService;
    
    @Autowired
    DutyDao dutyDao;
    
    @Autowired
    DutyMenuDao dutyMenuDao;
	
	@Autowired
	SysMenuService sysMenuService;
	
	@Autowired
	SysOfficeTreeService sysOfficeService;
    
    @Override
    public Duty update(Duty entity) throws ServiceException {
        DutyExample dutyExample = new DutyExample();
        DutyExample.Criteria cr = dutyExample.createCriteria();
        cr.andDutyNameEqualTo(entity.getDutyName());
        cr.andOfficeIdEqualTo(entity.getOfficeId());
        List<Duty> list = this.dutyDao.selectByExample(dutyExample);
        boolean up=false;
        if(list!=null&&list.size()>0){
            Duty dict = list.get(0);
           // entity.setCreateBy(dict.getCreateBy());
            if(dict.getId().equals(entity.getId())){
                up = true;
            }
        }else{
            up=true;
        }
        if(up){
            try {
//            	//获得部门信息
//            	SysOffice s =sysOfficeService.queryById(entity.getOfficeId());
//            	entity.setOfficeName(s.getOfficeName());
                this.dutyDao.updateByPrimaryKeySelective(entity);
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }else{
            throw new ServiceException("当前部门下已存在相同职务！");
        }
        return entity;
    }

   
    @Override
    public Duty save(Duty entity) throws ServiceException {
        DutyExample dutyExample = new DutyExample();
        DutyExample.Criteria cr = dutyExample.createCriteria();
        cr.andDutyNameEqualTo(entity.getDutyName());
        cr.andOfficeIdEqualTo(entity.getOfficeId());
        List<Duty> list = this.dutyDao.selectByExample(dutyExample);
        if (list != null && list.size() > 0) {
            throw new ServiceException("当前部门下已存在相同职务！");
        } else {
            try {
//            	//获得部门信息
//            	SysOffice s =sysOfficeService.queryById(entity.getOfficeId());
//            	entity.setOfficeName(s.getOfficeName());
                this.dutyDao.insertSelective(entity);
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }
        return entity;
    }
    
    /**
	 * 保存带权限的职务信息
	 * @param entity
	 * @param menus
	 * @return entity Duty类型
	 * @throws ServiceException
	 */
    @Override
    @Transactional
	public Duty save(Duty entity, String menus) throws ServiceException {
    	entity = this.save(entity);
    	this.addPermission(entity.getId(), menus);
		return entity;
	}
    
    /**
	 * 修改带权限的职务信息
	 * @param entity
	 * @param menus
	 * @return
	 * @throws ServiceException
	 */
    @Override
    @Transactional
    public Duty update(Duty entity, String menus) throws ServiceException {
    	entity = this.update(entity);
    	this.addPermission(entity.getId(), menus);
		return entity;
    }

    
    @Override
    public Duty queryById(Object id) {
        if (id==null) {
            this.logger.error("id can not be null or empty!");
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        Duty duty = null;
        try {
            duty = this.dutyDao.selectByPrimaryKey(pasId);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
        return  duty;
    }

    
    @Override
    public void deleteById(Object id, String upBy, boolean ifLogic) throws ServiceException {
        if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        if (!ifLogic) {
            // 物理删除
            this.dutyDao.deleteByPrimaryKey(pasId);
        } else {
            // 逻辑删除结束
            Duty duty = new Duty();
            duty.setCreateDate(null);
            duty.setUpdateBy(upBy);
            duty.setDelFlag(BaseEntity.YES);
            duty.setId(pasId);
            this.dutyDao.updateByPrimaryKeySelective(duty);
        }
    }

    @Override
    @Transactional
    public void deleteByIds(String ids, String upBy, boolean ifLogic) throws ServiceException {
        if (StringUtils.isBlank(ids)) {
            this.logger.error("id can not be null or empty!");
            throw new ServiceException("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        DutyExample dutyExample = new DutyExample();
        DutyExample.Criteria cr = dutyExample.createCriteria();
        cr.andIdIn(Arrays.asList(split));
        try {
            if (!ifLogic) {
                // 物理删除
                this.dutyDao.deleteByExample(dutyExample);
            } else {
                // 此处用逻辑删除
                Duty duty = new Duty();
                duty.setCreateDate(null);
                duty.setUpdateBy(upBy);
                duty.setDelFlag(BaseEntity.YES);
                this.dutyDao.updateByExampleSelective(duty, dutyExample);
            }
            //删除其下所有权限
            this.dutyMenuDao.deleteByDutyId(Arrays.asList(split));
        } catch (Exception e) {
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
        
        
    }

    
    @Override
    public void getPage(Duty entity, PageEntity pageentity) throws ServiceException{
        this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        try {
            List<Duty> result = this.dutyDao.getDutyListPage(pageentity, entity);
            pageentity.setContent(result);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#getPageRe(net.lantrack.framework.core.entity.BaseEntity, net.lantrack.framework.core.entity.PageEntity)
     */
    @Override
    public void getPageRe(Duty entity, PageEntity pageentity) {
        
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
    public List<Duty> getAll(Duty entity) {
        return null;
    }

    /**
     * 根据部门id获取其下职务
     */
    @Override
    public List<Duty> getDutyByOfficeId(String id) {
        if(StringUtils.isBlank(id)){
            this.logger.error("id can not be null or empty!");
            throw new ServiceException("id can not be null or empty!");
        }
        DutyExample dutyExample = new DutyExample();
        DutyExample.Criteria cr = dutyExample.createCriteria();
        cr.andOfficeIdEqualTo(id);
        try {
            List<Duty> list = this.dutyDao.selectByExample(dutyExample);
            return list;
        } catch (Exception e) {
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 配置权限
     */
    @Override
    public void addPermission(Integer id, String menus) throws ServiceException{
        if(id==null||StringUtils.isBlank(menus)){
            throw new ServiceException("参数异常");
        }
        String[] split = menus.split(",");
        List<DutyMenu> dmList = new ArrayList<DutyMenu>(split.length);
        for(String menu:split){
            DutyMenu dm = new DutyMenu(id, Integer.valueOf(menu));
            dmList.add(dm);
        }
        List<String> duList = new ArrayList<String>(1);
        duList.add(id.toString());
        this.dutyMenuDao.deleteByDutyId(duList);
        //单条插入
//        for(DutyMenu dm:dmList){
//            try {
//                this.dutyMenuDao.insert(dm);
//            } catch (Exception e) {
//               continue;
//            }
//        }
        //批量插入
        try {
            this.dutyMenuDao.insertList(dmList);
        } catch (Exception e) {
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
    }


    
    @Override
    public Map<String, Object> getMenuTreeByDutyId(Integer id) throws ServiceException{
        Map<String, Object> map = new HashMap<String, Object>();
		List<MenuTreeModel> currentMenuList = new ArrayList<MenuTreeModel>();
		List<MenuTreeModel> menuList = new ArrayList<MenuTreeModel>();
		List<Integer> checkedMenus = new ArrayList<Integer>();
		// 先获取当前登录用户所有的权限列表
		String currentUserId = UserUtil.getCurrentUser();
		if (StringUtils.isNotBlank(currentUserId)) {
    		if (UserUtil.ifAdmin()) {
    			currentMenuList = sysMenuService.getAllMenuTree();
    		} else {
    			currentMenuList = this.dutyMenuDao.getMenuByDutyId(id);
    		}
    		map.put("menusAll", currentMenuList.size()>0 ? currentMenuList : "");
		}
		// 再获取操作的角色目前已有的权限列表
		menuList = this.dutyMenuDao.getMenuByDutyId(id);
        if (menuList != null && menuList.size() > 0) {
        	for (MenuTreeModel menu : menuList) {
        		checkedMenus.add(menu.getId());
        	}
        }
        map.put("checkedMenus", checkedMenus);
        
		return map;
	}
        
    @Override
    public Map<Integer, String> getMapByOfficeId(String officeId) {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	DutyExample example = new DutyExample();
    	DutyExample.Criteria cr = example.createCriteria();
    	cr.andDelFlagEqualTo("0");
    	if (StringUtils.isNotBlank(officeId)) {
    		cr.andOfficeIdEqualTo(officeId);
    	}
    	List<Duty> list = this.dutyDao.selectByExample(example);
    	if (list != null && list.size() > 0) {
    		for (int i=0; i < list.size(); i++) {
    			Duty duty = list.get(i);
    			map.put(duty.getId(), duty.getDutyName());
    		}
    		return map;
    	} else {
    		this.logger.warn("DutyServiceImp getMapByOfficeId(officeId) return empty");
    		return null;
    	}
    }


	
}
