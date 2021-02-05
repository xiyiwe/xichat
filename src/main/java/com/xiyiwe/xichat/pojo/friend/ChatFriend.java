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
    String userAccount;
    String fUserAccount;
    Date addTime;
}
