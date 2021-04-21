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
	void updateStatusByDeviceId(@Param("status") String status, @Param("id") long id);

	@Query(nativeQuery = true, value = "select id from device where credential=:credential")
	Long validateApprove(@Param("credential") String credential);

	@Query(nativeQuery = true, value = "select * from device where credential=:credential")
	DeviceEntity findByCredential(@Param("credential") String credential);

	@Query(nativeQuery = true, value = "select * from device where group_id=:groupId")
	List<DeviceEntity> findByGroupId(@Param("groupId") long groupId);

	@Query(nativeQuery = true, value = "update device set group_id=:groupId where id=:deviceId")
	void updateGroupIdById(@Param("groupId") long groupId, @Param("deviceId") long deviceId);

	@Query(nativeQuery = true, value = "select * from device where device_name=:deviceName")
	DeviceEntity findByName(@Param("deviceName") String deviceName);

	@Query(nativeQuery = true, value = "select * from device where device_type=:type")
	List<DeviceEntity> findByType(@Param("type") int type);
}
