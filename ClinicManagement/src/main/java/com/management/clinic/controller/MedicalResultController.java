package com.management.clinic.controller;

import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalMethodService;
import com.management.clinic.service.MedicalResultService;
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

@WebServlet(urlPatterns = {"/result/add", "/result/update", "/result/delete"})
public class MedicalResultController extends HttpServlet {

    @Inject
    private MedicalResultService resultService;
    @Inject
    private MedicalScheduleService medicalScheduleService;

    @Inject
    private MedicalMethodService methodService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/result/add":
                String scheduleId= req.getParameter("scheduleId");
                MedicalSchedule medicalSchedule= medicalScheduleService.findById(Long.parseLong(scheduleId));
                req.setAttribute("scheduleSelected",medicalSchedule);
                req.getRequestDispatcher("/views/result/add.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url="/user/home";
        switch (req.getServletPath()) {
            case "/result/add":
                try {
                    HttpSession session= req.getSession();
                    UserApp userApp= (UserApp) session.getAttribute(SessionConstant.USER_APP);
                    MedicalResult medicalResult= resultService.buildMedicalResultAdd(req);
                    medicalResult.setDoctorId(userApp.getId());
                    MedicalResult result= resultService.save(medicalResult);
                    if(result!=null){
                        List<MedicalMethod> methodList= resultService.buildListMedicalMethodAdd(req);
                        if(result!=null){
                            for(MedicalMethod method:methodList){
                                method.setResultId(result.getScheduleId());
                                methodService.save(method);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/result/update":
//                try {
//                    resultService.update(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
            case "/result/delete":
                try {
                    resultService.delete(Long.parseLong(req.getParameter("id")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        resp.sendRedirect(url);
    }
}
