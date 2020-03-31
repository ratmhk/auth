package com.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    /** 上下文 */
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 根据Bean ID获取Bean
     *
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(beanId);
    }
}
