/**
 *
 */
package net.lantrack.framework.sysbase.service;

import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.sysbase.entity.SysFile;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.model.Tree;
import net.lantrack.framework.sysbase.model.updownfile.FileLibAnalyze;

import java.rmi.ServerException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源管理Service
 * 2018年3月10日
 * @author lin
 */
public interface SysFileService {
	
    //系统文件存放目录P_ID
    public static final Integer SYS_ROOT_PID = 0;
    
    
	
	/**
	 * 统计当前系统附件 文件个数，总大小，临时文件大小
	 * @return
	 * @date 2019年4月12日
	 */
    FileLibAnalyze analyzeFile();
    
    /**
     * 获取从资料库上传的可用给上传使用
     * @param fileName
     * @return
     * @date 2019年4月22日
     */
    List<MapEntity> getUpFile(String fileName);
    
    /**
     * 获取文件目录树结构
     * @return
     * @date 2019年4月22日
     */
    List<Tree> getFileTree();
    
    /**
     * 文件预览
     * @param id
     * @param response
     * @date 2019年4月22日
     */
    void preview(Integer id,HttpServletResponse response);
    
    /**
     * 修改文件
     * @param entity
     * @return
     * @date 2019年4月19日
     */
    SysFile update(SysFile entity);
	
	/**
	 * 获取当前目录下的所有匹配的文件
	 * @Description: 
	 * @author lin
	 * @date 2018年5月21日
	 */
	List<SysFile> searchFiles(String path,String name) throws ServiceException;
	
	/**
	 * 批量下载文件
	 * @Description: 
	 * @author lin
	 * @date 2018年5月23日
	 */
	void downLoadFile(HttpServletResponse response,String ids);
	/**
	 * 删除文件或文件夹
	 * @Description: 
	 * @author lin
	 * @date 2018年5月21日
	 */
	boolean deleteFile(String path);
	/**
	 * 创建文件夹
	 * @Description: 
	 * @author lin
	 * @date 2018年5月21日
	 */
	void createDir(String id,String dirName);
	/**
	 * 上传文件
	 * @Description: 
	 * @author lin
	 * @date 2018年5月21日
	 */
	boolean upFile(String id,List<SysUpDownFile> files,HttpServletRequest req);
	/**
	 * 修改文件名称
	 * @Description: 
	 * @author lin
	 * @date 2018年5月21日
	 */
	boolean updateFileName(String path,String nname) throws ServiceException;

    /**
     * 根据pid查找子文件
     * @param pid
     * @return
     * @throws ServerException
     * 2018年3月10日
     * @author lin
     */
    List<SysFile> getFilesByPid(Integer pid) throws ServerException;

}