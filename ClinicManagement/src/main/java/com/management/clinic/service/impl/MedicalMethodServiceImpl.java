package com.management.clinic.service.impl;

import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.service.MedicalMethodService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class MedicalMethodServiceImpl implements MedicalMethodService {

  @Override
  public MedicalMethod save(MedicalMethod medicalMethod) {
    return null;
  }

  @Override
  public MedicalMethod update(MedicalMethod medicalMethod) {
    return null;
  }

  @Override
  public MedicalMethod findById(Long id) {
    return null;
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }

  @Override
  public List<MedicalMethod> findByResultId(Long resultId) {
    return null;
  }

  @Override
  public MedicalMethod buildData(HttpServletRequest req) {
    return null;
  }
}
