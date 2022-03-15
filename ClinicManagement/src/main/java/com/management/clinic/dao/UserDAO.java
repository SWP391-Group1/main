package com.management.clinic.dao;

import com.management.clinic.entity.UserApp;

import java.util.List;

public interface UserDAO {

    UserApp findByUsernameAndPassword(String username, String password);

    UserApp findByUsername(String username);

    Long save(UserApp userApp);

    void update(UserApp userApp);

    UserApp findById(Long id);

    void changePassword(Long id, String password);

    List<UserApp> getAllUserMember();
}
