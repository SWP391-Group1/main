package com.management.clinic.controller;

import com.management.clinic.constants.Constant;
import com.management.clinic.constants.ScheduleStatus;
import com.management.clinic.constants.SessionConstant;
import com.management.clinic.dao.impl.MedicalScheduleDAOImpl;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalScheduleService;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/schedule/add", "/schedule/update", "/schedule/delete", "/schedule/table",
        "/schedule", "/schedule/approve", "/schedule/reject", "/schedule/assign"})
public class MedicalScheduleController extends HttpServlet {

    @Inject
    private MedicalScheduleService scheduleService;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/schedule/table": {
                String status = req.getParameter("status");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                HttpSession session = req.getSession();
                UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                String fromDateStr = req.getParameter("fromTime");
                String toDateStr = req.getParameter("toTime");
                List<MedicalSchedule> medicalScheduleList;
                if (userApp.getRoleName().equals(Constant.ROLE_PATIENT)) {
                    medicalScheduleList = scheduleService.findByCreatedId(userApp.getId());
                } else {
                    if(userApp.getRoleName().equals(Constant.ROLE_DOCTOR)){
                        medicalScheduleList = scheduleService.findByIdAssign(userApp.getId());
                    } else {
                        medicalScheduleList = scheduleService.findAll();
                    }
                }
                if (!StringUtils.isBlank(status)) {
                    if (status.equalsIgnoreCase(ScheduleStatus.PENDING)) {
                        medicalScheduleList.removeIf(s -> !s.getStatus().equalsIgnoreCase(ScheduleStatus.PENDING));
                        req.setAttribute("status", ScheduleStatus.PENDING);
                    } else if (status.equalsIgnoreCase(ScheduleStatus.APPROVED)) {
                        medicalScheduleList.removeIf(s -> !s.getStatus().equalsIgnoreCase(ScheduleStatus.APPROVED));
                        req.setAttribute("status", ScheduleStatus.APPROVED);
                    } else if (status.equalsIgnoreCase(ScheduleStatus.COMPLETED)) {
                        medicalScheduleList.removeIf(s -> !s.getStatus().equalsIgnoreCase(ScheduleStatus.COMPLETED));
                        req.setAttribute("status", ScheduleStatus.COMPLETED);
                    } else if (status.equalsIgnoreCase(ScheduleStatus.REJECTED)) {
                        medicalScheduleList.removeIf(s -> !s.getStatus().equalsIgnoreCase(ScheduleStatus.REJECTED));
                        req.setAttribute("status", ScheduleStatus.REJECTED);
                    } else {
                        medicalScheduleList.removeIf(s -> s.getStatus().equalsIgnoreCase(ScheduleStatus.REJECTED));
                    }
                } else {
                    medicalScheduleList.removeIf(s -> s.getStatus().equalsIgnoreCase(ScheduleStatus.REJECTED));
                }
                if (!StringUtils.isBlank(fromDateStr)) {
                    Date fromDate = simpleDateFormat.parse(fromDateStr);
                    if (fromDate != null) {
                        req.setAttribute("fromTime", fromDate);
                        medicalScheduleList.removeIf(s -> s.getSchedule().before(fromDate));
                    }
                }
                if (!StringUtils.isBlank(toDateStr)) {
                    Date toDate = simpleDateFormat.parse(toDateStr);
                    if (toDate != null) {
                        req.setAttribute("toTime", toDate);
                        medicalScheduleList.removeIf(s -> s.getSchedule().after(toDate));
                    }
                }
                req.setAttribute("medicalScheduleList", scheduleService.getPatientInfo(medicalScheduleList));
                req.getRequestDispatcher("/views/schedule/table.jsp").forward(req, resp);
                break;
            }
            case "/schedule/add":
                req.getRequestDispatcher("/views/schedule/add.jsp").forward(req, resp);
                break;
            case "/schedule/update":
                String scheduleId = req.getParameter("id");
                MedicalSchedule medicalSchedule = scheduleService.findById(Long.parseLong(scheduleId));
                req.setAttribute("medicalSchedule", medicalSchedule);
                req.getRequestDispatcher("/views/schedule/update.jsp").forward(req, resp);
                break;
            case "/schedule/assign":
                String id = req.getParameter("id");
                MedicalSchedule medicalSch = scheduleService.findById(Long.parseLong(id));
                req.setAttribute("medicalSchedule", medicalSch);
                req.getRequestDispatcher("/views/schedule/assign.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getServletPath()) {
            case "/schedule/add": {
                try {
                    MedicalSchedule schedule = scheduleService.buildDataCreate(req);
                    scheduleService.save(schedule);
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case "/schedule/update": {
                try {
                    MedicalSchedule schedule = scheduleService.buildDataUpdate(req);
                    scheduleService.update(schedule);
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "/schedule/delete": {
                try {
                    scheduleService.delete(Long.parseLong(req.getParameter("id")));
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "/schedule/approve":
                try {
                    scheduleService.updateStatus(Long.parseLong(req.getParameter("id")), ScheduleStatus.APPROVED);
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/schedule/reject":
                try {
                    scheduleService.updateStatus(Long.parseLong(req.getParameter("id")), ScheduleStatus.REJECTED);
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/schedule/assign":
                try {
                    String doctor = req.getParameter("doctor_acc");
                    Long id = Long.parseLong(req.getParameter("id"));
                    MedicalScheduleDAOImpl daoMS = new MedicalScheduleDAOImpl();
                    daoMS.updateAssign(id, doctor);
                    resp.sendRedirect("/schedule/table");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        resp.sendRedirect(req.getContextPath());
    }
}
