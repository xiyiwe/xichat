//package com.xiyiwe.xichat.common.base;
//
//
//import com.xiyiwe.xichat.common.constant.MessageCodeConstant;
//import com.xiyiwe.xichat.utils.string.StringUtil;
//
///**
// * 类名称：BaseResponse
// * 类描述：response返回数据基础类
// * 创建人：舒小龙
// * 创建时间：2017年5月4日 下午5:09:59
// * 修改人：舒小龙
// * 修改时间：2017年5月4日 下午5:09:59
// * 版本信息：@version 1.0
// * Copyright (c) 2017万雍科技（上海）有限公司-版权所有
// * 修改备注：
// * @version
// */
//public class BaseResponse {
//	private String rspCode;
//	private String rspMsg;
//	private String debuggerErrorMsg;
//
//	// **************************构造器***************************
//	public BaseResponse() {
//		this.rspCode = MessageCodeConstant.RTN_CODE_SUCCESS;
//		this.rspMsg = getRspMsg(rspCode);
//	}
//
//	public BaseResponse(String rspCode) {
//		this.rspCode = rspCode;
//		if (StringUtil.isEmpty(rspCode)) {
//			this.rspCode = MessageCodeConstant.RTN_CODE_SUCCESS;
//		}
//		this.rspMsg = getRspMsg(rspCode);
//	}
//
//	public BaseResponse(String rspCode, String rspMsg) {
//		this.rspCode = rspCode;
//		if (StringUtil.isEmpty(rspCode)) {
//			this.rspCode = MessageCodeConstant.RTN_CODE_SUCCESS;
//		}
//		if (StringUtil.isEmpty(rspMsg)) {
//			this.rspMsg = getRspMsg(rspCode);
//		} else {
//			this.rspMsg = rspMsg;
//		}
//	}
//
//
//	// ***********************rspCode转换成rspMsg工具方法**************************
//	/**
//	 * Function Name               getRspMsg
//	 * @param rspCode
//	 * @return
//	 * @description 获取message.properties中的rspCode对应的value
//	 * Modify History:              Date             Programmer       Notes
//	 *                            ---------        ---------------  ---------
//	 **********************************************************************
//	 */
//	private String getRspMsg(String rspCode) {
//		if (StringUtil.isEmpty(rspMsg)) {
//			this.rspMsg = MessageProperties.getKeyValue(rspCode);
//			return rspMsg;
//		} else {
//			return rspMsg;
//		}
//	}
//
//	public String getRspCode() {
//		return rspCode;
//	}
//
//	public void setRspCode(String rspCode) {
//		this.rspCode = rspCode;
//	}
//
//	public String getRspMsg() {
//		return rspMsg;
//	}
//
//	public void setRspMsg(String rspMsg) {
//		this.rspMsg = rspMsg;
//	}
//
//	public String getDebuggerErrorMsg() {
//		return debuggerErrorMsg;
//	}
//
//	public void setDebuggerErrorMsg(String debuggerErrorMsg) {
//		this.debuggerErrorMsg = debuggerErrorMsg;
//	}
//
//}
