package com.xiyiwe.xichat.config.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 类名称：SecurityParamter
 * 类描述： 在形参上进行添加 结合 SecurityArgumentResolver 使用
 *   {@link com.vanysoft.config.web.handler.SecurityArgumentResolver}
 * 创建人：luoyr
 * 创建时间：2020-08-03 13:09
 * 修改人：
 * 修改时间：
 * 版本信息：@version 3.1.1
 * 修改备注：
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface DecodeParamter {
}
