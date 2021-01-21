package com.xiyiwe.xichat.utils.secret.user;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类名称：SecretKeyUtil
 * 类描述： 密码工具类
 */
public class SecretKeyUtil {

    /**
     * 随机生成的长度为8位的密码
     * 包含字母，数字，符号
     * @return: java.lang.String
     **/
    public static String randomGenerator() {

        String[] letterArr = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] symbolArr = new String[]{"~", "!", "#", "%", "-", "+", "="};
        String[] numberArr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> numberList = new ArrayList<>(Arrays.asList(numberArr));
        //打乱集合顺序
        Collections.shuffle(numberList);
        StringBuffer buffer = new StringBuffer();
        int i = 0, j = 0, k = 0;
        while (i < 6) {
            buffer.append(letterArr[RandomUtil.randomInt(0, 52)]);
            i++;
        }
        while (j < 2) {
            buffer.append(symbolArr[RandomUtil.randomInt(0, 7)]);
            j++;
        }
        buffer.append(RandomUtil.randomNumbers(2));

        String[] orderNumber = new String[10];
        numberList.toArray(orderNumber);
        String[] chars = buffer.toString().split("");
        StringBuffer buffer1 = new StringBuffer();
        for (int l = 0; l < chars.length; l++) {
            buffer1.append(chars[Integer.parseInt(orderNumber[l])]);
        }
        return buffer1.toString();
    }

    /**
     * 加密摘要算法-非对称加密
     * HMac+MD5
     * @param key HMac算法加密使用的key
     * @param content 明文
     * @return: java.lang.String 密文
     **/
    public static String encryHMacMD5(String key, String content) {
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key.getBytes());
        return mac.digestHex(DigestUtil.md5Hex(content));
    }
}
