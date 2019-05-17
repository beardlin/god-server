package net.lantrack.framework.core.importexport.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 读取大数据量Excel
 * 2018年2月06日
 * @author lin
 */
public class ExcelReader extends BigExcelReader{
    /**
     * 返回的excle对象List
     */
    private List<Object> rowList = new ArrayList<>();
    /**
     * 数据开始位置
     */
    private int start;
    
    
	public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public void setRowList(List rowList) {
        this.rowList = rowList;
    }
    public List getRowList() {
		return rowList;
	}
	public ExcelReader(File file,int start) throws Exception{
		super(file);
		this.start = start;
		this.parse();
	}
	public ExcelReader(String path,int start) throws Exception{
		super(path);
		this.start = start;
		this.parse();
	}
	
	
	
	@Override
	protected void outputRow(String[] datas, int[] rowTypes, int rowIndex) {
//		System.out.println("第"+rowIndex+"行的数据为："+Arrays.toString(datas));
	    if(rowIndex>=start){
	        List<String> list = Arrays.asList(datas);
	        rowList.add(list);
	    }
	}
	
}

