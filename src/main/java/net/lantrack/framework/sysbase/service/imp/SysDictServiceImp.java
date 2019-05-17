
package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;
import net.lantrack.framework.core.service.BaseService;
import net.lantrack.framework.core.service.PageService;
import net.lantrack.framework.sysbase.dao.SysDictDao;
import net.lantrack.framework.sysbase.entity.SysDict;
import net.lantrack.framework.sysbase.entity.SysDictExample;
import net.lantrack.framework.sysbase.entity.SysDictExample.Criteria;
import net.lantrack.framework.sysbase.model.dict.DictTypeModel;
import net.lantrack.framework.sysbase.service.SysDictService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典管理Service
 * @author lin
 */
@Service
public class SysDictServiceImp extends BaseService implements SysDictService {

    @Autowired()
    PageService pageService;
    @Autowired
    protected SysDictDao sysDictDao;

    @Override
    public void deleteById(Object id, String update_by, boolean ifLogic)
            throws ServiceException {
        if (id==null) {
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        if (!ifLogic) {
            // 物理删除
            this.sysDictDao.deleteByPrimaryKey(pasId);
        } else {
            // 逻辑删除结束
            SysDict dict = new SysDict();
            dict.setCreateDate(null);
            dict.setUpdateBy(update_by);
            dict.setDelFlag(BaseEntity.YES);
            dict.setId(pasId);
            this.sysDictDao.updateByPrimaryKeySelective(dict);
        }
    }

    @Override
    public void deleteByIds(String ids, String update_by, boolean ifLogic) throws ServiceException {
        if (StringUtils.isBlank(ids)) {
            this.logger.error("id can not be null or empty!");
        }
        String[] split = ids.split(",");
        // 添加条件
        SysDictExample dictExample = new SysDictExample();
        SysDictExample.Criteria cr = dictExample.createCriteria();
        cr.andIdIn(Arrays.asList(split));
        if (!ifLogic) {
            // 物理删除
            this.sysDictDao.deleteByExample(dictExample);
        } else {
            // 此处用逻辑删除
            SysDict dict = new SysDict();
            dict.setCreateDate(null);
            dict.setUpdateBy(update_by);
            dict.setDelFlag(BaseEntity.YES);
            this.sysDictDao.updateByExampleSelective(dict, dictExample);
        }
    }

    @Override
    public SysDict queryById(Object id) throws ServiceException {
        if (id==null) {
            this.logger.error("id can not be null or empty!");
            throw new ServiceException("id can not be null or empty!");
        }
        Integer pasId = Integer.valueOf(id.toString());
        SysDict dict = null;
        try {
            dict = this.sysDictDao.selectByPrimaryKey(pasId);
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error(e);
            throw new ServiceException(e.getMessage());
        }
        return  dict;
    }

    @Override
    public void getPage(SysDict entity, PageEntity pageentity) throws ServiceException {
        this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
        try {
            if(StringUtils.isBlank(entity.getType())){
                entity.setType(null);
            }
            if(StringUtils.isBlank(entity.getLabel())){
                entity.setLabel(null);
            }
            SysDict.turnOrderField(pageentity);
            List<SysDict> result = this.sysDictDao.getDictListPage(pageentity, entity);
            pageentity.setContent(result);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        
    }
    
    public boolean validateRepeat(SysDict entity) {
    	 SysDictExample dictExample = new SysDictExample();
         Criteria cr = dictExample.createCriteria();
         cr.andValueEqualTo(entity.getValue());
         cr.andLabelEqualTo(entity.getLabel());
         cr.andTypeEqualTo(entity.getType());
         List<SysDict> list = this.sysDictDao.selectByExample(dictExample);
         boolean up=false;
         if(list!=null&&list.size()>0){
             for(SysDict dict:list) {
            	 if(!dict.getId().equals(entity.getId())){
                     up = true;
                 }
             }
         }
    	return up;
    }

    @Override
    public SysDict update(SysDict entity) throws ServiceException{
    	boolean repeat = validateRepeat(entity);
        if(!repeat){
            try {
                this.sysDictDao.updateByPrimaryKeySelective(entity);
            } catch (Exception e) {
                this.logger.error(e);
                throw new ServiceException(e.getMessage());
            }
        }else{
            throw new ServiceException("当前字典中已存在相同记录");
        }
        return entity;
    }

    @Override
    public SysDict save(SysDict entity) throws ServiceException{
        try {
        	boolean repeat = validateRepeat(entity);
            if(!repeat){
            	sysDictDao.insertSelective(entity);
            }else{
                throw new ServiceException("当前字典中已存在相同记录");
            }
        } catch (Exception e) {
            throw printException(e);
        }
        return entity;
    }

    @Override
    public void deleteByIdsRe(String ids, String update_by) throws ServiceException {
        if (StringUtils.isBlank(ids)) {
            throw new ServiceException("ids can not be null or empty ''");
        }
        String[] split = ids.split(",");
        SysDictExample dictExample = new SysDictExample();
        SysDictExample.Criteria cr = dictExample.createCriteria();
        cr.andIdIn(Arrays.asList(split));
        // 数据还原
        SysDict dict = new SysDict();
        dict.setCreateDate(null);
        dict.setUpdateBy(update_by);
        dict.setDelFlag(BaseEntity.NO);
        this.sysDictDao.updateByExampleSelective(dict, dictExample);
    }

    @Override
    public void getPageRe(SysDict entity, PageEntity pageentity) {
        this.pageService.getPage(pageentity.getPerPageCount(), pageentity.getCurrentPage());
    }

    @Override
    public List<SysDict> getAll(SysDict entity) {
        SysDictExample dictExample = new SysDictExample();
        SysDictExample.Criteria cr = dictExample.createCriteria();
        if (StringUtils.isNotBlank(entity.getType())) {
        	cr.andTypeEqualTo(entity.getType());
        	dictExample.setOrderByClause("sort");
        }
        try {
            return this.sysDictDao.selectByExample(dictExample);
        } catch (Exception e) {
            this.logger.error("sysDictServiceImp getAll has Error:",e);
            return null;
        }
    }

    /**
     * 根据参数type获取指定类型的字典信息，以map形式返回结果
     */
    @Override
    public Map<String, String> getDictMap(SysDict entity, int pleaseSelect) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        List<SysDict> list = this.getAll(entity);
        if (pleaseSelect == 0) {
        	map.put("", "请选择");
        }
        if (list != null && list.size() > 0) {
            for (SysDict dict : list) {
                map.put(dict.getValue(), dict.getLabel());
            } 
        }
        return map;
    }

    
    @Override
    public Map<String, String> getDictTypeAll() {
        Map<String, String> typeMap = new LinkedHashMap<String, String>();
        try {
            List<DictTypeModel> dictType = this.sysDictDao.getDictType();
            if(dictType!=null){
                for(DictTypeModel dict:dictType){
                    typeMap.put(dict.getType(), dict.getDesc());
                }
            }
        } catch (Exception e) {
            this.logger.error(e);
        }
        return typeMap;
    }


}
