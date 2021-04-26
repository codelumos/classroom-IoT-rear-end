package org.nju.iot;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.nju.iot.form.DeviceTestForm;
import org.nju.iot.model.DroolsEntity;

public class DroolsTest {
    @Test
    public void test1() {
        KieServices kieServices = KieServices.Factory.get();
        // 获得Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();
        // Fact对象，事实对象
        DeviceTestForm form = new DeviceTestForm();
        DroolsEntity drools = new DroolsEntity("1");
        // 将对象插入到工作内存中
        session.insert(form);
        session.insert(drools);
        // 激活规则，由Drools框架自动进行规则匹配，如果匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }
}
