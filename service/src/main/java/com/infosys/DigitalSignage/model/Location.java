/**
 * 
 */
package com.infosys.DigitalSignage.model;

/**
 * @author Artha
 *
 */
public class Location {
	
	private Integer locationId;
	private Integer cityId;
	private String area;
	private String locality;
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	

}
