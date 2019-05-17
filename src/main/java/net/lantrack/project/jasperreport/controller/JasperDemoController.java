package net.lantrack.project.jasperreport.controller;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.project.jasperreport.common.DocType;
import net.lantrack.project.jasperreport.common.DocTypeUtils;
import net.lantrack.project.jasperreport.common.JasperUtils;
import net.lantrack.project.jasperreport.common.WebPathUtils;
import net.lantrack.project.jasperreport.service.JasperDemoService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jasper")
public class JasperDemoController {

    @Autowired
    JasperDemoService jasperDemoService;

    @RequestMapping(value = "/preview")
    public Object preview(HttpServletRequest request, HttpServletResponse response, ReturnEntity info) {
        String docTypeStr = request.getParameter("docType");
        Map<String, Object> parameters = jasperDemoService.getTestData();
        //引入jasper文件。由JRXML模板编译生成的二进制文件，用于代码填充数据
        String jasperPath = WebPathUtils.getClassPath() + "jasperreport/demo/DemoMap.jasper";
        JasperUtils jasperUtils = new JasperUtils(request, response);
        try {
            DocType docType = DocTypeUtils.getEnumDocType(docTypeStr);
            if (DocType.PDF.equals(docType)) {
                jasperUtils.exportPdf(jasperPath, parameters, "预览的文件名,可以为空", new JREmptyDataSource());
            } else {
                jasperUtils.exportHtml(jasperPath, parameters, "预览的文件名,可以为空", new JREmptyDataSource());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "demo/jasper/index";
    }

    @RequestMapping(value = "/download")
    public Object download(HttpServletRequest request, HttpServletResponse response, ReturnEntity info) {
        String docTypeStr = request.getParameter("docType");
        //引入jasper文件。由JRXML模板编译生成的二进制文件，用于代码填充数据
        String jasperPath = WebPathUtils.getClassPath() + "jasperreport/demo/DemoMap.jasper";
        Map testData = jasperDemoService.getTestData();
        JasperUtils jasperUtils = new JasperUtils(request, response);
        try {
            DocType docType = DocTypeUtils.getEnumDocType(docTypeStr);
            jasperUtils.createExportDocument(docType, jasperPath, testData, null, "爱干啥干啥");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "demo/jasper/index";
    }

}
