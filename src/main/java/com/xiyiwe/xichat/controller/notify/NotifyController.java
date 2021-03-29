package com.xiyiwe.xichat.controller.notify;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.notify.NotifyMapper;
import com.xiyiwe.xichat.pojo.friend.ChatFriend;
import com.xiyiwe.xichat.pojo.notify.Notify;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiyiwe
 * @date 2021/3/28 - 19:39
 */
@RestController
public class NotifyController {
    @Autowired
    NotifyMapper notifyMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    ChatFriendMapper chatFriendMapper;
    //处理好友请求
    @PostMapping("/notify/handleFriendInviteNotify")
    public void FriendNotify(HttpServletRequest request,@RequestBody Notify notify){
        String myAccount = "";
        if(request.getHeader("Authorization")!=null){
            myAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
        }
        notify.setIsConfirm(1);
        notifyMapper.updateById(notify);
        if(notify.getResult()==1){
            ChatFriend chatFriend = new ChatFriend();
            chatFriend.setUserAccount(myAccount);
            chatFriend.setFUserAccount(notify.getSenderAccount());
            Timestamp t = new Timestamp(System.currentTimeMillis());
            chatFriend.setAddTime(t);
            chatFriendMapper.insertChatFriend(chatFriend);
        }
    }
    //获得用户的所有事项
    @RequestMapping("/notify/getUserAllNotify")
    public List<Notify> FriendNotify(HttpServletRequest request){
        String myAccount = "";
        if(request.getHeader("Authorization")!=null){
            myAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
        }
        return notifyMapper.getUserAllNotify(myAccount);
    }
}
