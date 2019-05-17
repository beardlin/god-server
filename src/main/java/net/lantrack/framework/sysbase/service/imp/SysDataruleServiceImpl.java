package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.sysbase.dao.SysDataruleMapper;
import net.lantrack.framework.sysbase.entity.SysDatarule;
import net.lantrack.framework.sysbase.entity.SysDataruleExample;
import net.lantrack.framework.sysbase.entity.SysDataruleExample.Criteria;
import net.lantrack.framework.sysbase.model.menu.DataRuleTree;
import net.lantrack.framework.sysbase.service.AuthorizingService;
import net.lantrack.framework.sysbase.service.SysDataruleService;
import net.lantrack.framework.sysbase.util.UserUtil;

@Service
public class SysDataruleServiceImpl extends BaseService implements SysDataruleService {

	@Autowired
	protected SysDataruleMapper sysDataruleMapper;
	
	@Autowired
	protected AuthorizingService authorizingService;
	
	@Autowired
	protected BaseDao baseDao;
	
	@Override
	public List<SysDatarule> list(Integer menuId) {
		try {
			SysDataruleExample example = new SysDataruleExample();
			Criteria cr = example.createCriteria();
			cr.andMenuIdEqualTo(menuId);
			return sysDataruleMapper.selectByExample(example);
		} catch (Exception e) {
			printException(e);
		}
		return null;
	}

	@Override
	public SysDatarule detial(Integer id) {
		try {
			SysDatarule datarule = sysDataruleMapper.selectByPrimaryKey(id);
			return datarule;
		} catch (Exception e) {
			printException(e);
		}
		return null;
	}

	@Override
	public void delete(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("id不能为空");
		}
		String[] split = ids.split(",");
		try {
			SysDataruleExample example = new SysDataruleExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(Arrays.asList(split));
			sysDataruleMapper.deleteByExample(example);
		} catch (Exception e) {
			throw printException(e);
		}
	}

	boolean validateRepeat(SysDatarule rule) {
		boolean repeat = false;
		SysDataruleExample example = new SysDataruleExample();
		Criteria cr = example.createCriteria();
		cr.andMenuIdEqualTo(rule.getMenuId());
		cr.andRuleNameEqualTo(rule.getRuleName());
		List<SysDatarule> list = sysDataruleMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			for(SysDatarule bean:list) {
				if(!bean.getId().equals(rule.getId())) {
					repeat = true;
				}
			}
		}
		return repeat;
	}
	
	@Override
	public void update(SysDatarule rule) {
		if(validateRepeat(rule)) {
			throw new ServiceException("当前规则名称已存在");
		}
		try {
			sysDataruleMapper.updateByPrimaryKeySelective(rule);
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public Integer save(SysDatarule rule) {
		if(validateRepeat(rule)) {
			throw new ServiceException("当前规则名称已存在");
		}
		try {
			sysDataruleMapper.insertSelective(rule);
			return rule.getId();
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public List<DataRuleTree> dataruleTree() {
		List<DataRuleTree> list = new ArrayList<>();
		try {
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("select id,parent_id as pid,menu_name as name,sort ");
			//管理员查看所有
			if(UserUtil.ifAdmin()) {
				sqlbuf.append(" from sys_menu where target = '0'");
			}else {
				//查询当前用户能查看的菜单id
				Set<Integer> menuId = this.authorizingService.getMenuIdsByUserId(UserUtil.getCurrentUserId());
				if(menuId==null || menuId.size()==0) {
					return list;
				}
				StringBuffer idsBuf = new StringBuffer();
				for(Integer mid:menuId) {
					idsBuf.append("'").append(mid).append("',");
				}
				String ids  = "";
				if(idsBuf.length()>0) {
					ids = idsBuf.substring(0, idsBuf.length()-1);
				}
				sqlbuf.append(" from sys_menu where  id in (").append(ids).append(") and target = '0'");
			}
			
			//只查询菜单权限
			List<DataRuleTree> menuList = this.baseDao.queryList(sqlbuf.toString(), DataRuleTree.class);
			if(menuList==null || menuList.size()==0) {
				return list;
			}
			list.addAll(menuList);
			//查询当前菜单下的数据规则
			StringBuffer idsBuf = new StringBuffer();
			for(DataRuleTree menu:menuList) {
				idsBuf.append("'").append(menu.getId()).append("',");
			}
			if(idsBuf.length()>0) {
				String ids = idsBuf.substring(0, idsBuf.length()-1);
				//查询数据规则
				sqlbuf = new StringBuffer("select id,menu_id as pid,rule_name as name,'10000' as sort,'2' as type  ");
				sqlbuf.append(" from sys_datarule where menu_id in (").append(ids).append(")");
				List<DataRuleTree> datarule = this.baseDao.queryList(sqlbuf.toString(), DataRuleTree.class);
				list.addAll(datarule);
			}
		} catch (Exception e) {
			throw printException(e);
		}
		return list;
	}
	
	
}
