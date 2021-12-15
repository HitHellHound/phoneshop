package com.es.core.model.user;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginDto {
    @NotEmpty(message = "Login is required")
    private String login;
    @NotEmpty(message = "Password is required")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
