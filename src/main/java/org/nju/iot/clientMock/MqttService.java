package org.nju.iot.clientMock;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MqttService {
    //代理服务器ip地址
    public static final String BROKER_HOST = "ws://172.19.241.41:8083";
    //client集合
    public static Map<String, MqttClient> clientMap = new HashMap<>();

    public static void addClient(String client_id) {
        try {
            MqttClient client = new MqttClient(BROKER_HOST, client_id, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            //设置用户名
            options.setUserName(String.format("%tQ", new Date()));
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);

            // 连接
            client.connect(options);

            clientMap.put(client_id, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteClient(String client_id) {
        close(client_id);
        clientMap.remove(client_id);
    }

    public static boolean hasClient(String client_id) {
        return clientMap.get(client_id) != null;
    }

    public static void setCallback(String client_id, MqttCallback callback) {
        MqttClient client = clientMap.get(client_id);
        client.setCallback(callback);
    }

    public static void subscribe(String client_id, String topic, int qos) {
        MqttClient client = clientMap.get(client_id);
        try {

            client.subscribe(topic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void publish(String client_id, String topic, String content, int qos) {
        MqttClient client = clientMap.get(client_id);
        // 创建消息
        MqttMessage message = new MqttMessage(content.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        // 发布消息
        try {
            MqttTopic mqttTopic = client.getTopic(topic);
            MqttDeliveryToken token = mqttTopic.publish(message);
            token.waitForCompletion();
            //client.publish(topic, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connect(String client_id) {
        MqttClient client = clientMap.get(client_id);
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            //设置用户名
            options.setUserName(String.format("%tQ", new Date()));
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
     * 关闭连接
     */
    public static void close(String client_id) {
        MqttClient client = clientMap.get(client_id);
        try {
            client.disconnect();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
