package com.xiyiwe.xichat.utils.encode;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.pojo.message.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ServerEncoder implements Encoder.Text<Message>{

    @Override
    public String encode(Message message) throws EncodeException {
        return JSON.toJSONString(message);
        }


    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
