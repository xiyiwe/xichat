//package com.xiyiwe.xichat.common.base;
//
///**
// * 类名称：JSONResponse
// * 类描述：标准json数据格式装配
// * 修改备注：
// * @version
// */
//public class JSONResponse extends BaseResponse {
//
//	public Object resultData;
//	/**
//	 * 参数有误
//	 */
//	public static String RTN_CODE_ERROR = "000000";
//
//	public JSONResponse() {
//		super();
//	}
//
//	public JSONResponse(String rspCode) {
//		super(rspCode);
//	}
//
//	public JSONResponse(String rspCode, String rspMsg) {
//		super(rspCode, rspMsg);
//	}
//
//	public JSONResponse(Object resultData) {
//		super();
//		this.resultData = resultData;
//	}
//
//	public JSONResponse(String rspCode, Object resultData) {
//		super(rspCode);
//		this.resultData = resultData;
//	}
//
//	public JSONResponse(String rspCode, String rspMsg, Object resultData) {
//		super(rspCode, rspMsg);
//		this.resultData = resultData;
//	}
//
//}
