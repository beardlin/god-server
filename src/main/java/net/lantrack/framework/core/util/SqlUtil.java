package net.lantrack.framework.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.lantrack.framework.core.config.Config;


/**
 * sql注入拦截字段
 * @date 2018年12月3日
 */
public class SqlUtil {
	/**
	 * 拦截字符串
	 */
	private static List<String> intercepts = new ArrayList<>();;
	static {
		String[] split = Config.sqlInject.split(",");
		for(String str:split) {
			intercepts.add(str.toLowerCase());
		}
		System.out.println(intercepts);
	}
	
	/**
	 * 查看字符是否包含sql注入拦截字段
	 * @param field
	 * @return
	 * @date 2018年12月3日
	 */
	public static boolean checkVal(String field) {
		if(field==null||"".equals(field)) {
			return false;
		}
		field = field.toLowerCase();
		for(String str:intercepts) {
			if(field.contains(str)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 查看查询条件的字段是否包含sql注入字段
	 * @param clazz
	 * @return
	 * @date 2018年12月3日
	 */
	public static boolean checkCond(Object cond) {
		if(cond==null) {
			return false;
		}
		Field[] fields = cond.getClass().getDeclaredFields();
		for(Field field:fields) {
			try {
				field.setAccessible(true);
				Object val = field.get(cond);
				if(val!=null) {
					if(checkVal(val.toString())) {
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
