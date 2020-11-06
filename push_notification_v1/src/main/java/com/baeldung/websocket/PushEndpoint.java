package com.baeldung.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.baeldung.model.Message;

@ServerEndpoint(value = "/push/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class PushEndpoint {
    private Session session;
    private static final Set<PushEndpoint> pushEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, Session> sessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {

        this.session = session;
        pushEndpoints.add(this);
        users.put(session.getId(), username);
        sessions.put(username, session);
        
        System.out.println("ASDFASDFASDFASDFASDFASDFASDFASDF");

//        Message message = new Message();
//        message.setFrom(username);
//        message.setMsg("Connected!");
//        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        //message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
    	pushEndpoints.remove(this);
//        Message message = new Message();
//        message.setFrom(users.get(session.getId()));
//        message.setMsg("Disconnected!");
//        broadcast(message);
    }
    
    public static void send_user_msg(String username, Message message) throws IOException, EncodeException {
    	sessions.get(username).getBasicRemote().sendObject(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    public static void broadcast(Message message) throws IOException, EncodeException {
    	pushEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                        .sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}