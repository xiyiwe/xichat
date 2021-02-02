package com.xiyiwe.xichat.controller.group;

import com.xiyiwe.xichat.service.group.GroupService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    RedisService redisService;

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
}
