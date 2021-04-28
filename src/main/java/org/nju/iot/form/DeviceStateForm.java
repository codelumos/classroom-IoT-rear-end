package org.nju.iot.form;

import lombok.Data;

@Data
public class DeviceStateForm {
    // 设备ID
    private long id;
    // 设备类型
    private int deviceType = -1;
    // 设备开关状态
    public int openState = -1;

    // 灯->type=0
    public int lampSense = -1;      // 感光度:0~10
    public int brightness = -1;     // 亮度:0~10
    // 空调->type=1
    public int gear = -1;           // 档位:0~3
    public int temperature = -1;    // 温度
    public int pattern = -1;        // 模式:1->制冷;2->制热
}
