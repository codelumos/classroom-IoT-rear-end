package org.nju.iot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = RequestLogEntity.TABLE)
public class RequestLogEntity {
	public static final String TABLE = "request_log";

	@Id
	private long id;
	private long deviceId;
	private Timestamp requestTime;
}
