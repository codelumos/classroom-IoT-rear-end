package org.nju.iot.VO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class GroupVO {
    List<DeviceVO> deviceVOS;
    private long id;
    private String teamName;
    private Timestamp createTime;
}
