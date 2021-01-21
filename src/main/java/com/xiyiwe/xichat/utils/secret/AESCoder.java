package com.xiyiwe.xichat.utils.secret;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * 类名称：AESCoder
 * 类描述：AES加密工具
 * 创建人：luoyr
 * 创建时间：2020-08-03 09:11
 * 修改人：
 * 修改时间：
 * 版本信息：@version 3.1.1
 * 修改备注：
 */
public class AESCoder implements SecretCoder, SymmetryCoder {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";
    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

//    public String defaultKey = "ig5hqwkWRPA4+a9A/ORR3vAtYRHLlHVa";
    public String defaultKey = "1234567890ABCDEF";
    public static boolean initialized;

    public AESCoder() {
    }

    public static AESCoder getInstance() {
        return new AESCoder();
    }

    @Override
    public byte[] initKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(256);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    @Override
    public Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    public static void main(String[] args) throws Exception {

        String aaa ="测试数据";
        AESCoder aesCoder = AESCoder.getInstance();
        byte[] keys = aesCoder.initKey();
        System.out.println("aa"+ keys.length);

        System.out.println("对称性密钥:\t" + Base64.encodeBase64String(aesCoder.defaultKey.getBytes()));

        byte[] data = aesCoder.encrypt(aaa.getBytes("UTF-8"), aesCoder.defaultKey.getBytes());
        System.out.println("加密密文:\t" + Base64.encodeBase64String(data));
        String result = new String(aesCoder.decrypt(data, aesCoder.defaultKey.getBytes()));
        System.out.println("解密密文:\t" +result);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        //Base64转码防止乱码
        return cipher.doFinal(data);
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        //Base64转码防止乱码
        return cipher.doFinal(data);
    }
}
