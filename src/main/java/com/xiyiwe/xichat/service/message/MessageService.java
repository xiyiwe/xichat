package com.xiyiwe.xichat.service.message;

import com.xiyiwe.xichat.dao.message.MessageMapper;
import com.xiyiwe.xichat.pojo.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Message insertMessage(HashMap<String,String> params) {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageContent(params.get("sendMessage"));
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        message.setReceiverAccount(params.get("receiverAccount"));
        message.setReceiverName(params.get("receiverName"));
        message.setSenderName(params.get("senderName"));
        message.setSenderAccount(params.get("senderAccount"));
        message.setIsRead("0");
        message.setFileUrl(params.get("fileUrl"));
        messageMapper.insert(message);
        return message;
    }

    public List<Message> selectPageFriendMessagesByUserAccount( String userAccount,String fUserAccount,int page){
        page=page*10;
        return messageMapper.selectPageFriendMessagesByUserAccount(userAccount,fUserAccount,page);
    }
    public int updateById(Message message){
        return messageMapper.updateById(message);
    }
}
