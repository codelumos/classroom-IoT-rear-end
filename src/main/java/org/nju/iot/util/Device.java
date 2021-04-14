package org.nju.iot.util;


import com.alibaba.fastjson.JSONObject;

public class Device {
	public final String device_id;
	public Device(String device_id){
		this.device_id=device_id;
	}

	//更新设备影子表，向该设备对应get topic发送消息，通知设备更新状态
	public void setStatus(String status,int qos){
		//更新设备影子表

		//向该设备对应get topic发送消息，通知设备更新状态
		Mqtt mqtt=new Mqtt(device_id);
		mqtt.publish("/shadow/get/"+device_id,status,qos);
		mqtt.close();
	}
	//通过设备影子表获取设备状态
	public String getStatus(){
		String status="";
		return status;
	}
	//打开设备
	public void turn_on(){
		//获取设备影子
		String status="";
		//修改on_off值
		JSONObject jsonObject=JSONObject.parseObject(status);
		jsonObject.put("on_off","on");
		setStatus(jsonObject.toJSONString(),0);
	}
	//关闭设备
	public void turn_off(){
		//获取设备影子
		String status="";
		//修改on_off值
		JSONObject jsonObject=JSONObject.parseObject(status);
		jsonObject.put("on_off","off");
		setStatus(jsonObject.toJSONString(),0);
	}
}
