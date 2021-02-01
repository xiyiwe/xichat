package com.xiyiwe.xichat.pojo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleUserInfo implements Serializable {
    String userAccount;
    String userName;
}
