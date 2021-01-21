package com.xiyiwe.xichat.utils.secret.user;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名称:  UserUtil
 * 类描述:用户工具类
 */
public class UserUtil {

    private static DES MOBILE_DES = SecureUtil.des("MOBIL2E_KE45Y_2_5G/23dv".getBytes());
    private static DES EMAIL_DES = SecureUtil.des("EMAIL1AS!23#CX_A".getBytes());

    /**
     * 加密密码
     * @param userId 用户ID
     * @param password
     * @return
     * @throws Exception
     */
    public static String encodePassword(String userId, String password) {
        return SecretKeyUtil.encryHMacMD5(userId.substring(0, 4), password);
    }

    /**
     * 生成加密密码
     * @param userId 用户ID
     * @return
     */
    public static String generateRandomEncodePassword(String userId) {
        // 系统生成一个8-16位密码并加密，密码包含字母数字字符
        String randomPassword = SecretKeyUtil.randomGenerator();
        return encodePassword(userId.substring(0, 4), randomPassword);
    }


    /**
     * 加密手机号
     * @param mobile 手机号码
     * @return
     */
    public static String encodeMobile(String mobile) {
        return MOBILE_DES.encryptBase64(mobile);
    }

    /**
     * 解密手机号
     * @param encodeMobile 加密手机号码
     * @return 解密手机号
     */
    public static String decodeMobile(String encodeMobile) {
        try {
            String phone = MOBILE_DES.decryptStrFromBase64(encodeMobile);
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            if(phone.length() != 11){
                return encodeMobile;
            }else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if(isMatch){
                    return phone;
//                    System.out.println("您的手机号" + phone + "是正确格式@——@");
                } else {
                    return encodeMobile;
//                    System.out.println("您的手机号" + phone + "是错误格式！！！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeMobile;
    }

    /**
     * 加密邮箱
     * @param email 邮箱
     * @return
     */
    public static String encodeEmail(String email) {
        return EMAIL_DES.encryptBase64(email);
    }

    /**
     * 解密邮箱
     * @param encodeEmail 加密邮箱
     * @return 解密手机号
     */
    public static String decodeEmail(String encodeEmail) {
        try {
            return EMAIL_DES.decryptStrFromBase64(encodeEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeEmail;
    }

    /**
     * 加密身份证号
     * @param idNo 身份证号码
     * @return
     */
    public static String encodeIdentificationNo(String idNo) {
        return SecureUtil.hmacMd5().digestHex(idNo);
    }






        public static void main(String[] args) {
            String phone = MOBILE_DES.decryptStrFromBase64("13123456789");
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            if(phone.length() != 11){
                System.out.println("手机号应为11位数");
            }else{
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if(isMatch){
                    System.out.println("您的手机号" + phone + "是正确格式@——@");
                } else {
                    System.out.println("您的手机号" + phone + "是错误格式！！！");
                }
            }

        }


}
