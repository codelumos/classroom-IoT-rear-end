package org.nju.iot.controller;

import org.nju.iot.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataAnalysisController {
	@Autowired
	private DataAnalysisService analysisService;

}
