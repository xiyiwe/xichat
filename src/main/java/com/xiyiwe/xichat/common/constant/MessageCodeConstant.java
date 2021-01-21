package com.xiyiwe.xichat.common.constant;

/**
 * 类名称：MessageCodeConstant
 * 类描述：系统固定消息
 * 创建人：舒小龙
 * 创建时间：2017年5月15日 上午10:45:48
 * 修改人：舒小龙
 * 修改时间：2017年5月15日 上午10:45:48
 * 版本信息：@version 1.0
 * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
 * 修改备注：
 */
public class MessageCodeConstant {

    /**
     * 成功
     */
    public static String RTN_CODE_SUCCESS = "000000";

    /**
     * 未知异常代码
     */
    public static String RTN_CODE_UNKNOW_ERROR = "999999";

    /**
     * 表单验证不通过
     */
    public static String RTN_CODE_VALIDATE = "RTN_CODE_VALIDATE";

    /**
     * 更新失败
     */
    public static String RTN_CODE_UPDATE_FAIL = "RTN_CODE_UPDATE_FAIL";

    /**
     * 参数校验错误
     */
    public static String RTN_CODE_PARAM_VALID_FAIL = "RTN_CODE_PARAM_VALID_FAIL";

    /**
     * 类名称：AOPConstant
     * 类描述：全局aop异常类型
     * 创建人：舒小龙
     * 创建时间：2017年7月3日 下午4:03:31
     * 修改人：舒小龙
     * 修改时间：2017年7月3日 下午4:03:31
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class AOPConstant {

        /**
         * Redis中BaseAPI列表为空，请效验系统配置！
         */
        public static String AOP_NO_BASEAPILIST = "AOP_NO_BASEAPILIST";

        /**
         * 请求未备份，请注册url请求
         */
        public static String AOP_URI_NO_RECORD = "AOP_URI_NO_RECORD";

        /**
         * url注册非法，请重新注册
         */
        public static String AOP_URI_ERROR_RECORD = "AOP_URI_ERROR_RECORD";

    }

    /**
     * 类名称：LoginConstant
     * 类描述：用户登录通用接口
     * 创建人：舒小龙
     * 创建时间：2017年6月5日 下午3:08:52
     * 修改人：舒小龙
     * 修改时间：2017年6月5日 下午3:08:52
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class LoginConstant {
        /**
         * 用户名或密码为空
         */
        public static String NAME_PWD_EMPTY = "LOGINCONSTANT_NAME_PWD_EMPTY";
        /**
         * 用户名或密码错误
         */
        public static String NAME_PWD_ERROR = "LOGINCONSTANT_NAME_PWD_ERROR";
        /**
         * 用户已经被禁用
         */
        public static String USER_STATUS_DISABLED = "LOGINCONSTANT_USER_STATUS_DISABLED";

        /**
         * 登录超时，请重新登录!
         */
        public static String LOGIN_TIME_OUT = "LOGINCONSTANT_LOGIN_TIME_OUT";

        /**
         * 用户不存在
         */
        public static String USER_NOT_EXIST = "USER_NOT_EXIST";
        /**
         * 单点登录验证未通过
         */
        public static String LOGIN_VALIDATE_ERROR = "LOGIN_VALIDATE_ERROR";
    }

    /**
     * 类名称：PermissionConstant
     * 类描述：登录权限验证-constant
     * 创建人：舒小龙
     * 创建时间：2017年5月15日 下午2:16:54
     * 修改人：舒小龙
     * 修改时间：2017年5月15日 下午2:16:54
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class PermissionConstant {

        /**
         * Controller层参数缺失HttpServletRequest
         */
        public static String FUNCTION_PARAMETER_ERROR = "PERMISSIONCONSTANT_FUNCTION_PARAMETER_ERROR";

        /**
         * 未登录系统，或登录过期
         */
        public static String SESSIONERROR = "PERMISSIONCONSTANT_SESSIONERROR";

        /**
         * 登录用户无操作接口权限
         */
        public static String USER_NOPERMISSION = "PERMISSIONCONSTANT_USER_NOPERMISSION";

    }

    /**
     * 类名称：OrgException
     * 类描述：组织机构异常代码
     * 创建人：TianSonly
     * 创建时间：2017年5月22日 下午4:28:29
     * 修改人：Tian_
     * 修改时间：2017年5月22日 下午4:28:29
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class OrgException {
        /**
         * 手动输入住址机构代码，重复验证
         */
        public static String EXIST_ERROR = "ORGERR_0001";
        /**
         * 机构代码空
         */
        public static String CODE_EMPTY = "ORGERR_0002";
        /**
         * 机构名称空
         */
        public static String NAME_EMPTY = "ORGERR_0003";
        /**
         * 机构下有子机构
         */
        public static String CHILD_EXIST_ERROR = "ORGERR_0004";
        /**
         * 机构下有人员
         */
        public static String PERSON_EXIST_ERROR = "ORGERR_0005";
        /**
         * 上移——已经到最上面了
         */
        public static String TOP_ALREADY_ERROR = "ORGERR_0006";
        /**
         * 下移——已经到最下面了
         */
        public static String BUTTOM_ALREADY_ERROR = "ORGERR_0007";
        /**
         * 组织结构和父机构
         */
        public static String PARENTCODE_EQUAL_ORGCODE = "ORGERR_0008";
        /**
         * 组织结构名称重复
         */
        public static String NAME_EXIST = "ORGERR_0009";
        /**
         * 组织机构不存在
         */
        public static String ORG_NOT_EXIST = "ORGERR_0010";
    }

    /**
     * 类名称：UserException
     * 类描述：用户相关异常代码
     * 创建人：朱仕稳
     * 创建时间：2017年5月31日 上午9:43:28
     * 修改人  朱仕稳
     * 修改时间：2017年5月31日 上午9:43:28
     * 版本信息：@version 1.0
     * Copyright (c) 2017 上海万雍科技股份有限公司-版权所有
     * 修改备注：
     */
    public static class UserException {

        /**
         * 新密码不能与原始密码重复
         */
        public static String RTN_CODE_PASSWORD_REPEAT = "PASSWORD_REPEAT";

        /**
         * 修改密码失败
         */
        public static String RTN_CODE_UPDATE_FAIL = "UPDATE_PASSWORD_FAIL";

        /**
         * 原始密码输入错误
         */
        public static String RTN_CODE_ORIGINAL_PASSWORD_ERROR = "ORIGINAL_PASSWORD_IMPORT_ERROR";
    }

    /**
     * 类名称：TeamException
     * 类描述：群组异常类
     * 创建人：TianSonly
     * 创建时间：2017年5月26日 下午1:41:38
     * 修改人：TianSonly
     * 修改时间：2017年5月26日 下午1:41:38
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class TeamException {
        /**
         * 添加群组时名称为空
         */
        public static String NAME_EMPTY = "TEAMERR_0001";
        /**
         * 群组名称重复
         */
        public static String NAME_EXIST = "TEAMERR_0002";
        /**
         * 群组下有人员
         */
        public static String USER_EXIST = "TEAMERR_0003";
    }

    /**
     * 类名称：ExcelException
     * 类描述：Excel导入导出异常
     * 创建人：TianSonly
     * 创建时间：2017年6月8日 上午11:54:59
     * 修改人：TianSonly
     * 修改时间：2017年6月8日 上午11:54:59
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class ExcelException {

        /**
         * 请选择一个群组
         */
        public static String TEAM_NULL_ERROR = "TEAM_NULL_ERROR";

        /**
         * 单元格值错误,值自定义，国际化文件没有
         */
        public static String CELL_ERROR = "EXCEL_CELLVALUE_ERROR";
        /**
         * 文件格式错误，选择xls或者xlsx格式的文件
         */
        public static String TYPE_ERROR = "EXCEL_TYPE_ERROR";
        /**
         * 导入时校验校验结果异常
         */
        public static String VALIDATION_RESULT = "EXCEL_VALIDATION_RESULT";
        /**
         * 文件下载失败
         */
        public static String DOWNLOAD_FAIL = "EXCEL_DOWNLOAD_FIAL";
        /**
         * 请选择一个外部分类
         */
        public static String TAG_NULL_ERROR = "TAG_NULL_ERROR";
        /**
         * 请选择一个组织机构
         */
        public static String ORG_NULL_ERROR = "ORG_NULL_ERROR";
    }

    /**
     * 项目名称：万雍移动门户
     * 类名称：UserAndRoleException
     * 类描述：用户和角色
     * 创建人：李明明
     * 创建时间：2017年6月5日 下午3:13:14
     * 修改人：李明明
     * 修改时间：2017年6月5日 下午3:13:14
     * 版本信息：@version 1.0
     * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
     * 修改备注：
     */
    public static class UserAndRoleException {
        /**
         * userCode存在
         */
        public static String RTN_CODE_USER_CODE = "USERANDROLE_USERCODE_EXIT";

        /**
         * userpassword
         */
        public static String RTN_CODE_USERPASSWORD_CODE = "USERANDROLE_USERPASSWORD_ERROR";

        /**
         * 子分类不可删除
         */
        public static String RTN_CODE_CHILDTAG_CODE = "USERANDROLE_CHILDTAG_01";

        /**
         * roleCode重复
         */
        public static String RTN_CODE_ROLE_CODE = "USERANDROLE_ROLE_01";
    }

    /**
     * @方法名称
     * @描述（证书验证异常验证码）
     * @参数
     * @返回值
     * @创建人 陆宏维
     * @创建时间 2018/8/1
     * @修改人和其它信息
     */
    public static class LicenConstant {
        /**
         * 证书参数错误
         */
        public static String LICEN_CODE_ERROR = "LICEN_CODE_ERROR";
        /**
         * 人数超限错误
         */
        public static String LICEN_OUT_USER = "LICEN_OUT_USER";

    }


    /**
     * 项目名称：messageCenter
     * 类名称：MsgException
     * 类描述：消息异常码
     * 创建人：朱仕稳
     * 创建时间：2017年8月16日 上午10:25:36
     * 修改人  朱仕稳
     * 修改时间：2017年8月16日 上午10:25:36
     * 版本信息：@version 1.0
     * Copyright (c) 2017 上海万雍科技股份有限公司-版权所有
     * 修改备注：
     */
    public static class MsgConstant {

        /**
         * 消息发送失败
         */
        public static String RTN_CODE_MSG_SENDMSG_FAIL = "MSG_SENDMSG_FAIL";


        //消息发送模式
        /**
         * 消息发送模式：普通模式
         */
        public static String MSG_SENDMODE_NORMAL = "normal";
        /**
         * 消息发送模式：全体教师
         */
        public static String MSG_SENDMODE_ALLTEACHER = "allTeacher";
        /**
         * 消息发送模式：全体学生
         */
        public static String MSG_SENDMODE_ALLSTUDENT = "allStudent";
        /**
         * 消息发送模式：全体人员
         */
        public static String MSG_SENDMODE_ALL = "all";

        //消息发送渠道
        /**
         * 消息发送渠道：短信
         */
        public static String MSG_SENDCHANNEL_SMS = "SMS";

        /**
         * 消息发送渠道：邮件
         */
        public static String MSG_SENDCHANNEL_MAIL = "Mail";

        /**
         * 消息发送渠道：微信
         */
        public static String MSG_SENDCHANNEL_WECHAT = "WeChat";

        /**
         * 消息发送渠道：钉钉
         */
        public static String MSG_SENDCHANNEL_DINGTALK = "DingTalk";

        /**
         * 消息发送渠道：智能模式
         */
        public static String MSG_SENDCHANNEL_INTELLIGENCEMODE = "IntelligenceMode";

        /**
         * 消息类型：图文
         */
        public static String MSG_SNEDMSGTYPE_NEW = "0";

        /**
         * 消息类型：文本
         */
        public static String MSG_SNEDMSGTYPE_TEXT = "1";

        /**
         * 消息类型：外链
         */
        public static String MSG_SNEDMSGTYPE_CHAIN = "2";


        //发送结果
        /**
         * 未发送
         */
        public static String MSG_SENDRESULT_NOTSEND = "0";

        /**
         * 已发送
         */
        public static String MSG_SENDRESULT_SENDED = "1";

        /**
         * 发送中(正在发送)
         */
        public static String MSG_SENDRESULT_SENDING = "2";


        //发送状态
        /**
         * 存为草稿
         */
        public static String MSG_SENDSTATUS_SAVEDRAFT = "0";

        /**
         * 实时发送
         */
        public static String MSG_SENDSTATUS_REALTIME = "1";

        /**
         * 定时发送
         */
        public static String MSG_SENDSTATUS_TIMING = "2";

        /**
         * 预览发送
         */
        public static String MSG_SENDSTATUS_PREVIEW = "3";

        //第三方接口调用返回结果
        /**
         * 参数校验不通过
         */
        public static String RTN_CODE_PARAMSCHECK_FAIL = "500001";


        /**
         * 应用编号或秘钥错误
         */
        public static String RTN_CODE_ERRORCOUNT = "500002";

        /**
         * token无效
         */
        public static String RTN_CODE_FAILTOKEN = "500003";

        /**
         * IP不在白名单范围内
         */
        public static String IP_NOTIN_WHITELIST = "500004";
        /**
         * 当前消息不存在，请进行确认！
         */
        public static String MESSAGE_NO_EXISTENCE = "500005";
        /**
         * 当前消息不属于待办消息!
         */
        public static String  MESSAGE_NO_NEED_DO = "500006";

        /**
         * 微信发送图文消息接口
         */
        public static String WECRAT_SEND_NEWS_API = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

        /**
         * 消息发送结果：失败
         */
        public static String MSG_SEND_RESULT_FAIL = "9";

        /**
         * 消息发送结果：成功
         */
        public static String MSG_SEND_RESULT_SUCCESS = "1";


    }

}
