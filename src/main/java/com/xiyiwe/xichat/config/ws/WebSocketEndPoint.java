package com.xiyiwe.xichat.config.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@Component
@ServerEndpoint("/friendsChat/{userAccount}")
public class WebSocketEndPoint {
    @OnOpen
    public void onOpen(Session session, @PathParam("userAccount") String userAccount) {
        SessionPool.sessions.put(userAccount,session);
//        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new ArrayList<>());
//        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
//        onlineUserList.add(userId);
//        sessionList.add(session);
//        // 发送上线通知
//        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
    }
    @OnClose
    public void onClose(Session session) throws IOException {
        SessionPool.close(session.getId());
        session.close();
    }
    @OnMessage
    public void OnMessage(String message,Session session){
        SessionPool.sendMessage(message);
    }

}
