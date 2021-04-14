package org.nju.iot.service;

import org.nju.iot.dao.DeviceDao;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.model.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private RequestLogDao requestLogDao;

	public long addDevice(long deviceId, String deviceName, int type) {
		DeviceEntity device = new DeviceEntity();
		device.setId(deviceId);
		device.setDeviceName(deviceName);
		device.setDeviceType(type);
		device.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return deviceDao.save(device).getId();
	}

	public boolean deviceConnection(long deviceId, String deviceName, int type) {
		RequestLog log = new RequestLog();
		log.setId(deviceId);
		log.setDeviceName(deviceName);
		log.setRequestTime(new Timestamp(System.currentTimeMillis()));
		requestLogDao.save(log);
		return true;
	}

	public void deviceTest(){
	}

	public List<DeviceEntity> getDeviceList() {
		return deviceDao.findAll();
	}

	public boolean deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
		return true;
	}

}
