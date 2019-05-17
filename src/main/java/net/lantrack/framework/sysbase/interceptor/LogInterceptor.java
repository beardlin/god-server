package net.lantrack.framework.sysbase.interceptor;


import net.lantrack.framework.core.log.LogFactoty;
import net.lantrack.framework.core.util.DateUtil;
import org.apache.logging.log4j.core.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * @author 大法师
 */
public class LogInterceptor  implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
    /**
     * 日志对象
     */
    protected static Logger logger = LogFactoty.getRunLogger();
    

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //1、开始时间
        long beginTime = System.currentTimeMillis();
        //线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(beginTime);        
        if (logger.isInfoEnabled()) {
            logger.info("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //modelAndView.getModelMap();
        if (modelAndView != null) {
            logger.debug("ViewName: " + modelAndView.getViewName());

        }
    }

    //记录操作日志 ，
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
      //得到线程绑定的局部变量（开始时间）
        long beginTime = startTimeThreadLocal.get();
      //2、结束时间
        long endTime = System.currentTimeMillis();    
        // 打印JVM信息。
        if (logger.isInfoEnabled()) {
            logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtil.formatDateTime(endTime - beginTime),
                    request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
        LogUtil.saveLog(request,startTimeThreadLocal.get(),  System.currentTimeMillis(), handler);
    }


}
