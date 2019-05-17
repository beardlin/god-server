
package net.lantrack.framework.core.importexport.util;

import net.lantrack.framework.core.util.SpringContextHolder;
import net.lantrack.framework.sysbase.entity.ImportLog;
import net.lantrack.framework.sysbase.service.ImportLogService;
import net.lantrack.framework.sysbase.util.UserUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 保存日志 2018年1月19日
 * 
 * @author lin
 */
public class ExcelLogUtil {

    static ImportLogService logservice = SpringContextHolder.getBean("importLogServiceImp");
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
     * 保存日志
     * @throws IOException
     */
    public static ImportLog saveLog(String modelType,String fileName,Integer totalCount,
            Integer sucCount,Integer errCount,String remarks) {
        ImportLog log = new ImportLog();
        log.setCreateBy(UserUtil.getCurrentUser());
        log.setFileName(fileName);
        log.setErrCount(errCount);
        log.setModelType(modelType);
        log.setRemarks(remarks);
        log.setSucCount(sucCount);
        log.setTotalCount(totalCount);
        getLogPoolInstance().execute(new SaveLogThread(log));
        return log;
    }

    /**
     * 保存日志线程 减少开销让在线程里处理搜索数据等信息
     */
    public static class SaveLogThread extends Thread {
        
        private ImportLog log;
        public SaveLogThread(ImportLog log) {
            super(SaveLogThread.class.getSimpleName());
            this.log = log;
        }
        // 保存日志
        @Override
        public void run() {
            if(StringUtils.isNotBlank(log.getModelType())){
                logservice.save(log);
            }
        }
    }
}
