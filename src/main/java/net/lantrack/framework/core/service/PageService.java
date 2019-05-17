package net.lantrack.framework.core.service;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;

import java.util.List;

public interface PageService {
    /**
     * 第一步 使用分页先使用此方法初始化分页服务参数；
     * @param pageSize    一页显示多少条记录 如果传为小于或等于0，则按默认显示20条
     * @param currentpage
     */
    public void getPage(int pageSize, int currentpage) throws ServiceException;

   

    /**
     * 第二步 添加结果集和计算页数
     * @param className
     * @param where
     */
    public PageEntity doList(List<?> list,long count) throws ServiceException;

}
