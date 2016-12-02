package com.vloureiro.process;

import java.io.IOException;
import java.util.Set;

import javax.websocket.Session;

public class ProcessResponse {

	public String processReponseMessage(ProcessCountValue count, Session session, Set<Session> clients) {

		String countValue = count.toJsonString(count.returnCount());
		// Broadcast count value to connected sessions
		for (Session client : clients) {
			if (!client.equals(session)) {
				try {
					client.getBasicRemote().sendText(countValue);
				} catch (IOException e) {
					clients.remove(this);
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}
		return countValue;
	}

}
