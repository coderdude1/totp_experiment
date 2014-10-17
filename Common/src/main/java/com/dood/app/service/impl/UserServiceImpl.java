package com.dood.app.service.impl;

import com.dood.app.dao.UserDao;
import com.dood.app.entities.User;
import com.dood.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {
    @Qualifier("userDao")//TODO why is this needed?
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteByUserId(Long id) {
        userDao.delete(id);
    }
}
