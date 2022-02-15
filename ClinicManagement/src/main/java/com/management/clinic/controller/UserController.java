package com.management.clinic.controller;

import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;
import com.management.clinic.service.UserService;
import com.management.clinic.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/user/sign-in", "/user/sign-up", "/user/home"})
public class UserController extends HttpServlet {

    @Inject
    private UserService userService;

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
            case "/user/home":
                req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/user/sign-in";
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        switch (req.getServletPath()) {
            case "/user/sign-in": {
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
                String email = req.getParameter("txtEmail");
                String phone = req.getParameter("txtPhone");
                String dob = req.getParameter("dob");
                String gender = req.getParameter("gender");
                String cardId = req.getParameter("cardId");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                UserApp userApp = new UserApp();
                userApp.setEmail(email);
                userApp.setUsername(username);
                userApp.setPassword(password);
                userApp.setGender(gender);
                userApp.setPhoneNumber(phone);
                userApp.setCardId(cardId);
                userApp.setFirstName(firstName);
                userApp.setLastName(lastName);
                userApp.setStatus(true);
                userApp.setCreatedStamp(new Timestamp(new Date().getTime()));
//                List<RoleApp> roles= new ArrayList<>();
//                RoleApp roleUser=new RoleApp();
//                roleUser.setId(1L);
//                roleUser.setName("ROLE_USER");
//                roles.add(roleUser);
//                userApp.setRoleApps(roles);
                try {
                    Date dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                    userApp.setDob(dobDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userApp=userService.signUp(userApp);
                if (userApp!=null) {
                    HttpSession session = req.getSession();
                    session.setAttribute(SessionConstant.USER_APP, userApp);
                    url = "/user/sign-in";
                } else {
                    url += "?message=LOGIN_FAILED";
                }
                break;
            }
            default:
                break;
        }
        resp.sendRedirect(req.getContextPath() + url);
    }
}
