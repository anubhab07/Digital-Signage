package com.infosys.ds.model;

public class City {
	private Integer cityId;
	private String country;
	private String state;
	private String city;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	public City( String country, String state, String city) {
		super();
		
		this.country = country;
		this.state = state;
		this.city = city;
	}
	public City() {
		// TODO Auto-generated constructor stub
	}
	

}
