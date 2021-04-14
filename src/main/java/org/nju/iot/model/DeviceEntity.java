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
@Table(name = DeviceEntity.TABLE)
public class DeviceEntity {
	public static final String TABLE = "device";
	@Id
	private long id;
	private String deviceName;
	private int deviceType;
	private Timestamp createTime;
	private String status;

}
