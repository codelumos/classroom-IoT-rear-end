package org.nju.iot.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.nju.iot.clientMock.DeviceManage;
import org.nju.iot.clientMock.MqttService;
import org.nju.iot.dao.DeviceDao;
import org.nju.iot.utils.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MqttConfig {
    public static String client_id1="rear_end"+String.format("%ts", new Date());;
    public static String client_id2="device_end"+String.format("%ts", new Date());
    @Bean
    public void init(){
        //新建后端用client
        MqttService.addClient(client_id1);
        MqttService.setCallback(client_id1,new MqttCallback() {
            public void connectionLost(Throwable cause) {
                MqttService.connect(client_id1);
                System.out.println("rear_end connectionLost");
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //收到验证请求，查询数据库，若验证成功则发布验证结果
                System.out.println("rear_end message arrived");

                ApplicationContext context = SpringUtil.context;  //获取Spring容器
                DeviceDao dd = context.getBean(DeviceDao.class);  //获取bean

                String verifyQuery=new String(message.getPayload());
                String[] query=verifyQuery.split("@");
                String device_id=query[0];
                String credential=query[1];
                if(dd.validateApprove(credential)==Long.parseLong(device_id)){//验证成功
                    System.out.println("验证成功");
                    //订阅对应设备update topic
                    MqttService.subscribe(client_id1,"/shadow/update/"+device_id,1);
                    //发布验证结果
                    MqttService.publish(client_id1,"/verify/get",verifyQuery,1);
                }
                else {//验证失败
                    System.out.println("验证失败");
                    MqttService.publish(client_id1,"/verify/get","refuse",1);
                }
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("rear_end deliveryComplete---------" + token.isComplete());
            }
        });
        //订阅连接用topic
        MqttService.subscribe(client_id1,"/verify/update",1);

        //设备管理端用client
        MqttService.addClient(client_id2);
        MqttService.setCallback(client_id2,new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("DeviceManage connectionLost");
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //收到验证结果消息
                System.out.println("device manage message arrived");
                String verifyResult=new String(message.getPayload());
                String[] result=verifyResult.split("@");
                if(!verifyResult.equals("refuse")){
                    //添加设备用client
                    MqttService.addClient(result[0]);
                    //添加设备
                    DeviceManage.addDevice(Long.parseLong(result[0]),result[1],Integer.parseInt(result[2]));
                    System.out.println("添加完毕");
                }
                else
                    System.out.println("添加设备被拒绝");
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("DeviceManage deliveryComplete---------" + token.isComplete());
            }
        });
        MqttService.subscribe(client_id2,"/verify/get",1);
    }
}
