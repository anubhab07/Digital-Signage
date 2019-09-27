package com.infosys.ds.response;

import com.infosys.ds.model.DeviceProfile;
import com.infosys.ds.model.Location;

public class DeviceDataResponse {
	private Integer deviceId;
	private String deviceDesc;

	private Location locId;
	private DeviceProfile profileId;
	private Integer isActive;
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceDesc() {
		return deviceDesc;
	}
	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}
	public Location getLocId() {
		return locId;
	}
	public void setLocId(Location locId) {
		this.locId = locId;
	}
	public DeviceProfile getProfileId() {
		return profileId;
	}
	public void setProfileId(DeviceProfile profileId) {
		this.profileId = profileId;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	

}
