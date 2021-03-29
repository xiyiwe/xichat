package com.xiyiwe.xichat.service.friend;

import com.xiyiwe.xichat.dao.friend.ChatFriendMapper;
import com.xiyiwe.xichat.dao.message.MessageMapper;
import com.xiyiwe.xichat.pojo.user.User;
import com.xiyiwe.xichat.pojo.vo.FriendAndNotReadMessageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ChatFriendService {
    @Autowired
    ChatFriendMapper chatFriendMapper;
    @Autowired
    MessageMapper messageMapper;

    public List<FriendAndNotReadMessageCount> selectUserFriendsAndNotReadMessageByUserAccount(String userAccount) {
        List<User> simpleUserInfos = chatFriendMapper.selectUserFriendsByUserAccount(userAccount);
        List<FriendAndNotReadMessageCount> friendAndNotReadMessageCountList = new LinkedList<>();

        for (User simpleUserInfo : simpleUserInfos){
            Integer notReadMessageCount = messageMapper.getFriendNotReadMessageCount(userAccount,simpleUserInfo.getUserAccount());
            FriendAndNotReadMessageCount friendAndNotReadMessageCount = new FriendAndNotReadMessageCount();
            friendAndNotReadMessageCount.setNotReadMessageCount(notReadMessageCount);
            friendAndNotReadMessageCount.setUserAccount(simpleUserInfo.getUserAccount());
            friendAndNotReadMessageCount.setUserName(simpleUserInfo.getUserName());
            friendAndNotReadMessageCount.setState(simpleUserInfo.getState());
            friendAndNotReadMessageCount.setUserImg(simpleUserInfo.getUserImg());
            friendAndNotReadMessageCountList.add(friendAndNotReadMessageCount);
        }
        return friendAndNotReadMessageCountList;
    }

    public void deleteFriend(String userAccount, String fUserAccount) {
        chatFriendMapper.deleteFriend(userAccount,fUserAccount);
        chatFriendMapper.deleteFriend(fUserAccount,userAccount);
    }
}
