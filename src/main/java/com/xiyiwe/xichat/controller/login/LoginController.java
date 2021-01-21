package com.xiyiwe.xichat.controller.login;

import com.xiyiwe.xichat.config.annotation.DecodeParamter;
import com.xiyiwe.xichat.config.annotation.EncodeResult;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.login.LoginInfo;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.utils.secret.AESCoder;
import com.xiyiwe.xichat.utils.secret.AesUtil;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    String login(@RequestBody LoginInfo loginInfo) throws Exception {
        System.out.println(loginInfo);
//        System.out.println(AesUtil.aesDecrypt(loginInfo.getUserAccount(),"1234567890ABCDEF"));
//        System.out.println(AesUtil.aesDecrypt(loginInfo.getPassword(),"1234567890ABCDEF"));
        User rightUser = userMapper.selectByUserAccount(AesUtil.aesDecrypt(loginInfo.getUserAccount(), "1234567890ABCDEF"));
        if(rightUser!=null){
            if(DescUtil.decrypt(rightUser.getPassword()).equals(AesUtil.aesDecrypt(loginInfo.getPassword(),"1234567890ABCDEF"))){
                return "ok";
            }else{
                return "密码错误";
            }
        }else{
            return "用户名不存在";
        }
//        AESCoder aesCoder = new AESCoder();
//        aesCoder.decrypt(loginInfo.getUserAccount());
//        aesCoder.decrypt(loginInfo.getPassword());
    }
}
