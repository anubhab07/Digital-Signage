package com.infosys.DigitalSignage.model;

import java.time.LocalDateTime;

public class DeviceHeartBeat {
private Integer deviceId;
private LocalDateTime timeStamp;
private String log;
public Integer getDeviceId() {
	return deviceId;
}
public void setDeviceId(Integer deviceId) {
	this.deviceId = deviceId;
}
public LocalDateTime getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(LocalDateTime timeStamp) {
	this.timeStamp = timeStamp;
}
public String getLog() {
	return log;
}
public void setLog(String log) {
	this.log = log;
}
}
