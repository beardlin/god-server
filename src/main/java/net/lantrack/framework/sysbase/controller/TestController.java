
package net.lantrack.framework.sysbase.controller;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.importexport.Student;
import net.lantrack.framework.core.importexport.excel.ExportExcelUtil;
import net.lantrack.framework.core.importexport.excel.ImportExcelUtil;
import net.lantrack.framework.core.importexport.util.ExcelLogUtil;
import net.lantrack.framework.springplugin.controller.BaseController;
import net.lantrack.framework.sysbase.dao.IdUserDao;
import net.lantrack.framework.sysbase.dao.SysAreaDao;
import net.lantrack.framework.sysbase.entity.IdUser;
import net.lantrack.framework.sysbase.entity.ImportLog;
import net.lantrack.framework.sysbase.interceptor.LogDesc;
import net.lantrack.framework.sysbase.interceptor.LogType;
import net.lantrack.framework.sysbase.model.TestModel;
import net.lantrack.framework.sysbase.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController extends BaseController {

    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysAreaDao sysAreaDao;
    @Autowired
    IdUserDao idUserDao;

    
    @RequestMapping(value = "importExcel")
    public ReturnEntity getExcel(ReturnEntity info)  {
        File excel = new File("D://student.xlsx");
        try {
            List<Student> list = ImportExcelUtil.importExcel(Student.class, excel, 0, 1);
            ImportLog saveLog = ExcelLogUtil.saveLog("student", excel.getName(),
                    list.size(), list.size(), 0, "导入成功");
            info.success("导入成功");
            if(saveLog!=null){
                info.setResult(saveLog);
            }
        } catch (Exception e) {
            info.failed(e.getMessage());
        }
        return info;
    }
    
    @RequestMapping(value = "getExcel")
    public String getExcel(HttpServletResponse response) {
        List<Student> dataList = new ArrayList<Student>();
        for (int i = 0; i < 200; i++) {
            Student stu = new Student("tom", i, "1992-09-03");
            dataList.add(stu);
        }
        ExportExcelUtil.downloadExcelFile("学生基本信息表", null, dataList, response);
        return "";
    }

    @RequestMapping(value = "returnMap")
    public ReturnEntity testReturnMap(ReturnEntity info) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", null);
        map.put("age", "17");
        map.put("address", null);
        info.setResult(map);
        return info;
    }

    @RequestMapping(value = "hello")
    public ReturnEntity hello(ReturnEntity info, HttpServletRequest req) throws Exception {
        if (!authPermission(info,"test:hello")) {
            return info;
        }
        info.setResult("hello请求成功！");
        return info;
    }

    @RequestMapping(value = "hellono")
    public ReturnEntity nohello(ReturnEntity info) throws Exception {
        if (!authPermission(info,"test:hellono")) {
            return info;
        }
        System.out.println("hellono请求成功！");
        return info;
    }

    @LogDesc(value = "添加测试model", type = LogType.ADD, modelClass = TestModel.class)
    @RequestMapping(value = "saveTest")
    public String testAddModel(TestModel model, ReturnEntity info) {
        info.success("添加成功");
        return "";
    }

    @RequestMapping(value = "saveBacth")
    public String testByIdAuto(ReturnEntity info) {
        // IdUser u = new IdUser();
        // u.setuLike("test");
        // u.setuAge(10);
        // u.setuName("张三");
        // idUserDao.insert(u);
        // System.out.println(u);
        // return "";
        long s = System.currentTimeMillis();
        List<IdUser> list = new ArrayList<IdUser>();
        for (int i = 1; i <= 100000; i++) {
            IdUser u = new IdUser();
            u.setuLike("test" + i);
            u.setuAge(10);
            u.setuName("张三");
            list.add(u);
            int batch = i % 500;
            if (batch == 0) {
                try {
                    int num = idUserDao.insertList(list);
                    System.out.println(num);
                    list.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (list.size() > 0) {
            System.out.println(list.size());
            try {
                int num = idUserDao.insertList(list);
                System.out.println(num);
                list.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long e = System.currentTimeMillis();
        info.success("导入成功，用时："+(e - s) / 1000+"秒");
        return "";
    }

    // @RequestMapping("/loginsuc")
    // public String loginSuc(ReturnEntity info) throws Exception {
    // info.setResult(UserUtil.getMenuSysList());
    // info.success(UserUtil.getCurrentUser());
    // return "index";
    // }
    // @RequestMapping("index")
    // public String index(ReturnEntity info) throws Exception {
    // Session session = UserUtil.getSubject().getSession();
    // info.success(UserUtil.getCurrentUser());
    // return "index";
    // }
    // @LogDesc(value="登录",type=LogType.LOGIN)
    // @RequestMapping(value = "login", method = RequestMethod.POST)
    // public String loginFailed(ReturnEntity info) throws Exception {
    // info.failed("登录失败");
    // return "login";
    // }
    // @RequestMapping(value = "/ssoApiLoginSuccess")
    // @ResponseBody
    // public Object ssoApiLoginSuccess(HttpServletRequest request,
    // HttpServletResponse response, ReturnEntity info) throws Exception {
    // Principal principal = UserUtil.getPrincipal();
    // System.out.println(principal.getLoginName());
    // info.setResult(UserUtil.getSession().getId());
    // info.success("登录成功");
    // return info;
    // }

}
