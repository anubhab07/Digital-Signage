package com.infosys.ds.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Content;
import com.infosys.ds.service.DSClientService;
import com.infosys.ds.util.ContentUtils;

@Controller
@CrossOrigin
public class DSClientController {
	private Logger log = LoggerFactory.getLogger(DSClientController.class);
	@Autowired
	private DSClientService dsClientService;

	@GetMapping("/content/{contentId}")
	public void getContentBody(@PathVariable("contentId") int contentId, HttpServletResponse response) {
		String errorContent = null;
		try {
			Content content = dsClientService.fetchContentBody(contentId);
			try {
				byte[] data = null;
				String contentType = null;
				switch (content.getContentType().getContentTypeCd()) {
				case 1:
					data = content.getContentBody().getBytes();
					contentType = MimeTypeUtils.TEXT_HTML_VALUE;
					break;
				case 2:
					data = dsClientService.getPptContent(contentId).getBytes();
					contentType = MimeTypeUtils.TEXT_HTML_VALUE;
					break;
				case 5:
					data = content.getContentBody().getBytes();
					contentType = MimeTypeUtils.TEXT_PLAIN_VALUE;
					break;
				default:
					data = ContentUtils.base64Decode(content.getContentBody());
					contentType = ContentUtils.getConetentType(data);
					if (contentType != null && (contentType.toLowerCase().startsWith("image")
							|| contentType.toLowerCase().contains("png") || contentType.toLowerCase().contains("jpg")))
						data = ContentUtils.resize(data, content.getWidth(), content.getHeight());
				}
				response.setContentType(contentType);
				OutputStream os = response.getOutputStream();
				os.write(data);
				os.flush();
			} catch (Exception e) {
				log.error("Error in writing response", e);
			}
		} catch (DSException e) {
			log.error("Error in fetching content body: {}", e.getMessage());
			errorContent = "<h2 style='color: red'>" + e.getMessage() + "</h2>";
		} catch (Exception e) {
			log.error("Error in fetching content body: {}", e);
			errorContent = "<h2 style='color: red'>Unable to fetch content !</h2>";
		}
		if (!StringUtils.isEmpty(errorContent)) {
			response.setContentType(MimeTypeUtils.TEXT_HTML_VALUE);
			try {
				OutputStream os = response.getOutputStream();
				os.write(errorContent.getBytes());
				os.flush();
			} catch (Exception e) {
				log.error("Error in writing response", e);
			}
		}
	}

	@GetMapping("/ppt/{contentId}/{slideNo}")
	public void showPptContent(@PathVariable("contentId") int contentId, @PathVariable("slideNo") int slideNo,
			HttpServletResponse response) {
		try {
			byte[] data = ContentUtils.base64Decode(dsClientService.getSlideContent(contentId, slideNo));
			response.setContentType(ContentUtils.getConetentType(data));
			OutputStream os = response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (Exception e) {
			log.error("Error in writing response", e);
		}
	}
}
