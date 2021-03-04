package com.xiyiwe.xichat.service.message;

import com.xiyiwe.xichat.dao.group.GroupMapper;
import com.xiyiwe.xichat.dao.message.MessageMapper;
import com.xiyiwe.xichat.dao.user.UserMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;
import com.xiyiwe.xichat.utils.secret.desc.DescUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GroupMapper groupMapper;

    //    public Message insertMessage(HashMap<String,Object> params) {
    public Message insertMessage(SendMessageVo params) {
        Message message = new Message();

        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageContent(params.getSendMessage());
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        message.setReceiverAccount((params.getReceiverAccount()));
        message.setReceiverName(params.getReceiverName());
        message.setSenderName(params.getSenderName());
        message.setSenderAccount(params.getSenderAccount());
        message.setIsRead("0");
        message.setFileUrl(params.getFileUrl());
        message.setFileName(params.getFileName());
        if (params.getFileType().contains("image")) {
            message.setFileType("image");
        }  else if(!params.getFileUrl().equals("")){
            message.setFileType("file");
        }else{
            message.setFileType("");
        }
        message.setIsGroup("0");
    //如果是group信息
       if(params.getGroupId()!=null)
    {
        List<SimpleUserInfo> receiverList = this.getGroupUsersByGroupId(params.getGroupId());
        List<Message> messageList = new LinkedList<>();
        for (SimpleUserInfo simpleUserInfo : receiverList) {
            Message message1 = new Message();
            message1.setMessageId(UUID.randomUUID().toString());
            //加密信息
            message1.setMessageContent(DescUtil.encrypt(params.getSendMessage()));
            message1.setCreateTime(new Timestamp(System.currentTimeMillis()));
            message1.setSenderName(params.getSenderName());
            message1.setSenderAccount(params.getSenderAccount());
            message1.setIsRead("0");
            message1.setFileUrl(DescUtil.encrypt(params.getFileUrl()));
//            message1.setFileUrl(params.getFileUrl());
            message1.setFileName(params.getFileName());
            if (params.getFileType().contains("image")) {
                message1.setFileType("image");
            } else if(!params.getFileUrl().equals("")){
                message1.setFileType("file");
            }else{
                message1.setFileType("");
            }
            message1.setIsGroup("1");
            message1.setGroupId(params.getGroupId());
            message1.setReceiverAccount(simpleUserInfo.getUserAccount());
            message1.setReceiverName(simpleUserInfo.getUserName());
            messageList.add(message1);
        }
        messageMapper.insertGroupMessage(messageList);
    }else {
           message.setMessageContent(DescUtil.encrypt(params.getSendMessage()));
           message.setFileUrl(DescUtil.encrypt(params.getFileUrl()));
           messageMapper.insert(message);
           message.setMessageContent(params.getSendMessage());
           message.setFileUrl(params.getFileUrl());
           return message;
       }
       message.setIsGroup("1");
       message.setGroupId(params.getGroupId());
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
        List<Message> messagesList = messageMapper.getFriendNotReadMessage(userAccount, fUserAccount);
        messagesList.forEach((message)->{
            message.setMessageContent(DescUtil.decrypt(message.getMessageContent()));
            message.setFileUrl(DescUtil.decrypt(message.getFileUrl()));
        });
        return messagesList;
    }

    public List<Message> getMessagesWithFriendByPage(String userAccount, String fUserAccount,Integer page) {
        List<Message> messagesList = messageMapper.getMessagesWithFriendByPage(userAccount, fUserAccount, page);
        messagesList.forEach((message)->{
            message.setMessageContent(DescUtil.decrypt(message.getMessageContent()));
            message.setFileUrl(DescUtil.decrypt(message.getFileUrl()));
       });
//        for (Message message : messagesList) {
//            message.setMessageContent(DescUtil.decrypt(message.getMessageContent()));
//        }
        return messagesList;
    }

    public Integer getChatMessageCount(String userAccount, String fUserAccount) {
        return messageMapper.getChatMessageCount(userAccount,fUserAccount);
    }



    public String uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getContentType());
        String destination = "";
            if (file.getContentType().contains("image")){
                destination = "C:\\zyz\\biyesheji\\xichat\\xichat-vue\\xichat-vue\\public\\static\\images\\"+ file.getOriginalFilename();
            }
        else{
            destination = "C:\\zyz\\biyesheji\\xichat\\xichat-vue\\xichat-vue\\public\\static\\files\\"+ file.getOriginalFilename();
        }
        File file1 = new File(destination);
        file.transferTo(file1);
        return "上传成功";
    }
    public String isUserOrGroup(String receiverId){
        User user = userMapper.selectByUserAccount(receiverId);
        if(user!=null){
            return "user";
        }
        Group group = groupMapper.selectById(receiverId);
        if(group!=null){
            return "group";
        }
        return null;
    }

    public List<SimpleUserInfo> getGroupUsersByGroupId(String groupId) {
        List<SimpleUserInfo> users = groupMapper.getGroupUsersByGroupId(groupId);
        return users;
    }

    public List<Message> getGroupNotReadMessage(String userAccount, String groupId) {
        List<Message> messagesList = messageMapper.selectGroupNotReadMessage(userAccount, groupId);
        messagesList.forEach((message)->{
            message.setMessageContent(DescUtil.decrypt(message.getMessageContent()));
            message.setFileUrl(DescUtil.decrypt(message.getFileUrl()));
        });
        return messagesList;
    }

    public void updateGroupNotReadMessage(String userAccount, String groupId) {
        messageMapper.updateGroupNotReadMessage(userAccount, groupId);
    }

    public List<Message> getHistoryMessageWithGroupByPage(String userAccount, String groupId, int i) {
        List<Message> messagesList = messageMapper.getMessagesWithGroupByPage(userAccount, groupId, i);
        messagesList.forEach((message)->{
            message.setMessageContent(DescUtil.decrypt(message.getMessageContent()));
            message.setFileUrl(DescUtil.decrypt(message.getFileUrl()));
        });
        return messagesList;
    }

    public Integer getHistoryMessageCount(String userAccount, String groupId) {
        return messageMapper.getGroupMessageCount(userAccount,groupId);
    }

//    public List<Message> selectAllNotReadMessage(String userAccount) {
//        List<Message> notReadMessage = messageMapper.selectAllNotReadMessage(userAccount);
//        return null;
//    }
}
