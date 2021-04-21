package org.nju.iot.utils;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.InputStream;

@Slf4j
public class DroolsUtil {
    // 实例化 kie帮助类
    private KieHelper kieHelper = new KieHelper();

    public DroolsUtil() {
    }

    public KieHelper getKieHelper() {
        return kieHelper;
    }

    public static DroolsUtil getInstance() {
        return new DroolsUtil();
    }

    /**
     * 读取resources目录下规则
     */
    public DroolsUtil loadRuleResourcesFile(String file) {
        log.info("rule file ={}", file);
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        log.info("inputStream2 ={}", inputStream);
        Resource resource = ResourceFactory.newInputStreamResource(inputStream);
        kieHelper.addResource(resource, ResourceType.DRL);
        return this;
    }

    /**
     * 读取规则内容
     */
    public DroolsUtil loadRuleContent(String content) {
        log.info("rule content ={}", content);
        kieHelper.addContent(content, ResourceType.DRL);
        return this;
    }

    /**
     * 读取规则Url
     */
    public DroolsUtil loadRuleUrl(String url) {
        log.info("rule url ={}", url);
        kieHelper.addResource(ResourceFactory.newUrlResource(url), ResourceType.DRL);
        return this;
    }

    /**
     * 验证
     */
    public KieHelper verify() {
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.error("rule error ={}", results.getMessages());
            throw new IllegalStateException("rule error: " + results.getMessages());
        }
        return kieHelper;
    }

    /**
     * 编译 返回 KieSession
     */
    public KieSession buildNewKieSession() {
        return kieHelper.build().newKieSession();
    }

    public void dispose() {
        kieHelper = null;
    }
}


