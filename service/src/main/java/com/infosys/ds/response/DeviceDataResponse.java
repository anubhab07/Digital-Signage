package com.infosys.ds.response;

import com.infosys.ds.model.City;
import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.model.Location;

public class DeviceDataResponse  {
	private Integer device_Id;
	private String device_Desc;
	private String device_Type;
	private Integer loc_Id;
	private Integer city_id;
	private String country;
	private String state;
	private String city;
	private String area;
	private String locality;
	private Integer profile_id;
	private String profile_desc;
	private Integer height;
	private Integer width;
	private Integer isActive;
	public Integer getDevice_Id() {
		return device_Id;
	}
	public void setDevice_Id(Integer device_Id) {
		this.device_Id = device_Id;
	}
	public String getDevice_Desc() {
		return device_Desc;
	}
	public void setDevice_Desc(String device_Desc) {
		this.device_Desc = device_Desc;
	}
	public String getDevice_Type() {
		return device_Type;
	}
	public void setDevice_Type(String device_Type) {
		this.device_Type = device_Type;
	}
	public Integer getLoc_Id() {
		return loc_Id;
	}
	public void setLoc_Id(Integer loc_Id) {
		this.loc_Id = loc_Id;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Integer getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(Integer profile_id) {
		this.profile_id = profile_id;
	}
	public String getProfile_desc() {
		return profile_desc;
	}
	public void setProfile_desc(String profile_desc) {
		this.profile_desc = profile_desc;
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
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	

}
