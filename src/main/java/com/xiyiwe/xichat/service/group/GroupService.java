package com.xiyiwe.xichat.service.group;

import com.xiyiwe.xichat.dao.group.GroupMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    GroupMapper groupMapper;

    public Integer insertGroup(String groupName, String userAccount) {
        Group group = new Group();
        group.setGroupId(UUID.randomUUID().toString());
        group.setGroupName(groupName);
        return groupMapper.insert(group);
    }
}
