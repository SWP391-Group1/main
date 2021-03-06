package com.management.clinic.controller;

import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalMethodService;
import com.management.clinic.service.MedicalResultService;
import com.management.clinic.service.MedicalScheduleService;
import com.management.clinic.utils.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/result/add", "/result/view"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
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
            case "/result/view": {
                String scheduleId = req.getParameter("scheduleId");
                MedicalSchedule medicalSchedule = medicalScheduleService.findById(Long.parseLong(scheduleId));
                MedicalResult medicalResult = resultService.findByScheduleId(Long.parseLong(scheduleId));
                if (medicalSchedule.getType().equals("HEALTH_CARE")) {
                    List<MedicalMethod> medicalMethods = methodService.findByResultId(medicalResult.getId());
                    req.setAttribute("methodsSelected", medicalMethods);
                }

                req.setAttribute("resultSelected", medicalResult);
                req.setAttribute("scheduleSelected", medicalSchedule);
                req.getRequestDispatcher("/views/result/view.jsp").forward(req, resp);
                break;
            }

            case "/result/add": {
                String scheduleId = req.getParameter("scheduleId");
                MedicalSchedule medicalSchedule = medicalScheduleService.findById(Long.parseLong(scheduleId));
                req.setAttribute("scheduleSelected", medicalSchedule);
                req.getRequestDispatcher("/views/result/add.jsp").forward(req, resp);
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/result/add":
                try {
                    HttpSession session = req.getSession();
                    UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                    MedicalResult medicalResult = resultService.buildMedicalResultAdd(req);
                    medicalResult.setDoctorId(userApp.getId());
                    MedicalResult result = resultService.save(medicalResult);
                    if (result != null) {
                        List<MedicalMethod> methodList = resultService.buildListMedicalMethodAdd(req);
                        String serverPath = getServletContext().getRealPath("");
                        Map<Integer, String> paths = FileUtil.getUploadImages(req, serverPath);
                        if (result != null && methodList != null) {
                            for (int i = 0; i < methodList.size(); i++) {
                                methodList.get(i).setResultId(result.getId());
                                methodList.get(i).setImage(paths.get(i));
                                methodService.save(methodList.get(i));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        resp.sendRedirect("/schedule/table");
    }
}
