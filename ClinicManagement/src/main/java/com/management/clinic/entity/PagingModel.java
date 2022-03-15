package com.management.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingModel implements Serializable {

    private Integer page;
    private Integer totalPage;
    private Integer totalItem;
    private Integer maxPageItem;
    private String sortName;
    private String sortBy;
    private String message;
    private String alert;
    private String type;
    private List<HealthNews> listResult;
}
