package org.nju.iot.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.nju.iot.dao.DeviceDao;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.form.DeviceForm;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.model.RequestLogEntity;
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
	@Autowired
	private MqttService mqttService;

	private static final int QOS1 = 1;
	private static final int QOS2 = 2;
	private static final int QOS3 = 3;


	//添加设备
	public long addDevice(DeviceForm form) {
		DeviceEntity device = new DeviceEntity();
		device.setId(form.getId());
		device.setDeviceName(form.getDeviceName());
		device.setDeviceType(form.getDeviceType());
		device.setCreateTime(new Timestamp(System.currentTimeMillis()));
		device.setStatus(form.getStatus());
		device.setGroupId(form.getGroupId());
		deviceDao.save(device);

		//
		mqttService.connect();
		mqttService.setCallback(new MqttCallback() {
			public void connectionLost(Throwable cause) {
				System.out.println("add connectionLost");
			}
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				//收到验证请求，查询数据库，返回验证结果
				if(topic.equals("/verify/update")){
					String credential=new String(message.getPayload());
					String verifyResult="";
					if(deviceDao.validateApprove(credential))
						verifyResult="accept";
					else
						verifyResult="refuse";
					mqttService.publish("/verify/get",credential+"@"+verifyResult,1);
				}
			}
			public void deliveryComplete(IMqttDeliveryToken token) {
				System.out.println("deliveryComplete---------" + token.isComplete());
			}
		});
		//订阅连接用topic
		mqttService.subscribe("/verify/update",1);

		ClientService clientService=new ClientService();
		clientService.createDevice(form.getId(),form.getApprove(),form.getDeviceType());
		return device.getId();
	}

	//设备接入
	public boolean deviceConnection(long deviceId, String deviceName, int type) {
		RequestLogEntity log = new RequestLogEntity();
		log.setId(deviceId);
		log.setDeviceId(deviceId);
		log.setRequestTime(new Timestamp(System.currentTimeMillis()));
		requestLogDao.save(log);
		return true;
	}

	//设备调试
	public void deviceTest(String status, long deviceId){
		setStatus(status, QOS1, deviceId);
	}

	//获取设备列表
	public List<DeviceEntity> getDeviceList() {
		return deviceDao.findAll();
	}

	//获取单个设备详情
	public DeviceEntity getDetail(long deviceId){
		return deviceDao.getOne(deviceId);
	}

	//设备影子
	public String getShadow(long deviceId) {
		return deviceDao.getOne(deviceId).getStatus();
	}

	//删除设备
	public boolean deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
		return true;
	}

	//更新设备影子表，向该设备对应get topic发送消息，通知设备更新状态
	private void setStatus(String status,int qos, long deviceId){
		//更新设备影子表
		DeviceEntity deviceEntity = deviceDao.getOne(deviceId);
		deviceEntity.setStatus(status);
		deviceDao.updateStatus(status,deviceId);

		//向该设备对应get topic发送消息，通知设备更新状态
		mqttService.publish("/shadow/get/" + deviceId,status,qos);
		mqttService.close();
	}

}
