package net.lantrack.framework.sysbase.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.lantrack.framework.core.StatusCode;
import net.lantrack.framework.core.config.Config;
import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.util.PathUtil;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.entity.SysFileModel;
import net.lantrack.framework.sysbase.entity.SysTask;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.service.SysTaskService;
import net.lantrack.framework.sysbase.util.BackUpsUtil;


@Controller
@RequestMapping(value = "backups")
public class BackUpsController extends BaseController implements Job  {
	//	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	@Autowired
	private SysTaskService sysTaskService;

	//打开系统备份页面  backups/getBackupsList
	@RequestMapping(value ="/getBackupsList")
	public String getBackupsList(ReturnEntity ret,HttpServletRequest request){
		List<SysFileModel> list =new ArrayList<SysFileModel>();
		//备份文件的根路径
		String root =Config.backupsPath;
		File file = new File(PathUtil.formatFilePath(root));
		if(file.exists()){
			//获取root目录下的所有文件 
			File[] files = file.listFiles();
			for (File file2 : files) {
				SysFileModel fModel = new SysFileModel(file2.getName(), file2.getAbsolutePath());
				list.add(fModel);
			}
			//ret.setResult(list);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		//获取定时备份对象
		try {
			SysTask entity=this.sysTaskService.selectByJobName("backups");
			String time_cron=entity.getCronExps();
			String hours="";
			String minutes="";
			if(StringUtils.isNoneBlank(time_cron)) {
				String var[]=time_cron.split(" ");
				if(var.length>3) {
					hours=var[2];
					minutes=var[1];
				}
			}		
			map.put("hours", hours);
			map.put("minutes", minutes);
			map.put("days", entity.getFieldJson());
			map.put("remarks", entity.getRemarks());
			ret.setResult(map);
		} catch (Exception e) {
			ret.failed("查询异常");
		}
		return "sys/sysTaskUpdate";
	}


	/**
	 * 
	 * methodName: backups
	 * 系统文件+数据库备份
	 * date: 2018年1月24日 上午10:26:10 
	 * @param :  
	 * @author: liumy
	 * @param info
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="backups")
	@LogDesc(value="系统备份", type=LogType.ADD)
	public String backups(ReturnEntity info,HttpServletRequest request) {
		try {
			String rootPath = Config.backupsPath;
			String upfilePath=request.getSession().getServletContext().getRealPath("/");
			/**
			 * request = D:/apache-tomcat-8.0.33/webapps/
			 * 项目tomcat下的路径
			 */
			System.out.println("手动执行备份任务");
			// TODO  添加查询定时备份时的时间，待查查询
			BackUpsUtil.backups("", rootPath, upfilePath);
		} catch (Exception e) {
			info.failed("备份失败");
		}
		return "";
	}

	/**
	 * 
	 * methodName: restore
	 * 系统文件+数据库还原
	 * date: 2018年1月24日 下午3:46:08 
	 * @param :  
	 * @author: liumy
	 * @param info
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="restore")
	@LogDesc(value="系统恢复", type=LogType.UPDATE)
	public String restore(ReturnEntity info,HttpServletRequest request) {
		String fileName = request.getParameter("name") == null ? "" : request.getParameter("name").toString();
		if ("".equals(fileName)) {
			// 请求参数非法
			info.setStatus(StatusCode.PARAMETER_ERROR);
		} else {
			System.out.println("开始还原"+fileName);
			try {
				BackUpsUtil.restoreStart(fileName,request);
			} catch (Exception e) {
				info.failed("还原失败");

			}
		}
		return "";
	}
	
	@RequestMapping(value ="updateConfig")
	@LogDesc(value="更新定时备份配置", type=LogType.UPDATE)
	public String updateConfig(ReturnEntity info,HttpServletRequest request) {		
		String taskHour = request.getParameter("taskHour");
		String taskMinute = request.getParameter("taskMinute");
		String backupDay = request.getParameter("backupDay");
		String remarks = request.getParameter("remarks");
		if(StringUtils.isBlank(taskHour) || StringUtils.isBlank(taskMinute) || StringUtils.isBlank(backupDay)) {
			// 请求参数非法
			info.setStatus(StatusCode.PARAMETER_ERROR);
			return "";
		}
		//获取定时备份对象
		SysTask entity=this.sysTaskService.selectByJobName("backups");
		//更新
		entity.setCronExps("0 "+taskMinute+" "+taskHour+" * * ?");
		entity.setFieldJson(backupDay);
		entity.setRemarks(remarks);
		this.sysTaskService.update(entity);
		return "";
	}

	/**
	 * 定时任务 进行备份
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			//获得存放目录
			String tempString =BackUpsController.class.getResource("/").getFile().toString(); 
			if(tempString!=null && tempString.length()>1) {
				tempString=tempString.substring(1, tempString.indexOf("WEB-INF"));
			}
			String rootPath = PathUtil.formatFilePath(Config.backupsPath);
			//获得保存的天数
			JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			String delTime = dataMap.getString("data_json");
			BackUpsUtil.backups(delTime==""?"":delTime, rootPath,tempString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
