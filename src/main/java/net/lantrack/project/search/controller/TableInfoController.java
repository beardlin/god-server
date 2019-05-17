package net.lantrack.project.search.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.model.CustomCondtion;
import net.lantrack.project.search.service.TableInfoService;

@Controller
@RequestMapping("search")
public class TableInfoController extends BaseController{

	@Autowired
	private TableInfoService tableInfoService;
	

	// 通过模板id查询数据 
	// search/byTemplate.json
	@RequestMapping("byTemplate")
	private String byTemplate(Integer id,ReturnEntity info) {
		try {
			DataSerchModel model = tableInfoService.templateById(id);
			if(model==null) {
				info.failed("数据缺失！");
				return "";
			}
			info.setResult(model);
			info.success("查询成功");
		} catch (Exception e) {
			info.failed("查询失败:"+e.getMessage());
		}
		return "";
	}
	
	// 保存为模板 
	// search/savetemplate.json
	@RequestMapping("savetemplate")
	private String saveAsTemplate(String show,String modelName,String ids,HttpServletRequest req,ReturnEntity info) {
		String json = req.getParameter(formdata);
		try {
			tableInfoService.saveTemplate(show,modelName,ids,json);
			info.success("保存成功");
		} catch (Exception e) {
			info.failed("保存失败:"+e.getMessage());
		}
		return "";
	}
	
	//导出数据
	//  search/export.json
	@RequestMapping("/export")
	private String exportData(String ids,@RequestBody CustomCondtion[] condArry,
			HttpServletResponse response,ReturnEntity info) {
		try {
			List<CustomCondtion> conds = Arrays.asList(condArry);
			//1查出对应的列名
			Map<String,String> column = this.tableInfoService.getCustomerColumn(ids);
			PageEntity page = new PageEntity();
			page.setPerPageCount(1000000);
			this.tableInfoService.getCustomerDatas(ids,page,conds);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> resultList = (List<Map<String, Object>>) page.getContent();
			//3数据写入Excel
			this.tableInfoService.exportExcel(column, resultList, response);
		} catch (Exception e) {
			info.failed("导出失败:"+e.getMessage());
		}
		return "";
	}

	//查询自定义数据类型列信息
	// search/columninfo.json
	@RequestMapping("/columninfo")
	private String columnInfo(String ids,ReturnEntity info) {
		try {
			Map<String,String> column = this.tableInfoService.getCustomerColumn(ids);
			info.setResult(column);
			info.success("查询成功");
		} catch (Exception e) {
			info.failed("查询失败:"+e.getMessage());
		}
		return "";
	}

	//查询自定义数据类型数据
	// search/datas.json
	@RequestMapping("/datas")
	private String getDatas(String ids,@RequestBody CustomCondtion[] condArry,
			PageEntity page,ReturnEntity info) {
		try {
			List<CustomCondtion>	conds = Arrays.asList(condArry);
			this.tableInfoService.getCustomerDatas(ids,page,conds);
			info.setResult(page);
			info.success("查询成功");
		} catch (Exception e) {
			info.failed("查询失败:"+e.getMessage());
		}
		return "";
	}
	
	//查询树形表
	// search/tables.json
	@RequestMapping("/tables")
	private String getTables(ReturnEntity info) {
		try {
			List<TableInfo> treeTables = this.tableInfoService.getTreeTables();
			info.setResult(treeTables);
			info.success("查询成功");
		} catch (Exception e) {
			info.failed("查询失败:"+e.getMessage());
		}
		return "";
	}
	//查询字段列表
	// search/colums.json
	@RequestMapping("/colums")
	private String getColumns(@RequestParam(required=false) Integer tableId,PageEntity page,ReturnPage info) {
		try {
			this.tableInfoService.getColumnPage(tableId, page);
			info.setResult(page);
		} catch (Exception e) {
			info.failed("查询失败:"+e.getMessage());
		}
		return "";
	}
	
}
