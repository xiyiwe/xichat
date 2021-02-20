package com.xiyiwe.xichat.utils.encode;

import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.pojo.vo.SendMessageVo;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ServerDecoder implements Decoder.Text<SendMessageVo> {
    @Override
    public SendMessageVo decode(String s) throws DecodeException {
        System.out.println(s);
        SendMessageVo sendMessageVo = JSON.parseObject(s, SendMessageVo.class);
        return sendMessageVo;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
