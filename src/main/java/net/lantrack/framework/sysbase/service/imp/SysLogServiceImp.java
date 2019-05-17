package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.SysLogDao;
import net.lantrack.framework.sysbase.entity.SysLog;
import net.lantrack.framework.sysbase.entity.SysLogExample;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.log.LogModel;
import net.lantrack.framework.sysbase.service.SysLogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志管理Service实现类
 * @author hww
 * @version 2018-01-09
 */
@Service
public class SysLogServiceImp extends BaseService implements SysLogService {
	
	@Autowired
	protected PageService pageService;
	
	@Autowired
	protected SysLogDao sysLogDao;

	/**
	 * 操作日志没有修改方法
	 */
	@Override
	public SysLog update(SysLog entity) throws ServiceException {
		 return null;
	}

	/**
	 * 保存日志
	 * @param entity  SysLog类型
	 * @return entity SysLog类型，含已生成的主键id
	 */
	@Override
	public SysLog save(SysLog entity) throws ServiceException {
		this.sysLogDao.insert(entity);
		return entity;
	}

	/**
	 * 根据id获取指定日志
	 * @param id  Integer类型
	 * @return 日志记录   SysLog类型
	 */
	public LogModel queryModelById(Object id) {
		if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
		LogModel model = new LogModel();
		try {
			model = this.sysLogDao.selectByPrimaryKey(Long.valueOf(id.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@Override
	public SysLog queryById(Object id) {
		if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
		SysLog entity = new SysLog();
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
			SysLog entity = new SysLog();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
        	entity.setId(Long.valueOf(id.toString()));
            this.sysLogDao.updateByPrimaryKey(entity);
        } else {
        	// 物理删除
            this.sysLogDao.deleteByPrimaryKey(Long.valueOf(id.toString()));
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
        SysLogExample example = new SysLogExample();
        SysLogExample.Criteria cr = example.createCriteria();
        List<String> idList = new ArrayList<String>(Arrays.asList(split));
        cr.andIdIn(idList);
        if (!ifLogic) {
            // 物理删除
            this.sysLogDao.deleteByExample(example);
        } else {
            // 此处用逻辑删除
        	SysLog entity = new SysLog();
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
            this.sysLogDao.updateByExampleSelective(entity, example);
        }
	}

	@Override
	public void getPage(SysLog entity, PageEntity pageentity) {
		this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        if (StringUtils.isBlank(entity.getStartDate())) {
        	entity.setStartDate(null);
        }
        if (StringUtils.isBlank(entity.getEndDate())) {
        	entity.setEndDate(null);
        }
        if (StringUtils.isBlank(entity.getType())) {
        	entity.setType(null);
        }
        try {
            List<LogModel> result = this.sysLogDao.getLogListPage(pageentity, entity);
            for (LogModel logModel : result) {
            	logModel.setType(LogType.getValueByKey(logModel.getType()));
            }
            pageentity.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void getPageRe(SysLog entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIdsRe(String ids, String update_by)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SysLog> getAll(SysLog entity) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void clearLog() throws ServiceException {
        try {
            this.sysLogDao.clearLog();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

	
}