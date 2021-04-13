package nju.iot.controller;


import nju.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")

public class DeviceManage {
	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public long addDevice(@RequestParam long deviceId,
												@RequestParam String deviceName,
												@RequestParam int type) {
		return deviceService.addDevice(deviceId,deviceName,type);
// TODO: 2021/4/12 设备请求日志
	}
}
