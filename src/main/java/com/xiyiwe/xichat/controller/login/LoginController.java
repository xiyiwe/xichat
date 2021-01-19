package com.xiyiwe.xichat.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @PostMapping("/login")
    String login(String userAccount,String password){

        return "index";
    }
}
