package com.icop.amqp.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: liukj
 * @date: 2020/8/25
 * @description：
 */
//@Component
public final class SpringContextUtil implements ApplicationContextAware {

   // @Autowired
    DefaultListableBeanFactory defaultListableBeanFactory;

    private static SpringContextUtil springContext = null;
    private ApplicationContext applicationContext = null;

    private SpringContextUtil(){}

    public SpringContextUtil getInstance(){
        if(null == springContext){
            synchronized (SpringContextUtil.class){
                if(null == springContext){
                    springContext = new SpringContextUtil();
                }
            }
        }
        return springContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void registerSingleBean(String beanName, Object bean){
        defaultListableBeanFactory.registerSingleton(beanName,bean);
    }

    public Object getBean(Object bean){
        return applicationContext.getBean(bean.getClass());
    }


}
