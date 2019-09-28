package com.infosys.ds.model;

public class Slide {
	private int slideNo;
	private String slideContent;

	public int getSlideNo() {
		return slideNo;
	}

	public void setSlideNo(int slideNo) {
		this.slideNo = slideNo;
	}

	public String getSlideContent() {
		return slideContent;
	}

	public void setSlideContent(String slideContent) {
		this.slideContent = slideContent;
	}

	@Override
	public String toString() {
		return "Slide [slideNo=" + slideNo + ", slideContent=" + slideContent + "]";
	}
}
