package org.nju.iot.dao;

import org.nju.iot.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogDao extends JpaRepository<RequestLog, Long> {
}
