package com.management.clinic.dao.impl;

import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.UserApp;
import com.management.clinic.mapper.UserAppMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<UserApp> implements UserDAO {

    @Override
    public UserApp findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username =? AND password =?";
        List<UserApp> userApps = query(sql, new UserAppMapper(), username, password);
        return CollectionUtils.isEmpty(userApps) ? null : userApps.get(0);
    }

    @Override
    public Long save(UserApp userApp) {
        String sql = "INSERT INTO user (first_name, last_name, card_id, gender, dob, phone_number, email, " +
                "username, password, status, created_stamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, userApp.getFirstName(), userApp.getLastName(), userApp.getCardId(), userApp.getGender(),
                userApp.getDob(), userApp.getPhoneNumber(), userApp.getEmail(), userApp.getUsername(),
                userApp.getPassword(), userApp.getStatus(), new Date());
    }

    @Override
    public void update(UserApp userApp) {
        String sql = "UPDATE user SET first_name=?, last_name=?, card_id=?, gender=?, dob=?, phone_number=?, " +
                "email=?, avatar=?, password=?, modified_stamp WHERE id =?";
        update(sql, sql, userApp.getFirstName(), userApp.getLastName(), userApp.getCardId(), userApp.getGender(),
                userApp.getDob(), userApp.getPhoneNumber(), userApp.getEmail(), userApp.getAvatar(),
                userApp.getPassword(), new Date(), userApp.getId());
    }

    @Override
    public UserApp findById(Long id) {
        String sql = "SELECT * FROM user WHERE id= ?";
        List<UserApp> userApps = query(sql, new UserAppMapper(), id);
        return CollectionUtils.isEmpty(userApps) ? null : userApps.get(0);
    }
}
