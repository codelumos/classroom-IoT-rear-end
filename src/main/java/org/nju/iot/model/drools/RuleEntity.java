package org.nju.iot.model.drools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = RuleEntity.TABLE)
public class RuleEntity {
    public static final String TABLE = "rule";
    @Id
    private String id;
    private String paramSign;
    private Timestamp createTime;
    private Timestamp updateTime;
}
