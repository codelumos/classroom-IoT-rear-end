package org.nju.iot.form;

import lombok.Data;

@Data
public class DeviceTestForm {
	private long id;
	private int deviceType = -1;
	//所有设备共享
	public int openState = -1;
	//灯->type=0
	public int lampSense = -1;
	public int brightness = -1;
	//空调->type=1
	public int gear = -1;
	public int temperature = -1;
	public int pattern = -1;
}
