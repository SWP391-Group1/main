package com.management.clinic.dao.impl;

import com.management.clinic.dao.MedicalResultDAO;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.mapper.MedicalResultMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class MedicalResultDAOImpl extends AbstractDAO<MedicalResult> implements MedicalResultDAO {

    @Override
    public Long save(MedicalResult medicalResult) {
        String sql = "INSERT INTO medical_result (schedule_id, patient_id, doctor_id, name, " +
                "diagnosis, conclude, created_stamp VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, medicalResult.getScheduleId(), medicalResult.getPatientId(),
                medicalResult.getDoctorId(), medicalResult.getName(), medicalResult.getDiagnosis(),
                medicalResult.getConclude(), new Date());
    }

    @Override
    public MedicalResult update(MedicalResult medicalResult) {
        String sql = "UPDATE medical_result SET schedule_id=?, patient_id=?, doctor_id=?, name = ?, diagnosis= ?, " +
                "conclude = ?, modifiedStamp=? WHERE id =?";
        update(sql, medicalResult.getScheduleId(), medicalResult.getPatientId(),
                medicalResult.getDoctorId(), medicalResult.getName(), medicalResult.getDiagnosis(),
                medicalResult.getConclude(), new Date());
        return medicalResult;
    }

    @Override
    public MedicalResult findById(Long id) {
        String sql = "SELECT * FROM medical_result WHERE id= ?";
        List<MedicalResult> medicalResults = query(sql, new MedicalResultMapper(), id);
        return CollectionUtils.isEmpty(medicalResults) ? null : medicalResults.get(0);
    }

    @Override
    public boolean delete(Long id) {
        try {
            String sql = "DELETE FROM medical_result WHERE id = ?";
            update(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MedicalResult> findAll() {
        String sql = "SELECT * FROM medical_result";
        return query(sql, new MedicalResultMapper());
    }

    @Override
    public MedicalResult findByScheduleId(Long scheduleId) {
        String sql = "SELECT * FROM medical_result WHERE schedule_id = ?";
        List<MedicalResult> medicalResults = query(sql, new MedicalResultMapper(), scheduleId);
        return CollectionUtils.isEmpty(medicalResults) ? null : medicalResults.get(0);
    }

    @Override
    public List<MedicalResult> findByPatientId(Long patientId) {
        String sql = "SELECT * FROM medical_result WHERE patient_id = ?";
        List<MedicalResult> medicalResults = query(sql, new MedicalResultMapper(), patientId);
        return medicalResults;
    }

    @Override
    public List<MedicalResult> findByDoctorId(Long doctorId) {
        String sql = "SELECT * FROM medical_result WHERE doctor_id = ?";
        List<MedicalResult> medicalResults = query(sql, new MedicalResultMapper(), doctorId);
        return medicalResults;
    }
}
