package net.lantrack.framework.core.importexport.excel.reader;

public class ProcessListener implements ExcelObServer{

	@Override
	public void excute(ExcelInfo info) {
		System.out.println("currentRowNum:"+info.getCurretRowNum()
		+",totalCount:"+info.getTotalCount());
	}

}
