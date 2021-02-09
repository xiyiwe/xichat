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

    void updateFriendNotReadMessage(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount);

    List<Message> getFriendNotReadMessage(@Param("receiverAccount") String receiverAccount, @Param("senderAccount")String senderAccount);
}
