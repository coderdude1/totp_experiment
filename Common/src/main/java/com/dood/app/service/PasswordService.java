package com.dood.app.service;

public interface PasswordService {
    void changePassword(String oldPassword, String newPassword);

    boolean checkPassword(String password);
}
