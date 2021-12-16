package com.es.phoneshop.web.controller.pages;

import com.es.core.model.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/signIn")
public class LoginPageController {
    @Resource
    UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/userMiniStatus")
    public String getUserMiniStatus() {
        return "userMiniStatus";
    }
}
