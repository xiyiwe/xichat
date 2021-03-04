package com.xiyiwe.xichat.service.user;

import com.xiyiwe.xichat.dao.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public void updateUserStateOnline(String userAccount) {
        userMapper.updateUserStateOnline(userAccount);
    }

    public void updateUserStateOffOnline(String userAccount) {
        userMapper.updateUserStateOffOnline(userAccount);
    }
}
