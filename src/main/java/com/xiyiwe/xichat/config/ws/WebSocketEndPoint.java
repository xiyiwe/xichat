package com.xiyiwe.xichat.config.ws;

import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.utils.encode.ServerDecoder;
import com.xiyiwe.xichat.utils.encode.ServerEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@Component
@ServerEndpoint(value = "/friendsChat/{userAccount}",decoders = ServerDecoder.class,encoders = ServerEncoder.class)
public class WebSocketEndPoint {
//    public static WebSocketEndPoint webSocketEndPoint;
//    public MessageService messageService =SpringContextUtil.getContext().getBean(MessageService.class);;
    public static MessageService messageService;
    @Autowired
    public MessageService messageService2;
    @PostConstruct
    public void init(){
        messageService = messageService2;
    }
    @OnOpen
    public void onOpen(Session session, @PathParam("userAccount") String userAccount) {
        SessionPool.sessions.put(userAccount,session);
//        List<Message> notReadMessage = messageService.selectAllNotReadMessage(userAccount);
//        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new ArrayList<>());
//        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
//        onlineUserList.add(userId);
//        sessionList.add(session);
//        // 发送上线通知
//        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
    }
    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("调用了OnClose");
        SessionPool.close(session.getId());
        session.close();
    }
    @OnMessage
    public void OnMessage(SendMessageVo message, Session session){
        System.out.println(message);
//        HashMap params = JSON.parseObject(message, HashMap.class);
        Message returnMessage = messageService.insertMessage(message);
        SessionPool.sendMessage(returnMessage);
    }
//    @OnMessage
//    public void onMessage(Session session, byte[] message) {
//        messageService.saveFileFromBytes(message, "C:\\zyz\\tupian\\"+ UUID.randomUUID().toString());
//        System.out.println("收到2进制流");
//    }

}
