package net.lantrack.framework.sysbase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.service.UpfileService;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.springplugin.progress.ProgressEntity;
import net.lantrack.framework.springplugin.progress.ProgressEntityModel;
import net.lantrack.framework.springplugin.progress.UploadProgressListener;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.model.updownfile.FileAnalyze;
import net.lantrack.framework.sysbase.model.updownfile.FileSample;


/**
 * 文件上传接口      
 * @date 2019年4月4日
 */
@RestController
@RequestMapping("/sysfile")
public class UpfileController extends BaseController {

	@Autowired
	protected UpfileService upfileService;
	
	
	
	
	/**
	 * 当前系统附件资源占用情况   sysfile/analyze
	 * @param info
	 * @return
	 * @date 2019年4月12日
	 */
	@RequestMapping("/analyze")
	public ReturnEntity analyze(ReturnEntity info) {
		try {
			FileAnalyze analyze = this.upfileService.analyzeFile();
			info.setResult(analyze);
		} catch (Exception e) {
			info.failed("获取统计信息失败");
		}
		return info;
	}
	
	
	/**
	 * 手动清理当前系统临时文件  sysfile/clearTemp
	 * @param info
	 * @return
	 * @date 2019年4月12日
	 */
	@RequestMapping("/clearTemp")
	public ReturnEntity clearTemp(ReturnEntity info) {
		try {
			this.upfileService.clearTempFile();
			info.success("清理成功");
		} catch (Exception e) {
			info.failed("清理失败");
		}
		return info;
	}
	
	/**
	 * 获取当前表单下的附件回显，只有id和name 数组
	 * sysfile/attach
	 * @param parms
	 * @param info
	 * @return
	 * @date 2019年4月12日
	 */
	@RequestMapping("/attach")
	public ReturnEntity getFormAttach(@RequestBody Map<String, String> parms,ReturnEntity info) {
		String ids = parms.get("ids");
		try {
			List<FileSample> attach = this.upfileService.getFormAttach(ids);
			info.setResult(attach);
		} catch (Exception e) {
			info.failed("获取文件失败！");
		}
		return info;
	}
	
	/**
	 * 通过资料库文件上传接口
	 * sysfile/upbylib
	 * @param ids
	 * @param info
	 * @return
	 * @date 2019年4月8日
	 */
	@RequestMapping("/upbylib")
	public ReturnEntity upByLib(String ids,ReturnEntity info ) { 
		if(StringUtils.isBlank(ids)) {
			info.failed("没有选择文件");
			return info;
		}
		try {
			List<SysUpDownFile> list = this.upfileService.upfileByLib(ids);
			List<FileSample> files = new ArrayList<>(list.size());
			for(SysUpDownFile file:list) {
				FileSample sm = new FileSample(String.valueOf(file.getId()), file.getOname());
				files.add(sm);
			}
			info.setResult(files);
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}
	
	/**
	 * 文件上传接口
	 * sysfile/up
	 * @param request
	 * @param info
	 * @return
	 * @date 2019年4月8日
	 */
	@RequestMapping("/up")
	public ReturnEntity upFile(HttpServletRequest request,ReturnEntity info ) { 
		try {
			//判断否有附件
			if(upfileService.checkFile(request)){
				SysUpDownFile sysUpDownFile = new SysUpDownFile ();
				List<SysUpDownFile> upFiles = this.upfileService.upFile(sysUpDownFile, request,"*",true,true);
				List<FileSample> files = new ArrayList<>(upFiles.size());
				for(SysUpDownFile file:upFiles) {
					FileSample sm = new FileSample(String.valueOf(file.getId()), file.getOname());
					files.add(sm);
				}
				info.setResult(files);
			}else {
				info.failed("未检测到文件!");
			}
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}
	/**
	 * 文件删除  sysfile/del
	 * @param parms
	 * @param info
	 * @return
	 * @date 2019年4月8日
	 */
	@RequestMapping("/del")
	public ReturnEntity deleteFile(@RequestBody Map<String, String> parms,ReturnEntity info ){
		String ids = parms.get("ids");
		if (!StringUtils.isNotBlank(ids)){
			info.setStatus(StatusCode.PARAMETER_ERROR);
			info.appendMessage("ids 不能为空");
			return info;
		}
		try {
			if(this.upfileService.delFileByIds(ids)){
				info.success("删除成功");
			}else{
				info.failed("删除失败");
			}
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
		return info;
	}
	/**
	 * 文件上传进度条  sysfile/process
	 * @param request
	 * @param info
	 * @param pro
	 * @return
	 * @date 2019年4月8日
	 */
	@RequestMapping("/process")  
	public Object process(HttpServletRequest request,ReturnEntity info,ProgressEntityModel pro){  
		HttpSession session = request.getSession();
		try {
			ProgressEntity pEntity = (ProgressEntity) session.getAttribute(UploadProgressListener.PROGRESS_ENTITY); 
			pEntity.setpBytesRead(pEntity.getpBytesRead());
			pEntity.setpContentLength(pEntity.getpContentLength());
			pEntity.setpItems(pEntity.getpItems());
			info.setResult(pEntity);
		} catch (Exception e) {
			info.setStatus(StatusCode.PARAMETER_ERROR);
		}
		return "";
	}  
	
	/**
	 * 文件下载  sysfile/download
	 * @param ids
	 * @param info
	 * @return
	 * @date 2019年4月11日
	 * @RequestBody Map<String, String> parms
	 */
	@RequestMapping("/download")
	public ReturnEntity downFile(String ids,HttpServletRequest request,HttpServletResponse response,ReturnEntity info ){
//		String ids = parms.get("ids");
//		System.out.println(PathUtil.getResourceRootPath(request));
		try {
			if (!StringUtils.isNotBlank(ids)){
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			 upfileService.download(ids,request,response);
		}catch(Exception e){
			info.failed("下载失败");
		}
		return info;
	}
	
	/**
	 * 查看图片  sysfile/picture
	 * @param id图片ID,flag图像类型，flag = null 点击看大图,flag = 60,50查看自定义大小缩略图
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 * @date 2019年4月15日
	 * @RequestBody Map<String, String> parms
	 */
	@RequestMapping("/picture")
	public ReturnEntity getPicture(String id,String flag,HttpServletRequest request,
			HttpServletResponse response,ReturnEntity info ) {
//		String id = parms.get("id");
//		String flag = parms.get("flag");
		try {
			if (!StringUtils.isNotBlank(String.valueOf(id))){
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			upfileService.getPictrue(Integer.valueOf(id), response, request,flag);
		} catch (Exception e) {
			info.failed("查看失败");
		}
		return info;
	}
	/**
	 * 文件预览  sysfile/preview
	 * @param id
	 * @param response
	 * @param info
	 * @return
	 * @date 2019年4月16日
	 */
	@RequestMapping("/preview")
	public ReturnEntity perview(Integer id,HttpServletRequest request,HttpServletResponse response,ReturnEntity info ){
		try {
			if (!StringUtils.isNotBlank(String.valueOf(id))){
				info.setStatus(StatusCode.PARAMETER_ERROR);
				return info;
			}
			upfileService.preview(id, request,response);
		}catch(Exception e){
			info.failed("预览失败:"+e.getMessage());
		}
		return info;
	}
	

}
