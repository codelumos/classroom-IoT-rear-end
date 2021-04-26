package org.nju.iot.VO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestLogVO {
    private Timestamp requestTime;
    private String status;
}
