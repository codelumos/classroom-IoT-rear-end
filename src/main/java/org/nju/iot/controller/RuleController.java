package org.nju.iot.controller;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.process.Process;
import org.kie.api.definition.rule.Query;
import org.kie.api.definition.rule.Rule;
import org.kie.api.definition.type.FactType;
import org.kie.api.event.kiebase.KieBaseEventListener;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.KieSessionsPool;
import org.kie.api.runtime.StatelessKieSession;
import org.nju.iot.model.drools.QueryParam;
import org.nju.iot.model.drools.RuleResult;
import org.nju.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/rule")
public class RuleController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public void executeRule(@RequestParam String paramSign){
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer("myKBase");
		KieSession kieSession = kc.newKieSession("ksession-rule");
		QueryParam queryParam = new QueryParam();
		queryParam.setParamSign(paramSign);
		// 入参
		kieSession.insert(queryParam);
		kieSession.insert(deviceService);
		// 返参
		RuleResult resultParam = new RuleResult();
		kieSession.insert(resultParam);
		kieSession.fireAllRules();
		kieSession.dispose();
	}

}
