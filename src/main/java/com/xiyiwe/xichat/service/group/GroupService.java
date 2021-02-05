package com.xiyiwe.xichat.service.group;

import com.xiyiwe.xichat.dao.group.GroupMapper;
import com.xiyiwe.xichat.dao.group.UserGroupMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import com.xiyiwe.xichat.pojo.group.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    UserGroupMapper userGroupMapper;

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
}
