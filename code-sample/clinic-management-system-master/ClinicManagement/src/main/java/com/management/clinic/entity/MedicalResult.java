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
public class MedicalResult implements Serializable {

    private Long id;
    private Long scheduleId;
    private Long patientId;
    private Long doctorId;
    private String name;
    private String diagnosis;
    private String conclude;
    private Date createdStamp;
    private Date modifiedStamp;
}
