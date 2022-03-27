package com.management.clinic.service.impl;

import com.management.clinic.constants.Constant;
import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.UserService;
import javassist.NotFoundException;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        if (userApp.getRoleId() != null) {
            RoleApp roleApp = userDAO.findByRoleId(userApp.getRoleId());
            if (roleApp != null) {
                userApp.setRoleApp(roleApp);
                userApp.setRoleName(roleApp.getName());
            }
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
        RoleApp rolePatient = userDAO.findByRoleName(Constant.ROLE_PATIENT);
        if (rolePatient == null) {
            Long id = userDAO.save(RoleApp.builder().name(Constant.ROLE_PATIENT).build());
            if (id != null) {
                rolePatient = userDAO.findByRoleId(id);
            }
        }
        if (rolePatient != null) {
            userApp.setRoleId(rolePatient.getId());
        }
        if (userDAO.findByUsername(userApp.getUsername()) == null) {
            Long id = userDAO.save(userApp);
            if (id != null) {
                userApp = userDAO.findById(id);
            }
            if (userApp.getRoleId() != null) {
                RoleApp roleApp = this.userDAO.findByRoleId(userApp.getRoleId());
                if (roleApp != null) {
                    userApp.setRoleName(roleApp.getName());
                }
            }
            return userApp;
        }
        return null;
    }

    @Override
    public UserApp getUserInfoFromRequest(HttpServletRequest req, UserApp userApp) throws Exception {
        String email = req.getParameter("txtEmail");
        String phone = req.getParameter("txtPhone");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String cardId = req.getParameter("cardId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        userApp.setEmail(email);

        userApp.setGender(gender);
        userApp.setPhoneNumber(phone);
        userApp.setCardId(cardId);
        userApp.setFirstName(firstName);
        userApp.setLastName(lastName);
        userApp.setStatus(true);
        userApp.setCreatedStamp(new Timestamp(new Date().getTime()));

        Date dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        userApp.setDob(dobDate);

        return userApp;
    }

    @Override
    public UserApp findById(Long userId) {
        return userDAO.findById(userId);
    }

    public List<UserApp> getAllUserMember() {
        return userDAO.getAllUserMember();
    }

    @Override
    public List<UserApp> getUserMember(String type) {
        if (!StringUtils.isBlank(type) && !type.equalsIgnoreCase("ALL")) {
            return userDAO.findUserByRoleName(type);
        }
        return this.getAllUserMember();
    }

    @Override
    public List<UserApp> getUserMemberByCardID(String type, String cardID) {
        if (!StringUtils.isBlank(cardID)) {
            return userDAO.findUserByRoleAndCardID(type, cardID);
        }
        return this.getUserMember(type);
    }
}
