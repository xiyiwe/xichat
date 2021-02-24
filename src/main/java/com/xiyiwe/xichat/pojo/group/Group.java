package com.xiyiwe.xichat.pojo.group;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chatgroup")
public class Group {
    @TableId("groupId")
    String groupId;
    @TableField("groupName")
    String groupName;
}
