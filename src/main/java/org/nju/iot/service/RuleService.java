package org.nju.iot.service;

import org.nju.iot.dao.RuleDao;
import org.nju.iot.model.drools.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RuleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

	@Autowired
	private RuleDao ruleDao;
	@Autowired
	private DeviceService deviceService;

	public void executeRule(QueryParam param) {
		LOGGER.info("参数数据:"+param.getParamSign());
	}

	public void executeRemoveRule(QueryParam param) {
		LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
		ruleDao.deleteById(param.getParamId());
	}

}
