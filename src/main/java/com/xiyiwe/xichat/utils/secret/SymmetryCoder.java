package com.xiyiwe.xichat.utils.secret;

import java.security.Key;

/**
 * 类名称：SymmetryCoder
 * 类描述：对称性加密算法接口
 */
public interface SymmetryCoder {

    /**
     * 生成密钥
     * @author luoyr
     * @date 2020-08-03 9:20
     * @return: String 密钥
     **/
    byte[] initKey() throws Exception;

    /**
     * 转换密钥
     * @param key 密钥
     * @author luoyr
     * @date 2020-08-03 9:20
     * @return: java.security.Key 密钥
     **/
    Key toKey(byte[] key) throws Exception;
}
