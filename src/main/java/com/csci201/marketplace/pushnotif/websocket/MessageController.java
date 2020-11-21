package com.csci201.marketplace.pushnotif.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.csci201.marketplace.pushnotif.model.BoughtMessage;

@Controller
public class MessageController {
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public BoughtMessage send(BoughtMessage message) throws Exception {
	    return message;
	}
}
