package org.nju.iot.controller;


import org.nju.iot.form.DeviceForm;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")

public class DeviceManageController {
	@Autowired
	private DeviceService deviceService;

	//创建设备
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public long addDevice(@RequestBody DeviceForm form) {
		return deviceService.addDevice(form);
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
	public void deviceTest(@RequestParam String status,
												 @RequestParam long deviceId) {
		deviceService.deviceTest(status, deviceId);
	}

	//设备列表
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public List<DeviceEntity> getDeviceList() {
		return deviceService.getDeviceList();
	}

	//设备详情
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public DeviceEntity getDetail(@RequestParam long deviceId) {
		return deviceService.getDetail(deviceId);
	}

	//设备影子
	@RequestMapping(value = "/shadow", method = RequestMethod.GET)
	public String getShadow(@RequestParam long deviceId) {
		return deviceService.getShadow(deviceId);
	}

	//删除设备
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean deleteDevice(@RequestParam long deviceId) {
		return deviceService.deleteDevice(deviceId);
	}

}
