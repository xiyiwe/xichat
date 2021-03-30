package com.xiyiwe.xichat.pojo.friend;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("chat_friends")
@Accessors(chain=true)
public class ChatFriend {
//    @TableId(type = IdType.AUTO)
//    String id;
    //用户账号
    String userAccount;
    //好友账号
    String fUserAccount;
    //添加时间
    Date addTime;
}
