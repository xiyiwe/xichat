package com.xiyiwe.xichat.dao.group;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import com.xiyiwe.xichat.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GroupMapper extends BaseMapper<Group> {
    List<SimpleUserInfo> getGroupUsersByGroupId(@Param("groupId") String groupId);

    List<Group> selectGroupsByUserAccount(@Param("userAccount")String userAccount);

    List<User> selectGroupMemberInfo(@Param("groupId")String groupId);
}
