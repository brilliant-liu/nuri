package com.icop.redis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: liukj
 * @create: 2019-01-26
 * @description: 获取Spring容器内已经存在的对象，使用注解的形式往spring容器中注入bean
 **/
@Component
public class BeanUtils implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    private static ApplicationContext applicationContext =null;

    private static BeanUtils beanUtils =new BeanUtils();


    private BeanUtils(){ }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if( BeanUtils.applicationContext == null ){
            BeanUtils.applicationContext=applicationContext;
        }
        logger.info("====== applicationContext INIT SUCCESSS ======");
    }

    /**
     * @Author: liukj
     * @Date: 2019/1/26 0026
     * @Description: 提供spring容器的对象接口
     */
    public static ApplicationContext getApplicationContext(){
        return BeanUtils.applicationContext;
    }

    /**
     * @Author: liukj
     * @Date: 2019/1/26 0026
     * @Description: 提供Bean管理工具的唯一实例
     */

    public static BeanUtils getBeanUtils(){
        return beanUtils;
    }

    /**
     * @Author: liukj
     * @Date: 2019/1/26 0026
     * @Description: 根据bean对象的名称获取Bean
     */
    public static Object getBean(String beanName){
        return getApplicationContext().getBean(beanName);
    }

    /**
     * @Author: liukj
     * @Date: 2019/1/26 0026
     * @Description: 根据bean对象的路径获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * @Author: liukj
     * @Date: 2019/1/26 0026
     * @Description: 当该bean存在多个不同的name是，可使用此方法获取Bean
     */
    public static <T> T getBean(String name,Class<T> clazz) {
        return getApplicationContext().getBean(name,clazz);
    }


}