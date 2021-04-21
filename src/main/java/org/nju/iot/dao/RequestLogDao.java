package org.nju.iot.dao;

import org.nju.iot.model.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogDao extends JpaRepository<RequestLogEntity, Long> {

	@Query(nativeQuery = true, value = "select * from request_log where device_id=:deviceId order by request_time desc limit 1")
	RequestLogEntity findByDeviceId(@Param("deviceId") long deviceId);
}
