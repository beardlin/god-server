package net.lantrack.framework.sysbase.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import net.lantrack.BaseJunitTest;
import net.lantrack.framework.sysbase.entity.SysMenu;
import net.lantrack.framework.sysbase.entity.SysMenuModel;
import net.lantrack.framework.sysbase.service.SysMenuService;

/**
 * 菜单测试
 * 2018年1月10日
 * @author lin
 */
public class SysMenuDaoTest extends BaseJunitTest {

	@Autowired
	SysMenuDao sysMenuDao;
	@Autowired
	SysMenuService sysMenuService;
	@Test
    public void getMaxSortByPid(){
        Integer maxSort = sysMenuDao.getMaxSortByPid(141);
        System.out.println(maxSort);
    }
	@Test
    public void testQueryByPid(){
	    List<SysMenu> list = sysMenuDao.queryByPid(1);
        System.out.println(list.size());
    }
	@Test
    public void testGetMenuService(){
        List<SysMenuModel> list = sysMenuService.getMenuAdmin(1);
        System.out.println(list.size());
    }
	@Test
	public void testGetMenu(){
	    List<SysMenuModel> list = sysMenuDao.getIndexMenuByParentId(1);
	    System.out.println(list.size());
	}
}
