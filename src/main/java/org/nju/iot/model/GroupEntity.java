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
@Table(name = GroupEntity.TABLE)
public class GroupEntity {
	public static final String TABLE = "group";
	@Id
	private long id;
	private String groupName;
	private Timestamp createTime;
}
