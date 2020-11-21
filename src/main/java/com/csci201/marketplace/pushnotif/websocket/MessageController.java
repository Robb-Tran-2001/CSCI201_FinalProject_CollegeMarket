package com.csci201.marketplace.pushnotif.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.csci201.marketplace.pushnotif.model.BoughtMessage;

@Controller
public class MessageController {
	
	@MessageMapping("/push_notif")
	@SendTo("/topic/messages")
	public BoughtMessage send(BoughtMessage message) throws Exception {
	    return message;
	}
	
	//@MessageMapping("/push_notif")
	@SendTo("/topic/messages")
	public static BoughtMessage send(String user, String item) throws Exception {
		BoughtMessage msg = new BoughtMessage();
		
		msg.setUser(user);
		msg.setItem(item);
		
	    return msg;
	}
}
