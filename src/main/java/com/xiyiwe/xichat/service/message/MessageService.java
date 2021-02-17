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

    public void updateFriendNotReadMessage(String userAccount, String fUserAccount) {
        messageMapper.updateFriendNotReadMessage(userAccount, fUserAccount);
    }

    public List<Message> getFriendNotReadMessage(String userAccount, String fUserAccount) {
        return messageMapper.getFriendNotReadMessage(userAccount,fUserAccount);
    }

    public List<Message> getMessagesWithFriendByPage(String userAccount, String fUserAccount,Integer page) {
        return messageMapper.getMessagesWithFriendByPage(userAccount,fUserAccount,page);
    }

    public Integer getChatMessageCount(String userAccount, String fUserAccount) {
        return messageMapper.getChatMessageCount(userAccount,fUserAccount);
    }


//    public List<Message> selectAllNotReadMessage(String userAccount) {
//        List<Message> notReadMessage = messageMapper.selectAllNotReadMessage(userAccount);
//        return null;
//    }
}
