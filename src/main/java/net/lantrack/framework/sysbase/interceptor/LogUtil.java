
package net.lantrack.framework.sysbase.interceptor;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.entity.ReturnPage;
import net.lantrack.framework.core.entity.ReturnResult;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.core.util.SpringContextHolder;
import net.lantrack.framework.core.util.StrUtil;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysLog;
import net.lantrack.framework.sysbase.model.AnnoEtl;
import net.lantrack.framework.sysbase.service.SysLogService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 保存日志 2018年1月19日
 * 
 * @author lin
 */
public class LogUtil extends org.apache.commons.lang3.StringUtils {

    static SysLogService logservice = SpringContextHolder.getBean("sysLogServiceImp");
    private static int logCorePoolSize = 10;
    private static ThreadPoolExecutor logThreadPool;

    public static ThreadPoolExecutor getLogPoolInstance() {
        if (logThreadPool == null) {
            logThreadPool = new ThreadPoolExecutor(logCorePoolSize, logCorePoolSize * 5, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        }
        return logThreadPool;
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            InetAddress host;
            String hostAddress = "localhost";
            try {
                host = InetAddress.getLocalHost();
                hostAddress = host.getHostAddress();// ipv4当前ip
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }// 本地地址
            ip = hostAddress;
        }
        // System.out.println("来访的ip="+ip);
        return ip;
    }

    /**
     * 在外部调用保存日志
     * 
     * @param title
     * @param type
     * @param status 2018年1月25日
     * @author lin
     */
    public static void saveLog(String title, String type, String status,String ...err) {
        SysLog log = new SysLog();
        log.setType(type);
        if ("1".equals(status)) {
            log.setStatus("1");
            log.setMessage("成功");
        }
        if ("2".equals(status)) {
            log.setStatus("2");
            log.setMessage("失败");
        }
        log.setRemoteAddr(UserUtil.getSession().getHost());
        log.setTitle(title);
        log.setCreateBy(UserUtil.getCurrentUser());
        log.setUpdateBy(UserUtil.getCurrentUser());
        logservice.save(log);
    }

    /**
     * 保存日志
     * 
     * @throws IOException
     */
    public static void saveLog(HttpServletRequest request, long beginTim, long endTime,
            Object handler) throws IOException {
        ReturnEntity re = null;
        ReturnPage rp = null;
        Object obj = request.getAttribute("returnEntity");
        if (obj != null) {
            re = (ReturnEntity) obj;
        } else {
            rp = (ReturnPage) request.getAttribute("returnPage");
        }
        // 保存系统状态码默认为正常
        int status = StatusCode.SERVER_NORMAL.getCode();
        String msg = StatusCode.SERVER_NORMAL.getMsg();
        ReturnResult result = null;
        if (re != null) {
            status = re.getStatus();
            msg = re.getMessage();
            result = (ReturnResult) re.getResult();
        } else if (rp != null) {
            status = rp.getStatus();
            msg = rp.getMessage();
            result = (ReturnResult) rp.getResult();
        }
        Integer rst = 1;
        String rmg = "操作成功";
        // 操作状态码记录，
        if (result != null) {
            rst = result.getRst();
            rmg = result.getRmg();
        }
        SysLog log = new SysLog();
        // 操作结果
        log.setStatus(status == 47 ? rst.toString() : "2");
        log.setMessage(status == 47 ? rmg : msg);
        log.setRemoteAddr(getRemoteAddr(request));
        log.setUserAgent(request.getHeader("user-agent"));
        log.setRequestUri(request.getRequestURI());
        log.setParams(request.getParameterMap());
        log.setMethod(request.getMethod());
        log.setCreateBy(UserUtil.getCurrentUser());
        // log.setStarttime(Double.longBitsToDouble(beginTim));
        // log.setEndtiime(Double.longBitsToDouble(endTime));
        log.setProtime((endTime - beginTim) < 0 ? 0 : (double) (endTime - beginTim) / 1000);
        getLogPoolInstance().execute(new SaveLogThread(log, handler));
    }

    /**
     * 保存日志线程 减少开销让在线程里处理搜索数据等信息
     */
    public static class SaveLogThread extends Thread {
        private Object handler;
        private SysLog log;

        public SaveLogThread(SysLog log, Object handler) {
            super(SaveLogThread.class.getSimpleName());
            this.handler = handler;
            this.log = log;
        }

        // 获取日志标题
        @Override
        public void run() {
            try {
                if (handler instanceof HandlerMethod) {
                    Method m = ((HandlerMethod) handler).getMethod();
                    LogDesc desc = m.getAnnotation(LogDesc.class);
                    if (desc == null) {
                        return;
                    }
                    log.setTitle(StrUtil.join(desc.value()));
                    LogType logType = desc.type();
                    if (logType != null) {
                        log.setType(logType.getType());
                    }
                    Class model = desc.modelClass();
                    if (model != null && !model.equals(String.class)) {
                        Map<String, String> fieldDesc = AnnoEtl.getFieldDesc(model);
                        if (fieldDesc == null || fieldDesc.size() == 0) {
                            return;
                        }
                        String params = log.getParams();
                        if (params == null) return;
                        Map<String, String[]> paramMap = new HashMap<String, String[]>();
                        if (params.contains(BaseController.formdata)) {
                            // System.out.println(params);
                            params = params.replace(BaseController.formdata + "=", "");
                            Object bean = GsonUtil.getSingleBean(params, model);
                            if (bean != null) {
                                Method[] methods = bean.getClass().getDeclaredMethods();
                                for (Method method : methods) {
                                    if (StringUtils.startsWith(method.getName(), "get")) {
                                        String field = StringUtils
                                                .uncapitalize(StringUtils.substring(
                                                        method.getName(), 3));
                                        Object fieldValue = method.invoke(bean);
                                        if (fieldValue != null) {
//                                            System.out.println(field + "--"
//                                                    + fieldValue);
                                            if (fieldDesc.containsKey(field)) {
                                                field = fieldDesc.get(field);
                                            }
                                            String[] fieldVal = {
                                                fieldValue.toString()
                                            };
                                            paramMap.put(field, fieldVal);
                                        }

                                    }
                                }
                            }
                        } else {
                            String[] split = params.split("&");
                            for (String parm : split) {
                                String[] parmArray = parm.split("=",-1);
                                String fieldName = "";
                                String fieldValue = "";
                                if (parmArray.length > 1) {
                                	 fieldName = parmArray[0];
                                     fieldValue = parmArray[1];
                                }
                                if (fieldDesc.containsKey(fieldName)) {
                                    fieldName = fieldDesc.get(fieldName);
                                }
                                String[] arrVal = {
                                    fieldValue
                                };
                                paramMap.put(fieldName, arrVal);
                            }
                        }
                        log.setParams(paramMap);
                    }
                    if (StringUtils.isNotBlank(log.getTitle())) {
                        // 只当日志的操作标题不为空时才保存这条日志记录
                        String ip = log.getRemoteAddr();
                        if (StringUtils.isNotBlank(ip) && !"0:0:0:0:0:0:0:1".equals(ip)) {
                            logservice.save(log);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
