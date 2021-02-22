package com.xiyiwe.xichat.service.message;

import com.xiyiwe.xichat.dao.message.MessageMapper;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

//    public Message insertMessage(HashMap<String,Object> params) {
    public Message insertMessage(SendMessageVo params) {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
//        message.setMessageContent((String) params.get("sendMessage"));
        message.setMessageContent(params.getSendMessage());
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        message.setReceiverAccount((String) params.get("receiverAccount"));
//        message.setReceiverName((String) params.get("receiverName"));
//        message.setSenderName((String) params.get("senderName"));
//        message.setSenderAccount((String) params.get("senderAccount"));
        message.setReceiverAccount((params.getReceiverAccount()));
        message.setReceiverName(params.getReceiverName());
        message.setSenderName(params.getSenderName());
        message.setSenderAccount(params.getSenderAccount());
        message.setIsRead("0");
        message.setFileUrl(params.getFileUrl());
        message.setFileName(params.getFileName());
        if (params.getFileType().contains("image")) {
            message.setFileType("image");
        }else{
            message.setFileType("file");
        }
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

//    public static void main(String[] args) {
//        saveFileFromBytes
//    }
    public static boolean saveFileFromBytes(byte[] b, String outputFile)
    {
        BufferedOutputStream stream = null;
        File file = null;
        try
        {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (stream != null)
            {
                try
                {
                    stream.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        return true;
    }

//    public Message insertMessageWithFile(SendMessageVo sendMessageVo) throws IOException {
//        System.out.println(sendMessageVo);
//        Message message = new Message();
//        message.setMessageId(UUID.randomUUID().toString());
//        message.setMessageContent(sendMessageVo.getSendMessage());
//        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        message.setReceiverAccount(sendMessageVo.getReceiverAccount());
//        message.setReceiverName(sendMessageVo.getReceiverName());
//        message.setSenderName(sendMessageVo.getSenderName());
//        message.setSenderAccount(sendMessageVo.getSenderAccount());
//        message.setIsRead("0");
//        MultipartFile file = sendMessageVo.getFileUrl();
//        System.out.println(file.getOriginalFilename());
//        String destination = "C:\\zyz\\tupian "+  file.getOriginalFilename()+"_"+UUID.randomUUID();
//        File file1 = new File(destination);
//        file.transferTo(file1);
//        message.setFileUrl(destination);
////        Session toUserSession = SessionPool.sessions.get(message.getReceiverAccount());
//            SessionPool.sendMessage(message);
//
//        return null;
//    }

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
//    public List<Message> selectAllNotReadMessage(String userAccount) {
//        List<Message> notReadMessage = messageMapper.selectAllNotReadMessage(userAccount);
//        return null;
//    }
}
