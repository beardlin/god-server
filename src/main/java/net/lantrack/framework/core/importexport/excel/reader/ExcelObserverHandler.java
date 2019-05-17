package net.lantrack.framework.core.importexport.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;


/**
 * Excel数据解析处理
 *       
 * @date 2019年5月11日
 */
public  class ExcelObserverHandler extends AnalysisEventListener<Object>{

	/**
	 * 初始化观察者数量
	 */
	private volatile List<ExcelObServer > observers = new ArrayList<>(1);
	
	private static volatile  ExcelObserverHandler handler = null;
	
	/**
	 * Excel数据转换的对象类型 
	 */
	private Class<?> clazz;
//	private Class<? extends Excel> clazz;
	
	private ExcelObserverHandler() {}
	
	public static ExcelObserverHandler getInstance(){
		if(handler==null) {
			synchronized(ExcelObserverHandler.class) {
				if(handler==null) {
					handler = new ExcelObserverHandler();
				}
			}
		}
		return handler;
	}

	/**
	 * 注册
	 * @param os
	 * @return
	 * @date 2019年5月11日
	 */
	protected ExcelObserverHandler  registObserver(ExcelObServer os) {
		if(os==null) {
			throw new NullPointerException("ExcelObServer Can't be null");
		}
		synchronized(observers) {
			if(!observers.contains(os)) {
				observers.add(os);
			}
		}
		return this;
	}
	
	/**
	 * 注销
	 * @param os
	 * @return
	 * @date 2019年5月11日
	 */
	protected ExcelObserverHandler  removeObserver(ExcelObServer os) {
		if(os==null) {
			throw new NullPointerException("ExcelObServer Can't be null");
		}
		synchronized(observers) {
			if(observers.contains(os)) {
				observers.remove(os);
			}
		}
		return this;
	}
	
	private void notifyAllObserver(ExcelInfo obj) {
		for(ExcelObServer os:observers) {
			os.excute(obj);
		}
	}
	
	protected int countObserver() {
		synchronized(observers) {
			return observers.size();
		}
	}
	
	protected ExcelObserverHandler clearObserver() {
		synchronized(observers) {
			observers.clear();
		}
		return this;
	}
	
	
	
	public List<ExcelObServer> getObservers() {
		return observers;
	}



	@SuppressWarnings({"deprecation","unchecked"})
	@Override
	public void invoke(Object object, AnalysisContext context) {
		if(clazz==null) {
			throw new NullPointerException("excel convert clazz can't be null");
		}
		
		Integer currentRowNum = context.getCurrentRowNum();
		Integer totalCount = context.getTotalCount();
		ExcelInfo info = new ExcelInfo(clazz,currentRowNum,totalCount,(List<Object>) object);
		notifyAllObserver(info);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println(context.getTotalCount());
	}

	protected void handlerExcelByTemplate(File excel,Class<?> clazz,int heardrIndex,int dataIndex) throws FileNotFoundException {
		if(clazz==null) {
			throw new NullPointerException("excel convert clazz can't be null");
		}
		this.clazz = clazz;
		InputStream inputStream = new FileInputStream(excel);
		EasyExcelFactory.readBySax(inputStream, new Sheet(1,0), this);
	}
	

	protected void handlerExcelByIndex(File excel,Class<?> clazz,int dataIndex) throws FileNotFoundException {
		if(clazz==null) {
			throw new NullPointerException("excel convert clazz can't be null");
		}
		this.clazz = clazz;
		InputStream inputStream = new FileInputStream(excel);
		EasyExcelFactory.readBySax(inputStream, new Sheet(1,0), this);
	}
	
	/**
	 * 解析数据
	 * @param rowData
	 * @date 2019年5月13日
	 */
	private void paserData(List<Object> rowData) {
		
	}
	
	
}
