package com.management.clinic.service;

import com.management.clinic.entity.UserApp;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    UserApp singIn(String username, String password);

    UserApp signUp(UserApp userApp);

    UserApp changePassword(Long userId, String oldPassword, String newPassword) throws NotFoundException;

    UserApp updateInfo(UserApp userApp);

    UserApp getUserInfoFromRequest(HttpServletRequest req, UserApp userApp) throws Exception;

    UserApp findById(Long userId);

    List<UserApp> getAllUserMember();

    List<UserApp> getUserMember(String type);

    List<UserApp> getUserMemberByCardID(String type, String cardID);
}
