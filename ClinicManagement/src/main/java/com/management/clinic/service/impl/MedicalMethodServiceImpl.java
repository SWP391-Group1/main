package com.management.clinic.service.impl;

import com.management.clinic.dao.MedicalMethodDAO;
import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.service.MedicalMethodService;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class MedicalMethodServiceImpl implements MedicalMethodService {

  @Inject
  private MedicalMethodDAO medicalMethodDAO;

  @Override
  public MedicalMethod save(MedicalMethod medicalMethod) {
    Long id = medicalMethodDAO.save(medicalMethod);
    if (id != null) {
      return this.findById(id);
    }
    return null;
  }

  @Override
  public MedicalMethod update(MedicalMethod medicalMethod) {
    return medicalMethodDAO.update(medicalMethod);
  }

  @Override
  public MedicalMethod findById(Long id) {
    return medicalMethodDAO.findById(id);
  }

  @Override
  public boolean delete(Long id) {
    try {
      return medicalMethodDAO.delete(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<MedicalMethod> findByResultId(Long resultId) {
    return medicalMethodDAO.findByResultId(resultId);
  }

  @Override
  public MedicalMethod buildData(HttpServletRequest req) {
    if (req != null) {
      MedicalMethod.builder()
          .id(Long.parseLong(req.getParameter("id")))
          .name(req.getParameter("name"))
          .diagnosis(req.getParameter("diagnosis"))
          .conclude(req.getParameter("conclude"))
          .createdStamp(new Date(req.getParameter("id")))
          .modifiedStamp(new Date(req.getParameter("modifiedStamp")))
          .resultId(Long.parseLong(req.getParameter("resultId")))
          .build();
    }
    return null;
  }
}
