package com.management.clinic.service.impl;

import com.management.clinic.dao.MedicalResultDAO;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.service.MedicalResultService;
import com.management.clinic.service.MedicalScheduleService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class MedicalResultServiceImpl implements MedicalResultService {

    @Inject
    private MedicalResultDAO medicalResultDAO;

    @Inject
    private MedicalScheduleService scheduleService;

    @Override
    public MedicalResult save(MedicalResult medicalResult) {
        Long id = medicalResultDAO.save(medicalResult);
        if (id != null) {
            scheduleService.updateStatus(medicalResult.getScheduleId(), Boolean.FALSE);
            return this.findById(id);
        }
        return null;
    }

    @Override
    public MedicalResult update(MedicalResult medicalResult) {
        return medicalResultDAO.update(medicalResult);
    }

    @Override
    public MedicalResult findById(Long id) {
        return medicalResultDAO.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        try {
            return medicalResultDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MedicalResult buildData(HttpServletRequest req) {
        if (req != null) {
            return MedicalResult.builder()
                    .id(Long.parseLong(req.getParameter("id")))
                    .scheduleId(Long.parseLong(req.getParameter("scheduleId")))
                    .doctorId(Long.parseLong(req.getParameter("doctorId")))
                    .name(req.getParameter("name"))
                    .diagnosis(req.getParameter("diagnosis"))
                    .conclude(req.getParameter("conclude"))
                    .build();
        }
        return null;
    }
}