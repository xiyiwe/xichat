package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAndNotReadMessageCount {
    //群组ID
    String groupId;
    //群组名
    String groupName;
    //群组未读消息
    Integer notReadMessageCount;
}
