package org.nju.iot.controller;

import org.kie.api.runtime.KieSession;
import org.nju.iot.model.drools.QueryParam;
import org.nju.iot.model.drools.RuleResult;
import org.nju.iot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {

	@Autowired
	private RuleService ruleService;
	private KieSession kieSession;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void param (){
		QueryParam queryParam1 = new QueryParam() ;
		queryParam1.setParamId("1");
		queryParam1.setParamSign("+");
		QueryParam queryParam2 = new QueryParam() ;
		queryParam2.setParamId("2");
		queryParam2.setParamSign("-");
		// 入参
		kieSession.insert(queryParam1) ;
		kieSession.insert(queryParam2) ;
		kieSession.insert(ruleService) ;
		// 返参
		RuleResult resultParam = new RuleResult() ;
		kieSession.insert(resultParam) ;
		kieSession.fireAllRules() ;
	}

}
