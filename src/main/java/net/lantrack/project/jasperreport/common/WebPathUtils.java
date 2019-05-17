package net.lantrack.project.jasperreport.common;

import org.apache.commons.lang3.StringUtils;

public class WebPathUtils{

    public static String getClassPath(){
        String systemName = System.getProperty("os.name");
        //判断当前环境，如果是Windows 要截取路径的第一个 '/'
        if(!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") !=-1){
            return WebPathUtils.class.getResource("/").getFile().toString().substring(1);
        }else{
            return WebPathUtils.class.getResource("/").getFile().toString();
        }
    }


    public static void main(String[] args) {
        System.out.println(getClassPath());
    }
}
