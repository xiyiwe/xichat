package com.xiyiwe.xichat.controller.login;

import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.utils.secret.AesUtil;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class RegisterController {

    @Autowired
    UserMapper userMapper;
    @PostMapping("/register")
    @ResponseBody
    String register(@RequestBody User user) throws Exception {
        String userAccount;
        String password;
        if (user.getUserAccount() != null && user.getUserName() != null && user.getPassword() != null) {
            userAccount = AesUtil.aesDecrypt(user.getUserAccount(), "1234567890ABCDEF");
            password = AesUtil.aesDecrypt(user.getPassword(), "1234567890ABCDEF");
        } else {
            return "注册失败，请检查相关信息";
        }
        userAccount = AesUtil.aesDecrypt(user.getUserAccount(), "1234567890ABCDEF");
        password = AesUtil.aesDecrypt(user.getPassword(), "1234567890ABCDEF");
        if ((userMapper.selectByUserAccount(userAccount)) == null) {
            User userAdd = new User();
            userAdd.setUserAccount(userAccount);
            userAdd.setPassword(DescUtil.encrypt(password));
            userAdd.setUserName(user.getUserName());
            try {
                String image = getImgFileToBase64("D:\\itemRepository\\gitRepository\\xichat-vue\\public\\static\\default\\defaultHeadImg.png","png");
                System.out.println(image);
                userAdd.setUserImg(image);
            } catch (Exception e) {
                System.out.println(e);
            }
            userAdd.setState("0");
//            userAdd.setUserId(UUID.randomUUID().toString());
            userMapper.insert(userAdd);
            return "ok";
        } else {
            return "账号重复";

        }
    }
//    public static String convertFileToBase64(String imgPath) {
//        byte[] data = null;
//        // 读取图片字节数组
//        try {
//            InputStream in = new FileInputStream(imgPath);
//            System.out.println("文件大小（字节）="+in.available());
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 对字节数组进行Base64编码，得到Base64编码的字符串
//        BASE64Encoder encoder = new BASE64Encoder();
//        String base64Str = encoder.encode(data);
//        System.out.println(base64Str);
//        return base64Str;
//    }
public static String getImgFileToBase64(String imgFile,String fileType) {
    //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    InputStream inputStream = null;
    byte[] buffer = null;
    //读取图片字节数组
    try {
        inputStream = new FileInputStream(imgFile);
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        buffer = new byte[count];
        inputStream.read(buffer);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (inputStream != null) {
            try {
                // 关闭inputStream流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 对字节数组Base64编码
    String encode = new BASE64Encoder().encode(buffer);
    encode = "data:" + fileType + ";base64,"+encode;
    return encode;
}

}
