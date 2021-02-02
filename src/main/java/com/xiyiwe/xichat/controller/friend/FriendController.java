package com.xiyiwe.xichat.controller.friend;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.friend.ChatFriend;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FriendController {
    @Autowired
    ChatFriendMapper chatFriendMapper;
    @Autowired
    RedisService redisService;
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
    @GetMapping("/friend/getUserFriends")
    List<SimpleUserInfo> getUserFriends(HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return chatFriendMapper.selectUserFriendsByUserAccount(userAccount);
        }else{
            return null;
        }

    }
}
