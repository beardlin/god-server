package net.lantrack.project.search.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.project.search.dao.ColumnInfoDao;
import net.lantrack.project.search.dao.TableInfoDao;
import net.lantrack.project.search.entity.ColumnInfo;
import net.lantrack.project.search.entity.ColumnInfoExample;
import net.lantrack.project.search.entity.ColumnInfoExample.Criteria;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.entity.TableInfoExample;
import net.lantrack.project.search.model.ColumnModel;
import net.lantrack.project.search.model.ConfigModel;
import net.lantrack.project.search.model.TableModel;
import net.lantrack.project.search.service.MetaDataService;
import net.lantrack.project.search.util.JDBCUtils;

@Service
public class MetaDataServiceImpl implements MetaDataService {

	@Autowired
	protected TableInfoDao tableInfoDao; 
	@Autowired
	protected ColumnInfoDao columnInfoDao; 
	
	@Override
	public List<TableModel> getDbTables() {
		Connection conn = JDBCUtils.getConnection();
		if(conn==null) {
			throw new ServiceException("获取连接失败");
		}
		ResultSet rs = null;
		Statement stm = null;
		List<TableModel> tables = new ArrayList<>();
		try {
			//数据库
			String dbName = conn.getCatalog();
			stm = conn.createStatement();
			//查看表基本信息
			String sql = "show table status";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TableModel tm = new TableModel(rs.getString("Name"), rs.getString("Comment"), dbName, rs.getString("Engine"));
				tables.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			JDBCUtils.close(rs, stm, null);
		}
		return tables;
	}
	
	@Override
	public List<ColumnModel> getColumnsByTable(String tableName, String dbName) {
		Connection conn = JDBCUtils.getConnection();
		if(conn==null) {
			throw new ServiceException("获取连接失败");
		}
		ResultSet rs = null;
		Statement stm = null;
		List<ColumnModel> columns = new ArrayList<>();
		try {
			stm = conn.createStatement();
			//查看表基本信息
			String sql = "SELECT COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,COLUMN_COMMENT  "
					+" FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema ='"+dbName+"' " 
					+" AND table_name ='"+tableName+"'";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				ColumnModel cm= new ColumnModel(rs.getString("COLUMN_NAME"), rs.getString("COLUMN_COMMENT"), 
						rs.getString("DATA_TYPE"), rs.getString("CHARACTER_MAXIMUM_LENGTH"));
				columns.add(cm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			JDBCUtils.close(rs, stm, conn);
		}
		return columns;		
	}
	//获取当前节点下已存在的表信息
	private Map<String, TableInfo> getHasExitTable(String pid){
		TableInfoExample example = new TableInfoExample();
		net.lantrack.project.search.entity.TableInfoExample.Criteria cr = example.createCriteria();
		cr.andPIdEqualTo(Integer.valueOf(pid));
		List<TableInfo> list = this.tableInfoDao.selectByExample(example);
		Map<String, TableInfo> map = new HashMap<>(list.size());
		for(TableInfo info:list) {
			map.put(info.getTableName(), info);
		}
		return map;
	}
	//删除库中无用表以及字段信息
	private void delTable(Map<String, TableInfo> map) {
		for(Map.Entry<String, TableInfo> entry:map.entrySet()) {
			TableInfo value = entry.getValue();
			this.tableInfoDao.deleteByPrimaryKey(value.getId());
			ColumnInfoExample example = new ColumnInfoExample();
			Criteria cr = example.createCriteria();
			cr.andTableIdEqualTo(value.getId());
			this.columnInfoDao.deleteByExample(example);
		}
	}
	@Override
	@Transactional
	public void configMetaData(ConfigModel configModel) {
		if(configModel==null) {
			throw new ServiceException("参数异常");
		}
		String pid = configModel.getId();
		List<TableModel> tables = configModel.getTables();
		TableInfo tableInfo = this.tableInfoDao.selectByPrimaryKey(Integer.valueOf(pid));
		String fullName = tableInfo.getFullName();
		List<TableInfo> infos = new ArrayList<>(tables.size());
		//获取已存在的表
		Map<String, TableInfo> exitTable = getHasExitTable(pid);
		//存放重复的
		Set<String> repeatSet = new HashSet<>();
		for(TableModel tm:tables) {
			if(exitTable.containsKey(tm.getTableName())) {
				repeatSet.add(tm.getTableName());
			}else {
				TableInfo temp = new TableInfo();
				temp.setDbName(tm.getDbName());
				temp.setFullName(fullName+"/"+tm.getZhName());
				temp.setpId(Integer.valueOf(pid));
				temp.setTableEngine(tm.getTableEngine());
				temp.setTableName(tm.getTableName());
				temp.setZhName(tm.getZhName());
				infos.add(temp);
			}
		}
		for(String tm:repeatSet) {
			if(exitTable.containsKey(tm)) {
				exitTable.remove(tm);
			}
		}
		//删除过滤出来的脏数据
		delTable(exitTable);
		for(TableInfo t:infos) {
			try {
				saveTableAndColumns(t);
			} catch (Exception e) {
				System.err.println(t.getTableName()+","+e.getMessage());
			}
		}
	}
	private Integer str2Int(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	private void saveTableAndColumns(TableInfo info) {
		//获取当前表所有字段信息
		List<ColumnModel> columns = getColumnsByTable(info.getTableName(), info.getDbName());
		info.setColumnNum(columns.size());
		List<ColumnInfo> cols = new ArrayList<>(columns.size());
		try {
			this.tableInfoDao.insert(info);
			for(ColumnModel cm:columns) {
				ColumnInfo c = new ColumnInfo();
				c.setColumnName(cm.getColumnName());
				c.setDataLength(str2Int(cm.getDataLength()));
				c.setDataType(cm.getDataType());
				c.setZhName(cm.getZhName());
				c.setTableId(info.getId());
				c.setTableName(info.getTableName());
				cols.add(c);
			}
			this.columnInfoDao.insertList(cols);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteColumns(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("参数异常");
		}
		String[] split = ids.split(",");
		ColumnInfoExample example = new ColumnInfoExample();
		Criteria cr = example.createCriteria();
		cr.andIdIn(Arrays.asList(split));
		try {
			this.columnInfoDao.deleteByExample(example);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateColumn(ColumnInfo entity) {
		Integer id = entity.getId();
		String zhName = entity.getZhName();
		String remarks = entity.getColumnRemarks();
		ColumnInfo info = this.columnInfoDao.selectByPrimaryKey(id);
		boolean change = false;
		if(StringUtils.isNotBlank(zhName)&&!zhName.equals(info.getZhName())) {
			info.setZhName(zhName);
			change = true;
		}
		if(remarks!=null&&!remarks.equals(info.getColumnRemarks())) {
			info.setColumnRemarks(remarks);
			change = true;
		}
		if(change) {
			try {
				this.columnInfoDao.updateByPrimaryKeySelective(info);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
		}
		
	}

	@Override
	public TableInfo getTableInfoById(String id) {
		try {
			return this.tableInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TableModel> initCheckedTable(String id) {
		TableInfoExample example = new TableInfoExample();
		net.lantrack.project.search.entity.TableInfoExample.Criteria cr = example.createCriteria();
		cr.andPIdEqualTo(Integer.valueOf(id));
		List<TableInfo> list = this.tableInfoDao.selectByExample(example);
		List<TableModel> dbTables = getDbTables();
		if(list!=null&&list.size()>0) {
			Map<String, String> tableMap = new HashMap<>(list.size());
			for(TableInfo info:list) {
				tableMap.put(info.getTableName(), info.getTableName());
			}
			for(TableModel model:dbTables) {
				if(tableMap.containsKey(model.getTableName())) {
					model.setChecked(true);
				}
			}
		}
		return dbTables;
	}

	@Override
	public List<ColumnInfo> getColumnByTableId(String tbId) {
		ColumnInfoExample example = new ColumnInfoExample();
		Criteria cr = example.createCriteria();
		cr.andTableIdEqualTo(Integer.valueOf(tbId));
		List<ColumnInfo> columns = this.columnInfoDao.selectByExample(example);
		return columns;
	}

	

}
