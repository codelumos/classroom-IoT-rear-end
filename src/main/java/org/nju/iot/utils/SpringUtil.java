package org.nju.iot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil extends ApplicationObjectSupport {
    public static ApplicationContext context;

    public static Object getBean(String serviceName){
        return context.getBean(serviceName);
    }

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        SpringUtil.context = context;
    }
}
