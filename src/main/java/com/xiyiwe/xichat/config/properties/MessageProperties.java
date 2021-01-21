//package com.xiyiwe.xichat.config.properties;
//
//import com.xiyiwe.xichat.utils.exception.ExceptionUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * 类名称：MessageProperties
// * 类描述：获取message.properties中的数据值
// * 创建人：舒小龙
// * 创建时间：2017年5月10日 下午6:59:41
// * 修改人：舒小龙
// * 修改时间：2017年5月10日 下午6:59:41
// * 版本信息：@version 1.0
// * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
// * 修改备注：
// * @version
// */
//public class MessageProperties {
//	// TODO 缺陷,不支持多语言
//	// 属性文件的路径
//	static String profilepath = "i18n/message.properties";
//
//	private static final Logger log = LoggerFactory.getLogger(MessageProperties.class);
//
//    /**
//    * 采用静态方法
//    */
//    private static Properties props = new Properties();
//	static {
//		InputStream in = null;
//		try {
//			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(profilepath);
//			props.load(in);
//		} catch (FileNotFoundException e) {
//			log.error(ExceptionUtil.stackTraceToString(e));
//		} catch (IOException e) {
//			log.error(ExceptionUtil.stackTraceToString(e));
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				log.error(ExceptionUtil.stackTraceToString(e));
//			}
//		}
//	}
//
//    /**
//     * Function Name               getKeyValue
//     * @param key
//     * @return
//     * @description 获取key对应的属性值
//     * Modify History:              Date             Programmer       Notes
//     *                            ---------        ---------------  ---------
//     *                      2017年5月10日 下午6:55:21           舒小龙                     Initial
//     **********************************************************************
//     */
//    public static String getKeyValue(String key) {
//        return props.getProperty(key);
//    }
//
////    /**
////     * 根据主键key读取主键的值value
////     * @param filePath 属性文件路径
////     * @param key 键名
////     */
////     public static String readValue(String filePath, String key) {
////         Properties props = new Properties();
////         try {
////             InputStream in = new BufferedInputStream(new FileInputStream(
////                     filePath));
////             props.load(in);
////             String value = props.getProperty(key);
////             System.out.println(key +"键的值是："+ value);
////             return value;
////         } catch (Exception e) {
////             e.printStackTrace();
////             return null;
////         }
////     }
////
////     /**
////     * 更新（或插入）一对properties信息(主键及其键值)
////     * 如果该主键已经存在，更新该主键的值；
////     * 如果该主键不存在，则插件一对键值。
////     * @param keyname 键名
////     * @param keyvalue 键值
////     */
////     public static void writeProperties(String keyname,String keyvalue) {
////         try {
////             // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
////             // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
////             OutputStream fos = new FileOutputStream(profilepath);
////             props.setProperty(keyname, keyvalue);
////             // 以适合使用 load 方法加载到 Properties 表中的格式，
////             // 将此 Properties 表中的属性列表（键和元素对）写入输出流
////             props.store(fos, "Update '" + keyname + "' value");
////         } catch (IOException e) {
////             System.err.println("属性文件更新错误");
////         }
////     }
////
////     /**
////     * 更新properties文件的键值对
////     * 如果该主键已经存在，更新该主键的值；
////     * 如果该主键不存在，则插件一对键值。
////     * @param keyname 键名
////     * @param keyvalue 键值
////     */
////     public void updateProperties(String keyname,String keyvalue) {
////         try {
////             props.load(new FileInputStream(profilepath));
////             // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
////             // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
////             OutputStream fos = new FileOutputStream(profilepath);
////             props.setProperty(keyname, keyvalue);
////             // 以适合使用 load 方法加载到 Properties 表中的格式，
////             // 将此 Properties 表中的属性列表（键和元素对）写入输出流
////             props.store(fos, "Update '" + keyname + "' value");
////         } catch (IOException e) {
////             System.err.println("属性文件更新错误");
////         }
////     }
//}
