package org.nju.iot.dao;

import org.nju.iot.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity,Long> {

}
