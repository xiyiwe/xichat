//package com.xiyiwe.xichat.web.handler;
//
//import com.alibaba.fastjson.JSON;
//
//import com.xiyiwe.xichat.config.annotation.EncodeResult;
//import com.xiyiwe.xichat.utils.secret.AESCoder;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.net.util.Base64;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//
///**
// * 类名称：SecurityHandlerMethodReturnValueHandler
// * 类描述：安全方法返回值加密
// *  对返回值为 JSONResponse对象中的属性 resultData 进行加密处理. {@link JSONResponse}
// * 创建人：luoyr
// * 创建时间：2020-08-04 15:38
// * 修改人：
// * 修改时间：
// * 版本信息：@version 3.1.1
// * 修改备注：
// */
//@Slf4j
//public class SecurityResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {
//
//    public SecurityResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
//        super(converters);
//    }
//
//    @Override
//    public boolean supportsReturnType(MethodParameter returnType) {
//        return returnType.getMethod().getAnnotation(EncodeResult.class) != null
//                || returnType.hasMethodAnnotation(EncodeResult.class);
//    }
//
//    @Override
//    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
//                                  NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException {
//        if (returnValue != null) {
//            if (returnValue instanceof JSONResponse) {
//                JSONResponse json = (JSONResponse) returnValue;
//                if (json.resultData != null) {
//                    String resultObj = JSON.toJSONString(json.resultData);
//                    AESCoder aesCoder = AESCoder.getInstance();
//                    byte[] encryptBytes = new byte[0];
//                    try {
//                        encryptBytes = aesCoder.encrypt(resultObj.getBytes("UTF-8"), aesCoder.defaultKey.getBytes());
//                    } catch (Exception e) {
//                        log.error("加密异常",e);
//                    }
//                    json.resultData = Base64.encodeBase64String(encryptBytes).replaceAll("\r\n","");
//                    try {
//                        BeanUtils.copyProperties(json, returnValue);
//                    } catch (IllegalAccessException e) {
//                        log.error("复制对象属性异常",e);
//                    } catch (InvocationTargetException e) {
//                        log.error("反射构造目标异常",e);
//                    }
//                    mavContainer.setRequestHandled(true);
//                }
//            }
//        }
//        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
//    }
//}
