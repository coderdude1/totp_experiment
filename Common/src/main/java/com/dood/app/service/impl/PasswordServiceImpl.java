package com.dood.app.service.impl;

import com.dood.app.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Randy on 10/24/2014.
 */
public class PasswordServiceImpl implements PasswordService{
    private static final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean checkPassword(String password) {
        return false;
    }
}
