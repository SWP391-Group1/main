package com.management.clinic.service.impl;

import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.UserService;
import javassist.NotFoundException;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public UserApp singIn(String username, String password) {
        UserApp userApp = userDAO.findByUsernameAndPassword(username, password);
        if (userApp == null || !userApp.getStatus()) {
            return null;
        }
        return userApp;
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws NotFoundException {
        UserApp userApp = userDAO.findById(userId);
        if (userApp == null) {
            throw new NotFoundException("User not found");
        }
        if (!userApp.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Old password incorrect");
        }
        userApp.setPassword(newPassword);
    }

    @Override
    public UserApp updateInfo(UserApp userApp) {
        userDAO.update(userApp);
        return userApp;
    }

    @Override
    public UserApp signUp(UserApp userApp) {
        // Check: if username is already ==> Can not sign up
        long record = userDAO.save(userApp);
        userApp.setPassword("");
        if (record > 0) {
            return userApp;
        }
        return null;
    }
}
