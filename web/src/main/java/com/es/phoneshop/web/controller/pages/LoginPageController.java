package com.es.phoneshop.web.controller.pages;

import com.es.core.model.user.LoginDto;
import com.es.core.model.user.UserRole;
import com.es.core.model.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController {
    @Resource
    UserService userService;

    @GetMapping
    public String loginPage(Model model) {
        if (userService.getUserRole() != UserRole.visitor) {
            return "redirect:/productList";
        }
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping
    public String tryLogin(@ModelAttribute("loginDto") @Valid LoginDto loginDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "login";
        }
        UserRole userRole = userService.login(loginDto.getLogin(), loginDto.getPassword());
        if (userRole == UserRole.admin) {
            return "redirect:/admin/orders";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/productList";
    }

    @RequestMapping("/userMiniStatus")
    public String getUserMiniStatus(Model model) {
        model.addAttribute("role", userService.getUserRole());
        return "userMiniStatus";
    }
}
