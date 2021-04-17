package org.nju.iot.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class DeviceManage {
    private static Map<Long,Device> deviceMap=new HashMap<>();//设备集合
    public DeviceManage(){
    }
    public static boolean hasDevice(long device_id){
        if(deviceMap.get(device_id)!=null)
            return true;
        else
            return false;
    }
    //验证
    public static void Verify(long device_id,String credential,int type){
        //发送消息，请求验证
        if(MqttService.hasClient("device_end"))
            MqttService.publish("device_end","/verify/update",device_id+"@"+credential+"@"+type,1);
        else
            System.out.println("未添加设备管理用client");
        try {
            Thread.sleep(2000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //添加设备
    public static void addDevice(long device_id,String credential,int type){
        if(type==1){
            Lamp lamp=new Lamp(device_id,credential);
            deviceMap.put(device_id,lamp);
            //添加设备用client
            MqttService.addClient(String.valueOf(device_id));
            MqttService.setCallback(String.valueOf(device_id),new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println(device_id+" connectionLost");
                }
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    //处理接收到的消息
                    //根据设备影子更新设备状态
//                    String status=new String(message.getPayload());
//                    DeviceManage.setDeviceStatus(device_id,status);

                    System.out.println("Topic:"+topic+"------设备更新完成");
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println(device_id+" deliveryComplete---------" + token.isComplete());
                }
            });
            //订阅自身的get topic
            MqttService.subscribe(String.valueOf(device_id),"/shadow/get/"+device_id,1);
        }
        if(type==2){

        }
        if(type==3){

        }
    }
    public static void setDeviceStatus(long device_id,String status){
        Device device=deviceMap.get(device_id);
        device.setStatus(status);
    }
}
