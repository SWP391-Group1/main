package com.management.clinic.controller;

import com.management.clinic.constants.MessageConstant;
import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.PagingModel;
import com.management.clinic.entity.RoleApp;
import com.management.clinic.entity.UserApp;
import com.management.clinic.paging.PageRequest;
import com.management.clinic.paging.Pageable;
import com.management.clinic.service.HealthNewsService;
import com.management.clinic.service.MedicalScheduleService;
import com.management.clinic.service.UserService;
import com.management.clinic.sort.Sorter;
import com.management.clinic.utils.FileUtil;
import com.management.clinic.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/user/sign-in", "/user/sign-up", "/user/home", "/user/profile",
        "/user/sign-out", "/user/password","/user/member","/user/addDoctor"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserController extends HttpServlet {

    @Inject
    private UserService userService;
    @Inject
    private MedicalScheduleService scheduleService;
    @Inject
    private HealthNewsService healthNewsService;

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
            case "/user/home": {
                PagingModel model = PagingModel.builder()
                        .page(StringUtils.isBlank(req.getParameter("page"))
                                ? 1 : Integer.parseInt(req.getParameter("page")))
                        .maxPageItem(StringUtils.isBlank(req.getParameter("maxPageItem"))
                                ? 2 : Integer.parseInt(req.getParameter("maxPageItem")))
                        .sortName(req.getParameter("sortName"))
                        .sortBy(req.getParameter("sortBy"))
                        .build();
                Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
                model.setListResult(healthNewsService.findAll(pageable));
                model.setTotalItem(healthNewsService.getTotalItem());
                model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
                req.setAttribute("model", model);
                req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
                break;
            }
            case "/user/profile":
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                break;
            case "/error/profile":
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                break;
            case "/user/password":
                req.getRequestDispatcher("/views/user/password.jsp").forward(req, resp);
                break;
            case "/user/member":{
                String type = req.getParameter("type");
                String cardID = req.getParameter("cardID");
                List<UserApp> members=new ArrayList<>();
                List<UserApp> membersByType = userService.getUserMember(type);
                if(!StringUtils.isBlank(cardID)){
                    members = searchUserByCardID(membersByType, cardID);
                }else{
                    members = membersByType;

                }

                req.setAttribute("members",members);
                req.setAttribute("type",type);
                req.getRequestDispatcher("/views/user/members.jsp").forward(req, resp);
                break;
            }
            case "/user/addDoctor":
                req.getRequestDispatcher("/views/user/addUser.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    private List<UserApp> searchUserByCardID(List<UserApp> all, String cardID){
        List<UserApp> members=new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getCardId().contains(cardID)){
                members.add(all.get(i));
            }
        }
        return  members;
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
                    session.setAttribute(SessionConstant.USER_ROLE, userApp.getRoleName());
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
                    userApp = userService.getUserInfoFromRequest(req, userApp);
                    userApp.setUsername(username);
                    userApp.setPassword(password);
                    String src = FileUtil.getUploadImage(req, serverPath);
                    userApp.setAvatar(src);
//                List<RoleApp> roles= new ArrayList<>();
//                RoleApp roleUser=new RoleApp();
//                roleUser.setId(1L);
//                roleUser.setName("ROLE_USER");
//                roles.add(roleUser);
//                userApp.setRoleApps(roles);
                    userApp = userService.signUp(userApp);
                    if (userApp != null) {
                        HttpSession session = req.getSession();
                        session.setAttribute(SessionConstant.USER_ROLE, userApp.getRoleName());
                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/home";
                    } else {
                        url += "?message=" + "USERNAME_EXISTED";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "/user/addDoctor": {
                url = "/user/addDoctor";
                try {
                    UserApp userApp = new UserApp();
                    userApp = userService.getUserInfoFromRequest(req, userApp);
                    userApp.setUsername(username);
                    userApp.setPassword(password);
                    String src = FileUtil.getUploadImage(req, serverPath);
                    userApp.setAvatar(src);
                    userApp.setRoleId(Long.parseLong(req.getParameter("type")));
            //        System.out.println(req.getParameter("type"));
//                    List<RoleApp> roles= new ArrayList<>();
//                    RoleApp roleUser=new RoleApp();
//                    roleUser.setId(Long.parseLong(req.getParameter("type")));
//                    roleUser.setName("ROLE_USER");
//                    roles.add(roleUser);
//                    userApp.setRoleApps(roles);

                    userApp = userService.signUp(userApp);
                    if (userApp != null) {
//                        HttpSession session = req.getSession();
//                        session.setAttribute(SessionConstant.USER_ROLE, userApp.getRoleName());
//                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/member";
                    } else {
                        url += "?message=" + "USERNAME_EXISTED";
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
                    userApp = userService.getUserInfoFromRequest(req, userApp);

                    String src = FileUtil.getUploadImage(req, serverPath);
                    if (src != null && !src.isEmpty()) {
                        userApp.setAvatar(src);
                    }
                    userApp = userService.updateInfo(userApp);
                    if (userApp != null) {
                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/profile?message=" + "UPDATE_PROFILE_SUCCESS";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "/user/password":
                try {
                    String oldPass = req.getParameter("oldPass");
                    String newPass = req.getParameter("newPass");
                    HttpSession session = req.getSession();
                    UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);

                    userApp = userService.changePassword(userApp.getId(), oldPass, newPass);
                    if (userApp != null) {
                        session.setAttribute(SessionConstant.USER_APP, userApp);
                        url = "/user/password?message=" +"CHANGE_PASSWORD_SUCCESS";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        resp.sendRedirect(req.getContextPath() + url);
    }
}
