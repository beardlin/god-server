package net.lantrack.framework.core.service;

/**
 * 短信接口
 *
 * @author coolcjava
 */
public interface SmsService {
    /**
     * 给phone号码发送短信，内容为cont
     *
     * @param phone
     * @param cont
     * @return
     */
    public Boolean sendSms(String phone, String cont);

}
