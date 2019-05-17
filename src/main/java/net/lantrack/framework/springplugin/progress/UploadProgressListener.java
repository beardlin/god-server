package net.lantrack.framework.springplugin.progress;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

/**
 * 监听上传文件
 * @author liumy
 *
 */
@Component
public class UploadProgressListener implements ProgressListener {

	private HttpSession session;  
	/*
	 * 定义一个session中的唯一一个上传时用的参数
	 */
	public static String PROGRESS_ENTITY = "lantrackProgressEntity";

    public void setSession(HttpSession session){  
        this.session=session;  
        ProgressEntity status = new ProgressEntity();  
        session.setAttribute(UploadProgressListener.PROGRESS_ENTITY, status);  
    }  

    /* 
     *  pBytesRead 到目前为止读取文件的比特数
     *  pContentLength 文件总大小
     *  pItems 目前正在读取第几个文件 
     */  
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {  
        ProgressEntity status = (ProgressEntity) session.getAttribute(UploadProgressListener.PROGRESS_ENTITY);  
        status.setpBytesRead(pBytesRead);  
        status.setpContentLength(pContentLength);  
        status.setpItems(pItems);
    }  

}
