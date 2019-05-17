//package net.lantrack.framework.sysbase.util;
//
//import com.google.common.collect.Lists;
//import net.lantrack.framework.core.config.Config;
//import net.lantrack.framework.core.dao.IDao;
//import net.lantrack.framework.core.util.FormatHandler;
//import net.lantrack.framework.core.util.SpringContextHolder;
//import net.lantrack.framework.sysbase.entity.*;
//import org.apache.commons.collections4.map.LinkedMap;
//
//import javax.persistence.Query;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class SysUtil {
//	private static IDao dao = SpringContextHolder.getBean("sysDictDao");
//
//	/**
//	 * 获取当前系统名称
//	 *
//	 * @return 取不到返回 new User()
//	 */
//	public static String getAppName() {
//
//		return Config.appName;
//	}
//
//	/**
//	 * 得一菜单串
//	 *
//	 */
//	public static String getMenuPosition(String id) {
//		try {
//			Query query = dao
//					.getEm()
//					.createQuery(
//							"select o from SysMenu o where  o.id!='root' and o.del_flag=0 and id=?1");
//			query.setHint("org.hibernate.cacheable", true);
//			query.setParameter(1, id);
//			SysMenu entity = (SysMenu) query.getSingleResult();
//			String in = FormatHandler.isFormatIds(entity.getParentIds());// 格式校验
//			// 拼in条件
//
//			Query queryt = dao
//					.getEm()
//					.createQuery(
//							"select o.name,o.href from SysMenu o where  o.id!='root' and  o.del_flag=0  and o.id in ("
//									+ in + ")  order by o.sort "); // 把父节点全搜索出来加进去
//			// //处尾“，”
//			queryt.setHint("org.hibernate.cacheable", true);
//
//			List list = queryt.getResultList();
//			// 第一个与最后一个不展示 连接
//			StringBuffer temp = new StringBuffer("");
//			for (int t = 0; t < list.size(); t++) {
//				Object[] te = (Object[]) list.get(t);
//				if (t == 0) {// 第一行不展示连接
//					temp.append(te[0].toString());
//				}/*
//				 * else if(t==list.size()-1){//最后一行不展示连接
//				 * temp.append("<span class='c-gray en'>&gt;</span>"
//				 * ).append(te[0].toString()); }
//				 */else {// 其他都加<a></a>
//					 temp.append("<span class='c-gray en'>&gt;</span><a href='")
//					 .append(te[1].toString())
//					 .append("'> <font color='#5A7DC6'>")
//					 .append(te[0].toString()).append("</font></a>");
//				 }
//			}
//			temp.append("<span class='c-gray en'>&gt;</span>").append(
//					entity.getName()); // 把自己加在尾部
//			return temp.toString();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			return "";
//		}
//		// return "用户管理-用户列表";
//	}
//	/**
//	 * 根据部门ID得到名称
//	 * @param id
//	 * @return
//	 */
//	public static String getOfficeName(String id){
//		Query query = dao.getEm().createQuery("select o from SysOffice o where  o.id!='root' and o.del_flag=0 and id=?1");
//		query.setHint("org.hibernate.cacheable", true);
//		query.setParameter(1, id);
//		try {
//			SysOffice entity =(SysOffice) query.getSingleResult()	;
//			return entity.getName();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return "";
//		}
//	}
//	/**
//	 * 根据用户ID得到名称
//	 * @param id
//	 * @return
//	 */
//	public static String getUserName(String id){
//		Query query = dao.getEm().createQuery("select o from SysUser o where  o.id!='root' and o.del_flag=0 and id=?1");
//		query.setHint("org.hibernate.cacheable", true);
//		query.setParameter(1, id);
//		try {
//			SysOffice entity =(SysOffice) query.getSingleResult()	;
//			return entity.getName();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return "";
//		}
//	}
//	/**
//	 * 根据地区ID得到名称
//	 * @param id
//	 * @return
//	 */
//	public static String getAreaName(String id){
//		Query query = dao.getEm().createQuery("select o from SysArea o where  o.id!='root' and o.del_flag=0 and id=?1");
//		query.setHint("org.hibernate.cacheable", true);
//		query.setParameter(1, id);
//		try {
//			SysOffice entity =(SysOffice) query.getSingleResult()	;
//			return entity.getName();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return "";
//		}
//	}
//	/**
//	 * 根据地区ID得到全区全称
//	 * @param id
//	 * @return
//	 */
//	public static String getAreaFullName(String id){
//		Query query = dao.getEm().createQuery("select o from SysArea o where  o.id!='root' and o.del_flag=0 and id=?1");
//		query.setHint("org.hibernate.cacheable", true);
//		query.setParameter(1, id);
//		try {
//			SysArea entity =(SysArea) query.getSingleResult()	;
//			return entity.getName();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return "";
//		}
//	}
//	public static  Map getDictMap(String type){
//		return getDictMap(type, 0);
//	}
//	/**
//	 * 给 下拉选择与单选 控件，默认使得。
//	 *
//	 * @param type
//	 * @param tag
//	 *            等1时为下拉选择。默认给一个选择
//	 * @return
//	 */
//	public static Map getDictMap(String type, int tag) {
//		try {
//			Query query = dao.getEm().createQuery("from SysDict where type=?1 order by sort" );
//			query.setHint("org.hibernate.cacheable", true);
//			query.setParameter(1, type);
//			List<SysDict> list = query.getResultList();
//
//			Map map = new LinkedMap();
//			if (tag == 1)// tag=1 为下拉选择，需求给一个空值
//			{
//				map.put("", "请选择");
//			}
//			for (int id = 0; id < list.size(); id++) {
//				SysDict entity = list.get(id);
//				map.put(entity.getValue(), entity.getLabel());
//			}
//			return map;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return new HashMap();
//			// e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 给得到所有字典分类，默认使得的标签。
//	 *
//	 * @param type
//	 * @param tag
//	 *            等1时为下拉选择。默认给一个选择
//	 * @return
//	 */
//	public static Map getDictTypeMap() {
//		try {
//			Query query = dao.getEm().createQuery(
//					"from SysDict where del_flag=0 group by type");
//			query.setHint("org.hibernate.cacheable", true);
//			List<SysDict> list = query.getResultList();
//			Map map = new HashMap();
//			map.put("", "请选择");
//
//			for (int id = 0; id < list.size(); id++) {
//				SysDict entity = list.get(id);
//				map.put(entity.getType(), entity.getDescription());
//			}
//			return map;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			return new HashMap();
//		}
//	}
//
//	/**
//	 * 根据字段的ID 找到对应的 名称
//	 *
//	 * @param ids
//	 * @return
//	 */
//	public static String getDicName(String ids) {
//		ids = FormatHandler.isFormatIds(ids);
//		StringBuffer str = new StringBuffer();
//		List list;
//		try {
//			Query query = dao.getEm().createQuery(
//					"from SysDict where value in(" + ids + ")");
//			query.setHint("org.hibernate.cacheable", true);
//			// query.setParameter(1, ids);
//			list = query.getResultList();
//			for (int id = 0; id < list.size(); id++) {
//				str.append(((SysDict) list.get(id)).getLabel()).append(" , ");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//		}
//		String s = str.toString();
//
//		return s.length() > 0 ? s.substring(0, s.length() - 2) : "";
//	}
//	/*	public static String lccheckbox(String ids,String Q,String W,String E,int R) {
//		return
//	}*/
//	/**
//	 * 自定义checkBox
//	 * @param type
//	 * @param cssClass
//	 * @param checked
//	 * @param fieldName
//	 * @param isNull
//	 * @return
//	 */
//	public static String lcChenkBox(String type,String cssClass,String checked,String  fieldName,Integer  isNull ){
//		return CheckBox(getDictMap(type), checked==null?"":checked, fieldName, isNull,cssClass);
//
//		//return "11";
//	}
//
//	public  static String LcCbox(Map map,String checked,String  fieldName,int  isNull,String cssClass ){
//		return CheckBox(map, checked==null?"":checked, fieldName, isNull,cssClass);
//
//		//return "11";
//	}
//
//	//吕石川 自定义单选radio 去掉默认选中，加上datatype限制   改完
//	public static String lcRadio(String type,String fieldName,String checked,String cssClass,Integer isNull){
//
//		return Radio(getDictMap(type), fieldName, checked==null?"":checked,isNull, cssClass);
//
//	}
//
//	//吕石川 自定义下拉选<select>
//
//	public static String lcSelect(String type,String fieldName,String selected,String cssClass,Integer isNull){
//
//		return Select(getDictMap(type), fieldName, selected==null?"":selected, isNull, cssClass);
//
//	}
//
//	/*
//	 * 雒晓林
//	 * Map如<1,热火>
//	 * checked验证是否选中的字符串 如 1 或1,2,3
//	 * name input的name属性，若输入null或"",则默认name='';
//	 * yn即datetype,验证是否为空，只有出入1时验证不为空
//	 * skin即classs属性值，一般用来控制风格,不需要时可以不用输入,或者输入""或null
//	 */
//	public static String CheckBox(Map<String,Object> map, String checked,String name,int yn,String ...skin){
//		if(!map.isEmpty()){
//			String[] str = null;
//			if(checked!=null&&!"".equals(checked.trim())&&checked.contains(",")){
//				str = checked.split(",");
//			}
//			Set keySet = map.keySet();
//			StringBuffer sb = new StringBuffer();
//			int n = 1;
//			for(Object obj: keySet){
//				if(name!=null&&!"".equals(name.trim())){
//					sb.append("<input type='checkbox'   name='").append(name).append("' ")
//					.append("  id='").append(name+n).append("' value='")
//					.append(obj).append("'");
//				}else{
//					sb.append("<input type='checkbox'   name='' ")
//					.append("  id='").append(""+n).append("' value='")
//					.append(obj).append("'");
//				}
//				if(skin!=null&&!"".equals(skin[0].trim())&&skin.length==1){
//					sb.append(" class='").append(skin[0]).append("' ");
//				}
//
//				if(str!=null){
//					for(String s : str){
//						if(s.equals(obj.toString())){
//							sb.append("  checked  ");
//						}
//					}
//				}else if(checked!=null&&!"".equals(checked.trim())&&checked.equals(obj.toString())){
//					sb.append("  checked  ");
//				}
//				if(yn==1&&n==1){
//					if(name!=null&&!"".equals(name.trim())){
//						sb.append("  datatype='").append(name).append("'  nullmsg ='不能为空'");
//
//					}else{
//						sb.append("  datatype='' ").append(" nullmsg ='不能为空'");
//					}
//				}
//				sb.append("  />").append(map.get(obj)).append("&nbsp;&nbsp;");
//				n++;
//			}
//			return sb.toString();
//		}
//		return "";
//	}
//
//	/**
//	 * 	吕石川
//	 * Map 如<10101,无烟煤>
//	 * name input标签的name属性值，若输入null或"",则默认name='';
//	 * checked验证的是否单选中这个按钮，如10102，选中了褐煤
//	 * 如果不指定，默认选中第一个.
//	 * skin即input标签的class属性值，一般用来控制风格,不需要时可以不用输入,或者输入""或null
//	 * yn即用来校验的
//	 */
//
//
//	public static String Radio(Map<String,Object> map,String name,String checked,Integer yn,String ...skin){
//		if(!map.isEmpty()){     //根据type去查的数据库， key对应sys_dict的value, value对应sys_dict的label
//			StringBuffer sb = new StringBuffer();
//			//得到所有的key的集合
//			Set<String> keySet = map.keySet();
//			int n=1;
//			for (String sysValue : keySet) {    //id生成策略是什么？ name允许为空串吗？
//				if(name!=null&&!"".equals(name.trim())){
//					sb.append("<input type='radio'   name='").append(name).append("' ")
//					.append("  id='").append(name+n).append("' value='")
//					.append(sysValue).append("'");
//
//				}else{
//					sb.append("<input type='radio'   name='' ")
//					.append("  id='").append(""+n).append("' value='")
//					.append(sysValue).append("'");
//
//				}
//				//class属性生成策略是什么？
//				if(skin!=null&&!"".equals(skin[0].trim())&&skin.length==1){
//					sb.append(" class='").append(skin[0]).append("' ");
//				}
//
//				//选中的拼串   不为空，也不为空串，且和遍历的sysValue其中一个相同，选中。
//				if(checked!=null&&!"".equals(checked.trim())&&checked.equals(sysValue.toString())){
//					sb.append("  checked  ");
//
//				}
//
//				if(yn==1&&n==1){
//					if(name!=null&&!"".equals(name.trim())){
//						sb.append("  datatype='").append(name).append("'  nullmsg ='不能为空'");
//
//					}else{
//						sb.append("  datatype='' ").append(" nullmsg ='不能为空'");
//					}
//				}
//
//				sb.append("  />").append(map.get(sysValue)).append("&nbsp;&nbsp;");
//				n++;
//
//
//			}
//
//			return sb.toString();
//		}
//		//map如果为空，返回null
//		return "";
//
//	}
//
//	/**
//	 * 	吕石川
//	 * Map,如<10101,无烟煤>
//	 * name 即下拉选select 的name属性值  可以为空吗？空串或者null?
//	 * selected 即这个下拉选被选中了，如10101，下拉选，选中了无烟煤
//	 * 如果不指定默认看到的效果是请选择
//	 *  yn即datetype,验证是否为空，只有出入1时验证不为空
//	 * skin即select标签的class属性值，一般用来控制风格,不需要时可以不用输入,或者输入""或null
//	 *
//	 */
//
//
//	public static String Select(Map<String,Object> map,String name,String selected,int yn,String ...skin){
//
//		if(!map.isEmpty()){    //根据type去查的数据库， key对应sys_dict的value, value对应sys_dict的label
//
//			StringBuffer sb = new StringBuffer();
//			int n=1;
//			if(name!=null&&!"".equals(name.trim())){
//				sb.append("<select  name='").append(name).append("'  id='").append(name+n).append("' ");
//			}else{
//
//				sb.append("<select  name='").append("'  id='").append(""+n).append("' ");
//
//			}
//			//有样式就加样式
//			if(skin!=null&&!"".equals(skin[0].trim())&&skin.length==1){
//				sb.append(" class='").append(skin[0]).append("' ");
//			}
//
//			sb.append(">");
//
//			Set<String> keySet = map.keySet();
//			sb.append("<option value='' >请选择</option><br>");
//			for (String sysValue : keySet) {
//
//				sb.append("<option value ='").append(sysValue).append("' ");
//				if(selected!=null&&!"".equals(sysValue)&&selected.equals(sysValue)){
//
//					sb.append(" selected");
//
//				}
//				sb.append(">");
//
//				sb.append(map.get(sysValue)).append("</option><br>");
//
//				if(yn==1&&n==1){
//					if(name!=null&&!"".equals(name.trim())){
//						sb.append("  datatype='").append(name).append("'  nullmsg ='不能为空'");
//
//					}else{
//						sb.append("  datatype='' ").append(" nullmsg ='不能为空'");
//					}
//				}
//				n++;
//			}
//
//			sb.append("</select>").append("&nbsp;&nbsp;");
//			return sb.toString();
//		}
//
//		return "";
//	}
//	/**
//	 * 供修改附件时使用 有修改此记录的能看到所有附件，包括其他人上传的
//	 * @param tableid  表唯一记录的ID
//	 * @param coName   当前是那个字段表
//	 * @return
//	 */
//	public static List<SysUpDownFile> queryFileListByTables(String tableid,String coName){
//		StringBuffer hql=new StringBuffer();
//		hql.append("select o from SysUpDownFile o where o.del_flag=0 and o.yn=1");
//		hql.append(" and o.tableid='").append(tableid).append("'").append(" and o.cont='").append(coName).append("'");
//		List<SysUpDownFile> list = dao.query(hql.toString());
//		if(list==null){
//			list= Lists.newArrayList();
//		}
//		return list ;
//	}
//	/**
//	 * 供添加失败时回写附件时使用 ，只能看到自己的临时上传的
//	 * 根据  用户名，表名，字段名 临时状态
//	 * @param   当前表名
//	 * @param userName   字段表
//	 * @return
//	 */
//	public static List<SysUpDownFile> queryFileListByTemp(String tableName,String coName){
//		StringBuffer hql=new StringBuffer();
//		hql.append("select o from SysUpDownFile o where o.del_flag=0 and o.yn=0");
//		hql.append(" and o.tablename='").append(tableName).append("'").append(" and o.cont='").append(coName).append("'")
//		.append(" and create_by='").append(UserUtil.getCurrentUser()).append("'");
//		List<SysUpDownFile> list = dao.query(hql.toString());
//		if(list==null){
//			list= Lists.newArrayList();
//		}
//		return list ;
//	}
//
//	/*
//	 * (临时先用着)
//	 * 自定义图片与下载文件显示，自动判断是图片还是文件
//	 */
//	public static String getDown(String id,String width,String height){
//		StringBuffer str = new StringBuffer();
//		try {
//			List	list = dao.query("from SysUpDownFile where id='"+id+"'");
//			if(list.size()>0){
//				SysUpDownFile entity = (SysUpDownFile) list.get(0);
//				String filety= entity.getFilety();
//				String allty=",BMP,JPG,JPEG,PNG,GIF.jpg.jpeg.gif.png.bmp";//图片格式集
//				if(allty.indexOf(filety)>0){//如果图片显示图片内容
//					str.append("<img alt=").append(entity.getOname()).append(" src=").append("/").append(Config.appName).append("/getpicture?id=")
//					.append(id).append(" style=width:").append(width).append("px;height:").append(height).append("px />");
//					//<img src="http://127.0.0.1/ld/getpicture?id=56c21ef6-75ba-406e-b279-c43ab77f361f" style="width:60px;height:60px"></img>
//
//				}else{//显示下载内容
//					//down.action?filenameInCN=下载后保存在客户端的文件名&path=服务器文件绝对路径（&yn=直接下载暂不用）
//					str.append("<a href='").append("/").append(Config.appName).append("/download?id=").append(id).append(" '>").append(entity.getOname())
//					.append("</a>");
//					System.out.println(str.toString());
//				}
//			}
//		} catch (Exception e) {
//
//		}
//		return str.toString();
//	}
//
//	/**
//	 * 取时间前
//	 * @param s
//	 * @return
//	 */
//	public static String getStart(String s){
//		  if(s!=null&&!"".equals(s.trim())){
//		    	 if(s.contains(",")){
//		    		 try {
//						String[] str = s.split(",");
//						return str[0];
//					} catch (Exception e) {
//						return "";
//					}
//		    	 }else{
//		    		 return s;
//		    	 }
//		     }
//			 return "";
//	}
//	/**
//	 * 取时间后
//	 * @param s
//	 * @return
//	 */
//	public static String getEnd(String s){
//		 if(s!=null&&!"".equals(s.trim())){
//	    	 if(s.contains(",")){
//	    		 String[] str = s.split(",");
//	    		 if(str.length==2){
//	    			 return str[1];
//	    		 }else if(str.length==1){
//	    			 return "";
//	    		 }
//	    	 }
//	    	 return "";
//	     }
//		 return "";
//	}
//}
