package net.lantrack.framework.core.importexport.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExcelListener extends AnalysisEventListener<Object>{

	private List<List<Object>> list = new ArrayList<>(); 
	
	@Override
	@SuppressWarnings("unchecked")
	public void invoke(Object object, AnalysisContext context) {
//		System.out.println("当前row:"+context.getCurrentRowNum());
		List<Object> rowData = (List<Object>) object;
		list.add(rowData);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("解析完成");
		
	}

	public List<List<Object>> getList() {
		return list;
	}

	public void setList(List<List<Object>> list) {
		this.list = list;
	}

	
}
