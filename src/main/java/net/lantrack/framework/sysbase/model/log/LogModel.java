package net.lantrack.framework.sysbase.model.log;

import org.springframework.context.annotation.Description;

import net.lantrack.framework.core.entity.BaseEntity;


/**
 * 向前台返回数据时的日志model
 * @author hww
 *
 */
public class LogModel extends BaseEntity<LogModel> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3402697047990867097L;
	/**
     * id
     */
    private Long id;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 状态码
     */
    private String status;
    /**
     * 信息
     */
    private String message;
    /**
     * 操作IP地址
     */
    private String remoteAddr;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 耗时
     */
    private Double protime;
    /**
     * 参数
     */
    private String params;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 用户姓名
     */
    private String createUser;
  
    
    @Description("编号")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Description("日志类型")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Description("日志标题")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Description("状态码")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Description("信息")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Description("操作IP地址")
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	@Description("用户代理")
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	@Description("请求URI")
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	@Description("操作方式")
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@Description("耗时")
	public Double getProtime() {
		return protime;
	}
	public void setProtime(Double protime) {
		this.protime = protime;
	}
	@Description("参数")
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Description("异常信息")
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	@Description("用户姓名")
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
    
    
	
}
