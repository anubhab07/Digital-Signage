package com.infosys.ds.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Content;
import com.infosys.ds.model.ContentType;
import com.infosys.ds.model.FetchContentResponse;

@Repository
public class DSClientRepository {
	private Logger log = LoggerFactory.getLogger(DSClientRepository.class);
	@Autowired
	private Environment env;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Content fetchContentBody(int id) throws DSException {
		try {
			String sql = env.getProperty("sql.fetch.contentBody");
			return jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, id);
					return ps;
				}
			}, new ResultSetExtractor<Content>() {

				@Override
				public Content extractData(ResultSet rs) throws SQLException, DataAccessException {
					Content data = new Content();
					while (rs.next()) {
						data.setContentBody(rs.getString(1));
						data.setContentType(new ContentType());
						data.getContentType().setTypeName(rs.getString("type_name"));
						data.getContentType().setContentTypeCd(rs.getInt("content_type_cd"));
						data.setHeight(rs.getInt("height"));
						data.setWidth(rs.getInt("width"));
						data.setMimeType(rs.getString("mime_type"));
					}
					return data;
				}
			});
		} catch (Exception e) {
			log.error("Error in fetching content body", e);
			throw new DSException("Unable to fetch content !");
		}
	}

	public List<Integer> fetchPptSlides(int contentId) throws DSException {
		try {
			String sql = env.getProperty("sql.fetch.allSlides");
			return jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, contentId);
					return ps;
				}
			}, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
		} catch (Exception e) {
			log.error("Error in fetching slides", e);
			throw new DSException("Unable to fetch content !");
		}
	}

	public String fetchSlideContent(int contentId, int slideNo) throws DSException {
		try {
			String sql = env.getProperty("sql.fetch.slideContent");
			return jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, contentId);
					ps.setInt(2, slideNo);
					return ps;
				}
			}, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					String data = null;
					while (rs.next()) {
						data = rs.getString(1);
					}
					return data;
				}
			});
		} catch (Exception e) {
			log.error("Error in fetching slide content", e);
			throw new DSException("Unable to fetch content !");
		}
	}

	public FetchContentResponse fetchContent() throws DSException {
		try {
			return jdbcTemplate.query(env.getProperty(""), new ResultSetExtractor<FetchContentResponse>() {

				@Override
				public FetchContentResponse extractData(ResultSet rs) throws SQLException, DataAccessException {
					FetchContentResponse data = new FetchContentResponse();
					while (rs.next()) {
						data.setUrl(env.getProperty("url.content") + rs.getInt("content_id"));
						data.setHeight(rs.getString("height"));
						data.setWidth(rs.getString("width"));
					}
					return data;
				}
			});
		} catch (Exception e) {
			log.error("Error in fetching slide content", e);
			throw new DSException("Unable to fetch content !");
		}
	}
}
