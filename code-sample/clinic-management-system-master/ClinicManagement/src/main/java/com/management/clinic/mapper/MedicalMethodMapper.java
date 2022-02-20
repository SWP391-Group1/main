package com.management.clinic.mapper;

import com.management.clinic.entity.MedicalMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalMethodMapper implements RowMapper<MedicalMethod> {
    @Override
    public MedicalMethod mapRow(ResultSet rs) {
        MedicalMethod medicalMethod = new MedicalMethod();
        try {
            medicalMethod.setId(rs.getLong("id"));
            medicalMethod.setResultId(rs.getLong("result_id"));
            medicalMethod.setName(rs.getString("name"));
            medicalMethod.setDiagnosis(rs.getString("diagnosis"));
            medicalMethod.setConclude(rs.getString("conclude"));
            medicalMethod.setType(rs.getString("type"));
            medicalMethod.setCreatedStamp(rs.getDate("createdStamp"));
            medicalMethod.setCreatedStamp(rs.getDate("modifiedStamp"));
            return medicalMethod;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
