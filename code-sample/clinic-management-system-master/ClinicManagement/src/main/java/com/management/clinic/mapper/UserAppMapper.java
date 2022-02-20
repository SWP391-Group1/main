package com.management.clinic.mapper;

import com.management.clinic.entity.UserApp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAppMapper implements RowMapper<UserApp> {
    @Override
    public UserApp mapRow(ResultSet rs) {
        UserApp userApp = new UserApp();
        try {
            userApp.setId(rs.getLong("id"));
            userApp.setCardId(rs.getString("card_id"));
            userApp.setFirstName(rs.getString("first_name"));
            userApp.setLastName(rs.getString("last_name"));
            userApp.setEmail(rs.getString("email"));
            userApp.setDob(rs.getDate("dob"));
            userApp.setAvatar(rs.getString("avatar"));
            userApp.setUsername(rs.getString("username"));
            userApp.setPassword(rs.getString("password"));
            userApp.setGender(rs.getString("gender"));
            userApp.setPhoneNumber(rs.getString("phone_number"));
            userApp.setStatus(rs.getBoolean("status"));
            userApp.setCreatedStamp(rs.getDate("created_stamp"));
            userApp.setModifiedStamp(rs.getDate("modified_stamp"));

//            RoleApp roleApp = new RoleApp();
//            roleApp.setId(rs.getLong("roleId"));
//            roleApp.setName(rs.getString("name"));

            return userApp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
