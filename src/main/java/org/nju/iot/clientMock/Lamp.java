package org.nju.iot.clientMock;

import com.alibaba.fastjson.JSONObject;

public class Lamp implements Device {
    public long device_id;
    public String credential;
    public boolean on_off;
    public Lamp(long device_id,String credential){
        this.device_id=device_id;
        this.credential=credential;
        on_off=false;

    }
    //更新设备状态
    public void setStatus(String status){
        JSONObject json_state=JSONObject.parseObject(status);
        String state=json_state.getString("state");
        JSONObject json_status=JSONObject.parseObject(state);

        on_off=json_status.getBoolean("on_off");
        System.out.println("device_id:"+device_id+"credential:"+credential+"  on_off:"+on_off);
    }
}
