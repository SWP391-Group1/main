package com.management.clinic.controller;

import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.service.MedicalMethodService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/method/add", "/method/update", "/method/delete"})
public class MedicalMethodController extends HttpServlet {

  @Inject
  private MedicalMethodService methodService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
      ServletException, IOException {
    switch (req.getServletPath()) {
      case "/method/add":
        req.getRequestDispatcher("/views/method/add.jsp").forward(req, resp);
        break;
      default:
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    MedicalMethod medicalMethod = methodService.buildData(req);
    switch (req.getServletPath()) {
      case "/method/add":
        try {
          methodService.save(medicalMethod);
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "/method/update":
        try {
          methodService.update(medicalMethod);
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "/method/delete":
        try {
          methodService.delete(Long.parseLong(req.getParameter("id")));
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
