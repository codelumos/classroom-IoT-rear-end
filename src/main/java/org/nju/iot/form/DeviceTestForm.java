package org.nju.iot.form;

import lombok.Data;

@Data
public class DeviceTestForm {
	private long id;
	private int deviceType;
	//所有设备共享
	public int openState;
	//灯->type=0
	public int lampSense;
	public int brightness;
	//空调->type=1
	public int gear;
	public int temperature;
	public int pattern;
}
