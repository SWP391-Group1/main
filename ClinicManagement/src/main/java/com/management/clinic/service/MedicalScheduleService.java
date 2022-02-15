package com.management.clinic.service;

import com.management.clinic.entity.MedicalSchedule;

public interface MedicalScheduleService {

    MedicalSchedule save(MedicalSchedule medicalSchedule);

    MedicalSchedule update(MedicalSchedule medicalSchedule);

    MedicalSchedule findById(Long id);

    boolean delete(Long id);
}
