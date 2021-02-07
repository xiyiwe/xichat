package com.xiyiwe.xichat.dao.friend;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.friend.ChatFriend;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChatFriendMapper extends BaseMapper<ChatFriend> {
    Integer insertChatFriend( ChatFriend chatFriend);
    List<SimpleUserInfo> selectUserFriendsByUserAccount(String Account);
}