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
			String htmlContent = "<html>\r\n" + "<head>\r\n" + "<link rel=\"stylesheet\"\r\n"
					+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"\r\n"
					+ "	integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\"\r\n"
					+ "	crossorigin=\"anonymous\">\r\n" + "</head>\r\n" + "<body style=\"margin: 0; padding: 0\">\r\n"
					+ "	<div id=\"carouselExampleSlidesOnly\" class=\"carousel slide\"\r\n"
					+ "		data-ride=\"carousel\" data-interval=\"1000\">\r\n"
					+ "		<div class=\"carousel-inner\">__INNER_CONTENT__</div>\r\n" + "	</div>\r\n"
					+ "	<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"\r\n"
					+ "		integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\"\r\n"
					+ "		crossorigin=\"anonymous\"></script>\r\n" + "	<script\r\n"
					+ "		src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"\r\n"
					+ "		integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\"\r\n"
					+ "		crossorigin=\"anonymous\"></script>\r\n" + "	<script\r\n"
					+ "		src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"\r\n"
					+ "		integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\"\r\n"
					+ "		crossorigin=\"anonymous\"></script>\r\n" + "</body>\r\n" + "</html>";
			List<Integer> slides = dsClientRepository.fetchPptSlides(contentId);
			StringBuilder content = new StringBuilder();
			AtomicInteger count = new AtomicInteger(1);
			slides.forEach(slide -> {
				int cnt = count.getAndAdd(1);
				content.append("<div class=\"carousel-item " + (cnt == 1 ? "active" : "")
						+ "\"><img class=\"d-block w-100\" src=\"" + env.getProperty("url.ppt.slides") + contentId + "/"
						+ slide + "\"></div>");
			});
			return htmlContent.replace("__INNER_CONTENT__", content.toString());
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
