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
import org.nju.iot.form.DeviceTestForm;
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
		DeviceTestForm form = new DeviceTestForm();
		if (paramSign.equals("开灯")) {
			form.setDeviceType(0);
			form.setOpenState(1);
			form.setLampSense(5);
			form.setBrightness(3);
		}
		if (paramSign.equals("关灯")) {
			form.setDeviceType(0);
        form.setOpenState(0);
        form.setLampSense(0);
        form.setBrightness(0);
		}
		if (paramSign.equals("开空调")) {
			form.setDeviceType(1);
			form.setOpenState(1);
			form.setGear(3);
			form.setTemperature(3);
			form.setPattern(1);
		}
		if (paramSign.equals("关空调")) {
			form.setDeviceType(1);
			form.setOpenState(0);
			form.setGear(0);
			form.setTemperature(0);
			form.setPattern(0);
		}if (paramSign.equals("开投影仪")) {
			form.setDeviceType(2);
			form.setOpenState(1);
		}if (paramSign.equals("关投影仪")) {
			form.setDeviceType(2);
			form.setOpenState(0);
		}
		deviceService.updateStatusByRule(form);
//		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer("myKBase");
//		KieSession kieSession = kc.newKieSession("ksession-rule");
//		QueryParam queryParam = new QueryParam();
//		queryParam.setParamSign(paramSign);
//		// 入参
//		kieSession.insert(queryParam);
//		kieSession.insert(deviceService);
//		// 返参
//		RuleResult resultParam = new RuleResult();
//		kieSession.insert(resultParam);
//		kieSession.fireAllRules();
//		kieSession.dispose();
	}

}
