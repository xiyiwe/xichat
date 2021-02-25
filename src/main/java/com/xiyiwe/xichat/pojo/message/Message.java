package com.xiyiwe.xichat.pojo.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
public class Message implements Serializable {
    //消息ID
    @TableId
    String messageId;
    //消息内容
    String messageContent;
    //发送时间
    Date createTime;
    //发送人账号
    String senderAccount;
    //接收人账号
    String receiverAccount;
    //发送人姓名
    String senderName;
    //接收人姓名
    String receiverName;
    //文件链接
    String fileUrl;
    //是否已读
    String isRead;
    //文件名
    String fileName;
    //文件类型
    String fileType;
    //是否是群组消息
    String isGroup;
    //群组ID
    String groupId;
}
