package com.xiyiwe.xichat.dao.notify;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiyiwe.xichat.pojo.message.Message;
import com.xiyiwe.xichat.pojo.notify.Notify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyiwe
 * @date 2021/3/28 - 19:20
 */
@Repository
@Mapper
public interface NotifyMapper extends BaseMapper<Notify> {

    List<Notify> getUserAllNotify(@Param("userAccount") String userAccount);

    Notify checkNotify(@Param("type")String type,@Param("senderAccount")String senderAccount,@Param("receiverAccount")String receiverAccount);

}
