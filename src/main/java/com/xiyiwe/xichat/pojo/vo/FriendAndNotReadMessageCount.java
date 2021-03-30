package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendAndNotReadMessageCount {
    //用户账号
    String userAccount;
    //用户名
    String userName;
    String userImg;
    //用户在线状态
    String state;
    //未读消息数量
    Integer notReadMessageCount;

}
