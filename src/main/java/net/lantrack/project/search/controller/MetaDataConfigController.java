package net.lantrack.project.search.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.project.search.entity.ColumnInfo;
import net.lantrack.project.search.entity.TableInfo;
import net.lantrack.project.search.model.ConfigModel;
import net.lantrack.project.search.model.TableModel;
import net.lantrack.project.search.service.MetaDataService;

@Controller
@RequestMapping("metadata")
public class MetaDataConfigController extends BaseController{

	@Autowired
	private MetaDataService metaDataService;
	


	//修改字段信息   metadata/updatecolumn.json
	@RequestMapping("/updatecolumn")
	public String updatecolumn(ColumnInfo entity,ReturnEntity info) {
		try {
			metaDataService.updateColumn(entity);
			info.success("修改成功");
		} catch (Exception e) {
			info.failed("修改失败："+e.getMessage());
		}
		return "";
	}
	
	//删除表中的字段   metadata/delcolumn.json
	@RequestMapping("/delcolumn")
	public String deleteColumn(String ids,ReturnEntity info) {
		try {
			this.metaDataService.deleteColumns(ids);
			info.success("删除成功");
		} catch (Exception e) {
			info.failed("删除失败："+e.getMessage());
		}
		return "";
	}
	
	//将对应表配置到自定义元数据中   metadata/config.json
	@RequestMapping("/config")
	public String config(@RequestBody ConfigModel configModel,ReturnEntity info) {
		try {
			this.metaDataService.configMetaData(configModel);
			info.success("配置成功");
		} catch (Exception e) {
			info.failed("配置失败："+e.getMessage());
		}
		return "";
	}
	
	//查看当前系统库中所有表   metadata/showTables.json
	@RequestMapping("/showTables")
	public String getTables(@RequestParam(required  =false) String id,ReturnEntity info) {
		try {
			Map<String, Object> reslut = new HashMap<>();
			if(StringUtils.isBlank(id)) {
				List<TableModel> dbTables = this.metaDataService.getDbTables();
				reslut.put("type", "tables");
				reslut.put("values", dbTables);
			}else {
				TableInfo tableInfo = this.metaDataService.getTableInfoById(id);
				//表名为空则为目录
				if(StringUtils.isBlank(tableInfo.getTableName())) {
					List<TableModel> dbTables = this.metaDataService.initCheckedTable(id);
					reslut.put("type", "tables");
					reslut.put("values", dbTables);
				}else {//不为空则为表，反回字段信息
					List<ColumnInfo> columns = this.metaDataService.getColumnByTableId(id);
					reslut.put("type", "columns");
					reslut.put("values", columns);
				}
			}
			info.setResult(reslut);
			info.success("查看成功");
		} catch (Exception e) {
			info.failed("查看失败："+e.getMessage());
		}
		return "";
	}
}
