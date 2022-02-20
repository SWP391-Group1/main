package com.management.clinic.service;

import com.management.clinic.entity.MedicalResult;

import javax.servlet.http.HttpServletRequest;

public interface MedicalResultService {

    MedicalResult save(MedicalResult medicalResult);

    MedicalResult update(MedicalResult medicalResult);

    MedicalResult findById(Long id);

    boolean delete(Long id);

    MedicalResult buildData(HttpServletRequest req);
}
