package org.nju.iot.dao;

import org.nju.iot.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity,Long> {
	@Modifying
	@Transactional
	@Query(value = "update " + DeviceEntity.TABLE + " t set t.status=:status where t.id=:id", nativeQuery = true)
	boolean updateStatus(@Param("status") String status, @Param("id") long id);
	@Query(value = "select t.id from " + DeviceEntity.TABLE + " t where t.approve=:approve", nativeQuery = true)
	boolean validateApprove(@Param("approve") String approve);
}
