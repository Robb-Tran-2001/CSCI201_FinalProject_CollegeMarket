package com.csci201.marketplace.pushnotif.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.support.ExecutorSubscribableChannel;

import com.csci201.marketplace.pushnotif.model.BoughtMessage;

@Controller
public class MessageController {
	
	@Autowired
    private SimpMessagingTemplate template;
		
	@MessageMapping("/push_notif")
	@SendTo("/topic/messages")
	public BoughtMessage send(BoughtMessage message) throws Exception {
	    return message;
	}
	
	//@MessageMapping("/push_notif")
	//@SendTo("/topic/messages")
	public void send(String user, String item) throws Exception {
		
		System.out.println("Enters the item sell push method");
		
		BoughtMessage msg = new BoughtMessage();
		
		msg.setUser(user);
		msg.setItem(item);
		
	    //return msg;
		
		if (template == null) {
			System.out.println("templage is null");
		}
		
		this.template.convertAndSend("/topic/messages", msg);
	}
}
