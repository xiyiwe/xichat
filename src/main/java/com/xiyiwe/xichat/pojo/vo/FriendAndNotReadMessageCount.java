package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendAndNotReadMessageCount {
    String userAccount;
    String userName;
    String state;
    Integer notReadMessageCount;

}
