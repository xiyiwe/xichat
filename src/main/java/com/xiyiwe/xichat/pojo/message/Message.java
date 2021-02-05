package com.xiyiwe.xichat.pojo.message;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
public class Message {
    String messageId;
    String messageContent;
    Date createTime;
    String senderAccount;
    String receiverAccount;
    String senderName;
    String receiverName;
    String imgUrl;
}
