package com.xiyiwe.xichat.pojo.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("userinfo")
public class User implements Serializable {
//    String userId;
    //用户账号
    @TableId
    String userAccount;
    //用户名
    String userName;
    //用户密码
    String password;
    //用户头像
    String userImg;
    //用户在线状态
    String state;
}
