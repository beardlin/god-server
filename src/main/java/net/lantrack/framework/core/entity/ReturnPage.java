
package net.lantrack.framework.core.entity;

import net.lantrack.framework.core.StatusCode;

import java.io.Serializable;
/**
 * 直接与PC或APP交互的带分页返回结果
 * 2018年1月12日
 * @author lin
 */
public class ReturnPage implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 请求系统时服务状态信息代码 StutusCode
     */
    public int status = 47;// 系统级别信息代码
    /**
     * 请求系统时服务状态信息
     */
    public String message = "正常";// 系统级别信息

    /**
     * 存放 返回的结果
     */
    public PageEntity result = new PageEntity(); //返回结果集

    public int getStatus() {
        return status;
    }
    /**
     * 操作成功  1
     * @param rmg
     * 2018年1月12日
     * @author lin
     */
    public void success(String rmg) {
        this.result.setRst(1);
        addRmg(rmg);
    }
    /**
     * 操作失败 2
     * @param rmg 操作失败描述
     * 2018年1月12日
     * @author lin
     */
    public void failed(String ...rmg) {
        this.result.setRst(2);
        addRmg(rmg);
    }

    private void addRmg(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        this.result.setRmg(sb.toString());
    }

    public Object getResult() {
        return result;
    }

    /**
     * case 47:codemsg="正常";break; 
     * case 1:codemsg="服务器内部错误";break; 
     * case 2:codemsg="请求参数非法";break; 
     * case 3:codemsg="认证失败";break; 
     * case 4:codemsg="禁止登录";break; 
     * case 5:codemsg="暂无权限";break; 
     * case 6:codemsg="资源不存在";break; 
     * case 102:codemsg="不通过白名单";break;
     * @param status
     */
    public void setStatus(StatusCode status) {
        this.setMessage(status.getMsg());// 根据状态码自动设置状态信息
        this.status = status.getCode();
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
    /**
     * 添加系统状态码描述
     * @param message
     * 2018年1月12日
     * @author lin
     */
    public void appendMessage(String message){
        this.message+=","+message;
    }
    /**
     * 将结果集注入
     * @param result
     * 2018年1月12日
     * @author lin
     */
    public void setResult(Object result) {
        this.result = (PageEntity) result;
    }

}
