package com.management.clinic.service;

import com.management.clinic.entity.MedicalMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MedicalMethodService {

    MedicalMethod save(MedicalMethod medicalMethod);

    MedicalMethod update(MedicalMethod medicalMethod);

    MedicalMethod findById(Long id);

    boolean delete(Long id);

    List<MedicalMethod> findByResultId(Long resultId);

    MedicalMethod buildData(HttpServletRequest req);
}
