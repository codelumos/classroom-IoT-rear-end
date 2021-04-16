package org.nju.iot.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class DeviceManage {
    private static Map<String,Device> deviceMap=new HashMap<>();//设备集合
    private MqttService mqttService=new MqttService();//用于验证设备证书
    private String verifiedCredential;
    private String verifyResult;
    public DeviceManage(){
        verifiedCredential="";
        verifyResult="";

        mqttService.connect();
        mqttService.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("DeviceManage connectionLost");
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //收到验证结果消息
                if(topic.equals("/verify/get")){
                    String[] result=new String(message.getPayload()).split("@");
                    verifiedCredential=result[0];
                    verifyResult=result[1];
                }
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("DeviceManage deliveryComplete---------" + token.isComplete());
            }
        });
        mqttService.subscribe("/verify/get",1);
    }
    //发送消息，请求验证
    public boolean Verify(String credential){
        mqttService.publish("/verify/update",credential,1);

        if(verifiedCredential.equals(credential)&&verifyResult.equals("accept"))
            return true;
        else
            return false;
    }
    public void addDevice(long device_id,String credential,int type){
        if(type==1){
            Lamp lamp=new Lamp(device_id,credential);
            deviceMap.put(credential,lamp);
        }
        if(type==2){

        }
        if(type==3){

        }
    }
}
