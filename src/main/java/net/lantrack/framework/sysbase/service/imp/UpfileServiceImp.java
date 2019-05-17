/**
 *
 */
package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.config.Config;
import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.UpfileService;
import net.lantrack.framework.sysbase.dao.SysFileMapper;
import net.lantrack.framework.sysbase.dao.SysUpDownFileDao;
import net.lantrack.framework.sysbase.entity.SysFile;
import net.lantrack.framework.sysbase.entity.SysFileExample;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.entity.SysUpDownFileExample;
import net.lantrack.framework.sysbase.entity.SysUpDownFileExample.Criteria;
import net.lantrack.framework.sysbase.model.updownfile.FileAnalyze;
import net.lantrack.framework.sysbase.model.updownfile.FileSample;
import net.lantrack.framework.sysbase.util.FtpUtil;
import net.lantrack.framework.sysbase.util.PictureUtil;
import net.lantrack.framework.sysbase.util.SysFileUtils;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.FileConvertionUtil;
import net.lantrack.framework.core.util.FileSizeUtil;
import net.lantrack.framework.core.util.FormatHandler;
import net.lantrack.framework.core.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.common.collect.Lists;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 文件上传Service
 * 2018年2月2日
 * @author lmy
 */
@SuppressWarnings("rawtypes")
@Service
public class UpfileServiceImp extends BaseService  implements UpfileService {
	@Autowired
	protected SysUpDownFileDao sysUpDownFileDao;
	
	@Autowired
	protected SysFileMapper sysFileMapper;
	
	@Autowired
	BaseDao baseDao;

	@Override
	public List<SysUpDownFile> upFile(SysUpDownFile sysUpDownFile, HttpServletRequest request,
			String fileType,Boolean temp,Boolean isfilelib) {
		//判断是否有上传的文件，是否为空
		if(!this.checkFile(request)){
			throw new ServiceException("请选择要上传的文件!");
		}
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (!multipartResolver.isMultipart(request)) {
			throw new ServiceException("form表单不是multipart/form-data类型!");
		}
		//文件类型的判断
		if(!this.checkFileType(request, fileType)){
			throw new ServiceException("文件类别不支持!");
		}
		List<SysUpDownFile> list =Lists.newArrayList();
		// 将request变成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 获取multiRequest 中所有的文件名,可有多种附件
		Iterator iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			SysUpDownFile sDownFile = new SysUpDownFile();
			// 一次遍历所有附件 根据 name 获取上传的附件...每个附件可有多个文件 
			List<MultipartFile> files = multiRequest.getFiles(iter.next().toString());
			if(files!=null && files.size()>0) {
				for(MultipartFile multipartFile:files) {
					//获取页面表单中文件组件的名字
					sDownFile.setFileContent(multipartFile.getName());
					//获得路径等参数
					this.getUpFilePath(sDownFile, multipartFile,isfilelib);
					try {
						if(USE_FTP) {//FTP上传
							FtpUtil.upload(sDownFile.getUploadpath(), multipartFile.getInputStream());
						}else {//本地存储
							multipartFile.transferTo(new File(sDownFile.getUploadpath()));
						}
						//上传完成后看是否入库
						if(temp){
							this.sysUpDownFileDao.insert(sDownFile);
						}
						list.add(sDownFile);
					} catch (IllegalStateException ilse) {
						ServiceException se =  new ServiceException("非法文件"+ilse.getMessage());
						se.initCause(ilse);
						throw se;
					} catch (Exception e) {
						throw new ServiceException("入库失败！");
					}
					
					
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * methodName: getSysUpDownFile
	 * 获得文件的上传路径和名字等
	 * date: 2018年1月16日 下午1:52:03 
	 * @param sDownFile
	 * @param multipartFile
	 * @param isfilelib 区分是否为附件和资料库文件  true选择资料库地址，false选择附件地址
	 */
	private void getUpFilePath(SysUpDownFile sDownFile,MultipartFile multipartFile,Boolean isfilelib){
		String rootPath = "";
		if(isfilelib) {//系统资料库根路径
			rootPath = Config.resourcePath;
		}else {//系统上传附件根路径
			rootPath = Config.uploadPath;
		}
		//文件上传路径
		String fileDir =rootPath+File.separator+DateUtil.getDate("yyyyMMdd")+File.separator;
		if(!USE_FTP) {//本地存储路径不存在新建路径
			File file = new File(fileDir);
			if(!file.exists()) {
				file.mkdirs();
			}
		}
		String filename =multipartFile.getOriginalFilename();
		//获得文件类型
		String postfixType = filename.substring(filename.lastIndexOf(".") + 1);
		//以yyyyMMddHHmmss格式命名新名称
		String newName = DateUtil.getNumDateFormat()+"."+postfixType;
		sDownFile.setOname(filename);
		sDownFile.setNname(newName);
		//格式化路径
		String filepath =PathUtil.formatFilePath(fileDir + newName);
		sDownFile.setFileType(postfixType);
		sDownFile.setFileSize(multipartFile.getSize());
		sDownFile.setUploadpath(filepath);
	}

	@Override
	public boolean checkFile(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		boolean temp = true;
		//判断 request 是否有文件上传,即多部分请求...
		if (this.isMultipart(request, multipartResolver)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			int count =0;
			while (iter.hasNext()) {// 遍历所有附件
				// 遍历所有文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next().toString());
				for(MultipartFile file:files) {
					if(!file.isEmpty()) {
						count++;
					}
				}
			}
			if(count==0) {
				return false;
			}
		} else {
			temp = false;
		}
		return temp;

	}

	@SuppressWarnings("unused")
	@Override
	public boolean checkFileType(HttpServletRequest request,String fileType){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		boolean temp = true;

		// 将request变成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 获取multiRequest 中所有的文件名
		Iterator iter = multiRequest.getFileNames();

		while (iter.hasNext()) {
			// 一次遍历所有文件
			MultipartFile file = multiRequest.getFile(iter.next().toString());
			if(file.isEmpty()) {
				continue;
			}
			String fileName =file.getOriginalFilename(); 
			int fileNameLength = fileName.lastIndexOf(".");
			String postfixType = fileName.substring(fileNameLength + 1);
			//上传文件包含系统禁止文件类型或者 不在当前表单指定上传格式里面
			if(!this.comparisonFileType(postfixType, fileType) || 
					Config.forbiddenUpfile.toLowerCase().contains(postfixType.toLowerCase())){
				temp = false;
				//跳出
				break; 
			}
		}
		return temp;
	}

	private boolean comparisonFileType(String postfixType, String fileType){
		String tempVariableOne = "*";
		String tempVariableTwo = "*.*";
		if(fileType ==  null || "".equals(fileType)
				|| tempVariableOne.equals(fileType) || tempVariableTwo.equals(fileType)){
			return true;
		}
		if(fileType.indexOf(postfixType) == -1){
			return false;
		}else{
			return true;			
		}
	}
	@Override
	public boolean checkMul(HttpServletRequest request) {
		//创建一个通用的多部分解析器. 
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		return this.isMultipart(request, multipartResolver);
	}

	private boolean isMultipart(HttpServletRequest request, CommonsMultipartResolver multipartResolver) {
		if (multipartResolver.isMultipart(request)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean delFileById(Integer id) throws ServiceException {
		try {
			if (id == null) {
				throw new ServiceException("id can not be null or empty!");
			}
			SysUpDownFile entity = this.sysUpDownFileDao.selectByPrimaryKey(id);
			if (entity != null) {
				//确定指定的文件是否存在,删除文件
				this.sysUpDownFileDao.deleteByPrimaryKey(id);
				if(USE_FTP) {//FTP删除
					return FtpUtil.delete(entity.getUploadpath());
				}else {//本地删除
					File file = new File(entity.getUploadpath());
					if (file.exists()) {
						file.delete();
					}
				}
			}
			return true;
		} catch (Exception e) {
			this.logger.error("文件删除失败："+e.getMessage());
			return false;
		}
	}
	
	
	@Override
	public void changeToPersistent(String ids,String tableId,String tableName) throws ServiceException {
		if (StringUtils.isBlank(ids)) {
			throw new ServiceException("id can not be null or empty!");
		}
		String formatIds = FormatHandler.isFormatIds(ids);
		try {
			StringBuffer sqlBuf = new StringBuffer("update sys_updownfile set yn = '1',tableid='");
			sqlBuf.append(tableId).append("',tablename='").append(tableName).append("' ");
			sqlBuf.append(" where id in(").append(formatIds).append(")");
		} catch (Exception e) {
			this.logger.error("文件ids"+ids+"持久化失败");
			throw new ServiceException("文件ids"+ids+"持久化失败");
		}
	}

	@Override
	public SysUpDownFile selectByPrimaryKey(Integer id) throws ServiceException {
		if (id == null) {
			throw new ServiceException("id can not be null or empty!");
		}
		Integer priId = Integer.valueOf(id.toString());
		return (SysUpDownFile) this.sysUpDownFileDao.selectByPrimaryKey(priId);
	}

	
	@Override
	public StatusCode getPictrue(Integer id, HttpServletResponse response, HttpServletRequest request,String flag) 
			throws IOException{
		//图片格式集
		if (id == null) {
			throw new ServiceException("id can not be null or empty!");
		}
		String allType = ".BMP.JPG.JPEG.PNG.GIF.jpg.jpeg.gif.png.bmp";
		SysUpDownFile sysUpDownFile = this.sysUpDownFileDao.selectByPrimaryKey(id);
		if(sysUpDownFile==null){
			return StatusCode.PARAMETER_ERROR;
		}
		String ifHavePicturePath= "";
		//指定的字符串值在字符串中首次出现的位置,没有返回-1
		if(allType.indexOf(sysUpDownFile.getFileType()) > 0){
			ifHavePicturePath = sysUpDownFile.getUploadpath();
		}
		if(!this.disposePictureIO(response, request, ifHavePicturePath,flag)){
			return StatusCode.SERVER_ERROR;
		}
		return StatusCode.SERVER_NORMAL;
	}
	/**
	 * 
	 * methodName: disposePictureIO
	 * 导出图片流
	 * date: 2018年1月17日 下午2:07:40 
	 * @param :  
	 * @author: liumy
	 * @param response 请求的相应
	 * @param request  
	 * @param ifHavePicturePath  是否有图片，有就用系统的默认图片
	 */
	public Boolean disposePictureIO(HttpServletResponse response, HttpServletRequest request ,
			String ifHavePicturePath,String flag){
		boolean temp = true ;

		try {
			response.setContentType("image/png");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0L);
//			File file = new File(ifHavePicturePath);
			//如果当前图片不存在，则使用系统的默认图片
//			if("".equals(ifHavePicturePath)&&!file.exists()){
//				String picturePath = "img/default.jpg";
//				ifHavePicturePath = this.disposePicturePath(request.getSession().getServletContext().getRealPath("/"), picturePath);
//			}
			
			//如果flag不为空，则查看自定义大小的压缩图片
			if(StringUtils.isNotBlank(flag)) {
				String[] wh = flag.split(",");
				if(USE_FTP) {//FTP存储
					InputStream stream = FtpUtil.getStream(ifHavePicturePath);
					PictureUtil.compressToOutPutStream(stream, Integer.valueOf(wh[0]), 
							Integer.parseInt(wh[1]), response.getOutputStream());
					stream.close();
				}else {//本地存储
					PictureUtil.compressToOutPutStream(ifHavePicturePath, Integer.valueOf(wh[0]), 
							Integer.parseInt(wh[1]), response.getOutputStream());
				}
			}else {
				if(USE_FTP) {//FTP下载
					ServletOutputStream outputStream = response.getOutputStream();
					boolean download = FtpUtil.download(ifHavePicturePath, outputStream);
					outputStream.close();
					return download;
				}else {//本地存储下载
					FileInputStream fis = new FileInputStream(ifHavePicturePath);
					//获取流中可读取的数据大小;
					int lisLength = fis.available();
					byte[] dataByte = new byte[lisLength];
					fis.read(dataByte);
					fis.close();
					//写图片
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					fis.close();
					outputStream.write(dataByte);
					outputStream.flush();
					outputStream.close();
				}
			}
		} catch (Exception e) {
			temp = false;
			this.logger.error(e.getMessage());
		}
		return temp;
	}


	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 */
	@Override
	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				//递归删除目录中的子目录下
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	@Override
	@Transactional
	public boolean delFileByIds(String  ids) throws ServiceException {
		if (StringUtils.isBlank(ids)) {
			this.logger.error("id can not be null or empty!");
			return false;
		}
		String[] tempIds = ids.split(",");
		SysUpDownFileExample example = new SysUpDownFileExample();
		Criteria cr = example.createCriteria();
		cr.andIdIn(Arrays.asList(tempIds));
		//文件id
		Integer fid = null;
		try {
			List<SysUpDownFile> list = this.sysUpDownFileDao.selectByExample(example);
			for(SysUpDownFile entity:list) {
				fid = entity.getId();
				//确定指定的文件是否存在,删除文件
				this.sysUpDownFileDao.deleteByPrimaryKey(entity.getId());
				if(USE_FTP) {//FTP存储
					FtpUtil.delete(entity.getUploadpath());
				}else {//本地存储
					File file = new File(entity.getUploadpath());
					if (file.exists()) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			this.logger.error(fid+"文件删除失败："+e.getMessage());
			return false;
		}
		return true;
	}
	
	@Override
	public List<FileSample> getFormAttach(String ids) {
		if(StringUtils.isBlank(ids)) {
			return null;
		}
		try {
			ids = FormatHandler.isFormatIds(ids);
			String sql = "select id,oname from sys_updownfile where id in("+ids+")";
			List<FileSample> list = this.baseDao.queryList(sql, FileSample.class);
			return list;
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return null;
	}
	@Override
	public void clearTempFile() {
		StringBuffer ids = new StringBuffer();
		try {
			SysUpDownFileExample example = new SysUpDownFileExample();
			Criteria cr = example.createCriteria();
			cr.andYnEqualTo("0");
			List<SysUpDownFile> list = this.sysUpDownFileDao.selectByExample(example);
			for(SysUpDownFile entity:list) {
				ids.append(entity.getId()).append(",");
				//确定指定的文件是否存在,删除文件
				this.sysUpDownFileDao.deleteByPrimaryKey(entity.getId());
				if(USE_FTP) {//FTP存储
					FtpUtil.delete(entity.getUploadpath());
				}else {//本地存储
					File file = new File(entity.getUploadpath());
					if (file.exists()) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			this.logger.error("删除临时文件失败："+ids.toString());
		}
	}
	@Override
	public FileAnalyze analyzeFile() {
		FileAnalyze analyze = new FileAnalyze();
		try {
			//统计系统附件总个数，总大小
			totalAnalyze(analyze);
			//统计临时附件总个数，总大小
			totalTempAnalyze(analyze);
			//统计系统附件所属表单的数量，大小
			totalTableAnalyze(analyze);
			//统计系统附件各类型数量，大小
			totalTypeAnalyze(analyze);
		} catch (Exception e) {
			this.logger.error("统计系统文件错误："+e.getMessage());
		}
		return analyze;
	}
	

	//统计系统附件各类型数量，大小
	private void totalTypeAnalyze(FileAnalyze analyze) {
		String totalSql = "SELECT file_type,COUNT(file_type) as typeCount,SUM(file_size) as typeSize  "
				+ "FROM `sys_updownfile` GROUP BY file_type ORDER BY typeCount DESC;";
		List<Map<String,Object>> listMap = this.baseDao.queryListMap(totalSql);
		if(listMap!=null && listMap.size()>0) {
			List<MapEntity> typeCountList = new ArrayList<>(listMap.size());
			List<MapEntity> typeSizeList = new ArrayList<>(listMap.size());
			for(Map<String, Object> map:listMap) {
				Object fileType = map.get("file_type");
				Object typeCount = map.get("typeCount");
				Object typeSize = map.get("typeSize");
				if(fileType!=null&&typeCount!=null&&typeSize!=null) {
					MapEntity tCount = new MapEntity();
					tCount.setK(fileType.toString());
					tCount.setV(typeCount.toString());
					typeCountList.add(tCount);
					
					MapEntity tSize = new MapEntity();
					tSize.setK(fileType.toString());
					tSize.setV(FileSizeUtil.formatFileSize(Long.valueOf(typeSize.toString())));
					typeSizeList.add(tSize);
				}
			}
			analyze.setTypeAnalyzCount(typeCountList);
			analyze.setTypeAnalyzSize(typeSizeList);
		}
	}
	
	//统计系统附件所属表单的数量，大小
	private void totalTableAnalyze(FileAnalyze analyze) {
		String totalSql = "SELECT tablename,COUNT(tablename) as tableCount,SUM(file_size) as tableSize  "
				+ "FROM `sys_updownfile` GROUP BY tablename ORDER BY tableCount DESC";
		List<Map<String,Object>> listMap = this.baseDao.queryListMap(totalSql);
		if(listMap!=null && listMap.size()>0) {
			List<MapEntity> tableCountList = new ArrayList<>(listMap.size());
			List<MapEntity> tableSizeList = new ArrayList<>(listMap.size());
			for(Map<String, Object> map:listMap) {
				Object tableName = map.get("tablename");
				Object tableCount = map.get("tableCount");
				Object tableSize = map.get("tableSize");
				if(tableName!=null&&tableCount!=null&&tableSize!=null) {
					MapEntity tCount = new MapEntity();
					tCount.setK(tableName.toString());
					tCount.setV(tableCount.toString());
					tableCountList.add(tCount);
					MapEntity tSize = new MapEntity();
					tSize.setK(tableName.toString());
					tSize.setV(FileSizeUtil.formatFileSize(Long.valueOf(tableSize.toString())));
					tableSizeList.add(tSize);
				}
			}
			analyze.setTableAnalyzCount(tableCountList);
			analyze.setTableAnalyzSize(tableSizeList);
		}
	}
	
	//统计系统附件垃圾文件总个数，总大小
	private void totalTempAnalyze(FileAnalyze analyze) {
		String totalSql = "SELECT COUNT(1) as totalCount ,SUM(file_size) as totalSize FROM `sys_updownfile` where yn ='0'";
		Map<String, Object> totalMap = this.baseDao.querySingleMap(totalSql);
		Object totalCount = totalMap.get("totalCount");
		Object totalSize = totalMap.get("totalSize");
		if(totalCount!=null) {
			analyze.setTempCount(totalCount.toString());
		}
		if(totalSize!=null) {
			analyze.setTempSize(FileSizeUtil.formatFileSize(Long.valueOf(totalSize.toString())));
		}
	}
	//统计系统附件总个数，总大小
	private void totalAnalyze(FileAnalyze analyze) {
		String totalSql = "SELECT COUNT(1) as totalCount ,SUM(file_size) as totalSize FROM `sys_updownfile`";
		Map<String, Object> totalMap = this.baseDao.querySingleMap(totalSql);
		Object totalCount = totalMap.get("totalCount");
		Object totalSize = totalMap.get("totalSize");
		if(totalCount!=null) {
			analyze.setTotalCount(totalCount.toString());
		}
		if(totalSize!=null) {
			analyze.setTotalSize(FileSizeUtil.formatFileSize(Long.valueOf(totalSize.toString())));
		}
	}
	@Override
	public void download(String ids,HttpServletRequest request,HttpServletResponse response) {
		if(StringUtils.isBlank(ids)) {
			return;
		}
		String[] split = ids.split(",");
		List<File> files = new ArrayList<>();
		try {
			SysUpDownFileExample example = new SysUpDownFileExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(Arrays.asList(split));
			List<SysUpDownFile> list = this.sysUpDownFileDao.selectByExample(example);
			for(SysUpDownFile file:list) {
				String uploadpath = file.getUploadpath();
				if(USE_FTP) {//FTP存储下载
					String oname = file.getOname();
					String filepath = PathUtil.getSysRootPath()+oname;
					File f = new File(PathUtil.formatFilePath(filepath));
					if(!f.exists()) {
						f.createNewFile();
					}
					FileOutputStream outputStream = new FileOutputStream(f);
					FtpUtil.download(uploadpath, outputStream);
					files.add(f);
					outputStream.flush();
					outputStream.close();
				}else {//本地存储
					File f = new File(uploadpath);
					if(f.exists()) {
						files.add(f);
					}
				}
			}
			if(files.size()==0) {
				return;
			}
			if(files.size()==1) {
				SysFileUtils.downLoadSingle(response, files.get(0));
			}else {
				SysFileUtils.downloadMultiple(response,files);
			}
		} catch (Exception e) {
			this.logger.error("文件下载失败：ids="+ids+","+e.getMessage());
		}finally {
			for(File file:files) {
				if(file.exists()) {
					file.delete();
				}
			}
		}
	}
	/**
	 * 文件预览接口 
	 */
	@Override
	public void preview(Integer id, HttpServletRequest request,HttpServletResponse response) {
		File pdf = null;
		File ori = null;
		try {
			SysUpDownFile file = this.sysUpDownFileDao.selectByPrimaryKey(id);
			//支持预览的文静类型
			String previewType=".DOC.DOCX.XLS.XLSX.TXT.PDF.doc.docx.xls.xlsx.txt.pdf";
			//当前文件类型
			String fileType = file.getFileType();
			if(file!=null&&previewType.contains(fileType)) {
				String uploadpath = file.getUploadpath();
				if(USE_FTP) {//FTP存储
					String rootPath = PathUtil.getSysRootPath();
					ori = new File(PathUtil.formatFilePath(rootPath+file.getOname()));
					OutputStream local = new FileOutputStream(ori); 
					FtpUtil.download(uploadpath, local);
					local.flush();
					local.close();
				}else {//本地存储
					ori = new File(uploadpath);
				}
				if(ori.exists()) {
					//如果当前文件类型为pdf格式，则直接返回
					if(".PDF.pdf".contains(fileType)) {
//						System.out.println("是PDF直接看");
						pdf = ori;
					}else {
//						System.out.println("不是PDF需要转换");
						pdf = FileConvertionUtil.toPDF(ori);
					}
					SysFileUtils.downLoadSingle(response, pdf);
				}
			}else {
				throw new ServiceException("当前文件不支持预览！");
			}
		} catch (Exception e) {
			throw printException(e);
		}finally {
			if(pdf!=null&&pdf.exists()) {
				pdf.delete();
			}
			//如果为FTP方式预览，则源文件也是临时的，转换完成后要删除
			if(USE_FTP&&ori!=null&&ori.exists()) {
				ori.delete();
			}
		}
	}
	@Override
	public List<SysUpDownFile> upfileByLib(String ids) {
		List<SysUpDownFile> list = new ArrayList<SysUpDownFile>();
		try {
			SysFileExample example = new SysFileExample();
			net.lantrack.framework.sysbase.entity.SysFileExample.Criteria cr = example.createCriteria();
			cr.andIdIn(Arrays.asList(ids.split(",")));
			List<SysFile> files = this.sysFileMapper.selectByExample(example);
			//文件上传路径
			String fileDir =Config.uploadPath+File.separator+DateUtil.getDate("yyyyMMdd")+File.separator;
			if(!USE_FTP) {//本地存储路径不存在新建路径
				File file = new File(fileDir);
				if(!file.exists()) {
					file.mkdirs();
				}
			}
			SysUpDownFile upfile = new SysUpDownFile();
			for(SysFile file:files) {
				SysUpDownFile upFileClone = (SysUpDownFile) upfile.clone();
				//以yyyyMMddHHmmss格式命名新名称
				String newName = DateUtil.getNumDateFormat()+"."+file.getFileType();
				upFileClone.setOname(file.getOldName());
				upFileClone.setNname(newName);
				upFileClone.setFileType(file.getFileType());
				upFileClone.setFileSize(file.getTarget());
				//文件保存地址
				String filepath =PathUtil.formatFilePath(fileDir + newName);
				upFileClone.setUploadpath(filepath);
				if(USE_FTP) {
					//将文件从资料库移动到附件库
					InputStream stream = FtpUtil.getStream(file.getFileUrl());
					if(!FtpUtil.upload(filepath, stream)) {
						throw new ServiceException("上传失败，文件不存在");
					}
					stream.close();
				}else {
					//源文件
					File srcFile = new File(PathUtil.formatFilePath(file.getFileUrl()));
					if(!srcFile.exists()) {
						throw new ServiceException("上传失败，文件不存在"); 
					}
					//目标文件
					File destFile = new File(filepath);
					SysFileUtils.copyFile(srcFile, destFile);
				}
				//入库
				this.sysUpDownFileDao.insertSelective(upFileClone);
				list.add(upFileClone);
			}
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	
	
}
