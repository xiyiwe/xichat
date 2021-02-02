package com.xiyiwe.xichat.pojo.group;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userGroup")
public class UserGroup {
    String groupId;
    String userId;
    String userGroupId;
}
