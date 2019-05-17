//package net.lantrack.framework.core.util;
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.apache.commons.io.FileUtils;
////import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.w3c.dom.Document;
//
//
//
///**
// * Excel文件预览公共类
// * @author weiwei
// *
// */
//public class ExcelPreView {
//	
//	public static void main(String[] args) throws Exception {
//	
//		HttpServletRequest request = null;
//		HttpServletResponse response=null;
//		ExcelPreView.convertExceltoHtml(response,request, "D:\\jeeplus\\view\\4月需求整改跟踪.xlsx","1", "4月需求整改跟踪.xlsx");
//		
//	}
//	   
//	
//	   public static void convertExceltoHtml(HttpServletResponse response,HttpServletRequest request,String filepath,String sourceid,String fileName) throws Exception {
//		     //判断Excel文件是2003版还是2007版
//	          String suffix = filepath.substring(filepath.lastIndexOf("."));
//	          if(suffix.equals(".xlsx")){
//	        	  //07版
//	        	  ExcelPreView.Excel07ToHtml(response,request,filepath,sourceid,fileName);
//	        	  
//	          }else {
//	        	  //03版
//	        	  ExcelPreView.Excel03ToHtml(response,request,filepath,sourceid,fileName);
//	          }
//		
//	}
//	
//	   /**
//		 * excel03转html
//		 * filename:要读取的文件所在文件夹
//		 * filepath:excel绝对路径
//		 * htmlname:生成html名称
//		 * path:html存放路径
//		 * */
//		 public static void Excel03ToHtml (HttpServletResponse response,HttpServletRequest request,String filepath,String sourceid,String fileName) throws Exception {
//			 response.setContentType("text/html;charset=utf-8");
//			 response.setHeader("Content-Disposition", "inline"+ "; filename=\"" + fileName+"\""); 	
//
//			 String htmlname="exportExcel"+sourceid+".html";
//			
//			 File file = new File(filepath);
//			 String path=file.getParent();//获取文件所在文件夹
//			
//			/* fileExists(path);*///此方法是判断目录文件夹是否存在，这里就不贴了
//			
//		    InputStream input=new FileInputStream(filepath);
//		    HSSFWorkbook excelBook=new HSSFWorkbook(input);
//		    ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
//		    excelToHtmlConverter.processWorkbook(excelBook);//excel转html
//		    Document htmlDocument =excelToHtmlConverter.getDocument();
//		    ByteArrayOutputStream outStream = new ByteArrayOutputStream();//字节数组输出流
//		    DOMSource domSource = new DOMSource (htmlDocument);
//		    StreamResult streamResult = new StreamResult (outStream);
//		    /** 将document中的内容写入文件中，创建html页面 */
//		    TransformerFactory tf = TransformerFactory.newInstance();
//		    Transformer serializer = tf.newTransformer();
//		    serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
//		    serializer.setOutputProperty (OutputKeys.INDENT, "yes");
//		    serializer.setOutputProperty (OutputKeys.METHOD, "html");
//		    serializer.transform (domSource, streamResult);
//		    
//		    String content = new String (outStream.toString("UTF-8"));
//		    
//		    FileUtils.writeStringToFile(new File (path, htmlname), content, "utf-8");
//		    PrintWriter pw = response.getWriter();
//		    InputStreamReader inputStreamReader=null;
//	        try {
//	        	char[] chars=new char[2048];
//				int tmp=0;
//				inputStreamReader = new InputStreamReader( new FileInputStream(path+File.separator+htmlname),"GBK");
//            	if((tmp=inputStreamReader.read(chars))>0) {
//    				pw.write(chars, 0, tmp);
//    			}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//			     pw.flush();
//					
//				 if (input != null)
//					 input.close();
//				 if(outStream != null)
//				     outStream.close();
//				 if(inputStreamReader != null ) 
//					 inputStreamReader.close();
//		         if (pw != null)
//		        	 pw.close();
//			}
//		 }
//		 
//		 
//		   /**
//			 * excel07转html
//			 * filename:要读取的文件所在文件夹
//			 * filepath:excel文件绝对路径
//			 * htmlname:生成html名称
//			 * path:html存放路径
//			 * */
//		 @ResponseBody
//		 public static void Excel07ToHtml (HttpServletResponse response,HttpServletRequest request,String filepath,String sourceid,String fileName) throws Exception{
//			
//			 response.setContentType("text/html;charset=utf-8");
//			 response.setHeader("Content-Disposition", "inline"+ "; filename=\"" + fileName+"\""); 	
//			 
//			 String htmlname="exportExcel"+sourceid+".html";
//			 	File file = new File(filepath);
//			 	String path=file.getParent();//获取文件所在文件夹
//			 	 
//			 	/* fileExists(path);*/
//			  	Workbook workbook = null;
//		        InputStream is = new FileInputStream(filepath);
//		        
//		    	PrintWriter pw = response.getWriter();
//		        InputStreamReader inputStreamReader=null;
//		        
//		        try {
//		        	String html="";
//		            workbook =  new XSSFWorkbook(is);
//		            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
//		                Sheet sheet = workbook.getSheetAt(numSheet);
//		                if (sheet == null) {
//		                    continue;
//		                }
//		                html+="=======================" + sheet.getSheetName() + "=========================<br><br>";
//	 
//		                int firstRowIndex = sheet.getFirstRowNum();
//		                int lastRowIndex = sheet.getLastRowNum();
//		                html+="<table border='1' align='left'>";
//		                Row firstRow = sheet.getRow(firstRowIndex);
//		                for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
//		                    Cell cell = firstRow.getCell(i);
//		                    String cellValue = getCellValue(cell, true);
//		                    html+="<th>" + cellValue + "</th>";
//		                }
//	 
//	 
//		                //行
//		                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
//		                    Row currentRow = sheet.getRow(rowIndex);
//		                    html+="<tr>";
//		                    if(currentRow!=null){
//		                    	
//		                    	int firstColumnIndex = currentRow.getFirstCellNum(); 
//		                    	int lastColumnIndex = currentRow.getLastCellNum();
//		                    	//列
//		                    	for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
//		                    		Cell currentCell = currentRow.getCell(columnIndex);
//		                    		String currentCellValue = getCellValue(currentCell, true);
//		                    		html+="<td>"+currentCellValue + "</td>";
//		                    	}
//		                    }else{
//		                    	 html+=" ";
//		                    }
//		                    html+="</tr>";
//		                }
//		                html+="</table>";
//		            
//	 
//		                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//			       	     DOMSource domSource = new DOMSource ();
//			       	     StreamResult streamResult = new StreamResult (outStream);
//			       	     
//			       	     TransformerFactory tf = TransformerFactory.newInstance();
//			       	     Transformer serializer = tf.newTransformer();
//			       	     serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
//			       	     serializer.setOutputProperty (OutputKeys.INDENT, "yes");
//			       	     serializer.setOutputProperty (OutputKeys.METHOD, "html");
//			       	     serializer.transform (domSource, streamResult);
//			       	     outStream.close();
//			       	  FileUtils.writeStringToFile(new File (path, htmlname), html, "gbk");
//					
//						char[] chars=new char[2048];
//						int tmp=0;
//						inputStreamReader = new InputStreamReader( new FileInputStream(path+File.separator+htmlname),"GBK");
//		            	if((tmp=inputStreamReader.read(chars))>0) {
//		    				pw.write(chars, 0, tmp);
//		    			}
//						
//		            }
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }finally {
//				     pw.flush();
//						
//					 if (is != null)
//						 is.close();
//					 if(inputStreamReader != null ) 
//						 inputStreamReader.close();
//			         if (pw != null)
//			        	 pw.close();
//				}
//		   	     
//		 }
//	
//		   /**
//		     * 读取单元格
//		     * 
//		     */
//		    private static String getCellValue(Cell cell, boolean treatAsStr) {
//		        if (cell == null) {
//		            return "";
//		        }
//
//		        if (treatAsStr) {
//		            cell.setCellType(Cell.CELL_TYPE_STRING);
//		        }
//
//		        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//		            return String.valueOf(cell.getBooleanCellValue());
//		        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//		            return String.valueOf(cell.getNumericCellValue());
//		        } else {
//		            return String.valueOf(cell.getStringCellValue());
//		        }
//		    }
//
//
//    //判断文件是否存在
//	/*private static void fileExists(String path) {
//		File file =new File(path);
//		System.out.println(file.exists());
//	}*/
//
//	 
//}
