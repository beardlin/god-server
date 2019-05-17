package net.lantrack.project.search.dao;

import java.util.List;

import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.project.search.entity.DataSerchModel;
import net.lantrack.project.search.entity.DataSerchModelExample;
import org.apache.ibatis.annotations.Param;

public interface DataSerchModelDao {
	
	/**
	 * 分页查询，以**listPage命名
	 * @Description: 
	 * @author lin
	 * @date 2018年6月19日
	 */
	List<DataSerchModel> getListPage(@Param("example") DataSerchModelExample example,@Param("page") PageEntity page);
	
    long countByExample(DataSerchModelExample example);

    int deleteByExample(DataSerchModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataSerchModel record);

    int insertSelective(DataSerchModel record);

    List<DataSerchModel> selectByExample(DataSerchModelExample example);

    DataSerchModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataSerchModel record, @Param("example") DataSerchModelExample example);

    int updateByExample(@Param("record") DataSerchModel record, @Param("example") DataSerchModelExample example);

    int updateByPrimaryKeySelective(DataSerchModel record);

    int updateByPrimaryKey(DataSerchModel record);
}