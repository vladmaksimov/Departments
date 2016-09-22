package com.maksimov.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created on 22.09.16.
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        SpringUtils.applicationContext = context;
    }

}
