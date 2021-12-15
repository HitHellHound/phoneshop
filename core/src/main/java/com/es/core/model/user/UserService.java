package com.es.core.model.user;

public interface UserService {
    UserRole login(String login, String password);
    void logout();
    UserRole getUserRole();
}
