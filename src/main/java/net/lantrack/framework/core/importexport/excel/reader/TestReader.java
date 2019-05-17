package net.lantrack.framework.core.importexport.excel.reader;

import java.io.File;
import java.io.FileNotFoundException;

import net.lantrack.framework.core.importexport.Student;

public class TestReader {

	public static void main(String[] args) throws FileNotFoundException {
		ExcelObserverHandler handler = ExcelObserverHandler.getInstance().registObserver(new ExcelDataHanlder());
		File excel = new File("D:/student.xlsx");
		handler.handlerExcelByTemplate(excel, Student.class, 0, 1);
	}
}
