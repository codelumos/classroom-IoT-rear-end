package org.nju.iot.controller;


import org.nju.iot.VO.DeviceVO;
import org.nju.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")

public class DeviceManageController {
	@Autowired
	private DeviceService deviceService;

	//创建设备
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public long addDevice(@RequestParam String deviceName,
												@RequestParam int deviceType) throws Exception {
		return deviceService.addDevice(deviceName, deviceType);
	}

	//设备烧录
	@RequestMapping(value = "/program", method = RequestMethod.POST)
	public boolean deviceProgram(@RequestParam String credential) {
		return deviceService.deviceConnection(credential);
	}

	//设备列表
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public List<DeviceVO> getAllDevices() {
		return deviceService.getDeviceList();
	}

	//设备详情
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public DeviceVO getDeviceInfoById(@RequestParam long deviceId) {
		return deviceService.getDetail(deviceId);
	}

	//设备影子
	@RequestMapping(value = "/edit/shadow", method = RequestMethod.POST)
	public boolean getShadow(@RequestParam long deviceId) {
		return deviceService.getShadow(deviceId);
	}

	//删除设备
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean deleteDevices(@RequestParam List<Long> deviceIds) {
		return deviceService.deleteDevices(deviceIds);
	}

	//设备调试
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void deviceTest(@RequestParam String status,
												 @RequestParam long deviceId) {
		deviceService.deviceTest(status, deviceId);
	}
}
