package com.xiyiwe.xichat.controller.user;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.service.friend.ChatFriendService;
import com.xiyiwe.xichat.service.message.MessageService;
import com.xiyiwe.xichat.service.redisService.RedisService;
import com.xiyiwe.xichat.utils.secret.AesUtil;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
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
    @RequestMapping("/user/updateuser")
    @ResponseBody
    String updateUser(@RequestBody User user, HttpServletRequest request) throws Exception {
//        Map<String, String> returnData = new HashMap<>();
        try{
            String myAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
        }catch (Exception e) {
            System.out.println(e.toString());
//            returnData.put("msg","未登录");
            return "未登录";
        }
//        user.setUserName(simpleUserInfo.getUserName());
//        user.setUserAccount(simpleUserInfo.getUserAccount());
        if(user.getPassword()!=null&&!user.getPassword().equals("")){
            user.setPassword( DescUtil.encrypt(AesUtil.aesDecrypt( user.getPassword(),"1234567890ABCDEF")));
        }else{
            user.setPassword(null);
        }
        userMapper.updateById(user);
        return "ok";

    }
}
