package com.xiyiwe.xichat.pojo.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("userinfo")
public class User implements Serializable {
//    String userId;
    @TableId
    String userAccount;
    String userName;
    String password;
    String userImg;
}
