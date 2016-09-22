package com.maksimov.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created on 22.09.16.
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public void setApplicationContext(ApplicationContext context) {
        SpringUtils.applicationContext = context;
    }

}
