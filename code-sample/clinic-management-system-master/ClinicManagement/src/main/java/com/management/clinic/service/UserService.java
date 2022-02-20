package com.management.clinic.service;

import com.management.clinic.entity.UserApp;
import javassist.NotFoundException;

public interface UserService {
    UserApp singIn(String username, String password);

    UserApp signUp(UserApp userApp);

    void changePassword(Long userId, String oldPassword, String newPassword) throws NotFoundException;

    UserApp updateInfo(UserApp userApp);
}
