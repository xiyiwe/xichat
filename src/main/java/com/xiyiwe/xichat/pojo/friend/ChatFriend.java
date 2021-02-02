package com.xiyiwe.xichat.pojo.friend;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableField(value = "fuserid")
    String fUserAccount;
    Date addTime;
}
