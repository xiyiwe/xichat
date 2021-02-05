package com.xiyiwe.xichat.utils.secret;

/**
 * 类名称：SecretCoder
 * 类描述： 安全加密/解密接口类
 * 修改备注：
 */
public interface SecretCoder {

    /**
     * 解密
     * @param data
     * @param key
     * @author luoyr
     * @date 2020-08-03 15:05
     * @return: byte[]
     **/
    byte[] decrypt(byte[] data, byte[] key) throws Exception;

    /**
     * 加密
     * @param data
     * @param key
     * @author luoyr
     * @date 2020-08-03 15:05
     * @return: byte[]
     **/
    byte[] encrypt(byte[] data, byte[] key) throws Exception;
}
