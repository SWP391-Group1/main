package com.management.clinic.mapper;

import com.management.clinic.entity.MedicalResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalResultMapper implements RowMapper<MedicalResult> {
    @Override
    public MedicalResult mapRow(ResultSet rs) {
        MedicalResult medicalResult = new MedicalResult();
        try {
            medicalResult.setId(rs.getLong("id"));
            medicalResult.setScheduleId(rs.getLong("schedule_id"));
            medicalResult.setPatientId(rs.getLong("patient_id"));
            medicalResult.setDoctorId(rs.getLong("doctor_id"));
            medicalResult.setName(rs.getString("name"));
            medicalResult.setDiagnosis(rs.getString("diagnosis"));
            medicalResult.setConclude(rs.getString("conclude"));
            medicalResult.setCreatedStamp(rs.getDate("created_stamp"));
            medicalResult.setModifiedStamp(rs.getDate("modified_stamp"));
            return medicalResult;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
