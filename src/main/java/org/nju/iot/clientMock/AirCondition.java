package org.nju.iot.clientMock;

import com.alibaba.fastjson.JSONObject;

public class AirCondition implements Device {
    public long device_id;
    public String credential;
    public int open_state;
    public int gear;
    public int temperature;
    public int pattern;
    public AirCondition(long device_id,String credential){
        this.device_id=device_id;
        this.credential=credential;
        open_state=0;
        gear=0;
        temperature=0;
        pattern=0;
    }
    //更新设备状态
    public void setStatus(String status){
        JSONObject json_state=JSONObject.parseObject(status);
        String state=json_state.getString("state");
        JSONObject json_status=JSONObject.parseObject(state);

        open_state=json_status.getIntValue("openState");
        gear=json_state.getIntValue("gear");
        temperature=json_state.getIntValue("temperature");
        pattern=json_state.getIntValue("pattern");
        System.out.println("device_id:"+device_id+" credential:"+credential+"  openState:"+open_state+" gear:"+gear+" temperature:"+temperature+" pattern"+pattern);
    }
}
