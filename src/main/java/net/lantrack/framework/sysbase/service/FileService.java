///**
// *
// */
//package net.lantrack.framework.sysbase.service;
//
//import net.lantrack.framework.core.lcexception.ServiceException;
//import net.lantrack.framework.core.service.CrudService;
//import net.lantrack.framework.sysbase.entity.SysUpDownFile;
//
//import java.util.List;
//
//public interface FileService extends CrudService<SysUpDownFile> {
//
//	/**
//	 *
//	 * @param list 上传文件的集合
//	 * @param tableid 被保存表的id
//	 * @param tablename被保存表的名字
//	 * @return
//	 * @throws Exception
//	 */
//	public  Boolean updataUpfile(List<SysUpDownFile> list, String tableid, String tablename)
//		    throws Exception;
//
//	public boolean updateYnById(String id) throws ServiceException;
//
//	public List<SysUpDownFile> queryListByTableId(String tableid);
//
//
//}