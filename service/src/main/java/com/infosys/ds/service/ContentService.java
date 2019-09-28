package com.infosys.ds.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Content;
import com.infosys.ds.model.ContentDimenssion;
import com.infosys.ds.model.ContentType;
import com.infosys.ds.repository.ContentRepository;
import com.infosys.ds.util.ContentUtils;

@Service
public class ContentService {
	private Logger log = LoggerFactory.getLogger(ContentService.class);
	@Autowired
	private ContentRepository contentRepository;
	@Autowired
	private Environment env;

	public String saveContent(ContentDimenssion content) throws DSException {
		try {
			StringBuilder htmlData = new StringBuilder();
			Content data = new Content();
			data.setContentBody(saveContentList(content.getChildArr()));
			data.setContentType(new ContentType());
			data.getContentType().setContentTypeCd(1);
			data.setHeight((int) content.getHeight());
			data.setWidth((int) content.getWidth());
			data = saveContentBody(data);

			htmlData.append("<iframe width=\"");
			htmlData.append(content.getWidth());
			htmlData.append("\" height=\"");
			htmlData.append(content.getHeight());
			htmlData.append("\" src=\"");
			htmlData.append(env.getProperty("url.content") + data.getContentId());
			htmlData.append("\" style=\"overflow:hidden;border:none\" />");

			return htmlData.toString();
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in saving content", e);
			throw new DSException("Unable to save content !");
		}
	}

	private Content saveContentBody(Content content) throws DSException {
		try {
			if (content.getContentType().getContentTypeCd() == 2) {
				content.setContentId(
						contentRepository.saveContent(null, 2, content.getHeight(), content.getWidth(), 1));
				contentRepository.saveSlides(content.getContentId(), ContentUtils.ppt2Png(content.getContentBody()));
			} else {
				content.setContentId(contentRepository.saveContent(content.getContentBody(),
						content.getContentType().getContentTypeCd(), content.getHeight(), content.getWidth(), 1));
			}
			return content;
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in saving content", e);
			throw new DSException("Unable to save content !");
		}
	}

	private String saveContentList(List<ContentDimenssion> data) throws DSException {
		try {
			StringBuilder htmlData = new StringBuilder();
			htmlData.append("<div style=\"position:absolute;\">");
			for (ContentDimenssion content : data)
				htmlData.append(saveContentData(content));
			htmlData.append("</div>");
			return htmlData.toString();
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in saving content data", e);
			throw new DSException("Unable to save content !");
		}
	}

	private String saveContentData(ContentDimenssion content) throws DSException {
		if (content == null)
			return "";
		try {
			if (content.getChildArr() != null & !content.getChildArr().isEmpty())
				return saveContentList(content.getChildArr());
			Content contentBody = new Content();
			contentBody.setContentBody(content.getBase64Url());
			contentBody.setContentType(new ContentType());
			contentBody.getContentType().setContentTypeCd(getContentTypeCode(content.getType()));
			contentBody.setHeight((int) content.getHeight());
			contentBody.setWidth((int) content.getWidth());
			contentBody = saveContentBody(contentBody);
			StringBuilder htmlData = new StringBuilder();
			htmlData.append("<div style=\"position:absolute;top:");
			htmlData.append(content.getStartY() + "px");
			htmlData.append(";left:");
			htmlData.append(content.getStartX() + "px");
			htmlData.append(";right:");
			htmlData.append((content.getStartX() + content.getWidth()) + "px");
			htmlData.append(";bottom:");
			htmlData.append((content.getStartY() + content.getHeight()) + "px");
			htmlData.append(";\">");
			htmlData.append("<iframe width=\"");
			htmlData.append(content.getWidth());
			htmlData.append("\" height=\"");
			htmlData.append(content.getHeight());
			htmlData.append("\" src=\"");
			htmlData.append(env.getProperty("url.content") + contentBody.getContentId());
			htmlData.append("\" style=\"overflow:hidden;border:none\" /></div>");
			return htmlData.toString();
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in saving content data", e);
			throw new DSException("Unable to save content !");
		}
	}

	private int getContentTypeCode(String contentTypeName) throws DSException {
		try {
			if (contentTypeName.equalsIgnoreCase("text/html"))
				return 1;
			if (contentTypeName
					.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
				return 2;
			if (contentTypeName.toLowerCase().startsWith("image"))
				return 3;
			if (contentTypeName.toLowerCase().startsWith("video"))
				return 4;
			if (contentTypeName.equalsIgnoreCase("text/plain"))
				return 5;
			else
				throw new DSException("Content type " + contentTypeName + " not supported !");
		} catch (DSException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error in saving content data", e);
			throw new DSException("Unable to save content !");
		}
	}
}
