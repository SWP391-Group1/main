package com.management.clinic.dao;

import com.management.clinic.entity.MedicalSchedule;

import java.util.List;

public interface MedicalScheduleDAO {

    Long save(MedicalSchedule medicalSchedule);

    MedicalSchedule update(MedicalSchedule medicalSchedule);

    MedicalSchedule findById(Long id);

    boolean delete(Long id);

    List<MedicalSchedule> findByCreatedId(Long patientId);

    List<MedicalSchedule> findAll();

    void updateStatus(Long scheduleId, Boolean status);
}
