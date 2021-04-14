package org.nju.iot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = RequestLog.TABLE)
public class RequestLog {
	public static final String TABLE = "request_log";
	@Id
	private long id;
	private String deviceName;
	private Timestamp requestTime;
}
