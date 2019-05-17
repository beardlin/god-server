package net.lantrack.framework.sysbase.controller;


import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.service.UpfileService;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysFile;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.model.Tree;
import net.lantrack.framework.sysbase.model.updownfile.FileLibAnalyze;
import net.lantrack.framework.sysbase.service.SysFileService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统资料管理
 * @author lin
 */
@RestController
@RequestMapping("/filelib")
public class SysFileController extends BaseController {

    @Autowired
    SysFileService sysFileService;
    @Autowired
	UpfileService upfileService; 
    
    
	
	/**
	 * 当前系统资料库资源占用情况   filelib/analyze
	 * @param info
	 * @return
	 * @date 2019年4月12日
	 */
	@RequestMapping("/analyze")
	public ReturnEntity analyze(ReturnEntity info) {
		try {
			FileLibAnalyze analyze = this.sysFileService.analyzeFile();
			info.setResult(analyze);
		} catch (Exception e) {
			info.failed("获取统计信息失败");
		}
		return info;
	}
    
    
    /**
     * 获取可上传的文件  ,文件上传选择文件
     * filelib/getupfile
     * @param parms
     * @param info
     * @return
     * @date 2019年4月22日
     */
    @RequestMapping("/getupfile")
    public ReturnEntity getupfile(Map<String, String> parms,ReturnEntity info) {
    	String name = parms.get("name");
    	try {
    		List<MapEntity> upFileList = this.sysFileService.getUpFile(name);
        	info.setResult(upFileList);
		} catch (Exception e) {
			info.failed("获取可上传文件失败！");
		}
    	return info;
    }
    
    /**
     * 获取资料库的目录tree结构
     * filelib/tree 
     * @param info
     * @return
     * @date 2019年4月22日
     */
    @RequestMapping("/tree")
    public ReturnEntity fileTree(ReturnEntity info) {
    	try {
    		List<Tree> fileTree = this.sysFileService.getFileTree();
        	info.setResult(fileTree);
		} catch (Exception e) {
			info.failed("查看目录结构失败！");
		}
    	return info;
    }
    
    /**
     * 文件预览  filelib/preview
     * @param parms
     * @param response
     * @param info
     * @return
     * @date 2019年4月22日
     */
    @RequestMapping("/preview")
    public ReturnEntity preview(@RequestBody Map<String, String> parms,
    		HttpServletResponse response,ReturnEntity info) {
    	String id = parms.get("id");
    	try {
			this.sysFileService.preview(Integer.valueOf(id), response);
		} catch (Exception e) {
			info.failed("预览失败："+e.getMessage());
		}
    	return info;
    }
    
    //搜索当前文件夹下目录文件   filelib/findFile
    @RequestMapping("/findFile")
    public ReturnEntity findFile(@RequestBody Map<String, String> parms,ReturnEntity info) {
    	String id = parms.get("id");
    	String name = parms.get("name");
    	try {
    		List<SysFile> list = this.sysFileService.searchFiles(id,name);
    		info.setResult(list);
    		info.success("查询成功");
		} catch (Exception e) {
			info.failed("查看失败："+e.getMessage());
		}
    	return info;
    }
    
    //下载文件
    //  filelib/download
    @RequestMapping("/download")
    public ReturnEntity downLoad(@RequestBody Map<String, String> parms,HttpServletResponse repos,ReturnEntity info) {
    	String ids = parms.get("ids");
    	if(StringUtils.isBlank(ids)) {
    		info.setStatus(StatusCode.PARAMETER_ERROR);
    		return info;
    	}
    	try {
    		sysFileService.downLoadFile(repos,ids);
		} catch (Exception e) {
			info.failed("下载失败："+e.getMessage());
		}
    	return info;
    }
    
    //删除文件  filelib/deleteFile
    @RequestMapping("/deleteFile")
    public ReturnEntity deleteFile(@RequestBody Map<String, String> parms,ReturnEntity info) {
    	String ids = parms.get("ids");
    	try {
			if(!this.sysFileService.deleteFile(ids)) {
				info.failed("删除失败");
			}
			info.success("删除成功");
		} catch (Exception e) {
			info.failed("删除失败："+e.getMessage());
		}
    	return info;
    }
    
    //创建文件夹  filelib/createDir
    @RequestMapping("/createDir")
    public ReturnEntity createDir(@RequestBody Map<String, String> parms,ReturnEntity info) {
    	String id = parms.get("id");
    	String dir = parms.get("dir");
    	try {
    		this.sysFileService.createDir(id, dir);
		} catch (Exception e) {
			info.failed(e.getMessage());
		}
    	return info;
    }
    
    
    //上传文件   filelib/upFile
    @RequestMapping("/upFile")
    public ReturnEntity upFile(@RequestBody Map<String, String> parms,HttpServletRequest req,ReturnEntity info) {
    	//当前所在文件夹id，即pid
    	String pid = parms.get("pid");
    	List<SysUpDownFile> files = null;
		// 上传文件
		if (upfileService.checkFile(req)) {
			SysUpDownFile upfile = new SysUpDownFile();
			try {
				files = upfileService.upFile(upfile, req, "*", false,true);
				if (files == null||files.size()==0) {
					info.failed("文件上传失败!");
					return info;
				}
			} catch (Exception e) {
				info.failed("文件上传失败:" + e.getMessage());
				return info;
			}
		}
		if (files == null||files.size()==0) {
			info.failed("未检测到文件");
			return info;
		}
		// 解析文件
		try {
			this.sysFileService.upFile(pid, files,req);
			info.success("操作成功");
		} catch (Exception e) {
			info.failed("操作失败:"+e.getMessage());
		}
    	return info;
    }
    
    //重命名   filelib/updateFileName
    @RequestMapping("/updateFileName")
    public ReturnEntity updateFileName(@RequestBody Map<String, String> parms,ReturnEntity info) {
    	String id = parms.get("id");
    	String name = parms.get("name");
    	try {
    		if(this.sysFileService.updateFileName(id, name)) {
    			info.success("操作成功");
    		}else {
    			info.failed("操作失败");
			}
		} catch (Exception e) {
			info.failed("操作失败："+e.getMessage());
		}
    	return info;
    }
    
    
    /**
     * 根据父id查看当前文件夹下文件
     */
    // filelib/getFiles
    @RequestMapping("/getFiles")
    public ReturnEntity getFile(@RequestBody Map<String, String> parms,ReturnEntity info){
    	String id = parms.get("id");
        try {
        	Integer pid = null;
        	if(StringUtils.isNotBlank(id)) {
        		pid = Integer.valueOf(id);
        	}
            List<SysFile> filesByPid = sysFileService.getFilesByPid(pid);
            if(filesByPid==null||filesByPid.size()==0){
                info.failed("当前文件夹下无其他文件");
            }else{
                info.success("查看成功");
                info.setResult(filesByPid);
            }
        } catch (ServerException e) {
            info.failed(e.getMessage());
        }
        return info;
    }

}
