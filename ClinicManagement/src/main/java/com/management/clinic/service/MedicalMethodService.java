package com.management.clinic.service;

import com.management.clinic.entity.MedicalMethod;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface MedicalMethodService {
  MedicalMethod save(MedicalMethod medicalMethod);

  MedicalMethod update(MedicalMethod medicalMethod);

  MedicalMethod findById(Long id);

  boolean delete(Long id);

  List<MedicalMethod> findByResultId(Long resultId);

  MedicalMethod buildData(HttpServletRequest req);
}
