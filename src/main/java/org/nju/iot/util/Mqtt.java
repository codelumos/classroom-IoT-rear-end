package org.nju.iot.util;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Date;

/*
QoS0 代表，Sender 发送的一条消息，Receiver 最多能收到一次，也就是说 Sender 尽力向 Receiver 发送消息，如果发送失败，也就算了；
QoS1 代表，Sender 发送的一条消息，Receiver 至少能收到一次，也就是说 Sender 向 Receiver 发送消息，如果发送失败，会继续重试，直到 Receiver 收到消息为止，但是因为重传的原因，Receiver 有可能会收到重复的消息；
QoS2 代表，Sender 发送的一条消息，Receiver 确保能收到而且只收到一次，也就是说 Sender 尽力向 Receiver 发送消息，如果发送失败，会继续重试，直到 Receiver 收到消息为止，同时保证 Receiver 不会因为消息重传而收到重复的消息。
 */

public class Mqtt {
	/**
	 * 代理服务器ip地址
	 */
	public static final String BROKER_HOST = "ws://172.19.241.41:8083";
	/**
	 * 客户端唯一标识，时间戳生成
	 */
	public static final String CLIENT_ID = String.format("%ts", new Date());
	/**
	 * 客户端对象
	 */
	private MqttClient client;

	public Mqtt(String user_name) {
		//初始化客户端对象，建立连接
		try {
			client = new MqttClient(BROKER_HOST, CLIENT_ID, new MemoryPersistence());

			MqttConnectOptions options = new MqttConnectOptions();
			//设置用户名
			options.setUserName(user_name);
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(true);
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);

			// 连接
			client.connect(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 订阅主题
	 * topic 主题名
	 * qos Qos等级
	 */
	public void subscribe(String topic, int qos) {
		try {
			// 设置回调函数
			client.setCallback(new MqttCallback() {

				public void connectionLost(Throwable cause) {
					System.out.println("connectionLost");
				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					//收到消息后的处理
					System.out.println("topic:" + topic);
					System.out.println("Qos:" + message.getQos());
					System.out.println("message content:" + new String(message.getPayload()));

				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("deliveryComplete---------" + token.isComplete());
				}

			});
			client.subscribe(topic, qos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发布信息
	 * topic 主题名
	 * content 消息内容
	 * qos Qos等级
	 */
	public void publish(String topic, String content, int qos) {
		// 创建消息
		MqttMessage message = new MqttMessage(content.getBytes());
		// 设置消息的服务质量
		message.setQos(qos);
		// 发布消息
		try {
			client.publish(topic, message);
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