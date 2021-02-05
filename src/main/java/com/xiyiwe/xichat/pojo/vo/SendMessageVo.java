package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageVo {
    String sendMessage;
    String senderAccount;
    String senderName;
    String receiverAccount;
    String receiverName;
    Date createTime;
    String fileUrl;
}
