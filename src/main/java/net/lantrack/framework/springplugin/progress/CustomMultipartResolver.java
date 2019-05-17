package net.lantrack.framework.springplugin.progress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CustomMultipartResolver extends CommonsMultipartResolver{


	@Autowired
	private UploadProgressListener uploadProgressListener;

	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		//问文件上传进度监听器设置session用于存储上传进度
		uploadProgressListener.setSession(request.getSession());
		//将文件上传进度监听器加入到 fileUpload 中
		fileUpload.setProgressListener(uploadProgressListener);
		
//		Long sizeLong = (long) new ServletRequestContext(request).getContentLength();
//		if(sizeLong > fileUpload.getSizeMax()){
//			return definParseFileItems("系统上传文件过大");
//		}
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		}
		catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		}
		catch (FileUploadBase.FileSizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getFileSizeMax(), ex);
		}
		catch (FileUploadException ex) {
			throw new MultipartException("Failed to parse multipart servlet request", ex);
		}
	}
	
	/**
	 * 
	 * methodName: definParseFileItems
	 * 处理文件异常
	 * 提供所支持的文件类型配置；
                     提供文件大小限制配置；
                     提供文件保存路劲配置；
	 * date: 2018年1月30日 下午4:20:33 
	 * @param :  
	 * @author: liumy
	 * @param rmgEx
	 * @return
	 */
	protected MultipartParsingResult definParseFileItems(String rmgEx) {
		MultiValueMap<String, MultipartFile> multipartFiles = new LinkedMultiValueMap<String, MultipartFile>();
		Map<String, String[]> multipartParameters = new HashMap<String, String[]>();
		Map<String, String> multipartParameterContentTypes = new HashMap<String, String>();
		multipartParameters.put("rmg", new String[] {rmgEx});
		multipartParameters.put("exCode", new String[] {"1"});
		return new MultipartParsingResult(multipartFiles, multipartParameters, multipartParameterContentTypes);
	}
	
}
