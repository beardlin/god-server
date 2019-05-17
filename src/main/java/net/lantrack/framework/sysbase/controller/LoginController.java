package net.lantrack.framework.sysbase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.shiro.FormAuthenticationFilter;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.LoginInfo;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.interceptor.LogUtil;
import net.lantrack.framework.sysbase.service.LoginInfoService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController extends BaseController {

	@Autowired
	private LoginInfoService loginInfoService;
	
	

	/**
	 * 获取用户权限以及个人信息
	 * @param info
	 * @return
	 */
    @RequestMapping("/userinfo")
    public ReturnEntity userinfo(ReturnEntity info){
    	Map<String, Object> result = new HashMap<>();
    	List<String> list = UserUtil.getPermissionList();
    	if(list.size()==0) {
    		list.add("role1");
    		list.add("role2");
    		list.add("role3");
    		list.add("role4");
    		list.add("role5");
    	}
    	result.put("roles",list);
    	result.put("username", UserUtil.getCurrentUser());
    	result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    	info.setResult(result);
    	return info;
    }
	

	/**
	 * 登录成功
	 * @param info
	 * @return
	 * @throws Exception
	 */
//	@LogDesc(value="登录",type=LogType.LOGIN)
//    @RequestMapping("/loginsuc")
//    public ReturnEntity loginSuc(ReturnEntity info,HttpServletRequest request) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("currentUser", UserUtil.getCurrentUser());
//		Serializable sn = UserUtil.getSession().getId();
//		map.put("sn",sn);
//		//通过用户名密码登录成功后将重置登录失败次数
//		LoginInfo loginInfo = this.loginInfoService.getInfo(UserUtil.getUser().getLoginName());
//		if(loginInfo!=null) {
//			loginInfo.errCount(0);
//			loginInfo.setLastSucTime(new Date());
//			loginInfo.setLastSucIp(request.getRemoteAddr());
//			loginInfo.setLastDevice(request.getParameter("deviceType"));
//			loginInfo.setStand2(sn.toString());
//			this.loginInfoService.updateInfo(loginInfo);
//		}
//    	info.setResult(map);
//    	info.success("登录成功");
//    	return info;
//    }
    /**
     * 直接访问index页面先判断session是否存在，若存在则获取菜单列表跳转到index页面，否则踢到登录界面
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping("index")
    public ReturnEntity index(ReturnEntity info) throws Exception {
        Session session = UserUtil.getSubject().getSession();
        if (session == null) {
        	info.failed("登录超时，请重新登录！");
        	return info;
        }
        Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sysMenuList", UserUtil.getMenuSysList());
		map.put("currentUser", UserUtil.getCurrentUser());
    	info.setResult(map);
    	return info;
    }
    /**
     * 获取菜单json格式  getMenu.json
     * @param info
     * @return
     * @throws Exception
     * 2018年2月27日
     * @author lin
     */
//    @RequestMapping("getMenu")
//    public ReturnEntity getMenu(ReturnEntity info) throws Exception {
//        Session session = UserUtil.getSubject().getSession();
//        if (session == null) {
//            info.failed("登录超时，请重新登录！");
//            return info;
//        }
//        Map<String, Object> map = new HashMap<String, Object>();
//        List<SysMenuModel> menuSysList = UserUtil.getMenuSysList();
//        for(SysMenuModel menu:menuSysList){
//            List<SysMenuModel> children = menu.getChildren();
//            int twoCount=0;
//            for(SysMenuModel me:children){
//                me.setPicon(menu.getIconimg());
//                me.setPname(menu.getMenuName());
//                List<SysMenuModel> childList = me.getChildren();
//                //存在3级菜单
//                if(childList!=null&&childList.size()>0){
//                    twoCount++;
//                }
//            }
//            if(twoCount>0){
//                for(SysMenuModel me:children){
//                    setChild(me);
//                }
//            }
//        }
//        map.put("sysMenuList", menuSysList);
//        map.put("currentUser", UserUtil.getCurrentUser());
//        info.setResult(map);
//        return info;
//    }
    @SuppressWarnings("unused")
	private void setChild(SysMenuModel me){
        List<SysMenuModel> children = me.getChildren();
        if(children==null){
            children = new ArrayList<SysMenuModel>(1);
        }
        if(children.size()==0){
            SysMenuModel chil = new SysMenuModel();
            chil.setHref(me.getHref());
            chil.setId(0);
            chil.setMenuName(me.getMenuName());
            chil.setParentId(me.getId());
            chil.setPicon(me.getIconimg());
            chil.setPname(me.getMenuName());
            chil.setTarget(me.getTarget());
            children.add(chil);
            me.setChildren(children);
        }
        
    }
    /**
     * 登录失败重新踢到登录界面
     * @param info
     * @return
     * @throws Exception
     */
    @LogDesc(value="登录",type=LogType.LOGIN)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ReturnEntity loginFailed(HttpServletRequest request,HttpServletResponse response,ReturnEntity info) throws Exception {
    	Object exceptName = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    	Object errMsg = request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
    	Object userName = request.getAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
    	if(errMsg==null) {
    		errMsg = "登录失败";
    	}
    	//登录失败后更新登录失败状态，看看是哪种原因导致失败
    	if(exceptName!=null&&AuthenticationException.class.getName().equals(exceptName)) {
    		SysUser user = UserUtil.getByLoginName(userName.toString());
    		if(user==null) {
    			user = UserUtil.getByPhone(userName.toString());
    		}
    		if(user!=null) {
    			try {
    				//查看是因为用户本被禁用或锁定失败
        			this.loginInfoService.validUserStatus(user.getLoginName());
        			//更新登录失败信息
        			LoginInfo loginInfo = this.loginInfoService.getInfo(user.getLoginName());
        			int remainCount = this.loginInfoService.loginErrorInfo(loginInfo);
        			if(remainCount>0) {
        				errMsg += "剩余失败次数"+remainCount;
        			}else {
        				errMsg = "失败次数过多,当前账户被锁定";
        			}
    			} catch (LockedAccountException e) {
    				// 账户被锁定
    				errMsg = e.getMessage();
    			} catch (DisabledAccountException e) {
    				// 账户被禁用
    				errMsg = e.getMessage();
    			}
    			
    		}
    	}
    	info.failed(errMsg.toString());
    	LogUtil.saveLog("登录", LogType.LOGIN.getType(), "2",errMsg.toString());
    	return info;
    }

}
