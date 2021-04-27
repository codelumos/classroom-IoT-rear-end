package org.nju.iot.service;


import com.alibaba.fastjson.JSONObject;
import org.nju.iot.VO.DeviceVO;
import org.nju.iot.clientMock.DeviceManage;
import org.nju.iot.clientMock.MqttService;
import org.nju.iot.config.MqttConfig;
import org.nju.iot.constant.Lock;
import org.nju.iot.constant.QOS;
import org.nju.iot.dao.DeviceDao;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.form.DeviceTestForm;
import org.nju.iot.model.DeviceEntity;
import org.nju.iot.model.RequestLogEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private RequestLogDao requestLogDao;

    // 添加设备
    public long addDevice(String deviceName, int deviceType) throws Exception {
        DeviceEntity device = new DeviceEntity();
        if (deviceDao.findByName(deviceName) != null) {
            return -1;
        }
        device.setDeviceName(deviceName);
        device.setDeviceType(deviceType);
        device.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        device.setCredential(uuid);
        return deviceDao.save(device).getId();
    }

    // 设备烧录
    public boolean deviceConnection(String credential) {
        DeviceEntity deviceEntity = deviceDao.findByCredential(credential);
        // 验证并添加
        Lock.setLock(true);
        DeviceManage.Verify(deviceEntity.getId(), deviceEntity.getCredential(), deviceEntity.getDeviceType());
        while (Lock.isLock()) {
        }
        // 查看是否成功烧录设备
        if (DeviceManage.hasDevice(deviceEntity.getId())) {
            System.out.println("设备烧录成功");
            return true;
        } else {
            System.out.println("设备烧录失败");
            return false;
        }
    }

    // 设备调试
    public void deviceTest(DeviceTestForm form) {
        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        object1.put("openState", form.getOpenState());
        if (form.getDeviceType() == 0) {
            object1.put("brightness", form.getBrightness());
            object1.put("lampSense", form.getLampSense());
        }
        if (form.getDeviceType() == 1) {
            object1.put("pattern", form.getPattern());
            object1.put("gear", form.getGear());
            object1.put("temperature", form.getTemperature());
        }
        object2.put("state", object1.toJSONString());
        MqttService.publish(String.valueOf(form.getId()), "/shadow/get/" + form.getId(), object2.toJSONString(), QOS.QOS1);
    }

    // 根据规则批量修改
    public void updateStatusByRule(DeviceTestForm form) {
        JSONObject object = new JSONObject();
        object.put("openState", form.getOpenState());
        if (form.getDeviceType() == 0) {
            object.put("brightness", form.getBrightness());
            object.put("lampSense", form.getLampSense());
        }
        if (form.getDeviceType() == 1) {
            object.put("pattern", form.getPattern());
            object.put("gear", form.getGear());
            object.put("temperature", form.getTemperature());
        }
        object.put("state", object.toJSONString());
        if (form.getDeviceType() != -1) {
            List<DeviceEntity> entities = deviceDao.findByType(form.getDeviceType());
            entities.forEach(e -> MqttService.publish(String.valueOf(e.getId()), "/shadow/get/" + e.getId(), object.toJSONString(), QOS.QOS1));
        }
    }

    // 获取设备列表
    public List<DeviceVO> getDeviceList() {
        List<DeviceEntity> entities = deviceDao.findAll();
        List<DeviceVO> deviceVOS = new ArrayList<>();
        entities.forEach(e -> {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(e, deviceVO);
            deviceVO.setOnlineState(MqttService.hasClient(String.valueOf(e.getId())));
            RequestLogEntity deviceLog = requestLogDao.findByDeviceId(e.getId());
            deviceVO.setStatus(deviceLog != null ? deviceLog.getStatus() : null);
            deviceDao.updateStatusByDeviceId(deviceLog != null ? deviceLog.getStatus() : null, e.getId());
            deviceVOS.add(deviceVO);
        });
        return deviceVOS;
    }

    // 获取单个设备详情
    public DeviceVO getDetail(long deviceId) {
        DeviceEntity deviceEntity = deviceDao.getOne(deviceId);
        DeviceVO deviceVO = new DeviceVO();
        BeanUtils.copyProperties(deviceEntity, deviceVO);
        deviceVO.setOnlineState(MqttService.hasClient(String.valueOf(deviceEntity.getId())));
        RequestLogEntity deviceLog = requestLogDao.findByDeviceId(deviceEntity.getId());
        deviceVO.setStatus(deviceLog != null ? deviceLog.getStatus() : null);
        deviceDao.updateStatusByDeviceId(deviceLog != null ? deviceLog.getStatus() : null, deviceEntity.getId());
        return deviceVO;
    }


    // 删除设备
    public boolean deleteDevices(List<Long> deviceIds) {
        deviceIds.forEach(d -> deviceDao.deleteById(d));
        // 通知设备端删除设备
        deviceIds.forEach(d -> MqttService.publish(MqttConfig.device_end_id, "/verify/get", "delete@" + d, QOS.QOS1));
        return true;
    }

    public List<DeviceTestForm> getStatus(List<Long> ids) {
        List<DeviceTestForm> forms = new ArrayList<>();
        ids.forEach(d -> {
            DeviceTestForm form = new DeviceTestForm();
            RequestLogEntity logEntity = requestLogDao.findByDeviceId(d);
            JSONObject object = JSONObject.parseObject(logEntity.getStatus());
            String state = object.getString("state");
            JSONObject status = JSONObject.parseObject(state);
            form.setOpenState(status.getIntValue("openState"));
            form.setId(d);
            int type = deviceDao.getOne(d).getDeviceType();
            form.setDeviceType(type);
            if (type == 0) {
                form.setBrightness(status.getIntValue("brightness"));
                form.setLampSense(status.getIntValue("lampSense"));
            }
            if (type == 1) {
                form.setPattern(status.getIntValue("pattern"));
                form.setTemperature(status.getIntValue("temperature"));
                form.setGear(status.getIntValue("gear"));
            }
            forms.add(form);

        });
        return forms;
    }

}
