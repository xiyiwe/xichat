package com.xiyiwe.xichat.service.notify;

import com.xiyiwe.xichat.dao.notify.NotifyMapper;
import com.xiyiwe.xichat.pojo.notify.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiyiwe
 * @date 2021/3/28 - 19:32
 */
@Service
public class NotifyService {
    @Autowired
    NotifyMapper notifyMapper;

    public Integer insertNotify(Notify notify){
        Notify repeatNotify = notifyMapper.checkNotify(notify.getType(),notify.getSenderAccount(),notify.getReceiverAccount());
        return notifyMapper.insert(notify);
    }
}
