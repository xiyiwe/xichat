package com.xiyiwe.xichat.dao.group;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.group.Group;
import com.xiyiwe.xichat.pojo.group.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {
    List<Group> selectAllGroupByUserAccount(String userAccount );


}
