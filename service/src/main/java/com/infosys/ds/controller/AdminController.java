package com.infosys.ds.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.response.CityDataResponse;
import com.infosys.ds.response.ProductRequest;
import com.infosys.ds.services.DsAdminServices;

@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	DsAdminServices dsAdminServices;
	
	
	

	@PostMapping(path="/getProfileId",produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer getProfileId(@RequestBody DeviceProfile deviceProfile) {
	
		
		DeviceProfile profileForRes = dsAdminServices.getProfileForRes(deviceProfile);
		
		return profileForRes.getProfileId();
		
	}
	
	@PostMapping(path="/Product",produces=MediaType.APPLICATION_JSON_VALUE)
	public BigInteger getProfileId(@RequestBody ProductRequest productRequest) {
	
		
	 BigInteger createDevice = dsAdminServices.createDevice(productRequest);
		
		return createDevice;
		
	}
	
}
