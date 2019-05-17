package net.lantrack.framework.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**
 * 文件类型转换工具
 * 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,EPUB, XPS, SWF 等相互转换
 * @date 2019年4月16日
 */
public class FileConvertionUtil {
	private static Logger logger = LoggerFactory.getLogger(FileConvertionUtil.class);

	/**
	 * 文件格式
	 */
	public static final int DOC = SaveFormat.DOC;
	public static final int DOCX = SaveFormat.DOCX;
	public static final int PDF = SaveFormat.PDF;
	public static final int HTML = SaveFormat.HTML;
	public static final int PNG = SaveFormat.PNG;
	public static final int BMP = SaveFormat.BMP;
	public static final int JPEG = SaveFormat.JPEG;
	public static final int GIF = SaveFormat.GIF;
	
	static {
		// 先注册许可证，在resouce中licensexml,否则转换出的文件有水印
		InputStream is = FileConvertionUtil.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
		License aposeLic = new License();
		try {
			aposeLic.setLicense(is);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件转换License注册失败：" + e.getMessage());
		}
	}
	/**
	 * 将文件转换为HTML格式
	 * @param ori
	 * @return
	 * @date 2019年4月16日
	 */
    public static File toHTML(File ori) {
    	File convertFile = null;
        try {
        	if(!ori.exists()) {
        		logger.error(ori.getAbsolutePath()+"被转换文件不存在！");
        		return convertFile;
        	}
        	//在同路径下生成被转换文件
        	convertFile = convertFile(ori.getAbsolutePath(),"html");
        	FileOutputStream os = new FileOutputStream(convertFile);
        	//读取源文件
        	Document doc = new Document(new FileInputStream(ori));
        	//进行转换（另存为）
        	doc.save(os,HTML);
        } catch (Exception e) {
        	logger.error("文件转换为HTML失败：{}",ori.getName()+e.getMessage());
        }
        return convertFile;
    }
	/**
	 * 将文件转换为PDF格式
	 * @param ori
	 * @return
	 * @date 2019年4月16日
	 */
    public static File toPDF(File ori) {
    	File convertFile = null;
        try {
        	String path = ori.getAbsolutePath();
        	if(!ori.exists()) {
        		logger.error(path+"被转换文件不存在！");
        		return convertFile;
        	}
        	if(path.endsWith("xls")||path.endsWith("xlsx")) {
        		convertFile = execlToPDF(ori);
        	}else {
        		convertFile = otherToPDF(ori);
        	}
        } catch (Exception e) {
        	logger.error("文件转换为PDF失败：{}",ori.getName()+e.getMessage());
        }
        return convertFile;
    }
    /**
     * 将Excel转换为PDF
     * @param ori
     * @return
     * @date 2019年4月16日
     */
    private static File execlToPDF(File ori) {
    	String inputFile = ori.getAbsolutePath();
    	int lastIndexOf = inputFile.lastIndexOf(".");
    	String pdfPath = inputFile.substring(0, lastIndexOf)+".pdf";
    	JacobUtil.excel2PDF(inputFile, pdfPath);
    	File pdfFile = new File(pdfPath);
    	if(pdfFile.exists()) {
    		return pdfFile;
    	}
    	return null;
    }
    
    /**
     * 将文件类型转换为PDF
     * @param ori
     * @return
     * @date 2019年4月16日
     */
	private static File otherToPDF(File ori) {
    	File convertFile = null;
    	FileInputStream inputStream = null;
    	FileOutputStream os = null;
        try {
        	if(!ori.exists()) {
        		logger.error(ori.getAbsolutePath()+"被转换文件不存在！");
        		return convertFile;
        	}
        	//在同路径下生成被转换文件
        	convertFile = convertFile(ori.getAbsolutePath(),"pdf");
        	//读取源文件
        	inputStream = new FileInputStream(ori);
        	Document doc = new Document(inputStream);
        	//进行转换（另存为）
        	os = new FileOutputStream(convertFile);
        	doc.save(os,PDF);
        } catch (Exception e) {
        	logger.error("文件转换为PDF失败：{}",ori.getName()+e.getMessage());
        }finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(os);
		}
        return convertFile;
    }
    
    
    /**
     * 将2003版Word文档（doc）转换为2007版（docx）Word文档
     */
    public static File toDocx(File doc) {
    	File convertFile = null;
        try {
        	if(!doc.exists()) {
        		logger.error(doc.getAbsolutePath()+"被转换文件不存在！");
        		return convertFile;
        	}
        	//在同路径下生成被转换文件
        	convertFile = convertFile(doc.getAbsolutePath(),"docx");
        	FileOutputStream os = new FileOutputStream(convertFile);
        	//读取源文件
        	Document document = new Document(new FileInputStream(doc));
        	//进行转换（另存为）
        	document.save(os,DOCX);
        } catch (Exception e) {
        	logger.error("文件转换为DOCX失败：{}",doc.getName()+e.getMessage());
        }
        return convertFile;
    }
    
    /**
     * 根据源文件类型生成一个新的文件类型
     * @param filePath 源文件
     * @param type 新文件类型
     * @return
     * @throws IOException 
     * @date 2019年4月16日
     */
    public static File convertFile(String filePath,String type) throws IOException {
    	int indexOf = filePath.lastIndexOf(".");
    	String substring = filePath.substring(0, indexOf);
    	File convert  = new File(substring+"."+type);
    	if(!convert.exists()) {
    		convert.createNewFile();
    	}
    	return convert;
    }
    
    public static void main(String[] args) throws IOException {
    	File file = new File("D:/student.xlsx");
    	long s = System.currentTimeMillis();
//    	otherToPDF(file);
    	toHTML(file);
    	long e = System.currentTimeMillis();
    	System.out.println(e-s);
	}

}
