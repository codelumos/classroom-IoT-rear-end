package org.nju.iot.clientMock;

import com.alibaba.fastjson.JSONObject;

public class Projector implements Device {
    public long device_id;
    public String credential;
    public int open_state;

    public Projector(long device_id, String credential) {
        this.device_id = device_id;
        this.credential = credential;
        open_state = 0;
    }

    public String getStatus() {
        JSONObject state = new JSONObject();
        state.put("openState", open_state);
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
    }
}
