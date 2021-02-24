package com.xiyiwe.xichat.dao.message;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.message.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectPageFriendMessagesByUserAccount(@Param("senderAccount")String userAccount,@Param("receiverAccount") String fUserAccount, @Param("page")int page);

    List<Message> selectPageGroupMessagesByUserAccount(@Param("receiverAccount")String receiverAccount,@Param("groupId")String groupId, @Param("page")int page);

    void updateFriendNotReadMessage(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount);

    List<Message> getFriendNotReadMessage(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount);

    List<Message> getMessagesWithFriendByPage(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount,@Param("page") Integer page);

    List<Message> getMessagesWithGroupByPage(@Param("receiverAccount")String receiverAccount, @Param("groupId")String groupId,@Param("page") Integer page);

    Integer getChatMessageCount(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount);

    Integer getGroupMessageCount(@Param("receiverAccount") String receiverAccount, @Param("groupId") String groupId);

    List<Message> selectGroupNotReadMessage(@Param("receiverAccount")String receiverAccount, @Param("groupId") String groupId);

    Integer getFriendNotReadMessageCount(@Param("receiverAccount") String receiverAccount, @Param("senderAccount") String senderAccount);

    Integer getGroupNotReadMessageCount(@Param("receiverAccount") String receiverAccount, @Param("groupId") String groupId);

    void insertGroupMessage(List<Message> messageList);

    void updateGroupNotReadMessage(@Param("receiverAccount") String receiverAccount, @Param("groupId") String groupId);


}
