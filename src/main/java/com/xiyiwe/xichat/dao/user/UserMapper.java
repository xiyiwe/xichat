package com.xiyiwe.xichat.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUserAccount(String userAccount);

    void updateUserStateOnline(@Param("userAccount") String userAccount);

    void updateUserStateOffOnline(@Param("userAccount") String userAccount);
}
