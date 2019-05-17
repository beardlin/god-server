package net.lantrack.project.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.socket.MySocketHandle;

@RestController
@RequestMapping("socketpush")
public class SocketPushController {
	@Autowired
	private MySocketHandle mySocketHandler;

//  socketpush/login
	@RequestMapping("login")
	public ReturnEntity login(HttpSession session, ReturnEntity info,String name) {
		session.setAttribute("user", name);
		info.success(name + "登录了");
		return info;
	}
//	socketpush/sendMessage
	@RequestMapping("sendMessage")
	public ReturnEntity sendMessage(HttpSession session, ReturnEntity info,String message) {
		String name = (String) session.getAttribute("user");
		mySocketHandler.sendMessageToUsers(name, new TextMessage(name + " : " + message));
		return info;
	}
}
