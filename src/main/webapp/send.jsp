<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="messages"></div>
	<div>
		<input type="text" id="message">
		<button onclick="mysend()">发送</button>
	</div>
</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript"> 
$(function(){ 
	//建立socket连接 
	var sock; 
	//判断当前浏览器是否支持webSocket
	if ('WebSocket' in window) {
		//建立连接 
		sock = new WebSocket("ws:192.168.118.1:8080/god-server2.0/socketRealData");
	} 
	sock.onopen = function (e) {
		//成功建立连接 
		console.log(e); 
	}; 
	sock.onmessage = function (e) {
		//接收到消息 
		console.log(e); 
		$("#messages").append("<p><font color='red'>"+e.data+"</font>") 
	}; 
	sock.onerror = function (e) {
		alert("连接发生错误 !");
		//连接发生错误 
		console.log(e);
	}; 
	sock.onclose = function (e) {
		alert("连接关闭  !");
		//连接关闭 
		console.log(e); 
	}; 
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。 4
	window.onbeforeunload = function(){ 
		websocket.close();
	}; 
}); 
	function mysend(){ 
		var message = $("#message").val(); 
		$("#messages").append("<p><font color='blue'>"+"我："+message+"</font>"); 
		$.post('http://192.168.118.1:8080/god-server2.0/socketpush/sendMessage?sn=6d265d9cca474c79baf7be6f27034e9b',
				{message:message},function(){ 
					$("message").val("");
				},'text'); 
	} 
</script>

</html>
