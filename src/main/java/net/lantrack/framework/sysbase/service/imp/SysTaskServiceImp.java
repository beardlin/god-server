package net.lantrack.framework.sysbase.service.imp;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.sysbase.dao.SysTaskDao;
import net.lantrack.framework.sysbase.entity.SysTask;
import net.lantrack.framework.sysbase.entity.SysTaskExample;
import net.lantrack.framework.sysbase.entity.SysTaskExample.Criteria;
import net.lantrack.framework.sysbase.service.SysTaskService;
import net.lantrack.framework.sysbase.util.QuartzManager;
/**
 * 任务管理
 * @author ypn
 * 2018年1月30日
 */
@Service
public class SysTaskServiceImp extends BaseService implements SysTaskService {
	@Autowired
	protected SysTaskDao sysTaskDao;
	
	@Override
	public SysTask update(SysTask entity) throws ServiceException {
		SysTaskExample taskExample=new SysTaskExample();
		Criteria cr = taskExample.createCriteria();
		cr.andTaskNameEqualTo(entity.getTaskName());
		List<SysTask> list=this.sysTaskDao.selectByExample(taskExample);
		boolean up=false;
        if(list!=null&&list.size()>0){
            SysTask task = list.get(0);
            if(task.getId().equals(entity.getId())){
                up = true;
            }
        }else{
            up=true;
        }
        if(up){
            try {
            	int flag=this.sysTaskDao.updateByPrimaryKeySelective(entity);
            	 if(flag==1){
            		 QuartzManager.modifyJobTime(entity.getJobName(), entity.getTriggerName(),entity.getCronExps());
            	 }
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }else{
            throw new ServiceException("当前任务中已存在相同记录");
        }
        return entity;
	}

	@Override
	public SysTask save(SysTask entity) throws ServiceException {		
		try {
			SysTaskExample taskExample = new SysTaskExample();
	        SysTaskExample.Criteria cr = taskExample.createCriteria();
	        cr.andTaskNameEqualTo(entity.getTaskName());
	        List<SysTask> list = this.sysTaskDao.selectByExample(taskExample);
	        boolean up=false;
	        if(list==null||list.size()==0){
	            up = true;
	        }
	        if(up){
	            int flag=this.sysTaskDao.insertSelective(entity);
	            //System.out.println("result flag="+flag);
	            if(flag==1){	            	
	            	//开启新线程执行定时任务
	            	Thread thread = new Thread(){
	             	   public void run(){
	             		  try {
							QuartzManager.addJob(entity.getJobName(),entity.getTriggerName(), (Class<? extends Job>)Class.forName(entity.getClassName()),entity.getCronExps(),null);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	             	   }
	             	};
	             	thread.start();
	            }
	        }else{
	            throw new ServiceException("当前任务已存在");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return entity;
	}

	@Override
	public SysTask queryById(Object id) {
		if (id==null) {
            this.logger.error("id can not be null or empty!");
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        SysTask task=null;
        try {
			task=this.sysTaskDao.selectByPrimaryKey(pasId);
		} catch (Exception e) {
			e.printStackTrace();
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
		}
		return task;
	}

	@Override
	public void deleteById(Object id, String update_by, boolean ifLogic)
			throws ServiceException {
		if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        int flag=0;
        if (!ifLogic) {
            // 物理删除
            flag=this.sysTaskDao.deleteByPrimaryKey(pasId);
        } else {
            // 逻辑删除结束
            SysTask task = new SysTask();
            task.setCreateDate(null);
            task.setUpdateBy(update_by);
            task.setDelFlag(BaseEntity.YES);
            task.setId(pasId);
            flag=this.sysTaskDao.updateByPrimaryKeySelective(task);
        }
        if(flag==1){	
        	SysTask entity=this.queryById(id);
    		//删除对应的定时任务
    		if(entity!=null){
    			QuartzManager.removeJob(entity.getJobName(), entity.getTriggerName());
    		}    
        	
        }
		
	}

	@Override
	public void deleteByIds(String ids, String update_by, boolean ifLogic)
			throws ServiceException {
		if (StringUtils.isBlank(ids)) {
            this.logger.error("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        SysTaskExample taskExample = new SysTaskExample();
        SysTaskExample.Criteria cr = taskExample.createCriteria();
        cr.andIdIn(Arrays.asList(split));
        int flag=0;
        if (!ifLogic) {
            // 物理删除
            flag=this.sysTaskDao.deleteByExample(taskExample);
        } else {
            // 此处用逻辑删除
            SysTask task = new SysTask();
            task.setCreateDate(null);
            task.setUpdateBy(update_by);
            task.setDelFlag(BaseEntity.YES);
            flag=this.sysTaskDao.updateByExampleSelective(task, taskExample);
        }
        if(flag==1){	
        	for(int i=0;i<split.length;i++){
        		SysTask entity=this.queryById(split[i]);
        		//删除对应的定时任务
        		if(entity!=null){
        			QuartzManager.removeJob(entity.getJobName(), entity.getTriggerName());
        		}            	
        	}
        	
        }
	}

	@Override
	public void getPage(SysTask entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPageRe(SysTask entity, PageEntity pageentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIdsRe(String ids, String update_by)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SysTask> getAll(SysTask entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查找已开启的任务，并加入定时任务中
	 * @return
	 */
	@Override
	public List<SysTask> startTask(String status) {
		SysTaskExample taskExample=new SysTaskExample();
		Criteria cr = taskExample.createCriteria();
		cr.andTaskStatusEqualTo(status);
		List<SysTask> list=this.sysTaskDao.selectByExample(taskExample);
		if(list!=null && list.size()>0){
			for(SysTask entity:list){
				//开启新线程执行定时任务
            	Thread thread = new Thread(){
             	   public void run(){
             		  try {
						QuartzManager.addJob(entity.getJobName(),entity.getTriggerName(), (Class<? extends Job>)Class.forName(entity.getClassName()),entity.getCronExps(),entity.getFieldJson());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
             	   }
             	};
             	thread.start();
			}
		}
		return list;
	}

	@Override
	public SysTask selectByJobName(String jobName) {
		SysTask task=null;
		try {
			if(StringUtils.isBlank(jobName)) {
				task=new SysTask();
			}else {
				task=this.sysTaskDao.selectByJobName(jobName);
			}
			if(task==null) {
				task=new SysTask();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return task;
	}

	
}
