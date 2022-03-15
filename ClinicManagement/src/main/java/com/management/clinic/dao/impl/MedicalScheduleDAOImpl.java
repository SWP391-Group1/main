package com.management.clinic.dao.impl;

import com.management.clinic.constants.ScheduleStatus;
import com.management.clinic.dao.MedicalScheduleDAO;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.mapper.MedicalScheduleMapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

public class MedicalScheduleDAOImpl extends AbstractDAO<MedicalSchedule> implements MedicalScheduleDAO {

    @Override
    public Long save(MedicalSchedule medicalSchedule) {
        String sql = "INSERT INTO medical_schedule (created_id, description, schedule, type, status, created_stamp) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return insert(sql, medicalSchedule.getCreatedId(), medicalSchedule.getDescription(),
                medicalSchedule.getSchedule(), medicalSchedule.getType(), ScheduleStatus.PENDING, new Date());
    }

    @Override
    public MedicalSchedule update(MedicalSchedule medicalSchedule) {
        String sql = "UPDATE medical_schedule SET description=?, schedule=?, type=?, modified_stamp=? WHERE id =?";
        update(sql, medicalSchedule.getDescription(), medicalSchedule.getSchedule(),
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
            MedicalSchedule medicalSchedule = this.findById(id);
            if (medicalSchedule != null) {
                if (medicalSchedule.getStatus().equalsIgnoreCase(ScheduleStatus.PENDING)) {
                    String sql = "DELETE FROM medical_schedule WHERE id = ?";
                    update(sql, id);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MedicalSchedule> findByCreatedId(Long createdId) {
        String sql = "SELECT * FROM medical_schedule WHERE created_id= ?";
        return query(sql, new MedicalScheduleMapper(), createdId);
    }

    @Override
    public List<MedicalSchedule> findAll() {
        String sql = "SELECT * FROM medical_schedule";
        return query(sql, new MedicalScheduleMapper());
    }

    @Override
    public void updateStatus(Long scheduleId, String status) {
        String sql = "UPDATE medical_schedule SET status = ? WHERE id = ?";
        update(sql, status, scheduleId);
    }
}
