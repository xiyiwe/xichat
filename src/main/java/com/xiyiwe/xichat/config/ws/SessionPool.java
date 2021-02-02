package com.xiyiwe.xichat.config.ws;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionPool {
    public static Map<String, Session> sessions = new ConcurrentHashMap<>();
    public static void close(String sessionId) throws IOException {
//        for (String userId : SessionPool.sessions.keySet()){
//            Session session = sessions.get(sessionId);
//            if(session.getId().equals(sessionId)){
//                sessions.remove(userId);
//                break;
//            }
//        }
        Session session = sessions.get(sessionId);
            if(session != null) {
                sessions.get(sessionId).close();
            }
    }
    public static void sendMessage(String sessionId , String message){
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }
    //全发
    public static void sendMessage( String message){
        for (String sessionId : SessionPool.sessions.keySet()){
            sessions.get(sessionId).getAsyncRemote().sendText(message);
        }
    }
}
