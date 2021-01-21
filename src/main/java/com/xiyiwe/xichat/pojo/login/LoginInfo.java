package com.xiyiwe.xichat.pojo.login;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 类名称：LoginInfo
 * 类描述：登录信息
 * 修改备注：
 */
@Data
public class LoginInfo {

    @NotBlank(message = "用户名为空")
    @Length(max = 64, message = "用户名长度错误")
//    @Pattern(regexp = "^([a-z]|[A-Z]|[0-9])([a-z]|[A-Z]|[0-9]|[\\.]|[-]|[_]|[@]){1,63}$", message = "登录名或登录密码不正确")
    private String userAccount;

    @NotBlank(message = "密码为空")
    @Length( max = 50, message = "密码长度错误")
    private String password;



}
