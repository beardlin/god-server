package net.lantrack.framework.core.importexport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler{
	
	private Configuration configuration = null;
	 
	@SuppressWarnings("deprecation")
    public DocumentHandler() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}
	public void createDoc(File outfile,String modle,Map<String,Object> dataMap) throws Exception{
		//要填入模本的数据文件dataMap
		//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		//这里我们的模板是放在/net/lantrack/doc包下面
//		configuration.setClassForTemplateLoading(this.getClass(), "/net/lantrack/doc");
		configuration.setClassForTemplateLoading(this.getClass(), "/doc");
		Template t=null;
			t = configuration.getTemplate(modle);
		Writer out = null;
		try {
			//这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以
			//，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。 
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile),"UTF-8"));
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
 
        try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    
	}
	public String getImageStr(String imgFile){
        InputStream in=null;
        byte[] data=null;
        try {
            in=new FileInputStream(imgFile);
            data=new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            return "";
        } catch (IOException e) {
//            e.printStackTrace();
            return "";
        }
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(data);
    }
	public static void main(String[] args) {
		DocumentHandler doc = new DocumentHandler();
		Map<String,Object> dataMap = new HashMap<String, Object>();
		List<String> list  = new ArrayList<String>();
		for(int i=0;i<30;i++){
			list.add(i+"");
		}
		dataMap.put("list",list);
		try {
			doc.createDoc(new File("D:/test.doc"),"test.ftl", dataMap);
			System.out.println("生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

