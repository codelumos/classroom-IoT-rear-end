package org.nju.iot.dao;

import org.nju.iot.model.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDao extends JpaRepository<RuleEntity, String> {
}
