package com.management.clinic.dao.impl;

import com.management.clinic.dao.MedicalMethodDAO;
import com.management.clinic.entity.MedicalMethod;
import com.management.clinic.entity.MedicalResult;
import com.management.clinic.mapper.MedicalMethodMapper;
import com.management.clinic.mapper.MedicalResultMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class MedicalMethodDAOImpl extends AbstractDAO<MedicalMethod> implements MedicalMethodDAO {
    @Override
    public Long save(MedicalMethod medicalMethod) {
        String sql = "INSERT INTO medical_method (result_id, name, diagnosis, conclude, created_stamp " +
                " VALUES (?, ?, ?, ?, ?)";
        return insert(sql, medicalMethod.getResultId(), medicalMethod.getName(),
                medicalMethod.getDiagnosis(), medicalMethod.getConclude(), new Date());
    }

    @Override
    public MedicalMethod update(MedicalMethod medicalMethod) {
        String sql = "UPDATE medical_method SET result_id=?, name=?, diagnosis=?, conclude = ?, " +
                "modified_stamp= ? WHERE id =?";
        update(sql, medicalMethod.getResultId(), medicalMethod.getName(),
                medicalMethod.getDiagnosis(), medicalMethod.getConclude(), new Date());
        return medicalMethod;
    }

    @Override
    public MedicalMethod findById(Long id) {
        String sql = "SELECT * FROM medical_method WHERE id= ?";
        List<MedicalMethod> medicalMethods = query(sql, new MedicalMethodMapper(), id);
        return CollectionUtils.isEmpty(medicalMethods) ? null : medicalMethods.get(0);
    }

    @Override
    public boolean delete(Long id) {
        try {
            String sql = "DELETE FROM medical_method WHERE id = ?";
            update(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MedicalResult> findAll() {
        String sql = "SELECT * FROM medical_method";
        return query(sql, new MedicalResultMapper());
    }

    @Override
    public List<MedicalMethod> findByResultId(Long resultId) {
        String sql = "SELECT * FROM medical_method WHERE result_id= ?";
        List<MedicalMethod> medicalMethods = query(sql, new MedicalMethodMapper(), resultId);
        return medicalMethods;
    }
}
