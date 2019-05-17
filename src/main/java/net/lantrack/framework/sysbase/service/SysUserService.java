package net.lantrack.framework.sysbase.service;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.CrudService;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.model.menu.MenuTreeModel;
import net.lantrack.framework.sysbase.model.user.UserAuthModel;
import net.lantrack.framework.sysbase.model.user.UserModel;
import net.lantrack.framework.sysbase.model.user.UserOfficeIdsModel;


/**
 * 用户管理Service
 * 2018年1月25日
 * @author hww
 */
public interface SysUserService extends CrudService<SysUser> {
    
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    
    
    
    /**
     * 给用户配置角色
     * @date 2019年3月7日
     */
    public void configRoles(String userIds,String roleIds);
    
    /**
     * 解锁账户
     * @param ids
     * @return
     * @date 2019年3月6日
     */
	public String unlockUser(String ids);
    /**
     * 恢复账户
     * @param ids
     * @return
     * @date 2019年3月6日
     */
	public String enableUser(String ids);
    
    /**
     * 禁用账户
     * @param ids
     * @return
     * @date 2019年3月6日
     */
	public String disableUser(String ids);
    
	/**
	 * 从单点登录系统抽取全部的用户信息
	 * @throws Exception
	 */
	public String extractUserFromSso(String sn) throws Exception;
	
	/**
	 * 保存已指派好职务或角色的用户权限关系数据
	 * @param model UserAuthModel
	 * @return true boolean
	 * @throws ServiceException
	 */
	public boolean saveUserDutyRoleMenu(UserAuthModel model) throws ServiceException;
	
	public SysUser getByLoginName(String loginName);
	
	public SysUser getByPhone(String phone);
	
	public UserModel queryByPk(String id);
	
	public List<MenuTreeModel> getUserMenus(String userId);
	
	/**
	 * 根据组织机构id分页获取所有下属的用户
	 * @param entity
	 * @param pageentity
	 */
	public void getUserByOfficeIdListPage(UserOfficeIdsModel entity, PageEntity pageentity);
	
	 /**
	  * 
	  * metoodName: updataPassworByPhone
	  * @date:  2019年1月23日 上午10:43:23
	  * @author:lmy
	  * @param password
	  * @param phone
	  * @return SysUser
	  */
	SysUser updataPassworByPhone(String password,String phone);
	
	
}