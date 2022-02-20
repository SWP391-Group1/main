package com.management.clinic.controller;

import com.management.clinic.entity.MedicalResult;
import com.management.clinic.service.MedicalResultService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/result/add", "/result/update", "/result/delete"})
public class MedicalResultController extends HttpServlet {

    @Inject
    private MedicalResultService resultService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/result/add":
                req.getRequestDispatcher("/views/result/add.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MedicalResult result = resultService.buildData(req);
        switch (req.getServletPath()) {
            case "/result/add":
                try {
                    resultService.save(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/result/update":
                try {
                    resultService.update(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        resp.sendRedirect(req.getContextPath());
    }
}
