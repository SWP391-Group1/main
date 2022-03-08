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
            medicalMethod.setImage(rs.getString("image"));
            //medicalMethod.setType(rs.getString("type"));
            medicalMethod.setCreatedStamp(rs.getDate("created_stamp"));
            medicalMethod.setCreatedStamp(rs.getDate("modified_stamp"));
            return medicalMethod;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
