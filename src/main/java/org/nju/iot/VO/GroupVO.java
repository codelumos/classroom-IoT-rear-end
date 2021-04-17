package org.nju.iot.VO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class GroupVO {
	private long id;
	private String groupName;
	private Timestamp createTime;
	List<DeviceVO> deviceVOS;
}
