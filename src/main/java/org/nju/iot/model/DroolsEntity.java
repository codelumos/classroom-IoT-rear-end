package org.nju.iot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DroolsEntity {
    @Id
    private String id;

    public DroolsEntity() {
    }

    public DroolsEntity(String id) {
        this.id = id;
    }
}
