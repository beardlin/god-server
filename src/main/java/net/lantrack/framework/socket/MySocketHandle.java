package net.lantrack.framework.socket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class MySocketHandle implements WebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Object userName = session.getAttributes().get("user");
		if (null != userName && !"".equals(userName.toString())) {
			users.put(userName.toString(), session);
			session.sendMessage(new TextMessage("system：" + userName.toString() + "连接成功。。。"));
		}

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session.getAttributes().get("user").toString());

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session.getAttributes().get("user").toString());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendMessageToUsers(String sender, TextMessage message) {
		Set<Map.Entry<String, WebSocketSession>> entrySet = users.entrySet();
		for (Map.Entry<String, WebSocketSession> entry : entrySet) {
			String userName = "";
			try {
				userName = entry.getKey();
				if (userName == null || userName.equals(sender)) {
					continue;
				}
				WebSocketSession session = entry.getValue();
				session.sendMessage(message);
			} catch (Exception e) {
				System.err.println("发送消息给" + userName + "失败"+ e.getMessage());
			}
		}
	}
	
	public void sendMessageToAllUsers(TextMessage message) {
		Set<Map.Entry<String, WebSocketSession>> entrySet = users.entrySet();
		for (Map.Entry<String, WebSocketSession> entry : entrySet) {
			try {
				WebSocketSession session = entry.getValue();
				session.sendMessage(message);
			} catch (Exception e) {
				System.err.println("发送消息给" + entry.getKey() + "失败"+ e.getMessage());
			}
		}
	}

	public void sendMessageToUser(String userName, TextMessage message) {
		try {
			WebSocketSession session = users.get(userName);
			session.sendMessage(message);
		} catch (Exception e) {
			System.err.println("发送消息给" + userName + "失败"+ e.getMessage());
		}
	}

}
