package net.lantrack.framework.sysbase.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.IdGen;
import net.lantrack.framework.springplugin.PropertyPlaceholder;
import net.lantrack.framework.sysbase.dao.SysOfficeTreeMapper;
import net.lantrack.framework.sysbase.entity.SysOfficeTree;
import net.lantrack.framework.sysbase.entity.SysOfficeTreeExample;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.model.office.OfficeModel;
import net.lantrack.framework.sysbase.service.SysOfficeTreeService;
import net.lantrack.framework.sysbase.util.UserUtil;
import net.lantrack.framework.sysbase.util.http.SsoApiUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 组织机构树形表      
 * @date 2019年3月12日
 */
@Service("sysOfficeTreeServiceImpl")
public class SysOfficeTreeServiceImpl extends BaseTreeService implements SysOfficeTreeService{
	
	@Autowired
	protected SysOfficeTreeMapper sysOfficeTreeMapper;
	
	@Autowired
	protected PageService pageService;
	
	@Autowired
	protected BaseDao baseDao;

	/**
	 * 添加组织机构
	 * @param entity  SysOffice类型
	 * @return entity SysOffice类型，含已生成的主键id
	 */
	@Override
	public SysOfficeTree save(SysOfficeTree entity) throws ServiceException {
		try {
			boolean repeat = validateRepeat("", entity.getpId(), entity.gettName());
			if(repeat) {
				throw new ServiceException("当前名称已存在");
			}
			//新添加的默认在后边追加
			if(entity.getoSort()==null) {
				entity.setoSort(getMaxSortByPid(entity.getpId()));
			}
			SysOfficeTree parent = queryById(entity.getpId());
			if(parent!=null) {
				entity.setpName(parent.gettName());
				//拼接pids和fullname
				String fullName = parent.gettName();
				if(StringUtils.isNotBlank(parent.getFullName())) {
					fullName  = parent.getFullName() + "," + entity.gettName();
				}
				String parentIds = parent.getId();
				if(StringUtils.isNotBlank(parent.getpIds())) {
					parentIds  = parent.getpIds() + "," + parent.getId();
				}
				entity.setFullName(fullName);
				entity.setpIds(parentIds);
			}
			entity.setId(IdGen.uuid());
			this.sysOfficeTreeMapper.insertSelective(entity);
			return entity;
		} catch (Exception e) {
			throw printException(e);
		}
		
	}
	
	/**
     * 修改组织机构
     * @param entity  SysOffice类型
	 * @return entity SysOffice类型
     */
	@Override
	public SysOfficeTree update(SysOfficeTree entity) throws ServiceException {
		try {
			boolean repeat = validateRepeat(entity.getId(), entity.getpId(), entity.gettName());
			if(repeat) {
				throw new ServiceException("当前名称已存在");
			}
			SysOfficeTree parent = queryById(entity.getpId());
			if(parent!=null) {
				entity.setpName(parent.gettName());
				//拼接pids和fullname
				String fullName = parent.gettName();
				if(StringUtils.isNotBlank(parent.getFullName())) {
					fullName  = parent.getFullName() + "_" + entity.gettName();
				}
				String parentIds = parent.getId();
				if(StringUtils.isNotBlank(parent.getpIds())) {
					parentIds  = parent.getpIds() + "," + parent.getId();
				}
				entity.setFullName(fullName);
				entity.setpIds(parentIds);
			}
			entity.setCreateBy(null);
			entity.setCreateDate(null);
			this.sysOfficeTreeMapper.updateByPrimaryKeySelective(entity);
			return entity;
		} catch (Exception e) {
			throw printException(e);
		}
	}


	/**
	 * 根据id获取指定组织机构
	 * @param id  String类型
	 * @return 组织机构记录   SysOffice类型
	 */
	@Override
	public SysOfficeTree queryById(Object id) {
		if (id == null || StringUtils.isBlank(id.toString())) {
            throw new ServiceException("id can not be null or empty!");
        }
		return this.sysOfficeTreeMapper.selectByPrimaryKey(id.toString());
	}

	/**
	 * 根据id删除指定组织机构
	 * @param id 
     * @param update_by 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
	 */
	@Override
	public void deleteById(Object id, String update_by, boolean ifLogic)
			throws ServiceException {
		if (id == null || StringUtils.isBlank(id.toString())) {
            throw new ServiceException("id can not be null or empty!");
        }
		if (ifLogic) {
			// 逻辑删除
			SysOfficeTree entity = new SysOfficeTree();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
        	entity.setId(id.toString());
            this.sysOfficeTreeMapper.updateByPrimaryKeySelective(entity);
        } else {
        	// 物理删除
            this.sysOfficeTreeMapper.deleteByPrimaryKey(id.toString());
        }
		
	}

	@Override
	public void deleteByIds(String ids, String update_by, boolean ifLogic)
			throws ServiceException {
		if (StringUtils.isBlank(ids)) {
            throw new ServiceException("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        SysOfficeTreeExample example = new SysOfficeTreeExample();
        SysOfficeTreeExample.Criteria cr = example.createCriteria();
        List<String> idList = new ArrayList<String>();
        if (split.length > 0) {
        	for(int i=0; i<split.length; i++) {
        		idList.add(split[i]);
        	}
        }
        cr.andIdIn(idList);
        if (!ifLogic) {
            // 物理删除
            this.sysOfficeTreeMapper.deleteByExample(example);
        } else {
            // 此处用逻辑删除
        	SysOfficeTree entity = new SysOfficeTree();
        	entity.setCreateDate(null);
        	entity.setUpdateBy(update_by);
        	entity.setDelFlag(BaseEntity.YES);
            this.sysOfficeTreeMapper.updateByExampleSelective(entity, example);
        }
	}

	@Override
	public void getPage(SysOfficeTree entity, PageEntity pageentity) {
		try {
			this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
			List<OfficeModel> result = this.sysOfficeTreeMapper.getOfficeListPage(pageentity, entity);
			pageentity.setContent(result);
		} catch (Exception e) {
			throw printException(e);
		}
		
	}

	@Override
	public void getPageRe(SysOfficeTree entity, PageEntity pageentity) {
		
	}

	@Override
	public void deleteByIdsRe(String ids, String update_by)
			throws ServiceException {
		
	}

	@Override
	public List<SysOfficeTree> getAll(SysOfficeTree entity) {
		SysOfficeTreeExample example = new SysOfficeTreeExample();
		SysOfficeTreeExample.Criteria cr = example.createCriteria();
		cr.andDelFlagEqualTo("0");
        try {
            return this.sysOfficeTreeMapper.selectByExample(example);
        } catch (Exception e) {
            this.logger.error("sysOfficeServiceImp getAll has Error:",e);
            throw new ServiceException("获取全部组织机构时发生异常");
        }
	}
	
	/* 获取树结构根据父id
     * @see net.lantrack.framework.sysbase.service.SysMenuService#getTreeByPid(java.lang.Integer)
     */
    @Override
    public List<OfficeModel> getTreeByPid(String pid) throws ServiceException {
    	List<SysOfficeTree> list = sysOfficeTreeMapper.queryByPid(pid);
        if (list != null) {
        	if ("area".equals(pid)) {
        		list.add(sysOfficeTreeMapper.selectByPrimaryKey(pid));
        	}
            ArrayList<OfficeModel> tree = Lists.newArrayListWithExpectedSize(list.size());
            for(SysOfficeTree office : list){
            	OfficeModel m = new OfficeModel();
            	m.setId(office.getId());
            	m.setParentId(office.getpId());
            	m.setOfficeName(office.gettName());
                tree.add(m);
            }
            return tree;
        }
        return null;
    }
    /**
     * 获取当前登录用户只自己所能见到的组织机构树
     */
    @Override
    public List<OfficeModel> getCurrentOfficeTree() {
    	List<OfficeModel> list = new ArrayList<OfficeModel>();
    	SysUser cUser = UserUtil.getUser();
    	String officeId = null;
    	if (cUser!=null&&StringUtils.isNotBlank(officeId = cUser.getOfficeId())) {
    		//当前机构
    		SysOfficeTree office = this.queryById(officeId);
    		list.add(turnOfficeToModel(office));
    		//parentIs包含当前机构id的子机构
    		SysOfficeTreeExample example = new SysOfficeTreeExample();
    		SysOfficeTreeExample.Criteria cr = example.createCriteria();
			cr.andPIdsLike("%"+officeId+"%");
			List<SysOfficeTree> sysList = sysOfficeTreeMapper.selectByExample(example);
			for(SysOfficeTree sysOffice : sysList){
				list.add(turnOfficeToModel(sysOffice));
            }
    	}
    	return list;
    }
    
    private  OfficeModel turnOfficeToModel(SysOfficeTree office) {
    	OfficeModel m = new OfficeModel();
    	m.setId(office.getId());
    	m.setParentId(office.getpId());
    	m.setOfficeName(office.gettName());
    	m.setSort(office.getoSort());
    	return m;
    }

	@Override
	public String extractOfficeFromSso(String sn) throws Exception {
    	String responseStr = "", rmg = "";	
    	try {
    		responseStr = SsoApiUtil.getInstance().getBasicDataList(PropertyPlaceholder.getProperty("sso.service")+PropertyPlaceholder.getProperty("sso.extractOfficeUrl")+"?sn="+sn);
        	JsonParser parser = new JsonParser();
            JsonObject jo = (JsonObject) parser.parse(responseStr);
            JsonArray jsonArray = jo.get("result").getAsJsonArray();
            List<SysOfficeTree> list = new ArrayList<SysOfficeTree>();
            for (int i=0; i<jsonArray.size(); i++) {
            	JsonElement element = jsonArray.get(i);
            	JsonObject jobt = (JsonObject) parser.parse(element.toString());
            	SysOfficeTree sysOffice = new SysOfficeTree();
            	sysOffice.setId(jobt.get("id").toString().substring(1, jobt.get("id").toString().length()-1));//id
            	sysOffice.setpName("null".equals(jobt.get("parent_name").toString()) ? null : jobt.get("parent_name").toString().substring(1, jobt.get("parent_name").toString().length()-1));//parent_name
            	sysOffice.setpId(jobt.get("parent_id").toString().substring(1, jobt.get("parent_id").toString().length()-1));//parent_id
            	sysOffice.setpIds(jobt.get("parent_ids").toString().substring(1, jobt.get("parent_ids").toString().length()-1));//parent_ids
            	sysOffice.settName(jobt.get("name").toString().substring(1, jobt.get("name").toString().length()-1));//name
            	sysOffice.setoSort("null".equals(jobt.get("sort").toString()) ? "0" : jobt.get("sort").toString());
            	sysOffice.setoAddress(jobt.get("address")==null || "null".equals(jobt.get("address").toString()) ? null : jobt.get("address").toString().substring(1, jobt.get("address").toString().length()-1)); 
            	sysOffice.setCreateBy(UserUtil.getCurrentUser());
            	sysOffice.setUpdateBy(UserUtil.getCurrentUser());
            	sysOffice.setRemarks(jobt.get("remarks")==null || "null".equals(jobt.get("remarks").toString()) ? null : jobt.get("remarks").toString().substring(1, jobt.get("remarks").toString().length()-1));
            	list.add(sysOffice);
            }
            List<SysOfficeTree> localList = this.getAllLocalOfficeList();
            if ((localList != null && localList.size() == 0) || localList == null) {
//            	sysOfficeTreeMapper.insertList(list);
            	rmg = "全量同步"+list.size()+"条组织机构数据成功";
            	System.out.println(DateUtil.getDateTime()+rmg);
            } else {
            	// 通过循环区分出哪些是需要新增的、哪些是需要修改的、哪些是需要删除的、哪些是原封不动的
            	Map<String, List<SysOfficeTree>> map = separateCrudOffice(list, localList);
            	List<SysOfficeTree> insertList = map.get("insertList");
            	List<SysOfficeTree> deleteList = map.get("deleteList");
            	List<SysOfficeTree> updateList = map.get("updateList");
            	List<SysOfficeTree> nochangeList = map.get("nochangeList");
            	if (insertList != null && insertList.size() > 0) {
//            		sysOfficeTreeMapper.insertList(insertList);
            	}
            	if (deleteList != null && deleteList.size() > 0) {
            		for (SysOfficeTree office : deleteList) {
            			sysOfficeTreeMapper.updateByPrimaryKeySelective(office);
                	}
            	}
            	if (updateList != null && updateList.size() > 0) {
            		for (SysOfficeTree office : updateList) {
            			sysOfficeTreeMapper.updateByPrimaryKeySelective(office);
            		}
            	}
            	if (nochangeList != null && nochangeList.size() > 0) {
            		for (SysOfficeTree office : nochangeList) {
            			sysOfficeTreeMapper.updateByPrimaryKeySelective(office);
            		}
            	}
            	if (insertList!=null && deleteList!=null && updateList!=null && nochangeList!=null) {
            		rmg = "组织机构同步结果为：新增"+insertList.size()+"条，更新"+updateList.size()+"条，删除"+deleteList.size()+"条，未变化的为"+nochangeList.size()+"条";
            		System.out.println(DateUtil.getDateTime()+rmg);
            	}
            }
         // 同步成功后延后12个小时再将新增（0）和修改（2）都置为不变（3），再将删除（1）的del_flag置为1
            new Thread(new DelayResetOfficeTreeThread(sysOfficeTreeMapper)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return rmg;
	}
	/**
	 * 获取本地所有的组织机构集合
	 * @return list List<SysOffice>类型
	 */
	public List<SysOfficeTree> getAllLocalOfficeList() {
		SysOfficeTreeExample example = new SysOfficeTreeExample();
		return this.sysOfficeTreeMapper.selectByExample(example);
	}
	/**
	 * 区分出哪些是需要新增的、哪些是需要修改的、哪些是需要删除的、哪些是原封不动的行政区域
	 * @param source 从单点登录系统抽取的全部SysOffice集合
	 * @param target 本地系统目前数据里的全部SysOffice集合
	 * @return
	 */
	public Map<String, List<SysOfficeTree>> separateCrudOffice(List<SysOfficeTree> source, List<SysOfficeTree> target) {
		Map<String, List<SysOfficeTree>> map = new HashMap<String, List<SysOfficeTree>>();
		List<SysOfficeTree> insertList = new ArrayList<SysOfficeTree>();//新增的list
		List<SysOfficeTree> deleteList = new ArrayList<SysOfficeTree>();//删除的list
		List<SysOfficeTree> updateList = new ArrayList<SysOfficeTree>();//修改的list
		List<SysOfficeTree> nochangeList = new ArrayList<SysOfficeTree>();//没变的list
		List<SysOfficeTree> updateAndNochangeList = new ArrayList<SysOfficeTree>();//交集中源头的list
		List<SysOfficeTree> updateAndNcTargetList = new ArrayList<SysOfficeTree>();//交集中目的端list
		if (source == null || target == null) {
			return map;
		}
		// 分离出两个list的交集，即两边都有的sourceList
		for (int i = 0; i < source.size(); i++) {
			SysOfficeTree sourceOffice = source.get(i);
			for (int j = 0; j < target.size(); j++) {
				SysOfficeTree targetOffice = target.get(j);
				if (targetOffice.getId().equals(sourceOffice.getId())) {
					updateAndNochangeList.add(sourceOffice);
					updateAndNcTargetList.add(targetOffice);
				}
			}
		}
		// 再分离出需要新增的list
		for (int i = 0; i < source.size(); i++) {
			SysOfficeTree sourceOffice = source.get(i);
			String sourceId = sourceOffice.getId();
			boolean isInsert = true;
			for (int j = 0; j < updateAndNochangeList.size(); j++) {
				SysOfficeTree tempOffice = updateAndNochangeList.get(j);
				if (sourceId.equals(tempOffice.getId())) {
					isInsert = false;
					break;
				}
			}
			if (isInsert) {
				// 同步标记  0新增
//				sourceOffice.setSyncFlag("0");
				sourceOffice.setStand1("0");
				insertList.add(sourceOffice);
			}
		}
		map.put("insertList", insertList);
		// 再分离出需要删除的list
		for (int i = 0; i < target.size(); i++) {
			SysOfficeTree targetOffice = target.get(i);
			String sourceId = targetOffice.getId();
			boolean isDelete = true;
			for (int j = 0; j < updateAndNochangeList.size(); j++) {
				SysOfficeTree tempOffice = updateAndNochangeList.get(j);
				if (sourceId.equals(tempOffice.getId())) {
					isDelete = false;
				}
			}
			if (isDelete && !"1".equals(targetOffice.getStand1())) {
				// 同步标记  1删除
//				targetOffice.setSyncFlag("1"); 
				targetOffice.setStand1("1");
				deleteList.add(targetOffice);
			}
		}
		map.put("deleteList", deleteList);
		// 最后分离出需要修改的list和没有任何变化的list,都可以是需要修改的list
		for (int i = 0; i < updateAndNochangeList.size(); i++) {
			SysOfficeTree sourceOffice = updateAndNochangeList.get(i);
			boolean isChanged = true;
			for (int j = 0; j < updateAndNcTargetList.size(); j++) {
				SysOfficeTree targetOffice = updateAndNcTargetList.get(j);
				if ((sourceOffice.getId()!=null && sourceOffice.getId().equals(targetOffice.getId())) && 
					  (sourceOffice.getpId()!=null && sourceOffice.getpId().equals(targetOffice.getpId())) && 
					  (sourceOffice.getpId()!=null && sourceOffice.getpId().equals(targetOffice.getpId())) && 
					  ((sourceOffice.gettName()!=null && sourceOffice.gettName().equals(targetOffice.gettName())) || 
					  (sourceOffice.gettName()==null && targetOffice.gettName()==null)) ) {
					// 同步标记  3不变
//					sourceOffice.setSyncFlag("3");
					sourceOffice.setStand1("3");
					nochangeList.add(sourceOffice);
					isChanged = false;
				}
			}
			if (isChanged) {
				// 同步标记  2修改
//				sourceOffice.setSyncFlag("2");
				sourceOffice.setStand1("2");
				updateList.add(sourceOffice);
			}
		}
		map.put("updateList", updateList);
		map.put("nochangeList", nochangeList);
		return map;
	}

	/**
	 * 根据传入的officeId获取其子孙后代所有的officeIds(含自己)
	 * @param officeId 当前传入的机构id
	 * @return officeIds 以逗号拼接的所有下属机构ids
	 */
	@Override
	public String getAllSubOfficeids(String officeId, boolean hasSingleQuote) {
		StringBuffer ids = new StringBuffer("");
		// 先把自己的id拼进去
		if (!ids.toString().contains(officeId)) {
			if (hasSingleQuote) {
				ids.append("'").append(officeId).append("'");
			} else {
				ids.append(officeId);
			}
		}
		List<OfficeModel> list = this.sysOfficeTreeMapper.queryChildrenByPid(officeId);
		if (list != null && list.size() > 0) {
			for (OfficeModel office : list) {
				if (office != null && StringUtils.isNotBlank(office.getId()) && !ids.toString().contains(office.getId())) {
					if (hasSingleQuote) {
						ids.append(",").append("'").append(office.getId()).append("'");
					} else {
						ids.append(",").append(office.getId());
					}
				}
			}
		}
		return ids.toString();
	}


	public boolean validateRepeat(String id,String pid, String nodeName) {
		SysOfficeTreeExample example = new SysOfficeTreeExample();
		SysOfficeTreeExample.Criteria cr = example.createCriteria();
		cr.andPIdEqualTo(pid);
		cr.andTNameEqualTo(nodeName);
		List<SysOfficeTree> list = this.sysOfficeTreeMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			SysOfficeTree office = list.get(0);
			if(!office.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}


	
	//查看当前部门下最大排序号
	private String getMaxSortByPid(String pid) {
		String sortl = "0";
		String sql = "select max(o_sort) as o_sort from sys_office_tree where p_id = '"+pid+"'";
		Map<String, Object> sortMap = this.baseDao.querySingleMap(sql);
		Object o_sort = sortMap.get("o_sort");
		if(o_sort!=null&&StringUtils.isNotBlank(o_sort.toString())) {
			sortl = (Long.valueOf(o_sort.toString())+1)+"";
		}
		return sortl;
	}

	@Override
	public String getTableName() {
		return " sys_office_tree ";
	}

	
}

//延迟一段时间后再重置同步标识位为不变
class DelayResetOfficeTreeThread implements Runnable {
	
	private SysOfficeTreeMapper sysOfficeTreeMapper;
	
	public DelayResetOfficeTreeThread(SysOfficeTreeMapper sysOfficeTreeMapper) {
		this.sysOfficeTreeMapper = sysOfficeTreeMapper;
	}
	
	@Override
	public void run() {
		try {
			/**
			 * 12小时=1000*60*60*12毫秒
			 * 1分钟=1000*60毫秒
			 * 延迟重置同步标识位
			 */
			Thread.sleep(1000*30);
			resetNoChangeFlag();
			resetDelFlag();
			System.out.println("延后30s同步标记重置完成");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 同步成功后延迟将新增（0）和修改（2）都置为不变（3）
	 */
	private void resetNoChangeFlag() {
		SysOfficeTree record = new SysOfficeTree();
		record.setStand1("3");
		SysOfficeTreeExample example = new SysOfficeTreeExample();
		SysOfficeTreeExample.Criteria cr = example.createCriteria();
		List<String> values = new ArrayList<String>();
		values.add("0");
		values.add("2");
		cr.andStand1In(values);
		sysOfficeTreeMapper.updateByExampleSelective(record, example);
	}
	/**
	 * 同步成功后延迟将删除（1）的del_flag置为1，即逻辑删除
	 */
	private void resetDelFlag() {
		SysOfficeTree record = new SysOfficeTree();
		SysOfficeTreeExample example = new SysOfficeTreeExample();
		SysOfficeTreeExample.Criteria cr = example.createCriteria();
		record.setStand1("1");
		record.setDelFlag("1");
		cr.andStand1EqualTo("1");
		sysOfficeTreeMapper.updateByExampleSelective(record, example);
	}
	
}
