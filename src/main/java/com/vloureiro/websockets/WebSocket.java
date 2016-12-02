package com.vloureiro.websockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.vloureiro.process.ProcessCountValue;
import com.vloureiro.process.ProcessResponse;

@ServerEndpoint("/websocket")
public class WebSocket {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public String onMessage(String message, Session session) {

		ProcessCountValue count = new ProcessCountValue();
		ProcessResponse response = new ProcessResponse();
		return response.processReponseMessage(count, session, clients);
	}

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("Client connected");

		ProcessCountValue count = new ProcessCountValue();
		try {
			session.getBasicRemote().sendText(count.toJsonString(count.returnAtualCountValue()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
	    clients.remove(session);
		System.out.println("Connection closed");
	}
}
