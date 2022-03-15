package com.management.clinic.mapper;

import com.management.clinic.entity.RoleApp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<RoleApp> {
    @Override
    public RoleApp mapRow(ResultSet rs) {
        RoleApp roleApp = new RoleApp();
        try {
            roleApp.setId(rs.getLong("id"));
            roleApp.setName(rs.getString("name"));
            return roleApp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
