//package net.lantrack.project.base.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.tomcat.util.threads.ThreadPoolExecutor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import net.lantrack.framework.core.entity.ReturnEntity;
//import net.lantrack.framework.springplugin.controller.BaseController;
//import net.lantrack.framework.sysbase.dao.IdUserDao;
//import net.lantrack.framework.sysbase.entity.IdUser;
//import net.lantrack.project.base.entity.TestModel;
//
//@RestController
//@RequestMapping("/test")
//public class TestValidateController extends BaseController {
//
//	@Autowired
//	IdUserDao idUserDao;
//
//	
//	//   test/save
//	@RequestMapping("/save")
//	public ReturnEntity testSave(ReturnEntity info) {
//		long start = System.currentTimeMillis();
//		long total = 50000000L;
//		for (int i = 0; i < total; i++) {
//			IdUser u = new IdUser();
//			u.setuLike("test");
//			u.setuAge(10);
//			u.setuName("张三" + i);
//			u.setCreateBy("create" + i);
//			u.setCreateDate(new Date());
//			u.setDelFlag("0");
//			u.setRemarks("hello,test,mysql,battch");
//			u.setuBirthday(new Date());
//			u.setuCode("code" + i);
//			u.setuCompany("company" + i);
//			u.setuEmail("email" + i);
//			u.setUpdateBy("updateBy" + i);
//			u.setUpdateDate(new Date());
//			u.setuPhone("phone" + i);
//			u.setuSchool("school" + i);
//			u.setuSex("男");
//			idUserDao.insert(u);
//			if (i % 500 == 0) {
//				System.out.println(i);
//			}
//		}
//		long end = System.currentTimeMillis();
//		long use = end-start;
//		System.out.println(total*1000/use);
//		return info;
//	}
//	
//	
//	private static int corePoolSize = 10;
//    private static int maximumPoolSize = 50;
//    private static int queueCapacity = 2000;
//    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(queueCapacity);
//    private static ExecutorService threadPool = new ThreadPoolExecutor(
//            corePoolSize, maximumPoolSize, 0L,TimeUnit.MILLISECONDS,queue); 
//
//	//   test/saveBattch
//	@RequestMapping("/saveBattch")
//	public ReturnEntity testSaveBattch(ReturnEntity info) {
//		
//		long total = 2000000L;
//		List<IdUser> list = new ArrayList<>(1000);
//		long s = System.currentTimeMillis();
//		for (int i = 0; i < total; i++) {
//			IdUser u = new IdUser();
//			u.setuLike("test");
//			u.setuAge(10);
//			u.setuName("张三" + i);
//			u.setCreateBy("create" + i);
//			u.setCreateDate(new Date());
//			u.setDelFlag("0");
//			u.setRemarks("hello,test,mysql,battch");
//			u.setuBirthday(new Date());
//			u.setuCode("code" + i);
//			u.setuCompany("company" + i);
//			u.setuEmail("email" + i);
//			u.setUpdateBy("updateBy" + i);
//			u.setUpdateDate(new Date());
//			u.setuPhone("phone" + i);
//			u.setuSchool("school" + i);
//			u.setuSex("男");
//			list.add(u);
//		}
//		long e = System.currentTimeMillis();
//		System.out.println(e-s);
//		long start = System.currentTimeMillis();
//		int battch = list.size()/1000;
//		for(int i=0;i<battch;i++) {
//			idUserDao.insertList(list.subList(i*1000, (i+1)*1000));
////			 threadPool.execute(new SaveThread(list.subList(i*1000, (i+1)*1000)));
//		}
//		long end = System.currentTimeMillis();
//		long use = end-start;
//		System.out.println(total*1000/use);
//		return info;
//	}
//	
//	 class SaveThread implements Runnable {
//		private List<IdUser> list;
//		
//		public SaveThread(List<IdUser> list) {
//			super();
//			this.list = list;
//		}
//		@Override
//		public void run() {
//			
//			idUserDao.insertList(list);
//		}
//		
//	}
//
//	// test/validate
//	@RequestMapping("/validate")
//	public ReturnEntity test1(@RequestBody String json, ReturnEntity info) {
//		try {
//			TestModel model = toObject(json, TestModel.class);
//			if (this.beanValidator(info, model)) {
//				info.success("校验成功");
//			}
//		} catch (Exception e) {
//
//		}
//		return info;
//	}
//}
