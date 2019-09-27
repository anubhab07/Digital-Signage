package com.infosys.ds.model;

public class DeviceProfile {
private Integer profileId;
private String profileDesc;
private Integer height;
private Integer width;
public DeviceProfile() {
	// TODO Auto-generated constructor stub
}
public DeviceProfile(String profileDesc, Integer height, Integer width) {
	super();
	this.profileDesc = profileDesc;
	this.height = height;
	this.width = width;
}
public Integer getProfileId() {
	return profileId;
}
public void setProfileId(Integer profileId) {
	this.profileId = profileId;
}
public String getProfileDesc() {
	return profileDesc;
}
public void setProfileDesc(String profileDesc) {
	this.profileDesc = profileDesc;
}
public Integer getHeight() {
	return height;
}
public void setHeight(Integer height) {
	this.height = height;
}
public Integer getWidth() {
	return width;
}
public void setWidth(Integer width) {
	this.width = width;
}
}
