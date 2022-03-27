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

    void updateStatus(Long scheduleId, String status);

    void updateAssign(Long scheduleId, String doctor);

    Long getDoctor(Long scheduleId);

    String getDoctorName(Long id);

    List<MedicalSchedule> findByIdAssign(Long id);

}
