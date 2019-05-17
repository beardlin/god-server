package net.lantrack.framework.core;

/**
 * 这里包含所有服务器状态的编号以及对应文本描述，使用 枚举
 *
 * @author : lihuadong@lantrack.net
 * @date : 2017/12/15 11:16
 */

/**
 *  case 47:codemsg="正常";break;
 *	case 1:codemsg="服务器内部错误";break;
 *	case 2:codemsg="请求参数非法";break;
 *	case 3:codemsg="认证失败";break;
 *	case 4:codemsg="禁止登录";break;
 *	case 5:codemsg="暂无权限";break;
 *	case 6:codemsg="资源不存在";break;
 *	case 102:codemsg="不通过白名单";break;
 * @param status
 */
public enum StatusCode {
    SERVER_NORMAL(47, "正常"),
    SERVER_ERROR(1, "服务器内部错误"),
    PARAMETER_ERROR(2, "请求参数非法"),
    AUTHENTICATION_ERROR(3, "认证失败"),
    LOGIN_FORBID_ERROR(4, "禁止登录"),
    NOPERMISS_ERROR(5, "暂无权限"),
    RESOURCE_NOTFIND(6, "资源不存在"),
    NOUID_ERROR(102, "不通过白名单");

    private int code;
    private String msg;
    
    

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
