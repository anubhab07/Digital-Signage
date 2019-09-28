package com.infosys.ds.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.ds.exception.DSException;
import com.infosys.ds.model.Slide;

public class ContentUtils {
	private static final Logger log = LoggerFactory.getLogger(ContentUtils.class);

	public static String base64Encode(byte[] content) {
		return Base64.getEncoder().encodeToString(content);
	}

	public static byte[] base64Decode(String encodedContent) {
		return Base64.getDecoder().decode(encodedContent);
	}

	public static String getConetentType(byte[] content) throws DSException {
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(content));
		try {
			return URLConnection.guessContentTypeFromStream(is);
		} catch (IOException e) {
			log.error("Error in finding content type", e);
			throw new DSException("Unable to load content !");
		}
	}

	public static String readFileData(Path filePath) throws DSException {
		try {
			return new String(Files.readAllBytes(filePath));
		} catch (Exception e) {
			log.error("Error in finding content type", e);
			throw new DSException("Unable to load content !");
		}
	}

	public static List<Slide> ppt2Png(String base64Data) throws DSException {
		try {
			List<Slide> data = new ArrayList<>();
			XMLSlideShow ppt = new XMLSlideShow(new ByteArrayInputStream(ContentUtils.base64Decode(base64Data)));
			// getting the dimensions and size of the slide
			Dimension pgsize = ppt.getPageSize();
			XSLFSlide[] slide = ppt.getSlides();

			BufferedImage img = null;

			for (int i = 0; i < slide.length; i++) {
				img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();

				// clear the drawing area
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

				// render
				slide[i].draw(graphics);
				// creating an image file as output
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(img, "png", out);
				out.close();
				Slide sld = new Slide();
				sld.setSlideNo(i + 1);
				sld.setSlideContent(ContentUtils.base64Encode(out.toByteArray()));
				data.add(sld);
			}
			return data;
		} catch (Exception e) {
			log.error("Error in parsing ppt content", e);
			throw new DSException("Unable to save content !");
		}
	}
}
