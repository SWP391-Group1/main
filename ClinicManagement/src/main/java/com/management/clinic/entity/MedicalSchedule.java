package com.management.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalSchedule implements Serializable {

    private Long id;
    private String description;
    private String type;
    private Date schedule;
    private Long createdId;
    private Date createdStamp;
    private Date modifiedStamp;
    private String status;
    private String patientName;
    private String patientPhone;
}
