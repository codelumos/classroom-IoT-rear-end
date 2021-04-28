package org.nju.iot.clientMock.impl;

import com.alibaba.fastjson.JSONObject;
import org.nju.iot.clientMock.Device;

public class AirCondition implements Device {
    public long device_id;
    public String credential;
    public int open_state;
    public int gear;
    public int temperature;
    public int pattern;

    public AirCondition(long device_id, String credential) {
        this.device_id = device_id;
        this.credential = credential;
        open_state = 0;
        gear = 0;
        temperature = 0;
        pattern = 0;
    }

    public String getStatus() {
        JSONObject state = new JSONObject();
        state.put("openState", open_state);
        state.put("gear", gear);
        state.put("temperature", temperature);
        state.put("pattern", pattern);
        JSONObject status = new JSONObject();
        status.put("state", state.toJSONString());

        return status.toJSONString();
    }

    // 更新设备状态
    public void setStatus(String status) {
        JSONObject json_state = JSONObject.parseObject(status);
        String state = json_state.getString("state");
        JSONObject json_status = JSONObject.parseObject(state);

        open_state = json_status.getIntValue("openState");
        gear = json_status.getIntValue("gear");
        temperature = json_status.getIntValue("temperature");
        pattern = json_status.getIntValue("pattern");

    }
}
