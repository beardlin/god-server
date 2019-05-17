package net.lantrack.framework.sysbase.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.DaoException;
import net.lantrack.framework.sysbase.entity.SysOfficeTree;
import net.lantrack.framework.sysbase.entity.SysOfficeTreeExample;
import net.lantrack.framework.sysbase.model.office.OfficeModel;

import org.apache.ibatis.annotations.Param;

public interface SysOfficeTreeMapper {
	
    
    /**
     * 分页，方法名必须以 "**ListPage" 格式命名 
     * 如果需要多表关联分页自己再实现
     * @param page
     * @param office
     * @return List<SysOffice>
     * 2018年1月13日
     * @author hww
     */
    public List<OfficeModel> getOfficeListPage(@Param("page") PageEntity page, 
            @Param("office") SysOfficeTree office) throws DaoException;
    
    /**
     * 根据父节点查找子节点
     * @param pid
     * @return
     * 2018年1月17日
     * @author hww
     */
    List<SysOfficeTree> queryByPid(String pid);
    
    /**
     * 根据父节点查找其所有子孙节点
     * @param pid
     * @return
     * 2018年1月17日
     * @author hww
     */
    List<OfficeModel> queryChildrenByPid(String pid);
	
    long countByExample(SysOfficeTreeExample example);

    int deleteByExample(SysOfficeTreeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOfficeTree record);

    int insertSelective(SysOfficeTree record);

    List<SysOfficeTree> selectByExample(SysOfficeTreeExample example);

    SysOfficeTree selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOfficeTree record, @Param("example") SysOfficeTreeExample example);

    int updateByExample(@Param("record") SysOfficeTree record, @Param("example") SysOfficeTreeExample example);

    int updateByPrimaryKeySelective(SysOfficeTree record);

    int updateByPrimaryKey(SysOfficeTree record);
}