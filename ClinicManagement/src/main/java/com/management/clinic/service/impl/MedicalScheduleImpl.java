package com.management.clinic.service.impl;

import com.management.clinic.constants.SessionConstant;
import com.management.clinic.dao.MedicalScheduleDAO;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalScheduleService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicalScheduleImpl implements MedicalScheduleService {

    @Inject
    private MedicalScheduleDAO medicalScheduleDAO;

    @Override
    public MedicalSchedule save(MedicalSchedule medicalSchedule) {
        Long id = medicalScheduleDAO.save(medicalSchedule);
        if (id != null) {
            return this.findById(id);
        }
        return null;
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
    public List<MedicalSchedule> findByCreatedId(Long id) {
        return medicalScheduleDAO.findByCreatedId(id);
    }

    @Override
    public boolean delete(Long id) {
        return medicalScheduleDAO.delete(id);
    }

    @Override
    public MedicalSchedule buildDataCreate(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
        String schedule = req.getParameter("schedule");
        Date scheduleDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(schedule);
        return MedicalSchedule.builder()
                .createdId(userApp.getId())
                .type(req.getParameter("type"))
                .description(req.getParameter("description"))
                .schedule(scheduleDate)
                .build();
    }

    @Override
    public MedicalSchedule buildDataUpdate(HttpServletRequest req) throws Exception {
        String schedule = req.getParameter("schedule");

        String id = req.getParameter("id");
        MedicalSchedule medicalSchedule = findById(Long.parseLong(id));
        medicalSchedule.setType(req.getParameter("type"));
        medicalSchedule.setDescription(req.getParameter("description"));

        if (schedule != null && !schedule.isEmpty()) {
            Date scheduleDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(schedule);
            medicalSchedule.setSchedule(scheduleDate);
        }
        return medicalSchedule;
    }

    @Override
    public void updateStatus(Long scheduleId, Boolean status) {
        medicalScheduleDAO.updateStatus(scheduleId, status);
    }

    @Override
    public List<MedicalSchedule> findAll() {
        return medicalScheduleDAO.findAll();
    }
}
