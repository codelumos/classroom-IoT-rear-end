package org.nju.iot.service;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqttService {
	//代理服务器ip地址
	public static final String BROKER_HOST = "ws://172.19.241.41:8083";
	//客户端唯一标识，时间戳生成
	public final String CLIENT_ID = String.format("%tQ", new Date());
	//客户端对象
	private MqttClient client;

	public void setCallback(MqttCallback callback){
		client.setCallback(callback);
	}

	public void subscribe(String topic, int qos) {
		try {
			// 设置回调函数
//			client.setCallback(new MqttCallback() {
//				public void connectionLost(Throwable cause) {
//					System.out.println("mqtt connectionLost");
//				}
//
//				public void messageArrived(String topic, MqttMessage message) throws Exception {
//					//收到消息后的处理
//					System.out.println("topic:" + topic);
//					System.out.println("Qos:" + message.getQos());
//					System.out.println("message content:" + new String(message.getPayload()));
//
//				}
//
//				public void deliveryComplete(IMqttDeliveryToken token) {
//					System.out.println("deliveryComplete---------" + token.isComplete());
//				}
//			});
			client.subscribe(topic, qos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publish(String topic, String content, int qos) {
		// 创建消息
		MqttMessage message = new MqttMessage(content.getBytes());
		// 设置消息的服务质量
		message.setQos(qos);
		// 发布消息
		try {
			MqttTopic mqttTopic=client.getTopic(topic);
			MqttDeliveryToken token=mqttTopic.publish(message);
			token.waitForCompletion();
			//client.publish(topic, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect(){
		try {
			client = new MqttClient(BROKER_HOST, CLIENT_ID, new MemoryPersistence());
			MqttConnectOptions options = new MqttConnectOptions();
			//设置用户名
			options.setUserName(String.format("%tQ", new Date()));
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(true);
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(100);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);

			// 连接
			client.connect(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			client.disconnect();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
