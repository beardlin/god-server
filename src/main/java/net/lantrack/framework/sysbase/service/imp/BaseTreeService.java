package net.lantrack.framework.sysbase.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.util.IdGen;
import net.lantrack.framework.sysbase.model.TreeModel;
import net.lantrack.framework.sysbase.service.TreeService;

@Service
public abstract class BaseTreeService extends BaseService implements TreeService{
	
	@Autowired
	protected BaseDao baseDao;
	/**
	 * 获取树形表名称
	 * @return
	 * @date 2019年3月12日
	 */
	public abstract String getTableName();

	@Override
	public List<TreeModel> getTree(String pid) {
		try {
			StringBuffer sql = new StringBuffer("select id,p_id,t_name from ");
			sql.append(getTableName()).append(" order by o_sort ");
			return this.baseDao.queryList(sql.toString(), TreeModel.class);
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public String deleteTreeNode(String id) {
		try {
			StringBuffer delSql = new StringBuffer("delete from ").append(getTableName());
			delSql.append(" where id = '").append(id).append("' or p_ids like '%");
			delSql.append(id).append("%'");
			this.baseDao.deleteSql(delSql.toString());
		} catch (Exception e) {
			throw printException(e);
		}
		return "";
	}

	@Override
	@Transactional
	public String updateTreeNode(String id, String nodeName) {
		if(StringUtils.isBlank(id) || StringUtils.isBlank(nodeName)) {
			throw printException(new ServiceException("节点id和节点名称不能为空"));
		}
		try {
			//查看当前名称是否有变动
			Map<String,Object> map = this.baseDao.querySingleMap("select t_name,full_name from "+getTableName()+" where id='"+id+"'");
			Object t_name = map.get("t_name");
			if(t_name==null||nodeName.equals(t_name)) {
				return "";
			}
			//修改全名称
			Object full_name = map.get("full_name");
			String fName = "";
			if(full_name != null) {
				fName = full_name.toString();
				int lastIndexOf = fName.lastIndexOf("_");
				String sub_full_name = fName.substring(0, lastIndexOf);
				fName = sub_full_name+"_"+nodeName;
			}
			//修改当前节点名称
			StringBuffer updateSql = new StringBuffer("update ").append(getTableName()).append(" set t_name='");
			updateSql.append(nodeName).append("',full_name ='").append(fName)
			.append("' where id = '").append(id).append("'");
			this.baseDao.updateSql(updateSql.toString());
			//修改子节点名称
			StringBuffer updateChildSql = new StringBuffer("update ").append(getTableName()).append(" set p_name='");
			updateChildSql.append(nodeName).append("' where p_id = '").append(id).append("'");
			this.baseDao.updateSql(updateChildSql.toString());
			//修改子孙节点名称
//			UPDATE `id_user` SET u_name = REPLACE(u_name,'_tom','_bob')
			StringBuffer updateChildsSql = new StringBuffer("update ").append(getTableName()).append(" set full_name=");
			updateChildsSql.append("REPLACE(full_name,'_").append(t_name).append("_','_").append(nodeName).append("_')")
			.append(" where p_ids like '%").append(id).append("%'");
			this.baseDao.updateSql(updateChildsSql.toString());
		} catch (Exception e) {
			throw printException(e);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		String fName = "组织机构_西城区疾控_中心领导_新节点_节点11";
//		int lastIndexOf = fName.lastIndexOf("_");
//		String sub_full_name = fName.substring(0, lastIndexOf);
//		System.out.println(sub_full_name);
//	}

	@Override
	public String addTreeNode(String pId, String nodeName) {
		if(StringUtils.isBlank(pId) || StringUtils.isBlank(nodeName)) {
			throw printException(new ServiceException("父节点id和节点名称不能为空"));
		}
		String uuid = IdGen.uuid();
		try {
			//校验数据重复性
//			if(validateRepeat("", pId, nodeName)) {
//				throw new ServiceException("当前节点已存在");
//			}
			//获取父节点信息
			Map<String,Object> map = this.baseDao.querySingleMap("select t_name,p_ids,full_name from "+getTableName()+" where id='"+pId+"'");
			String pIds = pId;
			String fullName = nodeName;
			Object p_name = map.get("t_name");
			Object p_ids = map.get("p_ids");
			if(p_ids!=null&&!"".equals(p_ids)) {
				pIds = p_ids.toString()+","+pId;
			}
			Object full_name = map.get("full_name");
			if(full_name!=null&&!"".equals(full_name)) {
				fullName = full_name.toString()+"_"+nodeName;
			}
			//获取当前父节点下排序
			String sort = getMaxSortByPid(pId);
			//插入数据
			StringBuffer sql = new StringBuffer("insert into ").append(getTableName());
			sql.append("(id,t_name,p_id,p_name,p_ids,full_name,o_sort) values (")
			.append("'").append(uuid).append("',")
			.append("'").append(nodeName).append("',")
			.append("'").append(pId).append("',")
			.append("'").append(p_name).append("',")
			.append("'").append(pIds).append("',")
			.append("'").append(fullName).append("',")
			.append("'").append(sort).append("')");
			this.baseDao.insertSql(sql.toString());
		} catch (Exception e) {
			throw printException(e);
		}
		return uuid;
	}
	
	//查看当前部门下最大排序号
	private String getMaxSortByPid(String pid) {
		String sortl = "0";
		String sql = "select max(o_sort) as o_sort from "+getTableName()+" where p_id = '"+pid+"'";
		Map<String, Object> sortMap = this.baseDao.querySingleMap(sql);
		Object o_sort = sortMap.get("o_sort");
		if(o_sort!=null&&StringUtils.isNotBlank(o_sort.toString())) {
			sortl = (Long.valueOf(o_sort.toString())+1)+"";
		}
		return sortl;
	}
	
	/**
	 * 校验节点的重复性
	 * @param id
	 * @param pid
	 * @param nodeName
	 * @return
	 * @date 2019年3月12日
	 */
	public boolean validateRepeat(String id,String pid, String nodeName) {
//		if(list!=null&&list.size()>0) {
//			SysOfficeTree office = list.get(0);
//			if(!office.getId().equals(id)) {
//				return true;
//			}
//		}
		return false;
	}

	
	
}
