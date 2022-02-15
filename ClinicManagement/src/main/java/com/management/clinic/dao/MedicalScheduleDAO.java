package com.management.clinic.dao;

import com.management.clinic.entity.MedicalSchedule;

public interface MedicalScheduleDAO {

    Long save(MedicalSchedule medicalSchedule);

    MedicalSchedule update(MedicalSchedule medicalSchedule);

    MedicalSchedule findById(Long id);

    boolean delete(Long id);
}
