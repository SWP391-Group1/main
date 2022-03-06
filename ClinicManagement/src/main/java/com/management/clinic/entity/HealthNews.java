package com.management.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthNews implements Serializable {

    private Long id;
    private Long createdId;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private Date createdStamp;
    private Date modifiedStamp;
    private String createdBy;
}
