package com.management.clinic.dao.impl;

import com.management.clinic.dao.HealthNewsDAO;
import com.management.clinic.entity.HealthNews;
import com.management.clinic.mapper.HealthNewsMapper;
import com.management.clinic.paging.Pageable;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

public class HealthNewsDAOImpl extends AbstractDAO<HealthNews> implements HealthNewsDAO {
    @Override
    public Long save(HealthNews healthNews) {
        String sql = "INSERT INTO health_news (created_id, title, thumbnail, short_description, content, " +
                "created_stamp) VALUES (?, ?, ?, ?, ?, ?)";
        return insert(sql, healthNews.getCreatedId(), healthNews.getTitle(), healthNews.getThumbnail(),
               healthNews.getShortDescription(), healthNews.getContent(), new Date());
    }

    @Override
    public HealthNews update(HealthNews healthNews) {
        String sql = "UPDATE health_news SET title=?, thumbnail = ?, short_description = ?, content= ?,  " +
                "modified_stamp=? WHERE id =?";
        update(sql, healthNews.getTitle(), healthNews.getThumbnail(), healthNews.getShortDescription(),
                healthNews.getContent(), new Date(), healthNews.getId());
        return healthNews;
    }

    @Override
    public HealthNews findById(Long id) {
        String sql = "SELECT * FROM health_news WHERE id= ?";
        List<HealthNews> healthNews = query(sql, new HealthNewsMapper(), id);
        return CollectionUtils.isEmpty(healthNews) ? null : healthNews.get(0);
    }

    @Override
    public boolean delete(Long id) {
        try {
            String sql = "DELETE FROM health_news WHERE id = ?";
            update(sql, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<HealthNews> findAll() {
        String sql = "SELECT * FROM health_news";
        return query(sql, new HealthNewsMapper());
    }

    @Override
    public List<HealthNews> findByCreatedId(Long createdId) {
        String sql = "SELECT * FROM health_news WHERE created_id= ?";
        List<HealthNews> healthNews = query(sql, new HealthNewsMapper(), createdId);
        return healthNews;
    }

    @Override
    public List<HealthNews> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM health_news");
        if (pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName())
                && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
        }
        return query(sql.toString(), new HealthNewsMapper());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM health_news";
        return count(sql);
    }
}
