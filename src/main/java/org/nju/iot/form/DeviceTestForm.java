package org.nju.iot.form;

import lombok.Data;

@Data
public class DeviceTestForm {
	private long id;
	private int deviceType;
	private String status;
	private long groupId;
	private String credential;
}
