package com.management.clinic.dao.impl;

import com.management.clinic.constants.ScheduleStatus;
import com.management.clinic.dao.MedicalScheduleDAO;
import com.management.clinic.entity.MedicalSchedule;
import com.management.clinic.mapper.MedicalScheduleMapper;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MedicalScheduleDAOImpl extends AbstractDAO<MedicalSchedule> implements MedicalScheduleDAO {

    @Override
    public void updateAssign(Long scheduleId, String doctor){
        String sql = "UPDATE medical_schedule SET assign=? WHERE id =?";
        update(sql, doctor, scheduleId);
    }

    @Override
    public List<MedicalSchedule> findByIdAssign(Long id) {
        String sql = "SELECT * FROM medical_schedule WHERE assign= ?";
        return query(sql, new MedicalScheduleMapper(), id);
    }

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

    @Override
    public Long getDoctor(Long scheduleId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT assign FROM medical_schedule WHERE id = ?";
        Long id = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, scheduleId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    @Override
    public String getDoctorName(Long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        String fname = "", lname = "";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fname = resultSet.getString(4);
                lname = resultSet.getString(5);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return fname + " " + lname;
    }
}
