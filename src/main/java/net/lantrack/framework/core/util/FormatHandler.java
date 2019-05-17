package net.lantrack.framework.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatHandler {


    //字符串   /输入任意字符串返回'+args+',
    //输入,str1,,str2,等格式输出str1,str2如果为空则输出null

    /**
     * //字符串   /输入任意字符串返回'+args+',
     *
     * @param s s,s
     * @return 's','s1'
     */
    public static String isFormatIds(String s) {
        StringBuffer sb = new StringBuffer();
        if (!"".equals(s)) {
            if (s.contains(",")) {
                String[] arr = s.split(",");
                StringBuffer sb1 = new StringBuffer();

                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].replaceAll("\\s*", "").trim();
                    if (!"".equals(arr[i].trim())) {
                        sb1.append(arr[i]).append(",");
                    }
                }
                String temp = "";
                if (!"".equals(sb1.toString())) {
                    temp = sb1.substring(0, sb1.length() - 1).toString();
                }
                if (temp.contains(",")) {
                    String[] str = temp.split(",");
                    for (int i = 0; i < str.length; i++) {
                        if (!"".equals(str[i])) {
                            sb.append("'").append(str[i]).append("',");
                        }
                    }
                    if (!"".equals(sb)) {
                        return sb.substring(0, sb.length() - 1);
                    }
                } else {
                    return sb.append("'").append(temp).append("'").toString();
                }
            } else {
                return sb.append("'").append(s).append("'").toString();
            }
        }
        return null;
    }

    /*
      * 输入s为时间格式的字符串，如:"2012-01-01,2012-01-01"、"2012-01-01,"或"2011-01-02"等
	  * 返回"'2012-01-01 00:00:00' < o.login_date  and  o.login_date < '2012-01-01 00:00:00'"
	  * 或'2012-01-01 00:00:00' < o.login_date、 o.login_date < '2012-01-01 00:00:00'等格式
	  * name即非空字符串，如果为空则返回null
	  */
    public static String isSegmetDateOrString(String s, String name) {
        if (s != null && !"".equals(s.trim()) && name != null && !"".equals(name.trim())) {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (s.contains(",")) {
                StringBuffer sb = new StringBuffer();
                String[] str = s.split(",");
                if (str.length == 2) {
                    String s1 = str[0].trim();
                    String s2 = str[1].trim();
                    if (!"".equals(s1) && !"".equals(s2)) {
                        try {
                            String str1 = format2.format(format1.parse(s1));
                            String str2 = format2.format(format1.parse(s2));
                            return sb.append("'").append(str1).append("' <= ").append(name)
                                    .append("  and  ").append(name).append(" <= '")
                                    .append(str2).append("'").toString();
                        } catch (ParseException e) {
                            return null;
                        }
                    } else if ("".equals(s1) && !"".equals(s2)) {
                        try {
                            String sd = format2.format(format1.parse(s2));
                            return sb.append(name).append(" <= '").append(sd)
                                    .append("'").toString();
                        } catch (ParseException e) {
                            return null;
                        }
                    }
                    return null;
                } else if (str.length == 1) {
                    try {
                        String date1 = format2.format(format1.parse(str[0].trim()));
                        return sb.append("'").append(date1).append("' <= ").append(name)
                                .toString();
                    } catch (ParseException e) {
                        return null;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }


    /**
     * 批量操作时前台给的参数形如["1","2","3"]时的处理
     * @param id
     * @return
     */
    public static String formatIds(String id) {
    	String ids = "";
    	if (id != null && id.startsWith("[") && id.endsWith("]")) {
    		ids = id.substring(1, id.length()-1);
    		if (ids.contains("\"")) {
    			ids = ids.replace("\"", "'");
    		}
    		if (ids.contains("&quot;")) {
    			ids = ids.replace("&quot;", "");
    		}
    	} else {
    		ids = id;
    	}
    	return ids;
    }
    
    public static void main(String[] arg) {
        StringBuffer ttemp = new StringBuffer();
        ttemp.append("dffffffffffffe");
        ttemp.delete(0, ttemp.length());
        System.out.println(ttemp.toString());
    }
}
