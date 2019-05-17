package net.lantrack.framework.sysbase.interceptor;


import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.sysbase.util.UserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 *
 * @author 大法师
 *将信息存入log4j
 */
public class GlobalExceptionHandler implements
		HandlerExceptionResolver {
	protected Logger logger = (Logger) LogManager.getLogger("mylog");
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		System.err.println("系统捕获异常开始：---------------------");
		ex.printStackTrace();
		System.err.println("系统捕获异常结束：---------------------");
		//将错误写入log4j
		this.logger.error(UserUtil.getCurrentUser(), ex);
		ModelAndView mv = new ModelAndView();
		ReturnEntity info = new ReturnEntity();
        info.setStatus(StatusCode.SERVER_ERROR);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
			response.getWriter().write(GsonUtil.toJson(info));
		} catch (IOException e) {
			
		} 
		return mv;
	}

	/**
	 * 根据异常类动态分类 并将异常的状态码广播出去
	 */
	private StatusCode classify(HttpServletRequest request, Exception ex, ReturnEntity info) {

		Throwable targetException = ex;
		StatusCode codeid = StatusCode.SERVER_ERROR;

		if (targetException instanceof AuthenticationException) {// 认证相关所有失败
//			info.addRmg("认证失败");
			codeid=StatusCode.AUTHENTICATION_ERROR;
		} else if (targetException instanceof AuthorizationException) {// 授权相关的异常
//			info.addRmg("没有授权或是权限不够");
			codeid=StatusCode.NOPERMISS_ERROR;
		}else if(targetException instanceof BindException||
				targetException instanceof ConstraintViolationException||
				targetException instanceof ValidationException||
				targetException instanceof BindException)//参数绑定异常
		{
//			info.addRmg("参数绑定异常");
			codeid=StatusCode.PARAMETER_ERROR;
		}else if(targetException instanceof MaxUploadSizeExceededException ){
//			info.addRmg("上传文件超过系统要求最大值"+ Config.maxUploadSize/1024*1024+"M");
			codeid=StatusCode.PARAMETER_ERROR;
		}
		else if(targetException instanceof ServiceException){
//			info.addRmg(ex.getMessage());
			codeid=StatusCode.PARAMETER_ERROR;
		}
		else if (targetException instanceof RuntimeException) {// 运行异常
//			info.addRmg("系统运行异常");
			codeid=StatusCode.SERVER_ERROR;
		} else if (targetException instanceof Error) {// 出错
//			info.addRmg("系统出错了");
			codeid=StatusCode.SERVER_ERROR;
		} else if (targetException instanceof Exception) {
//			info.addRmg("未知异常");
			codeid=StatusCode.SERVER_ERROR;
		} else {
			 codeid = StatusCode.SERVER_ERROR;
		}

		return codeid;
	}





//	private void seturl(ModelAndView mv,int stautcode){
//		if (stautcode == 1) {
//			mv.setViewName("/error/1");
//		} else if (stautcode == 2) {
//			mv.setViewName("/error/2");
//		} else if (stautcode == 3) {
//			mv.setViewName("/login");
//		} else if (stautcode == 4) {
//			mv.setViewName("/error/4");
//		} else if (stautcode == 5) {
//			mv.setViewName("/error/5");
//		} else if (stautcode == 101) {
//			mv.setViewName("/error/101");
//		} else if (stautcode == 102) {
//			mv.setViewName("/error/102");
//		} else if (200 < stautcode && stautcode < 300) {
//			if (stautcode == 232) {
//				mv.setViewName("/error/232");
//			} else if (stautcode == 234) {
//				mv.setViewName("/error/234");
//			} else {
//				mv.setViewName("/error/200");
//			}
//		} else if (300 < stautcode) {
//			mv.setViewName("/error/error");
//		} else {
//			mv.setViewName("/error/error");
//		}
//	}
}
