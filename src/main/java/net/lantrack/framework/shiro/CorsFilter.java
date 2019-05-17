package net.lantrack.framework.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class CorsFilter extends PathMatchingFilter{

	 @Override
	    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
		 	//解决跨域请求
	    	HttpServletRequest req = (HttpServletRequest) request;
	    	HttpServletResponse resp = (HttpServletResponse) response;
	    	// 设置：Access-Control-Allow-Origin头，处理Session问题
	    	String header = req.getHeader("Origin");
	    	String referer = req.getHeader("Referer");
	    	System.out.println("header Origin:"+header);
	    	System.out.println("header Referer:"+referer);
	    	resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
	    	resp.setHeader("Access-Control-Allow-Credentials", "true");
	    	resp.setHeader("P3P", "CP=CAO PSA OUR");
			if (req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(req.getMethod())) {
				resp.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE");
				resp.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept,Access-Token");
				resp.addHeader("Access-Control-Max-Age", "120");
			}
	        return true;
	    }
}
