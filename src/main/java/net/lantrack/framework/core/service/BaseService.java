/**
 *
 */
package net.lantrack.framework.core.service;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class BaseService {

	protected Logger logger = LogManager.getLogger(BaseService.class);
    /**
     * 日志对象
     */

    

    /**
     * 数据范围过滤（符合业务表字段不同的时候使用，采用exists方法）
     */
    public static void dataScopeFilter(BaseEntity<?> entity) {
    	entity.setUser(UserUtil.getUser());
//		List<DataRule> dataRuleList = UserUtils.getDataRuleList();
//		// 如果是超级管理员，则不过滤数据
//		if (dataRuleList.size() == 0) {
//			return;
//		}
//		// 数据范围
		StringBuilder sqlString = new StringBuilder();
//		
//		for(DataRule dataRule : dataRuleList){
//			if(entity.getClass().getSimpleName().equals(dataRule.getClassName())){
//				sqlString.append(dataRule.getDataScopeSql());
//			}
//		}
		entity.setDataFilter(sqlString.toString());
    }

    
    /**
     * 记录错误日志,自定义是否显示到控制台
     * @param e
     * @param if_print
     * @return
     * @date 2019年1月18日
     */
    protected ServiceException printException(Exception e,boolean if_print){
    	if(logger.isErrorEnabled()) {
			this.logger.error(e);
		}
    	if(if_print) {
    		e.printStackTrace();
    	}
		return new ServiceException(e.getMessage());
	}
    /**
     * 记录错误日志，默认打印到控制台
     * @param e
     * @return
     * @date 2019年1月18日
     */
    protected ServiceException printException(Exception e){
		return printException(e,true);
	}
    
    /**
     * 给数据添加机构分组
     * @param obj
     * @date 2018年8月6日
     */
    public  void setDataOffice(Object obj) {
//    	SysOffice office = UserUtil.getMainOffice();
//    	try {
//    		Field officeId = obj.getClass().getDeclaredField("officeId");
//        	Field officeName = obj.getClass().getDeclaredField("officeName");
//        	if(officeId!=null) {
//        		officeId.setAccessible(true);
//        		officeId.set(obj, office.getId());
//        	}
//        	if(officeName!=null) {
//        		officeName.setAccessible(true);
//        		officeName.set(obj, office.getOfficeName());
//        	}
//		} catch (Exception e) {
//			if(logger.isErrorEnabled()) {
//				this.logger.error(e);
//			}
//			e.printStackTrace();
//		}
    	
    }

}
