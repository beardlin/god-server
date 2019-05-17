package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.ImportLogDao;
import net.lantrack.framework.sysbase.entity.ImportLog;
import net.lantrack.framework.sysbase.entity.ImportLogExample;
import net.lantrack.framework.sysbase.service.ImportLogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImportLogServiceImp extends BaseService implements ImportLogService {
	
	@Autowired
	protected PageService pageService;
	
	@Autowired
	protected ImportLogDao importLogDao;

	/**
	 * 操作日志没有修改方法
	 */
	@Override
	public ImportLog update(ImportLog entity) throws ServiceException {
		 return null;
	}

	/**
	 * 保存日志
	 * @param entity  ImportLog类型
	 * @return entity ImportLog类型，含已生成的主键id
	 */
	@Override
	public ImportLog save(ImportLog entity) throws ServiceException {
		this.importLogDao.insert(entity);
		return entity;
	}

	/**
	 * 根据id获取指定日志
	 * @param id  Integer类型
	 * @return 日志记录   ImportLog类型
	 */
	public ImportLog queryModelById(Object id) {
		if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
		ImportLog model = new ImportLog();
		try {
			model = this.importLogDao.selectByPrimaryKey(Integer.valueOf(id.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@Override
	public ImportLog queryById(Object id) {
		if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
		ImportLog entity = new ImportLog();
		return entity;
	}

	/**
	 * 根据id删除指定日志
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
		if (ifLogic) {
			// 逻辑删除
			ImportLog entity = new ImportLog();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
        	entity.setId(Integer.valueOf(id.toString()));
            this.importLogDao.updateByPrimaryKey(entity);
        } else {
        	// 物理删除
            this.importLogDao.deleteByPrimaryKey(Integer.valueOf(id.toString()));
        }
	}

	/**
	 * 批量删除一组操作日志
	 * @param ids
     * @param update_by 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
	 */
	@Override
	public void deleteByIds(String ids, String update_by, boolean ifLogic)
			throws ServiceException {
		if (StringUtils.isBlank(ids)) {
            throw new ServiceException("ids can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        ImportLogExample example = new ImportLogExample();
        ImportLogExample.Criteria cr = example.createCriteria();
        List<String> idList = new ArrayList<String>(Arrays.asList(split));
        cr.andIdIn(idList);
        if (!ifLogic) {
            // 物理删除
            this.importLogDao.deleteByExample(example);
        } else {
            // 此处用逻辑删除
        	ImportLog entity = new ImportLog();
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
            this.importLogDao.updateByExampleSelective(entity, example);
        }
	}

	@Override
	public void getPage(ImportLog entity, PageEntity pageentity) {
		this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        if (StringUtils.isBlank(entity.getModelType())) {
        	entity.setModelType(null);
        }
        try {
            List<ImportLog> result = this.importLogDao.getLogListPage(pageentity, entity);
            pageentity.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void getPageRe(ImportLog entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIdsRe(String ids, String update_by)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ImportLog> getAll(ImportLog entity) {
		// TODO Auto-generated method stub
		return null;
	}


	
}