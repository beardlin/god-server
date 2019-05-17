package net.lantrack.project.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.service.DataSerchModelService;

@Controller
@RequestMapping("template")
public class DataSerchModelController extends BaseController{

	@Autowired
	private DataSerchModelService dataSerchModelService;
	
	
	//  模板分页列表
	//  template/page.josn
	@RequestMapping("/page")
	private String getPage(DataSerchModel cond,PageEntity page,ReturnPage info) {
		try {
			this.dataSerchModelService.getPage(cond, page);
			info.setResult(page);
			info.success("查询成功");
		} catch (Exception e) {
			info.failed("查询失败："+e.getMessage());
		}
		return "";
	}
	
	
	//  删除模板
	//  template/delete.josn
	@RequestMapping("/delete")
	private String delete(String ids,ReturnEntity info) {
		try {
			this.dataSerchModelService.deleteByIds(ids, "", false);
			info.success("删除成功");
		} catch (Exception e) {
			info.failed("删除失败："+e.getMessage());
		}
		return "";
	}
	
}
