package com.management.clinic.dao;



import com.management.clinic.entity.HealthNews;
import com.management.clinic.paging.Pageable;

import java.util.List;

public interface HealthNewsDAO {

    Long save(HealthNews healthNews);

    HealthNews update(HealthNews healthNews);

    HealthNews findById(Long id);

    boolean delete(Long id);

    List<HealthNews> findAll();

    List<HealthNews> findByCreatedId(Long createdId);

    List<HealthNews> findAll(Pageable pageable);

    int getTotalItem();
}
