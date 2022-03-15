package com.management.clinic.mapper;

import com.management.clinic.entity.HealthNews;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthNewsMapper implements RowMapper<HealthNews> {
    @Override
    public HealthNews mapRow(ResultSet rs) {
        HealthNews healthNews = new HealthNews();
        try {
            healthNews.setId(rs.getLong("id"));
            healthNews.setCreatedId(rs.getLong("created_id"));
            healthNews.setTitle(rs.getString("title"));
            healthNews.setThumbnail(rs.getString("thumbnail"));
            healthNews.setShortDescription(rs.getString("short_description"));
            healthNews.setContent(rs.getString("content"));
            healthNews.setCreatedStamp(rs.getDate("created_stamp"));
            healthNews.setModifiedStamp(rs.getDate("modified_stamp"));
            return healthNews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
