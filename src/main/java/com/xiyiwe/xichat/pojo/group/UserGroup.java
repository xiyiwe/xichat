package com.xiyiwe.xichat.pojo.group;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userGroup")
public class UserGroup {
    //群组ID
    String groupId;
    //用户账号
    String userAccount;
    //用户群组关系ID
    String userGroupId;
}
