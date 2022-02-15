package com.management.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "medical_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String description;
    private String type;
    private Date schedule;

    @Column(name = "created_id")
    private Long createdId;

    @Column(name = "created_stamp")
    private Date createdStamp;

    @Column(name = "modified_stamp")
    private Date modifiedStamp;
}
