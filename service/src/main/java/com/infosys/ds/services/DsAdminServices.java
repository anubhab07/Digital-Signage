package com.infosys.ds.services;

import java.math.BigInteger;
import java.util.List;

import com.infosys.ds.model.City;
import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.model.Location;
import com.infosys.ds.response.CityDataResponse;
import com.infosys.ds.response.DeviceDataResponse;
import com.infosys.ds.response.ProductRequest;

public interface DsAdminServices {

	//CityDataResponse getLoadingData();

	DeviceProfile getProfileForRes(DeviceProfile deviceProfile);

	City getCity(City city);
	Location getLocation(Location location);
	
	BigInteger createDevice(ProductRequest productRequest);

	DeviceDataResponse getDeviceInfo(Integer device_id);

	List<DeviceDataResponse> getAllDevicesInfo();
}
