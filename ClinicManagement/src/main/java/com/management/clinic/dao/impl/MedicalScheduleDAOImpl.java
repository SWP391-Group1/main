package com.management.clinic.dao.impl;

import com.management.clinic.dao.MedicalScheduleDAO;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.mapper.MedicalScheduleMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class MedicalScheduleDAOImpl extends AbstractDAO<MedicalSchedule> implements MedicalScheduleDAO {


    @Override
    public Long save(MedicalSchedule medicalSchedule) {
        String sql = "INSERT INTO medical_schedule (created_id, description, schedule, type, created_stamp, " +
                "VALUES (?, ?, ?, ?)";
        return insert(sql, medicalSchedule.getCreatedId(), medicalSchedule.getDescription(),
                medicalSchedule.getSchedule(), medicalSchedule.getType(), new Date());
    }

    @Override
    public MedicalSchedule update(MedicalSchedule medicalSchedule) {
        String sql = "UPDATE user SET description=?, schedule=?, type=?, modifiedStamp=? WHERE id =?";
        update(sql, sql, medicalSchedule.getDescription(), medicalSchedule.getSchedule(),
                medicalSchedule.getType(), new Date(), medicalSchedule.getId());
        return medicalSchedule;
    }

    @Override
    public MedicalSchedule findById(Long id) {
        String sql = "SELECT * FROM medical_schedule WHERE id= ?";
        List<MedicalSchedule> medicalSchedules = query(sql, new MedicalScheduleMapper(), id);
        return CollectionUtils.isEmpty(medicalSchedules) ? null : medicalSchedules.get(0);
    }

    @Override
    public boolean delete(Long id) {
        try {
            String sql = "DELETE FROM medical_schedule WHERE id = ?";
            // Check: if exist medical_result ==> Cannot delete

            //
            update(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
