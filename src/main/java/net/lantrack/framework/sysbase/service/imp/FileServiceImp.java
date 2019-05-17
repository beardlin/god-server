///**
// *
// */
//package net.lantrack.framework.sysbase.service.imp;
//
//import net.lantrack.framework.core.dao.IDao;
//import net.lantrack.framework.core.entity.PageEntity;
//import net.lantrack.framework.core.lcexception.ServiceException;
//import net.lantrack.framework.sysbase.entity.SysUpDownFile;
//import net.lantrack.framework.sysbase.service.FileService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * @author
// *
// */
//@Service
//public class FileServiceImp implements FileService {
//	@Autowired
//	public IDao sysUpDownFileDao;
//
//	@Override
//	public Boolean update(SysUpDownFile entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean save(SysUpDownFile entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public SysUpDownFile queryById(String id) throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SysUpDownFile> queryByWhere(SysUpDownFile entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean deleteById(Serializable id, String update_by)
//			throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean deleteByIds(String ids, String update_by)
//			throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void getPage(SysUpDownFile entity, PageEntity pageentity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void getPageRe(SysUpDownFile entity, PageEntity pageentity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Boolean deleteByIdsRe(String ids, String update_by)
//			throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<SysUpDownFile> getAll(SysUpDownFile entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	@Override
//	public Boolean updataUpfile(List<SysUpDownFile> list, String tableid,
//			String tablename) throws ServiceException {
//		boolean tempYn=true;
//		try {
//			for (int i = 0; i < list.size(); i++) {
//				SysUpDownFile sysUpDownFile=(SysUpDownFile) sysUpDownFileDao.load(list.get(i).getId());
//				sysUpDownFile.setTableid(tableid);
//				sysUpDownFile.setTablename(tablename);
//				sysUpDownFile.setYn(true);
//				sysUpDownFileDao.update(sysUpDownFile);
//			}
//		} catch (Exception e) {
//			tempYn=false;
//			throw new ServiceException(e.getMessage());
//		}
//		return tempYn;
//	}
//
//	@Override
//	public boolean updateYnById(String id) throws ServiceException {
//		boolean flag=true;
//		try {
//			String sql="UPDATE sys_updownfile set yn=FALSE where id='"+id+"'";
//			sysUpDownFileDao.sqlUpdate(sql);
//		} catch (Exception e) {
//			flag= false;
//			throw new ServiceException(e.getMessage());
//		}
//		return flag;
//	}
//
//	@Override
//	public List<SysUpDownFile> queryListByTableId(String tableid) {
//		if (!StringUtils.isNotBlank(tableid)){
//			throw new ServiceException("tableid不能为空或null");
//		}
//		StringBuffer hql=new StringBuffer();
//		hql.append("select o from SysUpDownFile o where o.del_flag=0 and o.yn=1");
//		hql.append(" and o.tableid='").append(tableid).append("'");
//		return this.sysUpDownFileDao.query(hql.toString());
//
//	}
//}