package com.dood.app.service;

import com.dood.app.entities.User;

import java.util.List;

/**
 * Created by randy on 8/27/2014.
 */
public interface UserService {
    List<User> findAll();
    void createUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    void deleteByUserId(Long id);
}
