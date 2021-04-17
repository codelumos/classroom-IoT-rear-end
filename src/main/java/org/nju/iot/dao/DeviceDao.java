package org.nju.iot.dao;

import org.nju.iot.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DeviceDao extends JpaRepository<DeviceEntity,Long> {
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update device set status=:status where id=:id")
	void updateStatus(@Param("status") String status, @Param("id") long id);

	@Query(nativeQuery = true, value = "select id from device where credential=:credential")
	boolean validateApprove(@Param("credential") String credential);

	@Query(nativeQuery = true, value = "select * from device where credential=:credential")
	DeviceEntity findByCredential(@Param("credential") String credential);

	@Query(nativeQuery = true, value = "select * from device where groupId=:groupId")
	List<DeviceEntity> findByGroupId(@Param("groupId") long groupId);

	@Query(nativeQuery = true, value = "update device set groupId=:groupId where id=:deviceId")
	void updateGroupIdById(@Param("groupId") long groupId, @Param("deviceId") long deviceId);
}
