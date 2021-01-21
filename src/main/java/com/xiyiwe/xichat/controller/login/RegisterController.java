package com.xiyiwe.xichat.controller.login;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.utils.secret.AesUtil;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class RegisterController {

    @Autowired
    UserMapper userMapper;
    @PostMapping("/register")
    @ResponseBody
    String register(@RequestBody User user) throws Exception {
        String userAccount = AesUtil.aesDecrypt(user.getUserAccount(),"1234567890ABCDEF");
        String password = AesUtil.aesDecrypt(user.getPassword(),"1234567890ABCDEF");
        if((userMapper.selectByUserAccount(userAccount))==null){
            User userAdd = new User();
            userAdd.setUserAccount(userAccount);
            userAdd.setPassword(DescUtil.encrypt(password));
            userAdd.setUserName(user.getUserName());
//            userAdd.setUserId(UUID.randomUUID().toString());
            userMapper.insert(userAdd);
            return "ok";
        }else{
            return "账号重复";
        }


    }
}
