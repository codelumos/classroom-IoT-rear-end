package org.nju.iot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = GroupEntity.TABLE)
public class GroupEntity {
    public static final String TABLE = "device_group";
    @Id
    private long id;
    private String groupName;
    private Timestamp createTime;
}
