package com.xiyiwe.xichat.config.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 类名称：EncodeResult
 * 类描述：在方法上添加注解结合 SecurityResponseBodyMethodProcessor 使用
 *    {@link com.vanysoft.config.web.handler.SecurityResponseBodyMethodProcessor}
 * 创建人：luoyr
 * 创建时间：2020-08-04 16:52
 * 修改人：
 * 修改时间：
 * 版本信息：@version 3.1.1
 * 修改备注：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface EncodeResult {
}
