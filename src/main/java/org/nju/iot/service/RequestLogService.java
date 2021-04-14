package org.nju.iot.service;

import org.nju.iot.dao.RequestLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestLogService {

	@Autowired
	private RequestLogDao requestLogDao;

}
