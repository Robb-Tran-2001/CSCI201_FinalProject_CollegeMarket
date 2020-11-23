package com.csci201.marketplace.pushnotif.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.support.ExecutorSubscribableChannel;

import com.csci201.marketplace.pushnotif.model.BoughtMessage;


/**
 * Handles push notifications to the frontend.
 *
 * Helpful sources: 
 * - https://www.baeldung.com/websockets-spring
 * - https://stackoverflow.com/questions/28250719/how-to-send-message-to-client-through-websocket-using-spring
 */
@Controller
public class MessageController {
	
	@Autowired
    private SimpMessagingTemplate template;
		
	/**
	 * Testing method. Receives message from frontend and broadcasts it to 
	 * all subscribers.
	 */
	@MessageMapping("/push_notif")
	@SendTo("/topic/messages")
	public BoughtMessage send(BoughtMessage message) throws Exception {
	    return message;
	}
	
	/**
	 * Creates a BoughtMessage object and broadcasts it as JSON to all subscribers
	 * in the frontend.
	 */
	public void send(String user, String item) throws Exception {		
		BoughtMessage msg = new BoughtMessage();
		
		msg.setUser(user);
		msg.setItem(item);
		
		// Lets you know whether or not template autowired successfully.
		if (template == null) {
			System.out.println("Template is null");
		}
		
		this.template.convertAndSend("/topic/messages", msg);
	}
}
