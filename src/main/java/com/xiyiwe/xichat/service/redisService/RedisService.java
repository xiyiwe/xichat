package com.xiyiwe.xichat.service.redisService;

import com.xiyiwe.xichat.pojo.user.SimpleUserInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    private  RedisTemplate<String,Object> redisTemplate;

    public final static Integer REDIS_LOGIN_TIMEOUT_120 = 120; //单位分钟，登录过期时间

    public void setUserInfo(String token, SimpleUserInfo simpleUserInfo) {
//        String jsonUserInfo = JSON.toJSONString(redisUserInfo);
//        System.out.println("存入redis的json数据："+jsonUserInfo);
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(token, simpleUserInfo, REDIS_LOGIN_TIMEOUT_120, TimeUnit.MINUTES);
    }

    public String initUserInfoToCache(SimpleUserInfo simpleUserInfo) {
        String token = UUID.randomUUID().toString();
        this.setUserInfo(token, simpleUserInfo);
        return token;
    }
    /**
     * @description 从redis缓存中获取用户的基础信息
     */
    public SimpleUserInfo getUserInfo(String token) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        Object userObj = vo.get(token);
        vo.set(token, userObj, REDIS_LOGIN_TIMEOUT_120, TimeUnit.MINUTES);
        if (null != userObj) {
            if (userObj instanceof SimpleUserInfo) {
                System.out.println("从redis中获取的用户信息："+userObj);
                return (SimpleUserInfo) userObj;
            }
        }
        return null;
    }


}
