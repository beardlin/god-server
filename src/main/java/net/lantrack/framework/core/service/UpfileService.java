package net.lantrack.framework.core.service;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.model.updownfile.FileAnalyze;
import net.lantrack.framework.sysbase.model.updownfile.FileSample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface UpfileService {
	
	/**
	 * 是否启用FTP
	 */
	public static final boolean USE_FTP = true;
	
	/**
	 * 通过资料库进行文件上传
	 * @param ids
	 * @return
	 */
	List<SysUpDownFile> upfileByLib(String ids);
	
	/**
	 * 文件预览
	 * @param id
	 * @param response
	 * @date 2019年4月16日
	 */
	void preview(Integer id,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 单个或批量下载文件
	 * @param ids
	 * @date 2019年4月15日
	 */
	void download(String ids,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 统计当前系统附件 文件个数，总大小，临时文件大小
	 * @return
	 * @date 2019年4月12日
	 */
	FileAnalyze analyzeFile();
	
	/**
	 * 获取当前表单下的附件数据回显
	 * @param ids
	 * @return
	 * @date 2019年4月12日
	 */
	List<FileSample> getFormAttach(String ids);

	/**
	 * 文件上传接口
	 * @param sysUpDownFile 文件参数等信息
	 * @param request
	 * @param fileType 上传文件的类型 *表示所有都可以，多文件类别采用"png,jpg"
	 * @param temp 是否入库
	 * @param isfilelib 是否为资料库true  ,系统附件则false
	 * @return SysUpDownFile
	 */
	List<SysUpDownFile> upFile(SysUpDownFile sysUpDownFile, HttpServletRequest request, String fileType,Boolean temp,Boolean isfilelib);
	
	/**
	 * 将当前表单成功提交的附件标志位持久性文件，并挂载到当前表单id下
	 * @param ids
	 * @param tableId
	 * @throws ServiceException
	 * @date 2019年4月12日
	 */
	void changeToPersistent(String ids,String tableId,String tableName) throws ServiceException;

	/**
	 * 
	 * methodName: checkFile
	 * 判断是否有上传的文件，是否为空
	 * date: 2018年1月17日 下午3:34:23 
	 * @param :  
	 * @author: liumy
	 * @param request
	 * @return
	 */
	boolean checkFile(HttpServletRequest request);

	/**
	 * 
	 * methodName: checkFileType
	 * 文件类型的判断;根据指定的文件
	 * date: 2018年1月17日 下午3:35:41 
	 * @param :  
	 * @author: liumy
	 * @param request
	 * @param type 指定的文件类型
	 * @return
	 */
	boolean checkFileType(HttpServletRequest request, String type);

	/**
	 * @param request
	 * @return
	 */
	boolean checkMul(HttpServletRequest request);

	/**
	 * 根据ID删除上传的文件（删除对应数据库和实体文件）
	 *
	 * @param id
	 * @return
	 */
	boolean delFileById(Integer id) throws ServiceException;
	/**
	 * 
	 * methodName: DownFile
	 * 根据文件id  下载文件
	 * date: 2018年1月16日 下午5:42:55 
	 * @param :  
	 * @param id 文件的id
	 * @return
	 * @author: liumy
	 * @throws Exception
	 */

//	ResponseEntity<byte[]> downFile(Integer id) throws Exception;
	/**
	 * 
	 * methodName: getPictrue
	 * 获得图片流，用于展示图片
	 * flag=null 或空则查看大图，如果flag = 60,50（width,heith） 则查看自定义缩略图
	 * date: 2018年1月17日 下午2:09:05 
	 * @param :  
	 * @author: liumy
	 * @param id  文件的id
	 * @param response 请求的相应
	 * @param request
	 * @throws IOException
	 */
	StatusCode getPictrue(Integer id,HttpServletResponse response, HttpServletRequest request,String flag) throws IOException;
	/**
	 * 查看图片 flag=null 或空则查看缩略图，如果flag = big则查看大图
	 * methodName: disposePictureIO
	 * date: 2018年3月14日 下午3:28:51 
	 * @param :  
	 * @author: liumy
	 * @param response
	 * @param request
	 * @param ifHavePicturePath
	 * @return
	 */
	Boolean disposePictureIO(HttpServletResponse response, HttpServletRequest request ,String ifHavePicturePath,String flag);

	

	/**
	 * 
	 * methodName: selectByPrimaryKey
	 * 根据id  获得一个上传实体对象
	 * date: 2018年1月16日 下午5:30:17 
	 * @param :  
	 * @author: liumy
	 * @param id 文件的id
	 * @return
	 * @throws ServiceException
	 */
	SysUpDownFile selectByPrimaryKey(Integer id) throws ServiceException;
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	boolean deleteDir(File dir) ;
	/**
	 * 
	 * methodName: delFileByIds
	 * 根据id 批量删除对应数据库和实体文件
	 * date: 2018年3月15日 下午3:23:54 
	 * @param :  
	 * @author: liumy
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	boolean delFileByIds(String ids) throws ServiceException;
	
	/**
	 * 清理垃圾文件（表单上传失败的）
	 * @date 2019年4月12日
	 */
	void clearTempFile();


}
