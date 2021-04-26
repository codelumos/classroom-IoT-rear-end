package org.nju.iot.controller;

import org.nju.iot.VO.RequestLogVO;
import org.nju.iot.dao.DeviceDao;
import org.nju.iot.dao.RequestLogDao;
import org.nju.iot.model.RequestLogEntity;
import org.nju.iot.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/requestLog")
public class RequestLogController {

	@Autowired
	private RequestLogService logService;

	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public Map<String, List<RequestLogVO>> getOverview() {
		return logService.getOverview();
	}
}
