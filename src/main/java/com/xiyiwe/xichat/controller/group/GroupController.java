package com.xiyiwe.xichat.controller.group;

import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.pojo.vo.GroupAndNotReadMessageCount;
import com.xiyiwe.xichat.pojo.vo.UserImgVo;
import com.xiyiwe.xichat.service.group.GroupService;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //添加群组
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
    //加入群组
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
    //分页查询与单个群组聊天历史记录
    @GetMapping("/group/getHistoryMessageWithGroupByPage/{groupId}/{page}")
    List<Message> getHistoryMessageWithGroupByPage(@PathVariable String groupId,@PathVariable Integer page, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getHistoryMessageWithGroupByPage(userAccount,groupId,(page-1)*10);
        }
        return null;
    }
    //获取单个群组聊天历史记录数量
    @GetMapping("/group/getGroupHistoryMessageCount/{groupId}")
    Integer getHistoryMessageCount(@PathVariable String groupId, HttpServletRequest request){
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return messageService.getHistoryMessageCount(userAccount,groupId);
        }
        return null;
    }
    //退出群组
    @RequestMapping("/group/quitGroup/{groupId}")
    void deleteFriend(@PathVariable String groupId,HttpServletRequest request)
    {
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            groupService.quitGroup(userAccount,groupId);
        }
    }
    //查出群组内所有人的信息
    @GetMapping("/group/getAllGroupMemberUserImg/{groupId}")
    Map<String,String> getAllGroupMemberUserImg(@PathVariable String groupId, HttpServletRequest request)
    {
        if(request.getHeader("Authorization")!=null){
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            return groupService.getAllGroupMemberUserImg(groupId);
        }
        return null;
    }
}
