/**
 *
 */
package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.entity.MapEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseDao;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.UpfileService;
import net.lantrack.framework.core.util.FileConvertionUtil;
import net.lantrack.framework.core.util.FileSizeUtil;
import net.lantrack.framework.core.util.PathUtil;
import net.lantrack.framework.core.util.SqlUtil;
import net.lantrack.framework.sysbase.dao.SysFileMapper;
import net.lantrack.framework.sysbase.entity.SysFile;
import net.lantrack.framework.sysbase.entity.SysFileExample;
import net.lantrack.framework.sysbase.entity.SysFileExample.Criteria;
import net.lantrack.framework.sysbase.entity.SysUpDownFile;
import net.lantrack.framework.sysbase.model.Tree;
import net.lantrack.framework.sysbase.model.updownfile.FileLibAnalyze;
import net.lantrack.framework.sysbase.service.SysFileService;
import net.lantrack.framework.sysbase.util.FtpUtil;
import net.lantrack.framework.sysbase.util.SysFileUtils;
import net.lantrack.framework.sysbase.util.TreeUtil;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 系统资源管理Service
 * 2018年3月10日
 * @author lin
 */
@Service
public class SysFileServiceImp extends BaseService implements SysFileService {
	@Autowired
	public SysFileMapper sysFileMapper;
	
	@Autowired
	BaseDao baseDao;
	

    /* (non-Javadoc)
     * @see net.lantrack.framework.core.service.CrudService#update(net.lantrack.framework.core.entity.BaseEntity)
     */
    @Override
    public SysFile update(SysFile entity) throws ServiceException {
        SysFileExample example = new SysFileExample();
        Criteria cr = example.createCriteria();
        cr.andOldNameEqualTo(entity.getOldName());
        cr.andIfDirectEqualTo(entity.getIfDirect());
        cr.andFileTypeEqualTo(entity.getFileType());
        cr.andPIdEqualTo(entity.getpId());
        List<SysFile> list = this.sysFileMapper.selectByExample(example);
        boolean up=false;
        if(list!=null&&list.size()>0){
            SysFile dict = list.get(0);
            if(dict.getId().equals(entity.getId())){
                up = true;
            }
        }else{
            up=true;
        }
        if(up){
            try {
                this.sysFileMapper.updateByPrimaryKeySelective(entity);
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }else{
            throw new ServiceException("当前文件夹中已存在相同文件！");
        }
        return entity;
    }








    /* (non-Javadoc)
     * @see net.lantrack.framework.sysbase.service.SysFileService#getFilesByPid(java.lang.Integer)
     */
    @Override
    public List<SysFile> getFilesByPid(Integer pid) throws ServerException {
        if (pid==null) {
        	pid=SYS_ROOT_PID;
        }
        try {
            return this.sysFileMapper.queryByPid(pid);
        } catch (Exception e) {
            throw printException(e);
        }
    }


	@Override
	public boolean updateFileName(String id, String nname) throws ServiceException {
		SysFile sysFile = this.sysFileMapper.selectByPrimaryKey(Integer.valueOf(id));
		sysFile.setOldName(nname);
		ServiceException repeat = validateDirRepeat(sysFile);
		if(repeat!=null) {
			throw repeat;
		}
		try {
			String updateSql="update sys_file set old_name = '"+nname+"' where id = "+id;
			this.baseDao.updateSql(updateSql);
		} catch (Exception e) {
			throw printException(e);
		}
		return true;
	}

	@Override
	public boolean upFile(String id, List<SysUpDownFile> files,HttpServletRequest request) throws ServiceException {
		//拼接错误消息
		StringBuffer errMsg = new StringBuffer();
		for(SysUpDownFile upfile:files) {
			SysFile nf = new SysFile();
			if(StringUtils.isBlank(id)) {
				//当id为空时，则放到根目录下
				nf.setDirectLevel(1);
				nf.setpId(SYS_ROOT_PID);
				nf.setpIds(SYS_ROOT_PID+"");
			}else {
				//将文件挂载到当前目录下
				SysFile dir = this.sysFileMapper.selectByPrimaryKey(Integer.valueOf(id));
				nf.setDirectLevel(dir.getDirectLevel()+1);
				nf.setpId(dir.getId());
				nf.setpIds(dir.getpIds()+","+dir.getId());
			}
			//赋值文件参数
			nf.setIfDirect(SysFileUtils.DIR_NO);
			nf.setFileType(upfile.getFileType());
			nf.setNewName(upfile.getNname());
			nf.setOldName(upfile.getOname());
			nf.setTarget(upfile.getFileSize());
			try {
				if(upfile.getOname()!=null&&upfile.getOname().length()>1000) {
					throw new ServiceException(upfile.getOname()+"文件名称过长");
				}
				this.sysFileMapper.insert(nf);
			} catch (Exception e) {
				//入库失败，则删除当前文件
				try {
					if(UpfileService.USE_FTP) {//FTP存储
						FtpUtil.delete(upfile.getUploadpath());
					}else {//本地存储
						SysFileUtils.deleteFile(upfile.getUploadpath());
					}
				} catch (Exception e1) {
					
				}
				//如果多个文件则，拼接错误文件，统一返回错误信息
				errMsg.append(upfile.getOname()).append(":").append(e.getMessage()).append(" ");
			}
		}
		if(errMsg.length()==0) {
			throw new ServiceException(errMsg.toString());
		}
		return true;
	}

	//验证统一目录下是否存在相同文件夹
	ServiceException validateDirRepeat(SysFile file) {
		SysFileExample example = new SysFileExample();
		Criteria cr = example.createCriteria();
		cr.andDirectLevelEqualTo(file.getDirectLevel());
		cr.andIfDirectEqualTo(file.getIfDirect());
		cr.andOldNameEqualTo(file.getOldName());
		List<SysFile> list = this.sysFileMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			SysFile sysFile = list.get(0);
			if(!sysFile.getId().equals(file.getId())) {
				return new ServiceException("当前文件夹已存在，请更换其他名称");
			}
		}
		return null;
	}
	
	@Override
	public void createDir(String id, String dirName) {
		if(SqlUtil.checkVal(id) || SqlUtil.checkVal(dirName)) {
			throw new ServiceException("请求参数非法");
		}
		SysFile nf = new SysFile();
		nf.setIfDirect(SysFileUtils.DIR_YES);
		nf.setOldName(dirName);
		if(StringUtils.isBlank(id)) {//当id为空时，则放到根目录下
			nf.setDirectLevel(1);
			nf.setpId(SYS_ROOT_PID);
			nf.setpIds(SYS_ROOT_PID+"");	
		}else {
			SysFile dir = this.sysFileMapper.selectByPrimaryKey(Integer.valueOf(id));
			nf.setDirectLevel(dir.getDirectLevel()+1);
			nf.setpId(dir.getId());
			nf.setpIds(dir.getpIds()+","+dir.getId());
		}
		//校验文件夹名称重复性
		ServiceException repeat = validateDirRepeat(nf);
		if(repeat!=null) {	
			throw repeat;
		}
		try {
			this.sysFileMapper.insert(nf);
		} catch (Exception e) {
			throw printException(e);
		}
	}

	@Override
	public boolean deleteFile(String ids) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("无可删除文件");
		}
		String[] split = ids.split(",");
		SysFileExample example = new SysFileExample();
		Criteria cr = example.createCriteria();
		cr.andIdIn(Arrays.asList(split));
		try {
			List<SysFile> list = this.sysFileMapper.selectByExample(example);
			for(SysFile file:list) {
				StringBuffer delSql = new StringBuffer("delete from sys_file");
				if(SysFileUtils.DIR_YES==file.getIfDirect()) {
					delSql.append(" where p_ids like '%,").append(file.getId()).append(",%'")
					.append(" or id =").append(file.getId()).append(" or p_id=").append(file.getId());
				}else {
					delSql.append(" where id = ").append(file.getId());
				}
				this.baseDao.deleteSql(delSql.toString());
				if(UpfileService.USE_FTP&&"0".equals(file.getIfDirect())) {//FTP存储删除
					FtpUtil.delete(file.getFileUrl());
				}else {//本地存储删除
					SysFileUtils.deleteFile(file.getFileUrl());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return true;
	}

	@Override
	public void downLoadFile(HttpServletResponse response,String ids) {
		if(StringUtils.isBlank(ids)) {
			throw new ServiceException("请选择要下载的文件");
		}
		//临时文件地址
		List<File> files = new ArrayList<>();
		try {
			String[] split = ids.split(",");
			SysFileExample example = new SysFileExample();
			Criteria cr = example.createCriteria();
			cr.andIdIn(Arrays.asList(split));
			List<SysFile> list = this.sysFileMapper.selectByExample(example);
			for(SysFile file:list) {
				//文件地址
				String uploadpath = file.getFileUrl();
				if(UpfileService.USE_FTP) {//FTP存储下载
					//在本地生成临时文件
					String oname = file.getOldName();
					String filepath = PathUtil.getSysRootPath()+oname;
					File f = new File(PathUtil.formatFilePath(filepath));
					if(!f.exists()) {
						f.createNewFile();
					}
					//从FTP上取得文件写入到临时文件中
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
			throw printException(e);
		}finally {
			for(File file:files) {
				if(file.exists()) {
					file.delete();
				}
			}
		}
	}

	@Override
	public List<SysFile> searchFiles(String id, String name) throws ServiceException {
		//校验是否包含非法参数
		if(SqlUtil.checkVal(id) || SqlUtil.checkVal(name)) {
			throw new ServiceException("请求参数非法");
		}
		StringBuffer selectSql = new StringBuffer("select * from sys_file where 1=1  ");
		//查询当前文件下及子文件夹中是否包含关键字
		if(!StringUtils.isBlank(id)) {
			selectSql.append("and ( p_ids like '%,").append(id).append(",%'")
					.append(" or p_id=").append(id).append(") ");
		}
		if(!StringUtils.isBlank(name)) {
			selectSql.append("  and old_name like '%").append(name).append("%' order by if_direct desc,update_date desc");
		}
		try {
			List<SysFile> files = this.sysFileMapper.searchBySql(selectSql.toString());
			return files;
		} catch (Exception e) {
			throw  printException(e);
		}
	}
	@Override
	public void preview(Integer id, HttpServletResponse response) {
		File pdf = null;
		File ori = null;
		boolean ispdf = false;
		try {
			SysFile file = this.sysFileMapper.selectByPrimaryKey(id);
			//支持预览的文静类型
			String previewType=".DOC.DOCX.XLS.XLSX.TXT.PDF.doc.docx.xls.xlsx.txt.pdf";
			//当前文件类型
			String fileType = file.getFileType();
			if(file!=null&&previewType.contains(fileType)) {
				String uploadpath = file.getFileUrl();
				if(UpfileService.USE_FTP) {//FTP存储
					String rootPath = PathUtil.getSysRootPath();
					ori = new File(PathUtil.formatFilePath(rootPath+file.getOldName()));
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
						pdf = ori;
						ispdf = true;
					}else {
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
			if(!ispdf&&pdf!=null&&pdf.exists()) {
				pdf.delete();
			}
			//如果为FTP方式预览，则源文件也是临时的，转换完成后要删除
			if(UpfileService.USE_FTP&&ori!=null&&ori.exists()) {
				ori.delete();
			}
		}
	}

	@Override
	public List<Tree> getFileTree() {
		//查看文件树结构
		StringBuffer sqlBuf = new StringBuffer("select id ,p_id as pid,old_name as label from sys_file where is_show = 0 ");
		try {
			List<Tree> list = this.baseDao.queryList(sqlBuf.toString(), Tree.class);
			return TreeUtil.converhandle(list);
		} catch (Exception e) {
			this.logger.error("获取资料库Tree结构失败："+e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
	}


	@Override
	public List<MapEntity> getUpFile(String fileName) {
		StringBuffer bufSql = new StringBuffer("select id as k,old_name as v from sys_file where is_show = 0 ");
		bufSql.append(" and create_by ='").append(UserUtil.getCurrentUser()).append("'");
		if(StringUtils.isNotBlank(fileName)) {
			bufSql.append(" and old_name like '%").append(fileName).append("%'");
		}
		bufSql.append(" order by create_date desc");
		try {
			return this.baseDao.queryList(bufSql.toString(), MapEntity.class);
		} catch (Exception e) {
			this.logger.error("获取可用文件列表失败："+e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public FileLibAnalyze analyzeFile() {
		FileLibAnalyze analyze = new FileLibAnalyze();
		try {
			//统计系统附件总个数，总大小
			totalAnalyze(analyze);
			//统计系统附件各类型数量，大小
			totalTypeAnalyze(analyze);
		} catch (Exception e) {
			this.logger.error("统计系统文件错误："+e.getMessage());
		}
		return analyze;
	}
	//统计系统附件各类型数量，大小
	private void totalTypeAnalyze(FileLibAnalyze analyze) {
		String totalSql = "SELECT file_type,COUNT(file_type) as typeCount,SUM(target) as typeSize  "
				+ "FROM `sys_file` WHERE if_direct = 0 GROUP BY file_type ORDER BY typeCount DESC";
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
	
	
	//统计系统附件总个数，总大小
	private void totalAnalyze(FileLibAnalyze analyze) {
		String totalSql = "SELECT COUNT(1) as totalCount ,SUM(target) as totalSize FROM `sys_file` WHERE if_direct = 0 ";
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
	
}