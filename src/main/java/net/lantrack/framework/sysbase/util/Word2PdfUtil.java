package net.lantrack.framework.sysbase.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
 
import org.aspectj.weaver.ast.Test;
 
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
 
 
/**
 * @author hww
 * @since 2018-09-27
 */
public class Word2PdfUtil {
 
    public static void main(String[] args) {
//    	Word2PdfUtil.doc2docx("D://下载文件//氟化物（电极法）.doc", "D://下载文件//氟化物（电极法）.docx");
    }
 
    /**
     * 验证License
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * 将Word文档（doc、docx）转换为PDF文件
     * @param inPath
     * @param outPath
     */
    public static void doc2pdf(String inPath, String outPath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
                                         // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("Word转Pdf成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将2003版Word文档（doc）转换为2007版（docx）Word文档
     * @param inPath
     * @param outPath
     */
    public static void doc2docx(String inPath, String outPath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.DOCX);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
                                         // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("doc转docx成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
