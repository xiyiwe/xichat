package com.xiyiwe.xichat.config.ws;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionPool {
    public static MessageService messageService;
    @Autowired
    public MessageService messageService2;
    @PostConstruct
    public void init(){
        messageService = messageService2;
    }
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

//        SendMessageVo sendMessage = new SendMessageVo();
//        sendMessage.setSendMessage(returnMessage.getMessageContent());
//        sendMessage.setSenderName(returnMessage.getSenderName());
//        sendMessage.setReceiverName(returnMessage.getReceiverName());
//        sendMessage.setReceiverAccount(returnMessage.getReceiverAccount());
//        sendMessage.setSenderAccount(returnMessage.getSenderAccount());
//        sendMessage.setCreateTime(returnMessage.getCreateTime());
//        sendMessage.setIsRead("0");
//        if(returnMessage.getFileUrl()!=null){
//            sendMessage.setFileUrl(returnMessage.getFileUrl());
//        }
        //TODO
        //fileUrl未set，为null处理
        Session toUserSession = sessions.get(returnMessage.getReceiverAccount());
        if(toUserSession != null){
            returnMessage.setIsRead("1");
            messageService.updateById(returnMessage);
            String toMessage = JSON.toJSONString(returnMessage);
//            toUserSession.getAsyncRemote().sendText(toMessage);
            toUserSession.getAsyncRemote().sendObject(returnMessage);
        }
//        for (String sessionId : SessionPool.sessions.keySet()){
//            sessions.get(sessionId).getAsyncRemote().sendText(message);
//        }
    }
}
