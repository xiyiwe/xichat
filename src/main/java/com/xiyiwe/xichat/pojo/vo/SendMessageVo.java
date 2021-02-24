package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageVo implements Serializable {
    String sendMessage;
    String senderAccount;
    String senderName;
    String receiverAccount;
    String receiverName;
    String fileUrl;
    String fileName;
    String fileType;
    String isGroup;
    String groupId;
}
