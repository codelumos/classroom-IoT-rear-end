package org.nju.iot.controller;

import org.nju.iot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drools")
public class RuleController {

	@Autowired
	private RuleService ruleService;

	@RequestMapping(value = "/rule", method = RequestMethod.POST)
	public void rule() {
//		ruleService.rule();
	}

}
