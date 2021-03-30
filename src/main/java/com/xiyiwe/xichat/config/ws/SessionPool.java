package com.xiyiwe.xichat.config.ws;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
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
        SessionPool.sessions.forEach((key,value)->{
            System.out.println(key);
            System.out.println(value);
        });
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
        System.err.println("调用了sendMessage");
        String friendOrGroup = messageService.isUserOrGroup(returnMessage.getReceiverAccount());
        if (friendOrGroup.equals("user")) {
            Session toUserSession = sessions.get(returnMessage.getReceiverAccount());
            if(toUserSession != null&&toUserSession.isOpen()){
                String toMessage = JSON.toJSONString(returnMessage);
                toUserSession.getAsyncRemote().sendObject(returnMessage);
                toUserSession = sessions.get(returnMessage.getSenderAccount());
                System.out.println("发送的数据："+returnMessage);
                toUserSession.getAsyncRemote().sendObject(returnMessage);
            }else {
                System.out.println("接收用户不在线，发送的数据："+returnMessage);
                toUserSession = sessions.get(returnMessage.getSenderAccount());
                toUserSession.getAsyncRemote().sendObject(returnMessage);
            }
        }else if (friendOrGroup.equals("group")){
            Session toUserSession = null;
            List<SimpleUserInfo> receiverList = messageService.getGroupUsersByGroupId(returnMessage.getReceiverAccount());
            for (SimpleUserInfo simpleUserInfo : receiverList) {
                toUserSession = sessions.get(simpleUserInfo.getUserAccount());
                if(toUserSession != null&&toUserSession.isOpen()){
//                    String toMessage = JSON.toJSONString(returnMessage);
                    toUserSession.getAsyncRemote().sendObject(returnMessage);
//                    toUserSession = sessions.get(returnMessage.getSenderAccount());
                    System.out.println("发送的数据："+returnMessage);
//                    toUserSession.getAsyncRemote().sendObject(returnMessage);
                }
//                else {
//                    System.out.println("接收用户不在线，发送的数据："+returnMessage);
//                    toUserSession = sessions.get(returnMessage.getSenderAccount());
//                    toUserSession.getAsyncRemote().sendObject(returnMessage);
//                }
            }

        }

    }
}
