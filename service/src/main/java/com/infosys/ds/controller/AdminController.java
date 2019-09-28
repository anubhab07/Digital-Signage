package com.infosys.ds.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.response.DeviceDataResponse;
import com.infosys.ds.response.ProductRequest;
import com.infosys.ds.services.DsAdminServices;

@RestController
@RequestMapping("api/admin")
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	DsAdminServices dsAdminServices;
	
	
	

	@PostMapping(path="/getProfileId",produces=MediaType.APPLICATION_JSON_VALUE)
	public Integer getProfileId(@RequestBody DeviceProfile deviceProfile) {
	
		
		DeviceProfile profileForRes = dsAdminServices.getProfileForRes(deviceProfile);
		
		return profileForRes.getProfileId();
		
	}
	
	@PostMapping(path="/createDevice",produces=MediaType.APPLICATION_JSON_VALUE)
	public String getProfileId(@RequestBody ProductRequest productRequest) {
	
		
	 BigInteger createDevice = dsAdminServices.createDevice(productRequest);
		
		return"{\"device_id\":\""+createDevice+"\"}";
		
	}
	
	@PostMapping(path="/fetchProduct/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public DeviceDataResponse getProfileId(@PathVariable("id") Integer device_id) {
	
		
		DeviceDataResponse deviceInfo = dsAdminServices.getDeviceInfo(device_id);
		
		return deviceInfo;
		
	}
	@GetMapping(path="/fetchAllDevices",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DeviceDataResponse> getProfileId() {
	
		
		List<DeviceDataResponse> deviceInfo = dsAdminServices.getAllDevicesInfo();
		
		return deviceInfo;
		
	}
	
}
