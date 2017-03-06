package com.barexas.service.user;


import com.barexas.entities.CustomUser;

public interface UserService {
    CustomUser getUserByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
}
