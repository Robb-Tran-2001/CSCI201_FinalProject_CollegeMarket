package com.csci201.marketplace.pushnotif.websocket;

import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Configures the web socket connection between frontend and backend.
 * 
 * Helpful source: https://www.baeldung.com/websockets-spring
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	/**
	 * Defines prefix for frontend subscriptions and sets a prefix for MessageController, 
	 * respectively.
	 */
	@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
	
	/**
	 * Adds the push notification endpoint, so that frontend can send messages
	 * to the backend.
	 */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint("/push_notif");
         registry.addEndpoint("/push_notif").withSockJS();
    }
}
