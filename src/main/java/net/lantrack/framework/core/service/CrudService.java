/**
 *
 */
package net.lantrack.framework.core.service;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.entity.PageEntity;
import net.lantrack.framework.core.lcexception.ServiceException;

import java.util.List;

/**
 * Service基类
 * @author lin
 */
public interface CrudService<T extends BaseEntity<T>> {

    /**
     * 更新接口
     *
     * @param entity
     * @return
     */
    public T update(T entity) throws ServiceException;

    /**
     * 保存接口
     *
     * @param entity
     * @return
     */
    public T save(T entity) throws ServiceException;

    /**
     * 得一个实体,此处id为Object类型，方便传输，
     * 具体类型到自己实现里在转一下
     * @param id 实体id，如果没有返回NULL
     */
    public T queryById(Object id);


    /**
     * 删除接口，根据ID删除
     * 此处id为Object类型，方便传输，
     * 具体类型到自己实现里在转一下
     * @param id 
     * @param upBy 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
     */
    public  void deleteById(Object id, String upBy,boolean ifLogic) throws ServiceException;

    /**
     * 删除接口，根据ID删除
     * @param ids 
     * @param upBy 删除者 （ 逻辑删除用）
     * @param ifLogic 是否启用逻辑删除  true逻辑，false物理
     */
    public  void deleteByIds(String ids, String upBy,boolean ifLogic) throws ServiceException;

    /**
     * 根据输入的内容根据所有结果,-带分页效果
     *
     * @param entity
     * @return
     */
    public  void getPage(T entity, PageEntity pageentity);

    /**
     * 查看回收站数据信息
     * @param entity
     * @param pageentity
     * @return
     */
    public  void getPageRe(T entity, PageEntity pageentity);

    /**
     * 逻辑删除根据IDS恢复接口，数据还原
     * @param ids
     * @param update_by
     * @return
     */
    public  void deleteByIdsRe(String ids, String update_by) throws ServiceException;

    /**
     * 根据条件查询所有为不空的数据
     *
     * @param entity
     * @return
     */
    public List<T> getAll(T entity);
}
