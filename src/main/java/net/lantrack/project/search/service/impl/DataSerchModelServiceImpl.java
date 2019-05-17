package net.lantrack.project.search.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.util.UserUtil;
import net.lantrack.project.search.dao.DataSerchModelDao;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.entity.DataSerchModelExample;
import net.lantrack.project.search.entity.DataSerchModelExample.Criteria;
import net.lantrack.project.search.service.DataSerchModelService;

@Service
public class DataSerchModelServiceImpl implements DataSerchModelService {

	@Autowired
	protected DataSerchModelDao  dataSerchModelDao;
	
	@Autowired
	protected PageService  pageService;
	
	
	@Override
	public DataSerchModel update(DataSerchModel entity) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	//校验数据重复性
	private ServiceException validateRepeat(DataSerchModel entity) {
		DataSerchModelExample example  = new DataSerchModelExample();
		Criteria cr = example.createCriteria();
		cr.andModelNameEqualTo(entity.getModelName());
		List<DataSerchModel> list = this.dataSerchModelDao.selectByExample(example);
		if(list!=null&&list.size()>0) {
			DataSerchModel model = list.get(0);
			if(!model.getId().equals(entity.getId())) {
				return new ServiceException("当前模板名称已存在");
			}
		}
		return null;
	}
	
	@Override
	public DataSerchModel save(DataSerchModel entity) throws ServiceException {
		ServiceException validata = validateRepeat(entity);
		if(validata!=null) {
			throw validata;
		}
		try {
			this.dataSerchModelDao.insert(entity);
			return entity;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public DataSerchModel queryById(Object id) {
		if(id==null) {
			throw new ServiceException("id 不能为空");
		}
		try {
			return this.dataSerchModelDao.selectByPrimaryKey(Integer.valueOf(id.toString()));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void deleteById(Object id, String upBy, boolean ifLogic) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIds(String ids, String upBy, boolean ifLogic) throws ServiceException {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("请求参数异常");
		}
		String[] split = ids.split(",");
		DataSerchModelExample example = new DataSerchModelExample();
		Criteria cr = example.createCriteria();
		cr.andIdIn(Arrays.asList(split));
		try {
			this.dataSerchModelDao.deleteByExample(example);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void getPage(DataSerchModel entity, PageEntity page) {
		this.pageService.getPage(page.getPerPageCount(), page.getCurrentPage());
		DataSerchModelExample example = new DataSerchModelExample();
		//拼接查询条件
		String currentUser = UserUtil.getCurrentUser();
		String officeId = UserUtil.getUser().getOfficeId();
		if(officeId==null){
			officeId = "";
		}
		//自己可见
		Criteria cr = example.createCriteria();
		cr.andCreateByEqualTo(currentUser);
		cr.andShowWhoEqualTo(show_myself);
		//部门可见
		Criteria cr1 = example.createCriteria();
		cr1.andOfficeCodeEqualTo(officeId);
		cr1.andShowWhoEqualTo(show_myoffice);
		example.or(cr1);
		//所有人可见
		Criteria cr2 = example.createCriteria();
		cr2.andShowWhoEqualTo(show_all);
		example.or(cr2);
		try {
			List<DataSerchModel> list = this.dataSerchModelDao.getListPage(example, page);
			page.setContent(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
	@Override
	public void getPageRe(DataSerchModel entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void deleteByIdsRe(String ids, String update_by) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DataSerchModel> getAll(DataSerchModel entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
