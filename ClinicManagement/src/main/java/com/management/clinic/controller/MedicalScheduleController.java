package com.management.clinic.controller;

import com.management.clinic.constants.Constant;
import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalScheduleService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/schedule/add", "/schedule/update", "/schedule/delete", "/schedule/table", "/schedule"})
public class MedicalScheduleController extends HttpServlet {

    @Inject
    private MedicalScheduleService scheduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/schedule/table": {
                HttpSession session = req.getSession();
                UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                List<MedicalSchedule> medicalScheduleList;
                if (userApp.getRoleName().equals(Constant.ROLE_PATIENT)) {
                    medicalScheduleList = scheduleService.findByCreatedId(userApp.getId());
                } else {
                    medicalScheduleList = scheduleService.findAll();
                }
                req.setAttribute("medicalScheduleList", medicalScheduleList);
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
                    resp.sendRedirect("/user/home");
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
            default:
                break;
        }
        resp.sendRedirect(req.getContextPath());
    }
}
