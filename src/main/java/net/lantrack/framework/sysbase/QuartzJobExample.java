package net.lantrack.framework.sysbase;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobExample implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");

		/*JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		String data_json = dataMap.getString("data_json");
		//根据data_json再解析具体值
		System.out.println("data_json="+data_json);*/

	}

}
