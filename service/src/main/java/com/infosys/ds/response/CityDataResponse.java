package com.infosys.ds.response;

import java.util.HashSet;
import java.util.List;

public class CityDataResponse {

	HashSet<String> countrys;
	HashSet<String> states;
	HashSet<String> citys;

	public HashSet<String> getCountrys() {
		return countrys;
	}
	public void setCountrys(HashSet<String> countrys) {
		this.countrys = countrys;
	}
	public HashSet<String> getStates() {
		return states;
	}
	public void setStates(HashSet<String> states) {
		this.states = states;
	}
	public HashSet<String> getCitys() {
		return citys;
	}
	public void setCitys(HashSet<String> citys) {
		this.citys = citys;
	}
	

}
