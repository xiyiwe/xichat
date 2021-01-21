package com.xiyiwe.xichat.utils.string;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * 项目名称：messageCenter
 * 类名称：StringUtil
 * 类描述：字符串,数组操作工具类
 * 创建人：朱仕稳
 * 创建时间：2017年7月18日 下午9:24:00
 * 修改人  朱仕稳
 * 修改时间：2017年7月18日 下午9:24:00
 * 版本信息：@version 1.0
 * Copyright (c) 2017 上海万雍科技股份有限公司-版权所有
 * 修改备注：
 */
public class StringUtil {

    public static final Pattern codeRegex = Pattern.compile("^[0-9a-zA-Z]{1,36}$");
    public static final Pattern parentcodeRegex = Pattern.compile("^[0-9a-zA-Z]{0,36}$");
    public static final Pattern nameRegex = Pattern.compile("^.{1,50}$");
    public static final Pattern telRegex = Pattern.compile("^(((0\\d{2,3}-)?\\d{7,8})|(1[3584]\\d{9}))$");
    public static final Pattern idRegex = Pattern.compile("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)");
    public static final Pattern emailRegex = Pattern.compile("^[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+$");
    public static final Pattern wechatRegex = Pattern.compile("^[a-zA-Z\\d_]{5,20}$");
    public static final Pattern qqRegex = Pattern.compile("^\\d{5,12}$");
    public static final Pattern mobileRegex = Pattern.compile("^.{0,20}$");
    public static final Pattern dingRegex = Pattern.compile("^[a-zA-Z\\d_]{3,64}$");
    public static final Pattern IMG_REGEX =  Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.gif|.GIF)$");
    public static final Pattern PHONE_NO_REGEX = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(19[0-9]{1}))+\\d{8})?$");

    public static boolean equals(String s1, String s2) {
        if (null == s1 && null == s2) {
            return true;
        } else if (null == s1 && null != s2) {
            return false;
        } else if (null != s1 && null == s2) {
            return false;
        } else if (s1.equals(s2)) {
            return true;
        } else {
            return false;
        }
    }

    public static final Pattern IMG_OFFICE_REGEX = Pattern.compile(".+(\\.jpeg|\\.jpg|\\.png|\\.gif|\\.doc|\\.docx|\\.xls|\\.xlsx|\\.ppt|\\.pdf)$");

    public static final Pattern IMG_OFFICE_TYPE_REGEX = Pattern.compile("^(jpeg|jpg|png|gif|doc|docx|xls|xlsx|ppt|pdf|zip)$");
    public static final Pattern IMG_TYPE_REGEX = Pattern.compile("^(jpeg|jpg|png|gif|JPEG|JPG|PNG|GIF)$");

    /**
     * 执行trim操作（包含全角和半角的空格;）
     *
     * @param str 字符串
     * @return 结果字符串
     */
    public static String trim(String str) {
        if (isBlank(str)) {//判断字符串是否为空或长度为零或是一段空格，上面有该函数代码
            return "";
        } else {
            str = str.trim();// 去掉半角符号的空格
            while (str.startsWith("　")) {//该空格为全角空格
                str = str.substring(1, str.length()).trim();
                if (isBlank(str)) {
                    return "";
                }
            }
            while (str.endsWith("　")) { // 该空格为全角空格
                str = str.substring(0, str.length() - 1).trim();
                if (isBlank(str)) {
                    return "";
                }
            }
            while (str.endsWith(" ")) { // 该空格为特殊空格
                str = str.substring(0, str.length() - 1).trim();
                if (isBlank(str)) {
                    return "";
                }
            }
        }
        return str;
    }

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(String str) {
        return !(str == null || "".equals(str));
    }

    /**
     * Function Name               listToTree
     *
     * @param sourceList
     * @param idKey
     * @param parentKey
     * @return
     * @description 树结构的list转成Map
     * Modify History:              Date             Programmer       Notes
     * ---------        ---------------  ---------
     * 2017年6月29日 上午11:45:42           田松林                     Initial
     * *********************************************************************
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Map> listToTree(List<Map<String, Object>> sourceList, String idKey,
                                       String parentKey) {
        Map<Object, Map> idKeyMap = new HashMap<Object, Map>();
        for (Map itemMap : sourceList) {
            idKeyMap.put(itemMap.get(idKey), itemMap);
        }

        List<Map> resultTreeList = new ArrayList<Map>();
        for (Map itemMap : sourceList) {
            // 没有父节点，表明是一级节点
            Object pid = itemMap.get(parentKey);
            itemMap.remove(parentKey);

            //不能注释
            if (idKeyMap.get(pid) == null) {
                pid = null;
            }

            if (pid == null || pid.equals(itemMap.get(idKey))) {
                resultTreeList.add(itemMap);
            } else {
                Map parant = idKeyMap.get(pid);
                if (parant == null) {
                    parant = new HashMap();
                    parant.put(idKey, pid);
                    idKeyMap.put(pid, parant);
                    resultTreeList.add(parant);
                }
                if (parant.get("children") == null) {
                    parant.put("children", new ArrayList());
                }
                List children = (List) parant.get("children");
                children.add(itemMap);
            }
        }

        return resultTreeList;
    }

    /**
     * Function Name               listToTree
     *
     * @param sourceList
     * @param idKey
     * @param parentKey
     * @return
     * @description 树结构的list转成Map
     * Modify History:              Date             Programmer       Notes
     * ---------        ---------------  ---------
     * 2017年6月29日 上午11:45:42           田松林                     Initial
     * *********************************************************************
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Map> listToTree(List<Map<String, Object>> sourceList, String idKey,
                                       String parentKey, String childrenStr) {
        Map<Object, Map> idKeyMap = new HashMap<Object, Map>();
        for (Map itemMap : sourceList) {
            idKeyMap.put(itemMap.get(idKey), itemMap);
        }

        List<Map> resultTreeList = new ArrayList<Map>();
        for (Map itemMap : sourceList) {
            // 没有父节点，表明是一级节点
            Object pid = itemMap.get(parentKey);
            itemMap.remove(parentKey);

            //不能注释
            if (idKeyMap.get(pid) == null) {
                pid = null;
            }

            if (pid == null || pid.equals(itemMap.get(idKey))) {
                resultTreeList.add(itemMap);
            } else {
                Map parant = idKeyMap.get(pid);
                if (parant == null) {
                    parant = new HashMap();
                    parant.put(idKey, pid);
                    idKeyMap.put(pid, parant);
                    resultTreeList.add(parant);
                }
                if (parant.get(childrenStr) == null) {
                    parant.put(childrenStr, new ArrayList());
                }
                List children = (List) parant.get(childrenStr);
                children.add(itemMap);
            }
        }

        return resultTreeList;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void removeSelfOrgTree(List<Map> tree, String orgCode) {

        for (int i = 0; i < tree.size(); i++) {
            Map item = tree.get(i);
            Object this_code = item.get("id");

            if (orgCode.equals(this_code)) {
                tree.remove(i);
            } else {
                List<Map> children = (List<Map>) item.get("children");
                if (children != null) {
                    removeSelfOrgTree(children, orgCode);
                }
            }

        }

    }

    public static boolean checkRegex(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }

    public static List<String> checkListRepeat(List<String> list) {
        List<String> exception = new ArrayList<String>();
        String temp = null;
        for (int i = 0; i < list.size() - 1; i++) {
            temp = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (temp.equals(list.get(j))) {
                    exception.add(temp);
                }
            }
        }
        return exception;
    }

    public static String getListStr(List<String> list) {
        String result = "";
        for (String obj : list) {
            result += "," + obj;
        }

        if (isEmpty(result)) {
            return "";
        } else {
            return result.substring(1);
        }
    }

    public static Set<String> strToList(String temp, String regex) {
        String[] arr = temp.split(regex);
        Set<String> list = new HashSet<String>();
        for (String str : arr) {
            list.add(str);
        }

        return list;
    }

    public static List<String> arrToList(String[] arr) {
        List<String> list = new ArrayList<String>();

        for (String str : arr) {
            list.add(str);
        }

        return list;
    }


    /**
     * 消息模板转换
     * @param content 转换的内容
     * @param convertValue 转换值
     * @param  expression 表达式
     * @return
     */
    public static String expressionConvert(String content, String convertValue, String expression) {
        if (StringUtils.isNotBlank(content) && StringUtils.isNotBlank(expression)) {
            return content.replace(expression, convertValue);
        }
        return "";
    }

}
