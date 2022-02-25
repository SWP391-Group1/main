package com.management.clinic.controller;

import com.management.clinic.constants.MessageConstant;
import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.MedicalScheduleService;
import com.management.clinic.service.UserService;
import com.management.clinic.utils.FileUtil;
import com.management.clinic.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = {"/user/sign-in", "/user/sign-up", "/user/home", "/user/profile","/user/sign-out"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserController extends HttpServlet {

    @Inject
    private UserService userService;
    @Inject
    private MedicalScheduleService scheduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageUtil.showMessage(req);
        switch (req.getServletPath()) {
            case "/user/sign-in":
                req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
                break;
            case "/user/sign-up":
                req.getRequestDispatcher("/views/regist/regist.jsp").forward(req, resp);
                break;
            case "/user/sign-out": {
                HttpSession session = req.getSession();
                session.invalidate();
                req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
            }
            break;
            case "/user/home":
                HttpSession session = req.getSession();
                UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                List<MedicalSchedule> medicalScheduleList = scheduleService.findByCreatedId(userApp.getId());
                req.setAttribute("medicalScheduleList", medicalScheduleList);
                req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
                break;
            case "/user/profile":
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                break;
            case "/error/profile":
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/error/500";
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String serverPath = getServletContext().getRealPath("");
        switch (req.getServletPath()) {
            case "/user/sign-in": {
                url = "/user/sign-in";
                UserApp userApp = userService.singIn(username, password);
                if (userApp != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute(SessionConstant.USER_APP, userApp);
                    url = "/user/home";
                } else {
                    url += "?message=LOGIN_FAILED";
                }
                break;
            }

            case "/user/sign-up": {
                url = "/user/sign-up";
                try {
                    UserApp userApp = new UserApp();
                    userApp = getUserInforFromRequest(req, userApp);
                    userApp.setUsername(username);
                    userApp.setPassword(password);
                    String src = FileUtil.getUploadImage(req, serverPath);
                    userApp.setAvatar(src);

                    userApp = userService.signUp(userApp);
                    if (userApp != null) {
                        HttpSession session = req.getSession();
                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/home";
                    } else {
                        url += "?message=" + MessageConstant.USERNAME_EXISTED;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "/user/profile": {
                try {
                    HttpSession session = req.getSession();
                    UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                    userApp = getUserInforFromRequest(req, userApp);

                    String src = FileUtil.getUploadImage(req, serverPath);
                    if (src != null && !src.isEmpty()) {
                        userApp.setAvatar(src);
                    }
                    userApp = userService.updateInfo(userApp);
                    if (userApp != null) {
                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/profile?message="+MessageConstant.UPDATE_PROFILE_SUCCESS;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                break;
        }
        resp.sendRedirect(req.getContextPath() + url);
    }

    private UserApp getUserInforFromRequest(HttpServletRequest req, UserApp userApp) throws Exception {
        String email = req.getParameter("txtEmail");
        String phone = req.getParameter("txtPhone");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String cardId = req.getParameter("cardId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        userApp.setEmail(email);

        userApp.setGender(gender);
        userApp.setPhoneNumber(phone);
        userApp.setCardId(cardId);
        userApp.setFirstName(firstName);
        userApp.setLastName(lastName);
        userApp.setStatus(true);
        userApp.setCreatedStamp(new Timestamp(new Date().getTime()));

        Date dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        userApp.setDob(dobDate);

        return userApp;
    }
}
