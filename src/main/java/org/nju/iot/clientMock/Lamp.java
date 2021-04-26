package org.nju.iot.clientMock;

import com.alibaba.fastjson.JSONObject;

public class Lamp implements Device {
    public long device_id;
    public String credential;
    public int open_state;
    public int lamp_sense;
    public int brightness;
    public Lamp(long device_id,String credential){
        this.device_id=device_id;
        this.credential=credential;
        open_state=0;
        lamp_sense=0;
        brightness=0;
    }
    //更新设备状态
    public void setStatus(String status){
        JSONObject json_state=JSONObject.parseObject(status);
        String state=json_state.getString("state");
        JSONObject json_status=JSONObject.parseObject(state);

        open_state=json_status.getIntValue("openState");
        lamp_sense=json_status.getIntValue("lampSense");
        brightness=json_status.getIntValue("brightness");
        System.out.println("device_id:"+device_id+" credential:"+credential+"  openState:"+open_state+" lampSense:"+lamp_sense+" brightness:"+brightness);
    }
    public String getStatus(){
        JSONObject state=new JSONObject();
        state.put("openState",open_state);
        state.put("lampSense",lamp_sense);
        state.put("brightness",brightness);
        JSONObject status=new JSONObject();
        status.put("state",state.toJSONString());

        return status.toJSONString();
    }
}
