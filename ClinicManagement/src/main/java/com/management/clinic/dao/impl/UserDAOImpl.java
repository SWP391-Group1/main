package com.management.clinic.dao.impl;

import com.management.clinic.dao.UserDAO;
import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;
import com.management.clinic.mapper.RoleMapper;
import com.management.clinic.mapper.UserAppMapper;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<UserApp> implements UserDAO {

    @Override
    public List<UserApp> getAllDoctor() {
        String sql = "SELECT * FROM user WHERE role_id = '2'";
        return query(sql, new UserAppMapper());
    }

    @Override
    public UserApp findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user u join role r ON u.role_id=r.id  WHERE username =? AND password =? ";
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
        String sql = "INSERT INTO user (role_id, first_name, last_name, card_id, gender, dob, phone_number, avatar, email, " +
                "username, password, status, created_stamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, userApp.getRoleId(), userApp.getFirstName(), userApp.getLastName(), userApp.getCardId(), userApp.getGender(),
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
    public RoleApp findByRoleId(Long roleId) {
        String sql = "Select * FROM role WHERE id = ?";
        List<RoleApp> roleApps = query(sql, new RoleMapper(), roleId);
        return CollectionUtils.isEmpty(roleApps) ? null : roleApps.get(0);
    }

    @Override
    public RoleApp findByRoleName(String roleName) {
        String sql = "Select * FROM role WHERE name = ?";
        List<RoleApp> roleApps = query(sql, new RoleMapper(), roleName);
        return CollectionUtils.isEmpty(roleApps) ? null : roleApps.get(0);
    }

    @Override
    public Long save(RoleApp roleApp) {
        String sql = "INSERT INTO role (name) VALUES (?)";
        return insert(sql, roleApp.getName());
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

    @Override
    public List<UserApp> findUserByRoleName(String roleName) {
        String sql = "SELECT * FROM user u JOIN role r ON u.role_id=r.id and r.name = ?";
        return query(sql, new UserAppMapper(), roleName);
    }

    @Override
    public List<UserApp> findUserByRoleAndCardID(String roleName, String cardId) {
        String sql = "SELECT * FROM user u JOIN role r ON u.role_id=r.id AND r.name = ? AND u.card_id = ?";
        return query(sql, new UserAppMapper(), roleName, cardId);
    }
}
