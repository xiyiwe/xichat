package com.xiyiwe.xichat.config.ws;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;
import com.xiyiwe.xichat.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionPool {
    @Autowired
    MessageService messageService;
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
    public static void sendAllMessage( String message){
        for (String sessionId : SessionPool.sessions.keySet()){
            sessions.get(sessionId).getAsyncRemote().sendText(message);
        }
    }
    public static void sendMessage( Message returnMessage){

        SendMessageVo sendMessage = new SendMessageVo();
        sendMessage.setSendMessage(returnMessage.getMessageContent());
        sendMessage.setSenderName(returnMessage.getSenderName());
        sendMessage.setReceiverName(returnMessage.getReceiverName());
        sendMessage.setReceiverAccount(returnMessage.getReceiverAccount());
        sendMessage.setSenderAccount(returnMessage.getSenderAccount());
        sendMessage.setCreateTime(returnMessage.getCreateTime());
        if(returnMessage.getFileUrl()!=null){
            sendMessage.setFileUrl(returnMessage.getFileUrl());
        }
        //TODO
        //fileUrl未set
        Session toUserSession = sessions.get(sendMessage.getReceiverAccount());
        String toMessage = JSON.toJSONString(sendMessage);
        if(toUserSession != null){
            toUserSession.getAsyncRemote().sendText(toMessage);
        }
//        for (String sessionId : SessionPool.sessions.keySet()){
//            sessions.get(sessionId).getAsyncRemote().sendText(message);
//        }
    }
}
