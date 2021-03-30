package com.xiyiwe.xichat.config.ws;

import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.user.UserService;
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
@ServerEndpoint(value = "/chat/{userAccount}",decoders = ServerDecoder.class,encoders = ServerEncoder.class)
public class WebSocketEndPoint {
//    public static WebSocketEndPoint webSocketEndPoint;
//    public MessageService messageService =SpringContextUtil.getContext().getBean(MessageService.class);;
    public static MessageService messageService;
    public static UserService userService;
    @Autowired
    public MessageService messageService2;
    @PostConstruct
    public void init(){
        messageService = messageService2;
    }
    @Autowired
    public UserService userService2;
    @PostConstruct
    public void init1(){
        userService = userService2;
    }
    //建立连接接口
    @OnOpen
    public void onOpen(Session session, @PathParam("userAccount") String userAccount) {
        System.out.println(userAccount+"调用了onopen接口 ");
        SessionPool.sessions.put(userAccount,session);
        userService.updateUserStateOnline(userAccount);
//        List<Message> notReadMessage = messageService.selectAllNotReadMessage(userAccount);
//        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new ArrayList<>());
//        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
//        onlineUserList.add(userId);
//        sessionList.add(session);
//        // 发送上线通知
//        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
    }
    //关闭连接接口
    @OnClose
    public void onClose(Session session,@PathParam("userAccount") String userAccount) throws IOException {
        System.out.println("调用了OnClose");
        System.out.println(userAccount);
        userService.updateUserStateOffOnline(userAccount);
        SessionPool.close(session.getId());
        session.close();
        SessionPool.sessions.remove(userAccount);
    }
    //发送消息接口
    @OnMessage
    public void OnMessage(SendMessageVo message, Session session){
        System.out.println(message);
//        HashMap params = JSON.parseObject(message, HashMap.class);
        Message returnMessage = messageService.insertMessage(message);
        SessionPool.sendMessage(returnMessage);
    }


}
