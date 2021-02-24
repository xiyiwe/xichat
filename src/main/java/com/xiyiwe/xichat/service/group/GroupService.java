package com.xiyiwe.xichat.service.group;

import com.xiyiwe.xichat.dao.group.GroupMapper;
import com.xiyiwe.xichat.dao.group.UserGroupMapper;
import com.xiyiwe.xichat.dao.message.MessageMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import com.xiyiwe.xichat.pojo.group.UserGroup;
import com.xiyiwe.xichat.pojo.vo.GroupAndNotReadMessageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    MessageMapper messageMapper;

    public void insertGroup(String groupName, String userAccount) {
        Group group = new Group();
        group.setGroupId(UUID.randomUUID().toString());
        group.setGroupName(groupName);
        groupMapper.insert(group);
        this.insertUserGroup(group.getGroupId(),userAccount);
    }
    public void insertUserGroup(String groupId, String userAccount){
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(groupId);
        userGroup.setUserGroupId(UUID.randomUUID().toString());
        userGroup.setUserAccount(userAccount);
        userGroupMapper.insert(userGroup);
    }

    public List<Group> getAllGroupByUserAccount(String userAccount) {
        return userGroupMapper.selectAllGroupByUserAccount(userAccount);
    }

    public List<GroupAndNotReadMessageCount> getUserGroupsAndNotReadMessageCount(String userAccount) {
        List<Group> groups = groupMapper.selectGroupsByUserAccount(userAccount);
        List<GroupAndNotReadMessageCount> GroupAndNotReadMessageCountList = new LinkedList<>();
        for (Group group : groups) {
            Integer notReadMessageCount =messageMapper.getGroupNotReadMessageCount(userAccount,group.getGroupId());
            GroupAndNotReadMessageCount groupAndNotReadMessageCount = new GroupAndNotReadMessageCount();
            groupAndNotReadMessageCount.setNotReadMessageCount(notReadMessageCount);
            groupAndNotReadMessageCount.setGroupId(group.getGroupId());
            groupAndNotReadMessageCount.setGroupName(group.getGroupName());
            GroupAndNotReadMessageCountList.add(groupAndNotReadMessageCount);
        }
        return GroupAndNotReadMessageCountList;
    }

    public void joinGroup(String groupId, String userAccount) {
        UserGroup userGroup = new UserGroup();
        userGroup.setUserGroupId(UUID.randomUUID().toString());
        userGroup.setUserAccount(userAccount);
        userGroup.setGroupId(groupId);
        userGroupMapper.insert(userGroup);
    }

}
