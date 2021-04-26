package org.nju.iot.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.nju.iot.form.DeviceTestForm;
import org.nju.iot.model.DroolsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private KieBase kieBase;
    @Autowired
    private DeviceService deviceService;

    public void execute(List<DeviceTestForm> device, DroolsEntity drools) {
        for (DeviceTestForm d : device) {
            // 执行规则
            KieSession session = kieBase.newKieSession();
            session.insert(drools);
            session.insert(d);
            session.fireAllRules();
            session.dispose();
            // 执行修改调试
            deviceService.deviceTest(d);
        }
    }

}
