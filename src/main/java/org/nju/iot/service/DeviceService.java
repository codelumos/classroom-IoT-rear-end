package org.nju.iot.service;

import org.nju.iot.VO.DeviceVO;
import org.nju.iot.clientMock.DeviceManage;
import org.nju.iot.clientMock.MqttService;
import org.nju.iot.dao.DeviceDao;
//import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.model.DeviceEntity;
//import org.nju.iot.model.RequestLogEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private RequestLogDao requestLogDao;

	private static final int QOS1 = 1;
	private static final int QOS2 = 2;
	private static final int QOS3 = 3;


	//添加设备
	public long addDevice(String deviceName, int deviceType) {
		DeviceEntity device = new DeviceEntity();
		device.setDeviceName(deviceName);
		device.setDeviceType(deviceType);
		device.setCreateTime(new Timestamp(System.currentTimeMillis()));
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		device.setCredential(uuid);
		return deviceDao.save(device).getId();
	}

	//设备烧录
	public boolean deviceConnection(String credential) {
		DeviceEntity deviceEntity= deviceDao.findByCredential(credential);
		//验证并添加
		DeviceManage.Verify(deviceEntity.getId(),deviceEntity.getCredential(),deviceEntity.getDeviceType());
		//查看是否成功添加设备
		if(DeviceManage.hasDevice(deviceEntity.getId())){
			System.out.println("设备添加成功");
			return true;
		}
		else{
			System.out.println("设备添加失败");
			return false;
		}
	}

	//设备调试
	public void deviceTest(String status, long deviceId){
		setStatus(status, QOS1, deviceId);
	}

	//获取设备列表
	public List<DeviceVO> getDeviceList() {
		List<DeviceEntity> entities = deviceDao.findAll();
		List<DeviceVO> deviceVOS = new ArrayList<>();
		entities.forEach(e -> {
			DeviceVO deviceVO = new DeviceVO();
			deviceVO.setId(e.getId());
			deviceVO.setDeviceName(e.getDeviceName());
			deviceVO.setDeviceType(e.getDeviceType());
			deviceVO.setCreateTime(e.getCreateTime());
			deviceVOS.add(deviceVO);
		});
		return deviceVOS;
	}

	//获取单个设备详情
	public DeviceVO getDetail(long deviceId){
		DeviceEntity deviceEntity = deviceDao.getOne(deviceId);
		DeviceVO deviceVO = new DeviceVO();
		BeanUtils.copyProperties(deviceEntity, deviceVO);
		return deviceVO;
	}

	//设备影子
	public boolean getShadow(long deviceId) {
		return true;
	}

	//删除设备
	public boolean deleteDevices(List<Long> deviceIds) {
		deviceIds.forEach(d ->deviceDao.deleteById(d));
		return true;
	}

	//更新设备影子表，向该设备对应get topic发送消息，通知设备更新状态
	private void setStatus(String status,int qos, long deviceId){
		//更新设备影子表
		DeviceEntity deviceEntity = deviceDao.getOne(deviceId);
		deviceEntity.setStatus(status);
		deviceDao.updateStatus(status,deviceId);

		//向该设备对应get topic发送消息，通知设备更新状态
		MqttService.publish(String.valueOf(deviceId),"/shadow/get/" + deviceId,status,qos);
	}


}
