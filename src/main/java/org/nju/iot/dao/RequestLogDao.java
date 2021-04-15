package org.nju.iot.dao;

import org.nju.iot.model.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogDao extends JpaRepository<RequestLogEntity, Long> {
}
