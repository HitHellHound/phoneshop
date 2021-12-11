package com.es.core.model.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService{
    @Resource
    private User user;
    @Value("${admin.login}")
    private String adminLogin;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public UserRole login(String login, String password) {
        if (login.equals(adminLogin) && password.equals(adminPassword)){
            user.setRole(UserRole.admin);
        }
        return user.getRole();
    }

    @Override
    public void logout() {
        user.setRole(UserRole.visitor);
    }

    @Override
    public UserRole getUserRole() {
        return user.getRole();
    }
}
