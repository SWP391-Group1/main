package com.management.clinic.dao;

import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;

import java.util.List;

public interface MedicalMethodDAO {

    Long save(MedicalMethod medicalMethod);

    MedicalMethod update(MedicalMethod medicalMethod);

    MedicalMethod findById(Long id);

    boolean delete(Long id);

    List<MedicalResult> findAll();

    List<MedicalMethod> findByResultId(Long resultId);
}
