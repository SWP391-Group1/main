package com.management.clinic.service;

import com.management.clinic.entity.MedicalSchedule;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MedicalScheduleService {

    MedicalSchedule save(MedicalSchedule medicalSchedule);

    MedicalSchedule update(MedicalSchedule medicalSchedule);

    MedicalSchedule findById(Long id);

    List<MedicalSchedule> findByCreatedId(Long createdId);

    boolean delete(Long id);

    MedicalSchedule buildDataCreate(HttpServletRequest req) throws Exception;

    MedicalSchedule buildDataUpdate(HttpServletRequest req) throws Exception;

    void updateStatus(Long scheduleId, Boolean status);

    List<MedicalSchedule> findAll();
}
