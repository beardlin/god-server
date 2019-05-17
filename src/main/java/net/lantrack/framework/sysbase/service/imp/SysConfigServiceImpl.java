package net.lantrack.framework.sysbase.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.sysbase.dao.SysConfigMapper;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.entity.SysConfigExample;
import net.lantrack.framework.sysbase.entity.SysConfigExample.Criteria;
import net.lantrack.framework.sysbase.service.SysConfigService;
@Service
//@Service("configServiceImpl")
public class SysConfigServiceImpl extends BaseService implements SysConfigService {

	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	@Autowired
	BaseDao baseDao;
	
	@Override
	public void updateConfig(SysConfig config) {
		try {
			SysConfigExample example = new SysConfigExample(); 
			Criteria cr = example.createCriteria();
			cr.andConfNameEqualTo(config.getConfName());
			this.sysConfigMapper.updateByExampleSelective(config, example);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public SysConfig getConfigByName(String name) {
		try {
			SysConfigExample example = new SysConfigExample(); 
			Criteria cr = example.createCriteria();
			cr.andConfNameEqualTo(name);
			List<SysConfig> list = this.sysConfigMapper.selectByExample(example);
			if(list!=null&&list.size()==1) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<SysConfig> getConfigList() {
		SysConfigExample example = new SysConfigExample(); 
		try {
			return this.sysConfigMapper.selectByExample(example);
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	@Transactional
	public void updateConfigList(List<SysConfig> configs) {
		if(configs==null || configs.size()==0) {
			return ;
		}
		try {
			for(SysConfig cf:configs) {
				String cfName = cf.getConfName();
				String cfVal = cf.getConfValue();
				StringBuffer updateBuf = new StringBuffer();
				updateBuf.append(" update sys_config set conf_value='").append(cfVal).append("' ");
				updateBuf.append(" where conf_name = '").append(cfName).append("'");
				baseDao.updateSql(updateBuf.toString());
			}
		} catch (Exception e) {
			throw printException(e);
		}
	}

}
