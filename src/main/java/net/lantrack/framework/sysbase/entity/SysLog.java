
package net.lantrack.framework.sysbase.entity;

import net.lantrack.framework.core.entity.BaseEntity;
import net.lantrack.framework.core.util.StrUtil;

import java.util.Map;

/**
 * 系统日志SysLog
 * 2018年1月6日
 * @author lin
 */
public class SysLog extends BaseEntity<SysLog> {
    
    
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
     * 开始时间
     */
    private Double starttime;
    /**
     * 结束时间
     */
    private Double endtiime;
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
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    
    //用户id
    private String userId;
    
     

    private static final long serialVersionUID = 1L;

    public SysLog(Long id, String type, String title, String status, String message,
            String createBy, String createDate, String remoteAddr, String userAgent,
            String requestUri, String method, Double starttime, Double endtiime, Double protime,
            String delFlag, String remarks, String updateBy, String updateDate, String params,
            String exception, String startDate, String endDate) {
        super(createBy, createDate, updateBy, updateDate, remarks, delFlag);
        this.id = id;
        this.type = type;
        this.title = title;
        this.status = status;
        this.message = message;
        this.remoteAddr = remoteAddr;
        this.userAgent = userAgent;
        this.requestUri = requestUri;
        this.method = method;
        this.starttime = starttime;
        this.endtiime = endtiime;
        this.protime = protime;
        this.params = params;
        this.exception = exception;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    

    public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public SysLog() {
        super();
    }
    public String getParams() {
        return params;
    }

    public void setParams(Map<String, String[]> paramMap) {
        if (paramMap == null){
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
//            if(BaseController.formdata.equals(param.getKey())){
//                params.append(StrUtil.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue);
//            }else{
//                params.append(StrUtil.abbr(StrUtil.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
//            }
            params.append(StrUtil.abbr(StrUtil.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));

        }
        this.params = params.toString();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Double getStarttime() {
        return starttime;
    }

    public void setStarttime(Double starttime) {
        this.starttime = starttime;
    }

    public Double getEndtiime() {
        return endtiime;
    }

    public void setEndtiime(Double endtiime) {
        this.endtiime = endtiime;
    }

    public Double getProtime() {
        return protime;
    }

    public void setProtime(Double protime) {
        this.protime = protime;
    }
    

    public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
    public String toString() {
        return "SysLog [id=" + id + ", type=" + type + ", title=" + title + ", status=" + status
                + ", message=" + message + ", remoteAddr=" + remoteAddr + ", userAgent="
                + userAgent + ", requestUri=" + requestUri + ", method=" + method + ", starttime="
                + starttime + ", endtiime=" + endtiime + ", protime=" + protime + "]";
    }
    
    
   
}
