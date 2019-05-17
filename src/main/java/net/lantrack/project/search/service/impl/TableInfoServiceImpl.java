package net.lantrack.project.search.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.importexport.excel.ExportExcelUtil;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.sysbase.util.UserUtil;
import net.lantrack.project.base.service.DictBaseService;
import net.lantrack.project.search.dao.ColumnInfoDao;
import net.lantrack.project.search.dao.TableInfoDao;
import net.lantrack.project.search.entity.ColumnInfo;
import net.lantrack.project.search.entity.ColumnInfoExample;
import net.lantrack.project.search.entity.ColumnInfoExample.Criteria;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.model.CustomCondtion;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.entity.TableInfoExample;
import net.lantrack.project.search.service.DataSerchModelService;
import net.lantrack.project.search.service.TableInfoService;

@Service
public class TableInfoServiceImpl implements TableInfoService {

	@Autowired
	protected TableInfoDao tableInfoDao;
	@Autowired
	protected ColumnInfoDao columnInfoDao;
	@Autowired
	protected PageService pageService;
	@Autowired
	protected DataSerchModelService dataSerchModelService;
	@Autowired
	protected DictBaseService dictBaseService;
	
	@Override
	public List<TableInfo> getTreeTables() {
		TableInfoExample example = new TableInfoExample();
		try {
			return this.tableInfoDao.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void getColumnPage(Integer tableId, PageEntity page) {
			this.pageService.getPage(page.getPerPageCount(), page.getCurrentPage());
		try {
			List<ColumnInfo> list = this.columnInfoDao.getListPage(tableId, page);
			page.setContent(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void getCustomerDatas(String ids,PageEntity page,List<CustomCondtion> conds) {
		this.pageService.getPage(page.getPerPageCount(), page.getCurrentPage());
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("请求参数异常");
		}
		String[] split = ids.split(",");
		ColumnInfoExample columnExample = new ColumnInfoExample();
		Criteria cr = columnExample.createCriteria();
		cr.andIdIn(Arrays.asList(split));
		List<ColumnInfo> columns = this.columnInfoDao.selectByExample(columnExample);
		String table = null;
		String column = "*";
		StringBuffer colBuffer = new StringBuffer();
		for(ColumnInfo info:columns) {
			if(table ==null) {
				table = info.getTableName();
			}
			colBuffer.append(info.getColumnName()).append(",");
		}
		if(colBuffer.length()>0) {
			column = colBuffer.substring(0, colBuffer.length()-1);
		}
		String sql = "select " + column + "  from  " + table +"  where 1 = 1  ";
		//查询条件
		String concatCondSql = concatCondSql(conds);
		System.out.println(concatCondSql.length());
		if(concatCondSql!=null&&concatCondSql.length()>3) {
			sql = sql + " and " + concatCondSql;
		}
		try {
			List<Map<String, Object>> list = this.tableInfoDao.selectDataBySqlListPage(sql, page,"");
			page.setContent(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	//拼接查询条件
	private String concatCondSql(List<CustomCondtion> conds) {
		if(conds==null||conds.size()==0) {
			return "";
		}
		CustomCondtion condtion = conds.get(conds.size()-1);
		condtion.setConcatCond(null);
//		String sql = "(asd and asd and asd or ( asd and asd or ( asd )))";
		int orCount = 1;
		StringBuffer sqlBuffer = new StringBuffer(" (");
		for(CustomCondtion cond:conds) {
			if(StringUtils.isBlank(cond.getCondtion())&&StringUtils.isBlank(cond.getValue())){
				continue;
			}
			sqlBuffer.append(cond.getField()).append(" ").append(cond.getCondtion())
			.append(formatValue(cond.getFieldType(),cond.getValue(),cond.getCondtion()));
			String concatCond = cond.getConcatCond();
			if(concatCond!=null) {
				concatCond = concatCond.toLowerCase();
				switch (concatCond) {
				//and 连接从左往右拼接
				case CONCAT_CONDTION_AND:
					sqlBuffer.append(" and ");
					break;
				// or 连接把后面的条件当成新的条件组	
				case CONCAT_CONDTION_OR:
					sqlBuffer.append(" or ( ");
					orCount++;
					break;
				}
			}
		}
		//最后右括号闭合
		for(int i=0;i<orCount;i++) {
			sqlBuffer.append(")");
		}
		return sqlBuffer.toString();
	}
	//格式化查询数据
	private String formatValue(String fieldType,String value,String cond) {
		if(fieldType==null) {
			fieldType = "";
		}
		String format="";
		switch (fieldType) {
		case "date": 
			if("like".equals(cond)&&StringUtils.isNotBlank(value)){
				format = "'%"+value+"%'"; 
			}else{
				format = "'"+value+"'"; 
			}
			break;
		case "datetime": 
			if("like".equals(cond)&&StringUtils.isNotBlank(value)){
				format = "'%"+value+"%'"; 
			}else{
				format = "'"+value+"'"; 
			}
			break;
		case "varchar": 
			if("like".equals(cond)&&StringUtils.isNotBlank(value)){
				format = "'%"+value+"%'"; 
			}else{
				format = "'"+value+"'"; 
			}
			break;
		case "int": format = value; break;
		case "double": format = value; break;
		case "float": format = value; break;
		case "boolean": format = value; break;
		default:format = value;
		}
		return format;
	}

	@Override
	public Map<String, String> getCustomerColumn(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("请求参数异常");
		}
		String[] split = ids.split(",");
		ColumnInfoExample columnExample = new ColumnInfoExample();
		Criteria cr = columnExample.createCriteria();
		cr.andIdIn(Arrays.asList(split));
		try {
			List<ColumnInfo> columns = this.columnInfoDao.selectByExample(columnExample);
			Map<String, String> columnMapping = new LinkedHashMap<>(columns.size());
			for(ColumnInfo info:columns) {
				columnMapping.put(info.getColumnName(), info.getZhName());
			}
			return columnMapping;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public DataSerchModel saveTemplate(String who,String modelName,String ids, String json) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("模板字段不能为空");
		}
		DataSerchModel model = new DataSerchModel();
		model.setModelField(ids);
		model.setShowWho(who);
		model.setOfficeCode(UserUtil.getUser().getOfficeId());
		model.setModelName(modelName);
		//查出对应的列名
		Map<String,String> column = this.getCustomerColumn(ids);
		StringBuffer bf = new StringBuffer();
		for(Map.Entry<String, String> entry:column.entrySet()) {
			bf.append(entry.getValue()).append(",");
		}
		if(bf.length()>0) {
			model.setModelFieldZh(bf.substring(0, bf.length()-1));
		}
		//查询条件
		List<CustomCondtion> conds = null;
		if(json!=null&&!"".equals(json.trim())) {
			try {
				conds = GsonUtil.jsonToList(json, CustomCondtion[].class);
			} catch (Exception e) {
				
			}
		}
		//保存查询条件
		if(conds!=null&&conds.size()>0) {
			model.setModelCond(json);
			String sql = concatCondSql(conds);
			// 查询条件 <,>,>=,<=
			Map<String, String> condMap = this.dictBaseService.queryDictMapByTypeAndDepart("search_cond", 0);
			for(Map.Entry<String, String> entry:condMap.entrySet()) {
				sql = sql.replace(entry.getKey(), entry.getValue());
			}
			sql = sql.replace(CONCAT_CONDTION_AND, "并且");
			sql = sql.replace(CONCAT_CONDTION_OR, "或者");
			sql = sql.replace("(", " ");
			sql = sql.replace(")", " ");
			for(Map.Entry<String, String> entry:column.entrySet()) {
				sql = sql.replace(entry.getKey(), entry.getValue());
			}
			model.setModelCondZh(sql);
		}
		try {
			this.dataSerchModelService.save(model);
			return model;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void exportExcel(Map<String, String> column, List<Map<String, Object>> resultList,
			HttpServletResponse response) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			ExportExcelUtil.exportExcelByColumnMap(column, resultList, outStream, "yyyy-MM-dd");
			ExportExcelUtil.downloadExcel(new Date().getTime()+"", outStream, response);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public DataSerchModel templateById(Integer id) {
		return dataSerchModelService.queryById(id);
	}

}
