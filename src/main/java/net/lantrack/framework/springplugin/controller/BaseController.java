
package net.lantrack.framework.springplugin.controller;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.util.BeanValidators;
import net.lantrack.framework.core.util.DateUtil;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.shiro.Principal;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BaseController 用于接收form表单提交数据和鉴权 2018年1月15日
 * @author lin
 */
public abstract class BaseController {
	
	public static final String formdata = "datajson";
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;
    
    
    /**
	 * 接收前台传入的from表单数据
	 * 
	 * @param req
	 * @param clazz
	 * @return
	 * @throws Exception
	 *             2018年1月23日
	 * @author lin
	 */
	protected synchronized <T> T toObject(String json, Class<T> clazz) throws Exception {
		T bean = GsonUtil.getSingleBean(json, clazz);
		return escapeFieldValue(bean);
	}
	/**
	 * json串转List
	 * @Description: 
	 * @author lin
	 * @date 2018年6月18日
	 */
	protected synchronized <T> List<T> toList(String json, Class<T[]> clazz) throws Exception {
		List<T> jsonToList = GsonUtil.jsonToList(json, clazz);
		return escapeListFieldValue(jsonToList);
	}
    
    /**
	 * 将对象中为字符串的字段编码，防止XSS注入
	 * @param t
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	protected <T> T escapeFieldValue(T t) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = t.getClass().getDeclaredFields();
		for(Field field:fields) {
			field.setAccessible(true);
			String typeName = field.getType().getName();
//			System.out.println(typeName);
			switch (typeName) {
			case "java.lang.String":
				Object fieldValue = field.get(t);
				if(fieldValue!=null&&!"".equals(fieldValue.toString())) {
					String escapeValue = StringEscapeUtils.escapeHtml4(fieldValue.toString().trim());
					field.set(t, escapeValue);
				}
				break;
			case "java.util.List":
				Object listValue = field.get(t);
				if(listValue!=null) {
					List<?> list = (List<?>) listValue;
					//调用list方法
					list = escapeListFieldValue(list);
					field.set(t, list);
				}
				break;
			}
		}
		return t;
	}
	
	/**
	 * 将对象中为字符串的字段编码，防止XSS注入
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected <T> List<T> escapeListFieldValue(List<T> list) throws IllegalArgumentException, IllegalAccessException{
		if(list!=null&&list.size()>0) {
			for(T t:list) {
				escapeFieldValue(t);
			}
		}
		return list;
	}
    
	/**
	 * 鉴权
	 * 
	 * @param permisson
	 * @return 有权限return ture,否则返回false 2018年1月23日
	 * @author lin
	 */
	protected boolean authPermission(ReturnEntity info,String ...permisson) {
		Principal principal = UserUtil.getPrincipal();
		if(principal == null) {
			return false;
		}
		if (UserUtil.currentUserIsAdmin.equals(principal.getIfAdmin())) {
			return true;
		} else {
			Subject subject = SecurityUtils.getSubject();
			for(String pms:permisson) {
				if(subject.isPermitted(pms)) {
					return true;
				}
			}
			info.setStatus(StatusCode.NOPERMISS_ERROR);
			return false;
		}
	}
	/**
	 * 鉴权
	 * 
	 * @param permisson
	 * @return 有权限return ture,否则返回false 2018年1月23日
	 * @author lin
	 */
	protected boolean authPermission(String ...permisson) {
		Principal principal = UserUtil.getPrincipal();
		if(principal == null) {
			return false;
		}
		if (UserUtil.currentUserIsAdmin.equals(principal.getIfAdmin())) {
			return true;
		} else {
			Subject subject = SecurityUtils.getSubject();
			for(String pms:permisson) {
				if(subject.isPermitted(pms)) {
					return true;
				}
			}
			return false;
		}
	}

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；验证失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(ReturnEntity info, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            info.setStatus(StatusCode.PARAMETER_ERROR);
            addMessage(info, list.toArray(new String[] {}));
            return false;
        }
        return true;
    }

    /**
     * 添加message消息
     *
     * @param
     */
    protected void addMessage(ReturnEntity info, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        info.setMessage(sb.toString());
//        info.failed(sb.toString());// 将校检错误信息返回
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }

    /**
     * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // this.setStatus(model, 0);
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
    }
    
    

    protected void setValidatErrorMsg(BindingResult bind, ReturnEntity info) {
        info.setStatus(StatusCode.PARAMETER_ERROR);
        List<String> errorMessages = new ArrayList<String>();
        List<FieldError> fer = bind.getFieldErrors();
        for (int i = 0; i < fer.size(); i++) {
            FieldError er = fer.get(i);

            if ("typeMismatch".equals(er.getCode()))// 暂时使用
            {
                // 类型转换应用，暂自己处理与分析
                errorMessages.add(er.getField() + ":" + this.getInputOrMsg(er.getDefaultMessage()));
            } else {
                errorMessages.add(er.getField() + ":" + er.getDefaultMessage());
            }
        }
        addMessage(info, errorMessages.toArray(new String[] {}));
    }

    private String getInputOrMsg(String message) {
        // String test =
        // "Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'sort'; nested exception is java.lang.NumberFormatException: For input string: ";
        // sort：必须为数字类型//required type 'java.lang.Integer' for property
        String em = "类型错误";
        try {
            int st = message.indexOf("required type '");
            int end = message.indexOf("' for property");
            String requiredtype = message.substring(st + 15, end);
            // System.out.println(requiredtype);
            switch (requiredtype) {
                case "java.lang.Integer":
                    em = "类型必须是数字";
                    break;
                case "java.lang.Boolean":
                    em = "类型必须为布尔";
                    break;
                case "java.lang.Double":
                    em = "类型必须是数字";
                    break;
                case "java.lang.Long":
                    em = "类型必须是数字";
                    break;
                case "java.lang.Float":
                    em = "类型必须是数字";
                    break;
                case "java.lang.Byte":
                    em = "类型必须是数字";
                default:

                    break;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block

        }
        return em;
    }
    
    /**
     * 通过response下载文件
     */
    public static void downloadFile(File file,HttpServletRequest request,HttpServletResponse response,String... file_name) {
    	response.reset();
 		OutputStream out = null;
 		try {
 			out = new BufferedOutputStream(response.getOutputStream());
 			// 设置response的Header
 			response.addHeader("Content-Length", "" + file.length());
 			response.setContentType("application/octet-stream");
 			//为了解决中文名称乱码问题
 			//根据用户使用浏览器类型
 			String userAgent = request.getHeader("User-Agent");
 			UserAgent agent = UserAgent.parseUserAgentString(userAgent);
 			Browser browser = agent.getBrowser();
 			String browserName = browser.toString().toUpperCase();
 			//获取文件名称
 			String fileName = file.getName();
 			if(file_name!=null&&file_name.length>0) {
 				fileName = file_name[0];
 			}
 			if (browserName.contains("IE")||browserName.contains("EDGE")) { 
 				fileName = URLEncoder.encode(fileName, "UTF-8"); 
 			} else { 
 				fileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
 			}
 			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
 			out.write(FileUtils.readFileToByteArray(file));
 			out.flush();
 		} catch (IOException e) {
 			IOUtils.closeQuietly(out);
 		}finally {
 			IOUtils.closeQuietly(out);
 		}
    }

}
