package net.lantrack.framework.shiro;


import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.util.GsonUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

/**
 * 自定义过滤器，用来处理自定义风格的请求
 * @author lin
 */
public class CustomFilter extends AccessControlFilter {

    
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
//            Session session = subject.getSession();
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    /**
     * 拒绝访问后自行处理
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	
    	
    	 ReturnEntity info = new ReturnEntity();
         info.setStatus(StatusCode.AUTHENTICATION_ERROR);
         response.setContentType("application/json");
         response.setCharacterEncoding("utf-8");
         response.getWriter().write(GsonUtil.toJson(info));    	
         
        //APP请求返回登录超时状态码，PC跳到登录超时页面后去登陆页
//        String sn = request.getParameter("sn");
//        if(sn!=null){//来自APP的请求
//            ReturnEntity info = new ReturnEntity();
//            info.setStatus(StatusCode.AUTHENTICATION_ERROR);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().write(GsonUtil.toJson(info));
//           
//        }else{
//            WebUtils.issueRedirect(request, response, getLoginUrl());//直接去登录页
//        }
       
        return false;
    }
}