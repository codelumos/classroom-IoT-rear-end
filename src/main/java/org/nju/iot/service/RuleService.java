package org.nju.iot.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.nju.iot.dao.RuleDao;
import org.nju.iot.form.DeviceTestForm;
import org.nju.iot.model.DroolsEntity;
import org.nju.iot.model.RuleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class RuleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private RuleDao ruleDao;
    @Autowired
    private KieBase kieBase;
    @Autowired
    private DeviceService deviceService;

    public List<RuleEntity> queryAll() {
        return ruleDao.findAll();
    }

    public RuleEntity update(String id, String name, String description) {
        RuleEntity entity = ruleDao.getOne(id);
        entity.setName(name);
        entity.setDescription(description);
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return ruleDao.save(entity);
    }

    public void execute(List<DeviceTestForm> device, DroolsEntity drools) {
        for (DeviceTestForm d : device) {
            System.out.println("设备: " + d.getId() + ", 执行规则: " + drools.getId());
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
