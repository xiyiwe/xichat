package com.xiyiwe.xichat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAndNotReadMessageCount {
    String groupId;
    String groupName;
    Integer notReadMessageCount;
}
