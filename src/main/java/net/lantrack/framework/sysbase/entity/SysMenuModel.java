package net.lantrack.framework.sysbase.entity;


import java.io.Serializable;
import java.util.List;


/**
 * 主页菜单显示页面
 * 2018年1月8日
 * @author lin
 */
public class SysMenuModel implements Serializable{
	
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;


    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 目标（新table页 还是直接打开）
     */
    private String target;

    /**
     * 图标
     */
    private String iconimg;
    /**
     * 父菜单名称
     */
    private String pname;
    /**
     * 父菜单图标
     */
    private String picon;

    /**
     * 子菜单
     */
    private List<SysMenuModel> children;
    
    

    public String getPname() {
        return pname==null?"":pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPicon() {
        return picon==null?"":picon;
    }

    public void setPicon(String picon) {
        this.picon = picon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName==null?"":menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href==null?"":href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target==null?"":target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIconimg() {
        return iconimg==null?"":iconimg;
    }

    public void setIconimg(String iconimg) {
        this.iconimg = iconimg;
    }

    public List<SysMenuModel> getChildren() {
        return children;
//    	if (UserUtil.ifAdmin()) {
//    		return children;
//    	} else {
//			Set<Integer> menus = UserUtil.getUserMenuIds();
//			Iterator<SysMenuModel> iterator = children.iterator();
//            while(iterator.hasNext()){
//                SysMenuModel next = iterator.next();
//                if (!menus.contains(next.getId())) {
//                    iterator.remove();
//                }
//            }
//    		return children;
//    	}
    }

    public void setChildren(List<SysMenuModel> children) {
        this.children = children;
    }

   
    
    
   
    
}