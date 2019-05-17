package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.SysAreaDao;
import net.lantrack.framework.sysbase.entity.SysArea;
import net.lantrack.framework.sysbase.entity.SysAreaExample;
import net.lantrack.framework.sysbase.entity.SysDict;
import net.lantrack.framework.sysbase.model.area.AreaModel;
import net.lantrack.framework.sysbase.service.SysAreaService;
import net.lantrack.framework.sysbase.service.SysDictService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 行政区域管理Service实现类
 * @author hww
 * @version 2018-01-08
 */
@Service
public class SysAreaServiceImp extends BaseService implements SysAreaService {
	
    @Autowired
	protected PageService pageService;

    @Autowired
	protected SysAreaDao sysAreaDao;
    
	@Autowired
	private SysDictService sysDictService;


    /**
	 * 添加区域
	 * @param entity  SysArea类型
	 * @return entity SysArea类型，含已生成的主键id
	 */
	@Override
	public SysArea save(SysArea entity) throws ServiceException {
		if (entity.getCreateBy() == null) {
			entity.setCreateBy(UserUtil.getCurrentUser());
		}
		if (entity.getUpdateBy() == null) {
			entity.setUpdateBy(UserUtil.getCurrentUser());
		}
		this.sysAreaDao.insert(entity);
		return entity;
	}
    
    
    /**
     * 修改区域
     * @param entity  SysArea类型
	 * @return entity SysArea类型
     */
	@Override
	public SysArea update(SysArea entity) throws ServiceException {
		if (entity.getCreateBy() == null) {
			entity.setCreateBy(UserUtil.getCurrentUser());
		}
		if (entity.getUpdateBy() == null) {
			entity.setUpdateBy(UserUtil.getCurrentUser());
		}
		this.sysAreaDao.updateByPrimaryKey(entity);
		return entity;
	}


	/**
	 * 根据id获取指定区域
	 * @param id  Integer类型
	 * @return 区域记录   SysArea类型
	 */
	@Override
	public SysArea queryById(Object id) {
		if (id == null || StringUtils.isBlank(id.toString())) {
            throw new ServiceException("id can not be null or empty!");
        }
		Integer pasrId = Integer.valueOf(id.toString());
		return this.sysAreaDao.selectByPrimaryKey(pasrId);
	}

	/**
	 * 根据id删除指定区域
	 * @param id 
     * @param update_by 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
	 */
	@Override
	public void deleteById(Object id, String update_by, boolean ifLogic)
			throws ServiceException {
		if (id == null) {
            throw new ServiceException("id can not be null or empty!");
        }
		Integer pasrId = Integer.valueOf(id.toString());
		if (ifLogic) {
			// 逻辑删除
        	SysArea entity = new SysArea();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
        	entity.setId(pasrId);
            this.sysAreaDao.updateByPrimaryKeySelective(entity);
        } else {
        	// 物理删除
            this.sysAreaDao.deleteByPrimaryKey(pasrId);
        }
	}

	/**
	 * 批量删除一组区域
	 * @param ids
     * @param update_by 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
	 */
	@Override
	public void deleteByIds(String ids, String update_by, boolean ifLogic)
			throws ServiceException {
		if (StringUtils.isBlank(ids)) {
            throw new ServiceException("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        SysAreaExample example = new SysAreaExample();
        SysAreaExample.Criteria cr = example.createCriteria();
        List<Integer> idList = new ArrayList<Integer>();
        if(split.length > 0) {
        	for(int i=0; i<split.length; i++) {
        		idList.add(Integer.parseInt(split[i]));
        	}
        }
        cr.andIdIn(idList);
        if (!ifLogic) {
            // 物理删除
            this.sysAreaDao.deleteByExample(example);
        } else {
            // 此处用逻辑删除
        	SysArea entity = new SysArea();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
            this.sysAreaDao.updateByExampleSelective(entity, example);
        }
		
	}

	@Override
	public void getPage(SysArea entity, PageEntity pageentity) {
		this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        
        try {
            List<AreaModel> tempList = this.sysAreaDao.getAreaListPage(pageentity, entity);
            SysDict sysDict = new SysDict();
        	sysDict.setType("area_type");
        	Map<String, String> dictMap = sysDictService.getDictMap(sysDict, 0);
        	List<AreaModel> areaList = new ArrayList<AreaModel>();
        	if (tempList != null && tempList.size() > 0) {
        		for (AreaModel area : tempList) {
        			area.setType(area.getType()==null?"":dictMap.get(area.getType()));
        			areaList.add(area);
        		}
        	}
            pageentity.setContent(areaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void getPageRe(SysArea entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 根据id删除指定区域以及其下所有子孙
	 * @param id 
     * @param update_by 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
	 */
	@Override
	@Transactional
	public void deleteByIdsRe(String id, String update_by) throws ServiceException {
		if (id == null) {
            throw new ServiceException("id can not be null or empty!");
        }
		// 删除自己
        this.sysAreaDao.deleteByPrimaryKey(Integer.valueOf(id));
        // 递归删除所有子节点
		SysAreaExample example = new SysAreaExample();
		SysAreaExample.Criteria cr = example.createCriteria();
		cr.andParentIdEqualTo(id);
		List<SysArea> list = this.sysAreaDao.selectByExample(example);
		for(SysArea sysArea : list) {
			if (sysArea != null && sysArea.getParentId() != null) {
				this.deleteByIdsRe(sysArea.getId()+"", update_by);
			}
		}
	}

	@Override
	public List<SysArea> getAll(SysArea entity) {
		SysAreaExample example = new SysAreaExample();
		SysAreaExample.Criteria cr = example.createCriteria();
		cr.andDelFlagEqualTo("0");
		if (StringUtils.isNotBlank(entity.getType())) {
			cr.andTypeEqualTo(entity.getType());
		}
		if (StringUtils.isNotBlank(entity.getParentId())) {
			cr.andParentIdEqualTo(entity.getParentId());
		}
        try {
        	SysDict sysDict = new SysDict();
        	sysDict.setType("area_type");
        	Map<String, String> dictMap = sysDictService.getDictMap(sysDict, 0);
        	List<SysArea> tempList = this.sysAreaDao.selectByExample(example);
        	List<SysArea> areaList = new ArrayList<SysArea>();
        	if (tempList != null && tempList.size() > 0) {
        		for (SysArea area : tempList) {
        			area.setType(area.getType()==null?"":dictMap.get(area.getType()));
        			areaList.add(area);
        		}
        	}
            return areaList;
        } catch (Exception e) {
            this.logger.error("sysAreaServiceImp getAll has Error:",e);
            return null;
        }
	}
	
	/**
	 * 根据当前节点的id查询其儿子节点集合
	 * @param id 当前节点id String类型
	 * @return list 儿子节点集合
	 */
	public List<SysArea> getChildrenById(String id) {
		SysAreaExample example = new SysAreaExample();
		SysAreaExample.Criteria cr = example.createCriteria();
		cr.andParentIdEqualTo(id);
		cr.andDelFlagEqualTo("0");
		List<SysArea> list = this.sysAreaDao.selectByExample(example);
		return list;
	}

}