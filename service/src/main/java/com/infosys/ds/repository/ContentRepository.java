package com.infosys.ds.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Slide;

@Repository
public class ContentRepository {
	private Logger log = LoggerFactory.getLogger(ContentRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	public int saveContent(String contentBody, int contentTypeCd, int height, int width, int userId)
			throws DSException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String sql = env.getProperty("sql.save.content");
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, contentBody);
					ps.setInt(2, contentTypeCd);
					ps.setInt(3, height);
					ps.setInt(4, width);
					ps.setInt(5, userId);
					ps.setInt(6, userId);
					return ps;
				}
			}, keyHolder);
			return keyHolder.getKey().intValue();
		} catch (Exception e) {
			log.error("Error in saving content", e);
			throw new DSException("Unable to save content !");
		}
	}

	public void saveSlides(int contentId, List<Slide> slides) throws DSException {
		try {
			String sql = env.getProperty("sql.save.slide");
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Slide slide = slides.get(i);
					ps.setInt(1, contentId);
					ps.setInt(2, slide.getSlideNo());
					ps.setString(3, slide.getSlideContent());
				}

				@Override
				public int getBatchSize() {
					return slides.size();
				}
			});
		} catch (Exception e) {
			log.error("Error in saving content", e);
			throw new DSException("Unable to save content !");
		}
	}
}
