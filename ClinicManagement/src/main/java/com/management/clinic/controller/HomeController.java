package com.management.clinic.controller;

import com.management.clinic.entity.PagingModel;
import com.management.clinic.paging.PageRequest;
import com.management.clinic.paging.Pageable;
import com.management.clinic.service.HealthNewsService;
import com.management.clinic.sort.Sorter;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    @Inject
    private HealthNewsService healthNewsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/home":
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
                req.getRequestDispatcher("/views/health-news/home.jsp").forward(req, resp);
                break;
            default:
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
