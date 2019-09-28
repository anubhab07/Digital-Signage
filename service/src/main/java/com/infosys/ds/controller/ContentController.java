package com.infosys.ds.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.ds.model.ContentDimenssion;
import com.infosys.ds.service.ContentService;

@RestController
@CrossOrigin
public class ContentController {
	private Logger log = LoggerFactory.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;

	@PostMapping("/saveContent")
	public @ResponseBody Map<String, Object> saveContent(@RequestBody ContentDimenssion data, HttpServletResponse res) {
		try {
			log.info("Conten: {}", new ObjectMapper().writeValueAsString(data));
			Map<String, Object> response = new HashMap<>();
			response.put("data", contentService.saveContent(data));
			return response;
		} catch (Exception e) {
			log.error("Error in saving content", e);
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}
	}
}
