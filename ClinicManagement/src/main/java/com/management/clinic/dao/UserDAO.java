package com.management.clinic.dao;

import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;

import java.util.List;

public interface UserDAO {

    UserApp findByUsernameAndPassword(String username, String password);

    UserApp findByUsername(String username);

    Long save(UserApp userApp);

    void update(UserApp userApp);

    UserApp findById(Long id);

    RoleApp findByRoleId(Long roleId);

    RoleApp findByRoleName(String roleName);

    Long save(RoleApp roleApp);

    void changePassword(Long id, String password);

    List<UserApp> getAllUserMember();

    List<UserApp> findUserByRoleName(String roleName);

    List<UserApp> findUserByRoleAndCardID(String roleName, String cardId);
}
