package com.xiyiwe.xichat.controller.friend;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.notify.NotifyMapper;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.friend.ChatFriend;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.notify.Notify;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.pojo.vo.FriendAndNotReadMessageCount;
import com.xiyiwe.xichat.service.friend.ChatFriendService;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.notify.NotifyService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    NotifyMapper notifyMapper;
    //申请添加朋友
    @GetMapping(value = "/friend/addfriend/{userAccount}", produces = "application/json;charset=UTF-8")
    Map<String, String> addFriend(@PathVariable String userAccount, HttpServletRequest request){
        Map<String, String> returnData = new HashMap<>();
        String myAccount = "";
        if(request.getHeader("Authorization")!=null){
            myAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            if (myAccount.equals(userAccount)){
                returnData.put("msg","不能添加自己");
                return returnData;
            }
        }
        User user = userMapper.selectByUserAccount(userAccount);
        if(user==null){
            returnData.put("msg","不存在该用户账号");
            return returnData;
        }
        if(chatFriendMapper.checkFriend(myAccount,userAccount)!=null){
            returnData.put("msg","已添加过该好友");
            return returnData;
        }
        Notify notify = new Notify();
        notify.setContent("账号"+myAccount+"申请添加你为好友");
        Timestamp t = new Timestamp(System.currentTimeMillis());
        notify.setCreateTime(t);
        notify.setIsConfirm(0);
        notify.setNotifyId(UUID.randomUUID().toString());
        notify.setReceiverAccount(userAccount);
        notify.setSenderAccount(myAccount);
        notify.setType("friendInvite");
        if(notifyMapper.checkNotify(notify.getType(),notify.getSenderAccount(),notify.getReceiverAccount())!=null){
            returnData.put("msg","已发送过申请");
            return returnData;
        }else{
            notifyMapper.insert(notify);
            returnData.put("msg","申请成功");
            return returnData;
        }
//        ChatFriend chatFriend = new ChatFriend();
//        chatFriend.setUserAccount(redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount());
//        chatFriend.setFUserAccount(userAccount);
//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        chatFriend.setAddTime(t);
//        try {
//            int insert = chatFriendMapper.insertChatFriend(chatFriend);
//            if (insert!=0){
//                returnData.put("msg","ok");
//            }else{
//                returnData.put("msg","添加失败，已拥有该好友");
//            }
//            return returnData;
//        }catch (Exception e){
//            returnData.put("msg","添加失败，已拥有该好友");
//            return returnData;
//        }
    }
    //查询所有好友发来的未读消息数量和好友信息和在线状态
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
    //分页查询与单个好友聊天历史记录
    @GetMapping("/friend/getHistoryMessageWithFriendByPage/{fUserAccount}/{page}")
    List<Message> getHistoryMessageWithFriend(@PathVariable String fUserAccount,@PathVariable Integer page, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getMessagesWithFriendByPage(userAccount,fUserAccount,(page-1)*10);
        }
        return null;
    }
    //获取单个好友聊天历史记录数量
    @GetMapping("/friend/getHistoryMessageCount/{fUserAccount}")
    Integer getHistoryMessageCount(@PathVariable String fUserAccount, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getChatMessageCount(userAccount,fUserAccount);
        }
        return null;
    }
    //上传文件
    @RequestMapping(value = "/friend/uploadFile", method = RequestMethod.POST)
    String upload( @RequestParam("file") MultipartFile file) throws IOException {
            return messageService.uploadFile(file);
    }
    //删除好友
    @RequestMapping("/friend/deleteFriend/{fUserAccount}")
    void deleteFriend(@PathVariable String fUserAccount,HttpServletRequest request)
    {
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            chatFriendService.deleteFriend(userAccount,fUserAccount);
        }
    }
}
