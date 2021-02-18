package com.xiyiwe.xichat.controller.friend;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.friend.ChatFriend;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.pojo.vo.FriendAndNotReadMessageCount;
import com.xiyiwe.xichat.service.friend.ChatFriendService;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FriendController {
    @Autowired
    ChatFriendService chatFriendService;
    @Autowired
    ChatFriendMapper chatFriendMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    MessageService messageService;
    @Autowired
    UserMapper userMapper;
    @PostMapping(value = "/friend/addfriend/{userAccount}", produces = "application/json;charset=UTF-8")
    Map<String, String> addFriend(@PathVariable String userAccount, HttpServletRequest request){
        User user = userMapper.selectByUserAccount(userAccount);
        Map<String, String> returnData = new HashMap<>();
        if(user==null){
            returnData.put("msg","不存在该用户账号");
            return returnData;
        }
        ChatFriend chatFriend = new ChatFriend();
        chatFriend.setUserAccount(redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount());
        chatFriend.setFUserAccount(userAccount);
        Timestamp t = new Timestamp(System.currentTimeMillis());
        chatFriend.setAddTime(t);
        try {
            int insert = chatFriendMapper.insertChatFriend(chatFriend);
            if (insert!=0){
                returnData.put("msg","ok");
            }else{
                returnData.put("msg","添加失败，已拥有该好友");
            }
            return returnData;
        }catch (Exception e){
            returnData.put("msg","添加失败，已拥有该好友");
            return returnData;
        }
    }
    //查询所有好友发来的未读消息数量和好友信息
    @GetMapping("/friend/getUserFriendsAndNotReadMessage")
    List<FriendAndNotReadMessageCount> getUserFriends(HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return chatFriendService.selectUserFriendsAndNotReadMessageByUserAccount(userAccount);
        }else{
            return null;
        }
    }
    //更新单个好友左侧未读信息数量
    @GetMapping("/friend/updateFriendNotReadMessage/{fUserAccount}")
    void getNotReadMessageFromFriend(@PathVariable String fUserAccount,HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            messageService.updateFriendNotReadMessage(userAccount,fUserAccount);
        }
    }
    //查询好友发来的具体未读消息
    @GetMapping("/friend/getFriendNotReadMessage/{fUserAccount}")
    List<Message> getFriendNotReadMessage(@PathVariable String fUserAccount, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getFriendNotReadMessage(userAccount,fUserAccount);
        }
        return null;
    }
    @GetMapping("/friend/getHistoryMessageWithFriendByPage/{fUserAccount}/{page}")
    List<Message> getHistoryMessageWithFriend(@PathVariable String fUserAccount,@PathVariable Integer page, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getMessagesWithFriendByPage(userAccount,fUserAccount,(page-1)*10);
        }
        return null;
    }
    @GetMapping("/friend/getHistoryMessageCount/{fUserAccount}")
    Integer getHistoryMessageCount(@PathVariable String fUserAccount, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getChatMessageCount(userAccount,fUserAccount);
        }
        return null;
    }
    @GetMapping("/friend/upload")
    Integer upload(@PathVariable String fUserAccount, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getChatMessageCount(userAccount,fUserAccount);
        }
        return null;
    }
}
