package com.management.clinic.service.impl;

import com.management.clinic.dao.MedicalScheduleDAO;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.service.MedicalScheduleService;

import javax.inject.Inject;

public class MedicalScheduleImpl implements MedicalScheduleService {

    @Inject
    private MedicalScheduleDAO medicalScheduleDAO;

    @Override
    public MedicalSchedule save(MedicalSchedule medicalSchedule) {
        Long id = medicalScheduleDAO.save(medicalSchedule);
        return this.findById(id);
    }

    @Override
    public MedicalSchedule update(MedicalSchedule medicalSchedule) {
        return medicalScheduleDAO.update(medicalSchedule);
    }

    @Override
    public MedicalSchedule findById(Long id) {
        return medicalScheduleDAO.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        return medicalScheduleDAO.delete(id);
    }
}
