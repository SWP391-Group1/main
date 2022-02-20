package com.management.clinic.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalMethod implements Serializable {

  private Long id;

  private Long resultId;

  private String name;

  private String type;

  private String diagnosis;

  private String conclude;

  private Date createdStamp;

  private Date modifiedStamp;
}
