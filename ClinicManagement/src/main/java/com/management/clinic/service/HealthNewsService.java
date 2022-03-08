package com.management.clinic.service;

import com.management.clinic.entity.HealthNews;
import com.management.clinic.paging.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface HealthNewsService {

    HealthNews save(HealthNews healthNews);

    HealthNews update(HealthNews healthNews);

    HealthNews findById(Long id);

    boolean delete(Long id);

    HealthNews buildData(HttpServletRequest request) throws ParseException;

    List<HealthNews> findAll();

    List<HealthNews> findByCreatedId(Long createdId);

    List<HealthNews> findAll(Pageable pageable);

    int getTotalItem();
}
