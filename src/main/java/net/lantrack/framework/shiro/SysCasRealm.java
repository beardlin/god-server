/*
 * Copyright 2017 Lantrack Corporation All Rights Reserved.
 *
 * The source code contained or described herein and all documents related to
 * the source code ("Material") are owned by Lantrack Corporation or its suppliers
 * or licensors. Title to the Material remains with Lantrack Corporation or its
 * suppliers and licensors. The Material contains trade secrets and proprietary
 * and confidential information of Lantrack or its suppliers and licensors. The
 * Material is protected by worldwide copyright and trade secret laws and
 * treaty provisions.
 * No part of the Material may be used, copied, reproduced, modified, published
 * , uploaded, posted, transmitted, distributed, or disclosed in any way
 * without Lantrack's prior express written permission.
 *
 * No license under any patent, copyright, trade secret or other intellectual
 * property right is granted to or conferred upon you by disclosure or delivery
 * of the Materials, either expressly, by implication, inducement, estoppel or
 * otherwise. Any license under such intellectual property rights must be
 * express and approved by Intel in writing.
 *
 */
package net.lantrack.framework.shiro;

import net.lantrack.framework.core.config.Config;
import net.lantrack.framework.core.util.StrUtil;
import net.lantrack.framework.security.session.SessionDAO;
import net.lantrack.framework.sysbase.entity.SysUser;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CAS认证
 * 2018年1月21日
 * @author lin
 */
@SuppressWarnings("deprecation")
public class SysCasRealm extends CasRealm{
    
    @Autowired
    private SessionDAO sessionDao;
    
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }
        String ticket = (String)casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        TicketValidator ticketValidator = ensureTicketValidator();
        try {
            // contact CAS server to validate service ticket
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            // get principal, user id and attributes
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String loginName = casPrincipal.getName();
            Map<String, Object> attributes = casPrincipal.getAttributes();
            String id = attributes.get("id")!=null ? attributes.get("id").toString() : "";
            String userName = attributes.get("userName")!=null ? attributes.get("userName").toString() : "";
//            String password = attributes.get("password")!=null ? attributes.get("password").toString() : "";
            String ifAdmin = attributes.get("ifAdmin")!=null ? attributes.get("ifAdmin").toString() : "";
            casToken.setUserId(loginName);
            String rememberMeAttributeName = getRememberMeAttributeName();
            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }
            Principal pc = new Principal(id, loginName, userName, "pc", ifAdmin);
            return new SimpleAuthenticationInfo(pc, ticket, getName());
        } catch (TicketValidationException e) { 
            e.printStackTrace();
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        // 获取当前已登录的用户
        if (!Config.TRUE.equals(Config.usermultiAccountLogin)){
            Collection<Session> sessions = sessionDao.getActiveSessions(true, principal, UserUtil.getSession());
            if (sessions.size() > 0){
                // 如果是登录进来的，则踢出已在线用户
                if (UserUtil.getSubject().isAuthenticated()){
                    for (Session session : sessions){
                        sessionDao.delete(session);
                    }
                }
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。 互斥登录只留一个，即不让存在有记住我！
                else{                   
                    UserUtil.getSubject().logout();
                    throw new AuthenticationException("账号已在其它地方登录，请重新登录。");
                }
            }
        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("user");
        info.addStringPermission("test:hello");
        // 添加用户权限 
        SysUser user = UserUtil.getByLoginName(principal.getLoginName());
        if (user != null) {
            List<String> permissionList = UserUtil.getPermissionList();
            if (permissionList != null && permissionList.size() > 0) {
	            for (String permission : permissionList){
	                if (StrUtil.isNotBlank(permission)){
	                    // 添加基于Permission的权限信息                  
	                    info.addStringPermission(permission);             
	                }
	            }
            }
            return info;
        } else {
            return null;
        }
    }
    

}
