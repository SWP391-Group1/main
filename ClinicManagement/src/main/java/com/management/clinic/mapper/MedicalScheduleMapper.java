package com.management.clinic.mapper;

import com.management.clinic.entity.MedicalSchedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalScheduleMapper implements RowMapper<MedicalSchedule> {
    @Override
    public MedicalSchedule mapRow(ResultSet rs) {
        MedicalSchedule medicalSchedule = new MedicalSchedule();
        try {
            medicalSchedule.setId(rs.getLong("id"));
            medicalSchedule.setSchedule(rs.getDate("schedule"));
            medicalSchedule.setDescription(rs.getString("description"));
            medicalSchedule.setType(rs.getString("type"));
            medicalSchedule.setCreatedStamp(rs.getDate("created_stamp"));
            medicalSchedule.setModifiedStamp(rs.getDate("modified_stamp"));
            medicalSchedule.setCreatedId(rs.getLong("created_id"));
            return medicalSchedule;
        } catch (SQLException e) {
            return null;
        }
    }
}
