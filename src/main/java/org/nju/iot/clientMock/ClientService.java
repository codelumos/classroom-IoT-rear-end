package org.nju.iot.clientMock;

//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.springframework.stereotype.Service;

//public class ClientService {
//    private static DeviceManage deviceManage=new DeviceManage();
//    /**
//     * 新建设备：由管理类向连接用TOPIC发送设备证书，验证证书成功后，新建Device类，初始化设备信息，建立连接，加入设备集合
//     * 设备证书
//     * 设备ID
//     */
//    public boolean createDevice(long device_id,String credential,int type){
//        boolean verifyResult=deviceManage.Verify(credential);
//        if(verifyResult)
//            deviceManage.addDevice(device_id,credential,type);
//        return verifyResult;
//    }
//}
