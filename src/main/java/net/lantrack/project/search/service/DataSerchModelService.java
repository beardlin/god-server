package net.lantrack.project.search.service;

import net.lantrack.framework.core.service.CrudService;
import net.lantrack.project.search.entity.DataSerchModel;

public interface DataSerchModelService extends CrudService<DataSerchModel>{
//	对谁可见（1自己2部门3全部）
	public static String show_myself = "1";
	public static String show_myoffice = "2";
	public static String show_all = "3";

}
