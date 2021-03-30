package com.xiyiwe.xichat.controller.login;

import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.login.LoginInfo;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.service.redisService.RedisService;
import com.xiyiwe.xichat.service.user.UserService;
import com.xiyiwe.xichat.utils.secret.AesUtil;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    //登录
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    Map<String, String> login(@RequestBody LoginInfo loginInfo) throws Exception {
        System.out.println(loginInfo);
//        System.out.println(AesUtil.aesDecrypt(loginInfo.getUserAccount(),"1234567890ABCDEF"));
//        System.out.println(AesUtil.aesDecrypt(loginInfo.getPassword(),"1234567890ABCDEF"));
        String userAccount = AesUtil.aesDecrypt(loginInfo.getUserAccount(), "1234567890ABCDEF");
        User rightUser = userMapper.selectByUserAccount(userAccount);
        Map<String, String> returnData = new HashMap<String, String>();
        if (rightUser != null) {
            if (DescUtil.decrypt(rightUser.getPassword()).equals(AesUtil.aesDecrypt(loginInfo.getPassword(), "1234567890ABCDEF"))) {
                SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
                simpleUserInfo.setUserAccount(userAccount);
                simpleUserInfo.setUserName(rightUser.getUserName());
                String token = redisService.initUserInfoToCache(simpleUserInfo);
                returnData.put("token", token);
                returnData.put("msg", "ok");
                returnData.put("userName", rightUser.getUserName());
                returnData.put("userAccount", rightUser.getUserAccount());
                returnData.put("userImg", rightUser.getUserImg());
                return returnData;
            } else {
                returnData.put("msg", "密码错误");
                return returnData;
            }
        } else {
            returnData.put("msg", "用户名不存在");
            return returnData;
        }
//        AESCoder aesCoder = new AESCoder();
//        aesCoder.decrypt(loginInfo.getUserAccount());
//        aesCoder.decrypt(loginInfo.getPassword());
    }
    //登出
    @GetMapping("/logout")
    void logout(HttpServletRequest request) {
        if (request.getHeader("Authorization") != null) {
            String userAccount = redisService.getUserInfo(request.getHeader("Authorization")).getUserAccount();
            userService.updateUserStateOffOnline(userAccount);
            redisTemplate.delete(request.getHeader("Authorization"));
        }

    }

    @GetMapping("/testredis")
    void testMethod() {
        SimpleUserInfo simpleUserInfo = new SimpleUserInfo();
        simpleUserInfo.setUserName("朱彦喆");
        simpleUserInfo.setUserAccount("张三");
        redisService.setUserInfo("123", simpleUserInfo);
        redisService.getUserInfo("123");
    }
    //检查token
    @GetMapping("/checkToken")
    Map<String, String> checkToken(HttpServletRequest request) {
        Map<String, String> returnData = new HashMap<>();
        try {
            if (redisService.getUserInfo(request.getHeader("Authorization")) != null) {
                returnData.put("msg", "ok");
                return returnData;
            }else{
                returnData.put("msg", "请重新登录");
                return returnData;
            }
        } catch (Exception e) {
            returnData.put("msg", "请重新登录");
            return returnData;
        }
    }
}
