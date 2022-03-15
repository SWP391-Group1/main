package com.management.clinic.service.impl;

import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.UserService;
import javassist.NotFoundException;

import javax.inject.Inject;
import java.util.List;

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
    public UserApp changePassword(Long userId, String oldPassword, String newPassword) throws NotFoundException {
        UserApp userApp = userDAO.findById(userId);
        if (userApp == null) {
            throw new NotFoundException("User not found");
        }
        if (!userApp.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Old password incorrect");
        }
        userDAO.changePassword(userApp.getId(), newPassword);
        return this.findById(userId);
    }

    @Override
    public UserApp updateInfo(UserApp userApp) {
        userDAO.update(userApp);
        return userDAO.findByUsername(userApp.getUsername());
    }

    @Override
    public UserApp signUp(UserApp userApp) {
        // Check: if username is already ==> Can not sign up
        if (userDAO.findByUsername(userApp.getUsername()) == null) {
            long id = userDAO.save(userApp);
            userApp=userDAO.findById(id);
            return userApp;
        }
        return null;
    }

    @Override
    public UserApp findById(Long userId) {
        return userDAO.findById(userId);
    }

    public List<UserApp> getAllUserMember(){
        return userDAO.getAllUserMember();
    }
}
