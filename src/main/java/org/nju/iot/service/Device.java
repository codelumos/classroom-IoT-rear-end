package org.nju.iot.service;

import org.springframework.stereotype.Service;

public class Device {
    public long device_id;
    public String credential;
    public boolean on_off;
    public Device(long device_id,String credential){
        this.device_id=device_id;
        this.credential=credential;
    }
}
