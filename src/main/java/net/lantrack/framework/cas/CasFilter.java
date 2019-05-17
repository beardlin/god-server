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
package net.lantrack.framework.cas;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义CAS Filter
 * 2018年1月22日
 * @author lin
 */
@SuppressWarnings("deprecation")
public class CasFilter extends org.apache.shiro.cas.CasFilter{
    private static Logger logger = LoggerFactory.getLogger(CasFilter.class);
    private static final String TICKET_PARAMETER = "ticket";
    private String failureUrl;
    
    public CasFilter() {
        
    }
    
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String ticket = httpRequest.getParameter(TICKET_PARAMETER);
        return new CasToken(ticket);
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return this.executeLogin(request, response);
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request, response, this.getSuccessUrl());
//        this.issueSuccessRedirect(request, response);
        return false;
    }
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request, ServletResponse response) {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication exception", ae);
        }
        
        Subject subject = this.getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            try {
                WebUtils.issueRedirect(request, response, this.failureUrl);
            } catch (IOException var7) {
                logger.error("Cannot redirect to failure url : {}", this.failureUrl, var7);
            }
        } else {
            try {
                this.issueSuccessRedirect(request, response);
            } catch (Exception var8) {
                logger.error("Cannot redirect to the default success url", var8);
            }
        }
        
        return false;
    }
    
    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    
    
}
