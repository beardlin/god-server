package net.lantrack;

import net.lantrack.framework.sysbase.QuartzJobExample;
import net.lantrack.framework.sysbase.util.QuartzManager;

import org.junit.Test;

/**
 * @Description: 测试类
 * 
 * @ClassName: QuartzTest.java
 */
public class QuartzTest {
    @Test
    public void quartz() {
        try {
            String job_name = "动态任务调度";
            String trigger_name = "动态任务触发器";  
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            Thread thread = new Thread(){
            	   public void run(){
            		   QuartzManager.addJob(job_name,trigger_name,QuartzJobExample.class ,"0/1 * * * * ?",null);
            	   }
            	};
            	thread.start();

            Thread.sleep(30000);
            System.out.println("【修改时间】开始(每2秒输出一次)...");
            QuartzManager.modifyJobTime(job_name, trigger_name,"10/2 * * * * ?");
            Thread.sleep(4000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(job_name, trigger_name);
            System.out.println("【移除定时】成功");

            System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
            QuartzManager.addJob(job_name, trigger_name, QuartzJobExample.class  ,"*/10 * * * * ?",null);
            Thread.sleep(30000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(job_name, trigger_name);
            System.out.println("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}