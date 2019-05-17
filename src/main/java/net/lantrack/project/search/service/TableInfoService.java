package net.lantrack.project.search.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.model.CustomCondtion;

public interface TableInfoService {
	//连接条件
	public static final String CONCAT_CONDTION_AND = "AND";
	public static final String CONCAT_CONDTION_OR = "OR";
	
	/**
	 * 查看模板详情
	 * @Description: 
	 * @author lin
	 * @date 2018年6月28日
	 */
	DataSerchModel templateById(Integer id);
	
	/**
	 * 导出数据到Excel
	 * @Description: 
	 * @author lin
	 * @date 2018年6月19日
	 */
	void exportExcel(Map<String,String> column,List<Map<String, Object>> resultList,
			HttpServletResponse response);
	
	/**
	 * 保存为模板
	 * @Description: 
	 * @author lin
	 * @date 2018年6月19日
	 */
	DataSerchModel saveTemplate(String who,String modelName,String ids,String conds);
//	DataSerchModel saveTemplate(String who,String ids,List<CustomCondtion> conds);
	
	/**
	 * 获取自定义列表头信息
	 * @Description: 
	 * @author lin
	 * @date 2018年6月14日
	 */
	Map<String, String> getCustomerColumn(String ids);
	/**
	 * 获取自定义列数据
	 * @Description: 
	 * @author lin
	 * @date 2018年6月14日
	 */
	void getCustomerDatas(String ids,PageEntity page,List<CustomCondtion> conds);
	
	/**
	 * 获取字段信息
	 * @Description: 
	 * @author lin
	 * @date 2018年6月13日
	 */
	void getColumnPage(Integer tableId,PageEntity page);

	/**
	 * 获取树形表结构
	 * @Description: 
	 * @author lin
	 * @date 2018年6月13日
	 */
	List<TableInfo> getTreeTables();
}
