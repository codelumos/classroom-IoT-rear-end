package org.nju.iot.service;

import org.nju.iot.dao.RuleDao;
import org.nju.iot.model.drools.QueryParam;
import org.nju.iot.model.drools.RuleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RuleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

	@Autowired
	private RuleDao ruleDao;

	public void executeAddRule(QueryParam param) {
		LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
		RuleEntity ruleEntity = new RuleEntity() ;
		ruleEntity.setId(param.getParamId());
		ruleEntity.setParamSign(param.getParamSign());
		ruleEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		ruleEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		ruleDao.save(ruleEntity);
	}

	public void executeRemoveRule(QueryParam param) {
		LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
		ruleDao.deleteById(param.getParamId());
	}

}
