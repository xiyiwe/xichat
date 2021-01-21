package com.xiyiwe.xichat.web.handler;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.xiyiwe.xichat.config.annotation.DecodeParamter;
import com.xiyiwe.xichat.utils.secret.AESCoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * 类名称：SecurityHandlerMethodArgumentResolver
 * 类描述：接口参数拦截解密
 * 创建人：luoyr
 * 创建时间：2020-08-03 17:42
 * 修改人：
 * 修改时间：
 * 版本信息：@version 3.1.1
 * 修改备注：
 */
@Slf4j
public class SecurityArgumentResolver implements HandlerMethodArgumentResolver {

    private AESCoder aesCoder = AESCoder.getInstance();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(DecodeParamter.class)
                || parameter.hasParameterAnnotation(DecodeParamter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String[]> parameterMap =  webRequest.getParameterMap();

        if (parameterMap != null && parameterMap.size() > 0) {
            if (ObjectUtil.isBasicType(parameter.getParameterType().newInstance())
                    || String.class.isAssignableFrom(parameter.getParameterType())) {
                return decode(webRequest.getParameter(parameter.getParameterName()));
            }
            Object object = parameter.getParameterType().newInstance();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                try {
                    if (entry.getValue() == null || entry.getValue()[0] == null) {
                        continue;
                    }
                    BeanUtils.setProperty(object, entry.getKey(), decode(entry.getValue()[0]));
                } catch (Exception e) {
                    log.error("参数解密异常, 请求参数:{},", JSON.toJSONString(parameterMap), e);
                }
            }
            return object;
        }
        System.out.println("参数拦截" + JSON.toJSONString(parameterMap));
        return null;
    }

    public String decode(String content) throws Exception {
        String data = EscapeUtil.unescape(content);
        byte[] base64Bytes = Base64.decodeBase64(data.getBytes());
        byte[] aesBytes = aesCoder.decrypt(base64Bytes, aesCoder.defaultKey.getBytes());
        return new String(aesBytes);

    }
}
