package net.lantrack.framework.core.importexport.excel.reader;

import java.util.List;


public  class ExcelDataHanlder implements ExcelObServer{

	@Override
	public void excute(ExcelInfo info) {
		List<Object> rowDatas = info.getRowDatas();
		System.out.println(info.getCurretRowNum()+"--"+rowDatas);
		
	}
	
	
}
