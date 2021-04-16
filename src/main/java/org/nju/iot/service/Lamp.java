package org.nju.iot.service;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

public class Lamp extends Device {
    private MqttService mqttService;
    public Lamp(long device_id,String credential){
        super(device_id,credential);
        mqttService=new MqttService();
        mqttService.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost");
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //处理接收到的消息
                //根据设备影子更新设备状态
                String status=new String(message.getPayload());
                setStatus(status);

                System.out.println("Topic:"+topic+"--------Status:"+status+"------设备更新完成");
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });
        mqttService.subscribe("/shadow/get/"+String.valueOf(device_id),2);
    }
    //更新设备状态
    public void setStatus(String status){
        JSONObject json_state=JSONObject.parseObject(status);
        String state=json_state.getString("state");
        JSONObject json_status=JSONObject.parseObject(state);

        on_off=json_status.getBoolean("on_off");
    }
}
