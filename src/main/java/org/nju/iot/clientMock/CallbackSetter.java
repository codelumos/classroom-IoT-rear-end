package org.nju.iot.clientMock;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.nju.iot.constant.QOS;

public class CallbackSetter {
    public static void setDeviceCallback(String device_id){
        //添加设备用client
        MqttService.addClient(device_id);
        MqttService.setCallback(device_id,new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println(device_id+" connectionLost");
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //处理接收到的消息
                //根据设备影子更新设备状态
                String status=new String(message.getPayload());
                DeviceManage.setDeviceStatus(Long.parseLong(device_id),status);

                System.out.println("Topic:"+topic+"------设备更新完成");
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println(device_id+" deliveryComplete---------" + token.isComplete());
            }
        });
        //订阅自身的get topic
        MqttService.subscribe(device_id,"/shadow/get/"+device_id, QOS.QOS1);
    }
}
