package org.nju.iot.controller;

import org.nju.iot.form.DeviceStateForm;
import org.nju.iot.model.DroolsEntity;
import org.nju.iot.model.RuleEntity;
import org.nju.iot.service.DeviceService;
import org.nju.iot.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<RuleEntity> queryAll() {
        return ruleService.queryAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RuleEntity update(@RequestParam String id, @RequestParam String name, @RequestParam String description) {
        return ruleService.update(id, name, description);
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public void executeRule(@RequestParam List<Long> deviceIds, @RequestParam String ruleId) {
        List<DeviceStateForm> device = deviceService.getStatus(deviceIds);
        DroolsEntity drools = new DroolsEntity(ruleId);
        ruleService.execute(device, drools);
    }

}
