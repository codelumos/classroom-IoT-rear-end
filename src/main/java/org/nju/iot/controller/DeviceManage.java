package org.nju.iot.controller;


import org.nju.iot.form.TestForm;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")

public class DeviceManage {
	@Autowired
	private DeviceService deviceService;

	//创建设备
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public long addDevice(@RequestParam long deviceId,
												@RequestParam String deviceName,
												@RequestParam int type) {
		return deviceService.addDevice(deviceId,deviceName,type);
// TODO: 2021/4/12 设备请求日志
	}

	//设备接入
	@RequestMapping(value = "/connection", method = RequestMethod.POST)
	public boolean deviceConnection(@RequestParam long deviceId,
																	@RequestParam String deviceName,
																	@RequestParam int type) {
		return deviceService.deviceConnection(deviceId, deviceName, type);
	}

	//设备调试
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void deviceTest(TestForm form) {
		deviceService.deviceTest(form);
	}

	//设备分组
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public List<DeviceEntity> getDeviceList() {
		return deviceService.getDeviceList();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean deleteDevice(@RequestParam long deviceId) {
		return deviceService.deleteDevice(deviceId);
	}

}
