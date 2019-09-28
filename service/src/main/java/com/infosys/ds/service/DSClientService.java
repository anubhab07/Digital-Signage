package com.infosys.ds.service;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Content;
import com.infosys.ds.repository.DSClientRepository;
import com.infosys.ds.util.ContentUtils;

@Service
public class DSClientService {
	private Logger log = LoggerFactory.getLogger(DSClientService.class);
	@Autowired
	private Environment env;
	@Autowired
	private DSClientRepository dsClientRepository;

	public Content fetchContentBody(int id) throws DSException {
		try {
			return dsClientRepository.fetchContentBody(id);
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in fetching content body", e);
			throw new DSException("Unable to fetch content !");
		}
	}

	public String getPptContent(int contentId) throws DSException {
		try {
			List<Integer> slides = dsClientRepository.fetchPptSlides(contentId);
			StringBuilder content = new StringBuilder();
			AtomicInteger count = new AtomicInteger(1);
			slides.forEach(slide -> {
				int cnt = count.getAndAdd(1);
				content.append("<div class=\"carousel-item " + (cnt == 1 ? "active" : "")
						+ "\"><img class=\"d-block w-100\" src=\"" + env.getProperty("url.ppt.slides") + contentId + "/"
						+ slide + "\"></div>");
			});
			return ContentUtils
					.readFileData(Paths.get(getClass().getClassLoader().getResource("static/ppt.html").toURI()))
					.replace("__INNER_CONTENT__", content.toString());
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in ppt content rendering", e);
			throw new DSException("Unable to get content !");
		}
	}

	public String getSlideContent(int contentId, int slideNo) throws DSException {
		try {
			return dsClientRepository.fetchSlideContent(contentId, slideNo);
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in fetching slide content", e);
			throw new DSException("Unable to fetch slide content !");
		}
	}
}
