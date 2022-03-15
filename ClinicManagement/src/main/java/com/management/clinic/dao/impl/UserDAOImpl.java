package com.management.clinic.dao.impl;

import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.UserApp;
import com.management.clinic.mapper.UserAppMapper;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Timestamp;
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
    public UserApp findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username =?";
        List<UserApp> userApps = query(sql, new UserAppMapper(), username);
        return CollectionUtils.isEmpty(userApps) ? null : userApps.get(0);
    }

    @Override
    public Long save(UserApp userApp) {
        String sql = "INSERT INTO user (first_name, last_name, card_id, gender, dob, phone_number, avatar, email, username, password, status, created_stamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, userApp.getFirstName(), userApp.getLastName(), userApp.getCardId(), userApp.getGender(),
                userApp.getDob(), userApp.getPhoneNumber(), userApp.getAvatar(), userApp.getEmail(), userApp.getUsername(),
                userApp.getPassword(), userApp.getStatus(), new Date());
    }

    @Override
    public void update(UserApp userApp) {
        String sql = "UPDATE user SET first_name=?, last_name=?, card_id=?, gender=?, dob=?, phone_number=?, email=?, avatar=?, modified_stamp=? WHERE id =?";
        update(sql, userApp.getFirstName(), userApp.getLastName(), userApp.getCardId(), userApp.getGender(),
                userApp.getDob(), userApp.getPhoneNumber(), userApp.getEmail(), userApp.getAvatar(), new Timestamp(new Date().getTime()), userApp.getId());
    }

    @Override
    public UserApp findById(Long id) {
        String sql = "SELECT * FROM user WHERE id= ?";
        List<UserApp> userApps = query(sql, new UserAppMapper(), id);
        return CollectionUtils.isEmpty(userApps) ? null : userApps.get(0);
    }

    @Override
    public void changePassword(Long id, String password) {
        String sql = "UPDATE user SET password = ? WHERE id =?";
        update(sql, password, id);
    }

    public List<UserApp> getAllUserMember() {
        String sql = "SELECT * FROM user u JOIN role r ON u.role_id=r.id and r.name != 'ADMIN'";
        return query(sql, new UserAppMapper());
    }
}
