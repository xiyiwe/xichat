package com.xiyiwe.xichat.pojo.notify;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xiyiwe
 * @date 2021/3/28 - 19:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notify {
    @TableId
    String notifyId;
    String type;
    String content;
    //是否处理
    Integer isConfirm;
    //处理结果
    Integer result;
    String senderAccount;
    String senderName;
    String receiverAccount;
    String receiverName;
    Date createTime;
}
