package org.nju.iot.form;

import lombok.Data;

@Data
public class DeviceForm {
	private long id;
	private String deviceName;
	private int deviceType;
	private String status;
	private long groupId;
	private String credential;
}
