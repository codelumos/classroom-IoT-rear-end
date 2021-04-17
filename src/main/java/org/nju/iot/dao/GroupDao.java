package org.nju.iot.dao;

import org.nju.iot.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<GroupEntity, Long> {
}
