package net.lantrack.framework.sms.tencent;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.JSONException;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import net.lantrack.framework.core.util.SpringContextHolder;
import net.lantrack.framework.sysbase.enm.SysConfigEnum;
import net.lantrack.framework.sysbase.entity.SysConfig;
import net.lantrack.framework.sysbase.service.SysConfigService;

public class TencentSmsUtil {
	protected static Logger logger = (Logger) LogManager.getLogger("mylog");
	
	//获取系统配置参数
	private static SysConfigService sysConfigService = SpringContextHolder.getBean("sysConfigServiceImpl");

	// 短信应用SDK AppID
	int appid = 1400009099; // 1400开头
	// 短信应用SDK AppKey
	String appkey = "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad";
	// 短信模板ID，需要在短信应用中申请
	int templateId = 7839;
	// 签名
	String smsSign = "腾讯云"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`


	//登录失败过多短信警告提醒发送
	public static void smsSingleSenderLoginError(String mobile,String  loginName,
			String errorCount,String remainCount) {
		SysConfig appId = sysConfigService.getConfigByName(SysConfigEnum.SMSAPPID.getConfName());
		SysConfig appkey = sysConfigService.getConfigByName(SysConfigEnum.SMSAPPKEY.getConfName());
		SysConfig templateId = sysConfigService.getConfigByName(SysConfigEnum.SMSCONTENTID_LOGIN_ERROR.getConfName());
		SysConfig smsSign = sysConfigService.getConfigByName(SysConfigEnum.SMSSIGN.getConfName());
		String[] params = {loginName,errorCount,remainCount};
		SmsSingleSender(mobile, appId.getConfValue(), appkey.getConfValue(), 
				templateId.getConfValue(), smsSign.getConfValue(), params);
	}
	
	
	//短信验证码发送
	public static void smsSingleSenderMsgCode(String mobile,String msgCode) {
		SysConfig appId = sysConfigService.getConfigByName(SysConfigEnum.SMSAPPID.getConfName());
		SysConfig appkey = sysConfigService.getConfigByName(SysConfigEnum.SMSAPPKEY.getConfName());
		SysConfig templateId = sysConfigService.getConfigByName(SysConfigEnum.SMSCONTENTID.getConfName());
		SysConfig smsSign = sysConfigService.getConfigByName(SysConfigEnum.SMSSIGN.getConfName());
		SysConfig smsMinut = sysConfigService.getConfigByName(SysConfigEnum.SMSMINUT.getConfName());
		String[] params = {msgCode,smsMinut.getConfValue()};
		SmsSingleSender(mobile, appId.getConfValue(), appkey.getConfValue(), 
				templateId.getConfValue(), smsSign.getConfValue(), params);
	}
	
	//自定义短信发送
	public static void SmsSingleSender(String mobile, String appId,String appkey,
			String templateId,String smsSign,String[]  params) {
		try {
			// 数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
//			String[] params = { "5678" };
			SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(appId), appkey);
			SmsSingleSenderResult result = ssender.sendWithParam("86", mobile, 
					Integer.parseInt(templateId), params, smsSign, "",""); // 签名参数未提供或者为空时，会使用默认签名发送短信
			System.out.println(result);
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		String[] params = {"546323","5"};
		TencentSmsUtil.SmsSingleSender("17096410076", "1400050482", "14b058358c7ca7c73653b58257262916",
				"58739", "联创高科", params);
	}
}
