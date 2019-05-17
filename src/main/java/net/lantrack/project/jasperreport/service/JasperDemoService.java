package net.lantrack.project.jasperreport.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperDemoService {
    public Map<String, Object> getTestData() {
        Map<String, Object> parameters = new HashMap<String, Object>(16);
        parameters.put("title", "中国");
        parameters.put("date", new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
        parameters.put("name", "小明");
        parameters.put("age", "18");
        parameters.put("dept", "开发部");
        parameters.put("gender", "男");
        return parameters;
    }
}
