package com.management.clinic.service.impl;

import com.management.clinic.dao.HealthNewsDAO;
import com.management.clinic.entity.HealthNews;
import com.management.clinic.entity.UserApp;
import com.management.clinic.paging.Pageable;
import com.management.clinic.service.HealthNewsService;
import com.management.clinic.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HealthNewsServiceImpl implements HealthNewsService {

    @Inject
    private HealthNewsDAO healthNewsDAO;

    @Inject
    private UserService userService;

    @Override
    public HealthNews save(HealthNews healthNews) {
        Long id = healthNewsDAO.save(healthNews);
        if (id != null) {
            return this.findById(id);
        }
        return null;
    }

    @Override
    public HealthNews update(HealthNews healthNews) {
        return healthNewsDAO.update(healthNews);
    }

    @Override
    public HealthNews findById(Long id) {
        return healthNewsDAO.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        return healthNewsDAO.delete(id);
    }

    @Override
    public HealthNews buildData(HttpServletRequest request) throws ParseException {
        if (request != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            return HealthNews.builder()
                    .id(StringUtils.isBlank(request.getParameter("newsId")) ? null : Long.parseLong(request.getParameter("newsId")))
                    .createdId(request.getParameter("createdId") == null ? null : Long.parseLong(request.getParameter("createdId")))
                    .title(request.getParameter("title"))
                    .thumbnail(request.getParameter("thumbnail"))
                    .shortDescription(request.getParameter("shortDescription"))
                    .content(request.getParameter("contentEditor"))
                    .createdStamp(StringUtils.isBlank(request.getParameter("createdStamp")) ? null
                            : simpleDateFormat.parse(request.getParameter("createdStamp")))
                    .modifiedStamp(StringUtils.isBlank(request.getParameter("modifiedStamp")) ? null
                            : simpleDateFormat.parse(request.getParameter("modifiedStamp")))
                    .build();
        }
        return null;
    }

    @Override
    public List<HealthNews> findAll() {
        return healthNewsDAO.findAll();
    }

    @Override
    public List<HealthNews> findByCreatedId(Long createdId) {
        return setCreatedBy(healthNewsDAO.findByCreatedId(createdId));
    }

    @Override
    public List<HealthNews> findAll(Pageable pageable) {
        return setCreatedBy(healthNewsDAO.findAll(pageable));
    }

    @Override
    public int getTotalItem() {
        return healthNewsDAO.getTotalItem();
    }

    private List<HealthNews> setCreatedBy(List<HealthNews> healthNews) {
        if (!CollectionUtils.isEmpty(healthNews)) {
            for (HealthNews healthNew : healthNews) {
                if (healthNew != null && healthNew.getCreatedId() != null) {
                    UserApp userApp = userService.findById(healthNew.getCreatedId());
                    if (userApp != null) {
                        healthNew.setCreatedBy(userApp.getFirstName() + " " + userApp.getLastName());
                    }
                }
            }

        }
        return healthNews;
    }
}
