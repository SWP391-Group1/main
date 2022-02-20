package com.management.clinic.dao;

import com.management.clinic.entity.MedicalResult;

import java.util.List;

public interface MedicalResultDAO {

    Long save(MedicalResult medicalResult);

    MedicalResult update(MedicalResult medicalResult);

    MedicalResult findById(Long id);

    boolean delete(Long id);

    List<MedicalResult> findAll();

    MedicalResult findByScheduleId(Long scheduleId);

    List<MedicalResult> findByPatientId(Long patientId);

    List<MedicalResult> findByDoctorId(Long doctorId);
}
