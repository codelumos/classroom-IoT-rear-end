package org.nju.iot.service;

import org.nju.iot.dao.DeviceDao;
import org.nju.iot.model.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;

	public long addDevice(long deviceId, String deviceName, int type) {
		DeviceEntity device = new DeviceEntity();
		device.setId(deviceId);
		device.setDeviceName(deviceName);
		device.setDeviceType(type);
		device.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return deviceDao.save(device).getId();
	}
}
