package com.management.clinic.controller;

import com.management.clinic.constants.SessionConstant;
import com.management.clinic.entity.HealthNews;
import com.management.clinic.entity.PagingModel;
import com.management.clinic.entity.UserApp;
import com.management.clinic.paging.PageRequest;
import com.management.clinic.paging.Pageable;
import com.management.clinic.service.HealthNewsService;
import com.management.clinic.sort.Sorter;
import com.management.clinic.utils.FileUtil;
import lombok.SneakyThrows;
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
import java.util.Locale;

@WebServlet(urlPatterns = {"/news", "/news/add", "/news/delete", "/news/details","/news/user/details"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class HealthNewController extends HttpServlet {

    @Inject
    private HealthNewsService healthNewsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/news":
                HttpSession session = req.getSession();
                UserApp userApp = (UserApp) session.getAttribute(SessionConstant.USER_APP);
                List<HealthNews> listNew = new ArrayList<>();
                String title = req.getParameter("title");
                if (userApp != null) {
                    List <HealthNews> listAllNews = healthNewsService.findByCreatedId(userApp.getId());
                    if(!StringUtils.isBlank(title)){
                        for (int i = 0; i < listAllNews.size(); i++) {
                            if(listAllNews.get(i).getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT))){
                                listNew.add(listAllNews.get(i));
                            }
                        }
                    }
                    else {
                        listNew = listAllNews;
                    }
                }
                req.setAttribute("listNews", listNew);

                PagingModel model = PagingModel.builder()
                        .page(StringUtils.isBlank(req.getParameter("page"))
                                ? 1 : Integer.parseInt(req.getParameter("page")))
                        .maxPageItem(StringUtils.isBlank(req.getParameter("maxPageItem"))
                                ? 4 : Integer.parseInt(req.getParameter("maxPageItem")))
                        .sortName(req.getParameter("sortName"))
                        .sortBy(req.getParameter("sortBy"))
                        .build();
                Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
                model.setListResult(healthNewsService.findAll(pageable));
                model.setTotalItem(healthNewsService.getTotalItem());
                model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
                req.setAttribute("model", model);

                req.getRequestDispatcher("/views/health-news/list.jsp").forward(req, resp);
                break;
            case "/news/add":
                String strId = req.getParameter("editId");
                if (!StringUtils.isBlank(strId)) {
                    req.setAttribute("news", healthNewsService.findById(Long.parseLong(strId)));
                    req.getRequestDispatcher("/views/health-news/add.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/views/health-news/add.jsp").forward(req, resp);
                }
                break;
            case "/news/details":
                String id = req.getParameter("id");
                boolean user= Boolean.parseBoolean(req.getParameter("user"));
                if (!StringUtils.isBlank(id)) {
                    req.setAttribute("news", healthNewsService.findById(Long.parseLong(id)));
                }
                if(user){
                    req.getRequestDispatcher("/views/health-news/user_news_detail.jsp").forward(req, resp);
                }else{
                    req.getRequestDispatcher("/views/health-news/detail.jsp").forward(req, resp);
                }
                break;
            default:
                break;
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HealthNews healthNews = healthNewsService.buildData(req);
        switch (req.getServletPath()) {
            case "/news/add":
                String serverPath = getServletContext().getRealPath("");

                if (healthNews != null && healthNews.getId() != null) {
                    // Handle when update thumbnail
                    String src = FileUtil.getUploadImage(req, serverPath);
                    if (src != null && !src.isEmpty()) {
                        healthNews.setThumbnail(src);
                    }
                    //
                    healthNewsService.update(healthNews);
                } else {
                    String src = FileUtil.getUploadImage(req, serverPath);
                    if (!StringUtils.isBlank(src)) {
                        healthNews.setThumbnail(src);
                    }
                    healthNewsService.save(healthNews);
                }
                break;
            case "/news/delete":
                String strId = req.getParameter("id");
                if (!StringUtils.isBlank(strId)) {
                    healthNewsService.delete(Long.parseLong(strId));
                }
                break;
            default:
                break;
        }
        resp.sendRedirect(req.getContextPath() + "/news");
    }
}
