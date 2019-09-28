package com.infosys.ds.model;

import java.util.List;

public class ContentDimenssion {
	private String type;
	private String base64Url;
	private String domId;
	private double startX;
	private double startY;
	private double width;
	private double height;
	private List<ContentDimenssion> childArr;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBase64Url() {
		return base64Url;
	}

	public void setBase64Url(String base64Url) {
		this.base64Url = base64Url;
	}

	public String getDomId() {
		return domId;
	}

	public void setDomId(String domId) {
		this.domId = domId;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public List<ContentDimenssion> getChildArr() {
		return childArr;
	}

	public void setChildArr(List<ContentDimenssion> childArr) {
		this.childArr = childArr;
	}

	@Override
	public String toString() {
		return "ContentDimenssion [type=" + type + ", base64Url=" + base64Url + ", domId=" + domId + ", startX="
				+ startX + ", startY=" + startY + ", width=" + width + ", height=" + height + ", childArr=" + childArr
				+ "]";
	}
}
