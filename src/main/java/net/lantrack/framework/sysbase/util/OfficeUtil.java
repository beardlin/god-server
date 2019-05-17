//package net.lantrack.framework.sysbase.util;
//
//import net.lantrack.framework.sysbase.entity.SysUser;
//
//public class OfficeUtil {
//
//
//
//	public static StringBuffer addSubOfficeSql(SysUser u, String o, StringBuffer hql){
//		if(u==null){
//			return hql;
//		}
//		String sub_office_str=u.getSub_office_ids();
//		if(sub_office_str!=null && !sub_office_str.equals("")){
//			String[] sub_office_ids=sub_office_str.split(",");
//			for(String sub_office_id:sub_office_ids){
//				hql.append(" or ").append(o).append(".parent_ids like '%").append(sub_office_id).append("%'")
//				.append(" or ").append(o).append(".id='").append(sub_office_id).append("'");
//			}
//		}
//		return hql;
//	}
//
//}
