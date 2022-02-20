package com.management.clinic.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  private Boolean status;
}