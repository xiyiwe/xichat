package com.xiyiwe.xichat.pojo.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
//    String userId;
    String userName;
    String userAccount;
    String password;
}
