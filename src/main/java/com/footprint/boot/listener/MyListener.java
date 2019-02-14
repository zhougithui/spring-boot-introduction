package com.footprint.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<ApplicationEvent>, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    private ApplicationContext applicationContext;

    public void onApplicationEvent(ApplicationEvent event) {
        logger.info((applicationContext != null ? applicationContext.getId() : "11" )+ "接收到应用事件：" + event.getClass().getName());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
