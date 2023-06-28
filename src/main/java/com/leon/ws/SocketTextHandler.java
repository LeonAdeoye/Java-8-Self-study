package com.leon.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler
{
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
	{
		System.out.println("Received message: " + message.getPayload());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		System.out.println("Connection established");
		session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
		System.out.println("Sent message from server to client.");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception
	{
		System.out.println("Connection closed");
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception
	{
		System.out.println("Transport error");
	}

	@Override
	public boolean supportsPartialMessages()
	{
		return false;
	}
}
