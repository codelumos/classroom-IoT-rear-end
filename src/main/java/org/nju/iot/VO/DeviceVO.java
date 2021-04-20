package org.nju.iot.VO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeviceVO {
	private long id;
	private String deviceName;
	private int deviceType;
	private Timestamp createTime;
	private String status;
	private Timestamp onlineTime;
	private long groupId;
	private String credential;
	private boolean onlineState;
}
