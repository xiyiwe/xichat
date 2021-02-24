package com.xiyiwe.xichat.controller.group;

import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.vo.GroupAndNotReadMessageCount;
import com.xiyiwe.xichat.service.group.GroupService;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    RedisService redisService;
    @Autowired
    MessageService messageService;

    @PostMapping("/group/addgroup/{groupName}")
    Map<String, String> addGroup(@PathVariable String groupName, HttpServletRequest request) {
        Map<String, String> returnData = new HashMap<>();
        String userAccount = "";
        try {
            userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
        } catch (Exception e) {
            returnData.put("msg", "请重新登录");
            return returnData;
        }
        groupService.insertGroup(groupName, userAccount);
        returnData.put("msg","ok");
        return returnData;
    }
    @PostMapping("/group/joingroup/{groupId}")
    Map<String, String> joingroup(@PathVariable String groupId, HttpServletRequest request){
        Map<String, String> returnData = new HashMap<>();
        String userAccount = "";
        try {
            userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
        } catch (Exception e) {
            returnData.put("msg", "请重新登录");
            return returnData;
        }
        groupService.joinGroup(groupId, userAccount);
        returnData.put("msg","ok");
        return returnData;
    }
//    @GetMapping("/group/getUserGroup")
//    Map<String, Object> getUserGroup(HttpServletRequest request){
//        Map<String, Object> returnData = new HashMap<>();
//        String userAccount = "";
//        try {
//            userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
//        } catch (Exception e) {
//            returnData.put("msg", "请重新登录");
//            return returnData;
//        }
//        List<Group> allGroup  = groupService.getAllGroupByUserAccount(userAccount);
//        returnData.put("allGroup",allGroup);
//        return returnData;
//    }

    //查询所有群组发来的未读消息数量和群组信息
    @GetMapping("/group/getUserGroupsAndNotReadMessageCount")
    List<GroupAndNotReadMessageCount> getUserGroupsAndNotReadMessage(HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return groupService.getUserGroupsAndNotReadMessageCount(userAccount);
        }else{
            return null;
        }
    }
    //更新单个群组左侧未读信息数量
    @GetMapping("/group/updateGroupNotReadMessage/{groupId}")
    void getNotReadMessageFromFriend(@PathVariable String groupId,HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            messageService.updateGroupNotReadMessage(userAccount,groupId);
        }
    }
    //查询群组发来的具体未读消息
    @GetMapping("/group/getGroupNotReadMessage/{groupId}")
    List<Message> getFriendNotReadMessage(@PathVariable String groupId, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getGroupNotReadMessage(userAccount,groupId);
        }
        return null;
    }
    @GetMapping("/group/getHistoryMessageWithGroupByPage/{groupId}/{page}")
    List<Message> getHistoryMessageWithGroupByPage(@PathVariable String groupId,@PathVariable Integer page, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getHistoryMessageWithGroupByPage(userAccount,groupId,(page-1)*10);
        }
        return null;
    }
    @GetMapping("/group/getGroupHistoryMessageCount/{groupId}")
    Integer getHistoryMessageCount(@PathVariable String groupId, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getHistoryMessageCount(userAccount,groupId);
        }
        return null;
    }
}
