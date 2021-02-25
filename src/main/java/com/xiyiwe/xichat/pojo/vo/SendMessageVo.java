package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageVo implements Serializable {
    //消息内容
    String sendMessage;
    //发送人账号
    String senderAccount;
    //发送人姓名
    String senderName;
    //接收人账号
    String receiverAccount;
    //接收人姓名
    String receiverName;
    //文件链接
    String fileUrl;
    //文件名
    String fileName;
    //文件类型
    String fileType;
    //是否是群组消息
    String isGroup;
    //群组ID
    String groupId;
}
