package net.lantrack.framework.sysbase.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 登录信息
 * @date 2019年1月17日
 */
public class LoginInfo implements Serializable {
	/**
	 * 登录名
	 */
    private String loginName;
    /**
	 * 账户是否被禁用（0否1是）
	 */
    private String ifForbidden="0";
    /**
	 * 是否锁定(0否1是)
	 */
    private String ifLock="0";
    /**
	 * 开始锁定时间
	 */
    private Date lockStartTime;
    /**
	 * 账户应解锁时间
	 */
    private Date lockEndTime;
    /**
	 * 登录失败次数
	 */
    private Integer errCount = 0;
    /**
	 * 最后一次登录成功时间
	 */
    private Date lastSucTime;
    /**
	 * 最后一次登录成功地址
	 */
    private String lastSucAddress;
    /**
	 * 最后一次登录成功ip
	 */
    private String lastSucIp;
    /**
	 * 最后一次登录成功设备（app,pc）
	 */
    private String lastDevice;
    /**
	 * 发送验证码时间
	 */
    private Date sendmsgTime;
    /**
	 * 验证码有效期结束时间
	 */
    private Date endmsgTime;
    /**
	 * 短信验证码
	 */
    private String msgCode;
    /**
     * 扫描二维码登录状态（0未登录1已登录）
     */
    private String stand1;
    /**
     * 扫描二维码成功后返回确认认证信息
     */
    private String stand2;
  
    private String stand3;
  
    private String stand4;
  
    private String stand5;
  
    private String stand6;

    private static final long serialVersionUID = 1L;

    public LoginInfo(String loginName, String ifForbidden, String ifLock, Date lockStartTime, Date lockEndTime, Integer errCount, Date lastSucTime, String lastSucAddress, String lastSucIp, String lastDevice, Date sendmsgTime, Date endmsgTime, String msgCode, String stand1, String stand2, String stand3, String stand4, String stand5, String stand6) {
        this.loginName = loginName;
        this.ifForbidden = ifForbidden;
        this.ifLock = ifLock;
        this.lockStartTime = lockStartTime;
        this.lockEndTime = lockEndTime;
        this.errCount = errCount;
        this.lastSucTime = lastSucTime;
        this.lastSucAddress = lastSucAddress;
        this.lastSucIp = lastSucIp;
        this.lastDevice = lastDevice;
        this.sendmsgTime = sendmsgTime;
        this.endmsgTime = endmsgTime;
        this.msgCode = msgCode;
        this.stand1 = stand1;
        this.stand2 = stand2;
        this.stand3 = stand3;
        this.stand4 = stand4;
        this.stand5 = stand5;
        this.stand6 = stand6;
    }

    public LoginInfo() {
        super();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getIfForbidden() {
        return ifForbidden;
    }

    public void setIfForbidden(String ifForbidden) {
        this.ifForbidden = ifForbidden == null ? null : ifForbidden.trim();
    }

    public String getIfLock() {
        return ifLock;
    }

    public void setIfLock(String ifLock) {
        this.ifLock = ifLock == null ? null : ifLock.trim();
    }

    public Date getLockStartTime() {
        return lockStartTime;
    }

    public void setLockStartTime(Date lockStartTime) {
        this.lockStartTime = lockStartTime;
    }

    public Date getLockEndTime() {
        return lockEndTime;
    }

    public void setLockEndTime(Date lockEndTime) {
        this.lockEndTime = lockEndTime;
    }

    public Integer getErrCount() {
        return errCount;
    }

    public void errCount(Integer errCount) {
        this.errCount = errCount;
    }

    public Date getLastSucTime() {
        return lastSucTime;
    }

    public void setLastSucTime(Date lastSucTime) {
        this.lastSucTime = lastSucTime;
    }

    public String getLastSucAddress() {
        return lastSucAddress;
    }

    public void setLastSucAddress(String lastSucAddress) {
        this.lastSucAddress = lastSucAddress == null ? null : lastSucAddress.trim();
    }

    public String getLastSucIp() {
        return lastSucIp;
    }

    public void setLastSucIp(String lastSucIp) {
        this.lastSucIp = lastSucIp == null ? null : lastSucIp.trim();
    }

    public String getLastDevice() {
        return lastDevice;
    }

    public void setLastDevice(String lastDevice) {
        this.lastDevice = lastDevice == null ? null : lastDevice.trim();
    }

    public Date getSendmsgTime() {
        return sendmsgTime;
    }

    public void setSendmsgTime(Date sendmsgTime) {
        this.sendmsgTime = sendmsgTime;
    }

    public Date getEndmsgTime() {
        return endmsgTime;
    }

    public void setEndmsgTime(Date endmsgTime) {
        this.endmsgTime = endmsgTime;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getStand1() {
        return stand1;
    }

    public void setStand1(String stand1) {
        this.stand1 = stand1 == null ? null : stand1.trim();
    }

    public String getStand2() {
        return stand2;
    }

    public void setStand2(String stand2) {
        this.stand2 = stand2 == null ? null : stand2.trim();
    }

    public String getStand3() {
        return stand3;
    }

    public void setStand3(String stand3) {
        this.stand3 = stand3 == null ? null : stand3.trim();
    }

    public String getStand4() {
        return stand4;
    }

    public void setStand4(String stand4) {
        this.stand4 = stand4 == null ? null : stand4.trim();
    }

    public String getStand5() {
        return stand5;
    }

    public void setStand5(String stand5) {
        this.stand5 = stand5 == null ? null : stand5.trim();
    }

    public String getStand6() {
        return stand6;
    }

    public void setStand6(String stand6) {
        this.stand6 = stand6 == null ? null : stand6.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loginName=").append(loginName);
        sb.append(", ifForbidden=").append(ifForbidden);
        sb.append(", ifLock=").append(ifLock);
        sb.append(", lockStartTime=").append(lockStartTime);
        sb.append(", lockEndTime=").append(lockEndTime);
        sb.append(", errCount=").append(errCount);
        sb.append(", lastSucTime=").append(lastSucTime);
        sb.append(", lastSucAddress=").append(lastSucAddress);
        sb.append(", lastSucIp=").append(lastSucIp);
        sb.append(", lastDevice=").append(lastDevice);
        sb.append(", sendmsgTime=").append(sendmsgTime);
        sb.append(", endmsgTime=").append(endmsgTime);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", stand1=").append(stand1);
        sb.append(", stand2=").append(stand2);
        sb.append(", stand3=").append(stand3);
        sb.append(", stand4=").append(stand4);
        sb.append(", stand5=").append(stand5);
        sb.append(", stand6=").append(stand6);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}