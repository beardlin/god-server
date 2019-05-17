package net.lantrack.framework.core.entity;


/**
 * 带分页返回的结果
 * 2018年1月12日
 * @author lin
 */
public class PageEntity extends ReturnResult{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 当前页码,默认为1
     */
    public Integer currentPage = 1;//当前页码
    /**
     * 一页多少条记录，默认20
     */
    public Integer perPageCount = 20;//一页多少条记录
    /**
     * 总共多少页
     */
    public long totalPage=0L;//总共多少页
    /**
     * 总记录数
     */
    public long totalCount=0L;//总记录数
    
    /**
     * 排序字段
     */
    public String orderField;//(排序字段)
    /**
     * 排序顺序   升序asc  降序desc
     */
    public String orderSort = "desc";//(排序顺序)
    
    public Integer getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPageCount() {
        return perPageCount;
    }

    public void setPerPageCount(Integer perPageCount) {
        this.perPageCount = perPageCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    private void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        if (totalCount != 0L) {
            long pageNumber = totalCount % (long) this.perPageCount != 0L ? totalCount
                    / (long) this.perPageCount + 1L : totalCount / (long) this.perPageCount;
            if ((long) this.currentPage > pageNumber){
                this.setCurrentPage((int)pageNumber);
            }
            this.setTotalPage(pageNumber);
        }
    }

    public String getOrderField() {
        return orderField==null?"":orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public void turnProToColumn(){
        String pro = this.orderField;
        if(pro!=null&&!"".equals(pro.trim())){
            byte[] bytes = pro.getBytes();
            int index = -1;
            int start = 'A';
            int end = 'Z';
            for(int i=0;i<bytes.length;i++){
                if(start<=bytes[i]&&bytes[i]<=end){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                char c = pro.charAt(index);
                char[] charArray ={c};
                String perfix = pro.substring(0, index);
                String suffix = pro.substring(index+1);
                pro=perfix+"_"+new String(charArray).toLowerCase()+suffix;
                this.orderField = pro;
            }
        }
    }
    
    
}
