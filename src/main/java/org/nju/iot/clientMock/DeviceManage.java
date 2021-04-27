package org.nju.iot.clientMock;

import org.nju.iot.config.MqttConfig;
import org.nju.iot.constant.Lock;
import org.nju.iot.constant.QOS;

import java.util.HashMap;
import java.util.Map;

public class DeviceManage {
    private static final Map<Long, Device> deviceMap = new HashMap<>();// 设备集合

    public DeviceManage() {
    }

    public static boolean hasDevice(long device_id) {
        return deviceMap.get(device_id) != null;
    }

    // 验证
    public static void Verify(long device_id, String credential, int type) {
        // 发送消息，请求验证
        if (MqttService.hasClient(MqttConfig.device_end_id))
            MqttService.publish(MqttConfig.device_end_id, "/verify/update", device_id + "@" + credential + "@" + type, QOS.QOS1);
        else
            System.out.println("未添加设备管理用client");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Lock.setLock(false);
    }

    // 添加设备
    public static void addDevice(long device_id, String credential, int type) {
        if (type == 0) {
            Lamp lamp = new Lamp(device_id, credential);
            deviceMap.put(device_id, lamp);
            CallbackSetter.setDeviceCallback(String.valueOf(device_id));
        }
        if (type == 1) {
            AirCondition airCondition = new AirCondition(device_id, credential);
            deviceMap.put(device_id, airCondition);
            CallbackSetter.setDeviceCallback(String.valueOf(device_id));
        }
        if (type == 2) {
            Projector projector = new Projector(device_id, credential);
            deviceMap.put(device_id, projector);
            CallbackSetter.setDeviceCallback(String.valueOf(device_id));
        }
        // 通知更新日志表
        MqttService.publish(String.valueOf(device_id), "/shadow/update/" + device_id, getDeviceStatus(device_id), QOS.QOS1);
    }

    // 删除设备
    public static void deleteDevice(long device_id) {
        if (hasDevice(device_id))
            deviceMap.remove(device_id);
    }

    // 设置设备状态
    public static void setDeviceStatus(long device_id, String status) {
        Device device = deviceMap.get(device_id);
        device.setStatus(status);
        // 通知更新日志表
        MqttService.publish(String.valueOf(device_id), "/shadow/update/" + device_id, status, QOS.QOS1);
    }

    // 获取设备状态
    public static String getDeviceStatus(long device_id) {
        Device device = deviceMap.get(device_id);
        return device != null ? device.getStatus() : null;
    }
}
