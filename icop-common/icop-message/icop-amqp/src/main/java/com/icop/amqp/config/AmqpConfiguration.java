package com.icop.amqp.config;

import com.icop.amqp.enums.ExchangeEnum;
import com.icop.amqp.enums.QueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author: liukj
 * @date: 2020/8/21
 * @description： AMQP配置类
 */
@Configuration
public class AmqpConfiguration implements BeanDefinitionRegistryPostProcessor{

    private ConfigurableListableBeanFactory configurableListableBeanFactory;
    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;


    /**
     * 注册交换机
     *
     * @return
     */
    @Bean("registerExchange")
    public Object registerExchange() {
        ExchangeEnum.exchangeList.forEach(exchange -> {
            String exchangeType = exchange.exchangeType;
            Exchange registerExchange = null;
            switch (exchangeType) {
                case ExchangeTypes.DIRECT:
                    registerExchange = new DirectExchange(exchange.name, exchange.durable, exchange.autoDelete, ExchangeEnum.arguments.get(exchange.name));
                    break;
                case ExchangeTypes.TOPIC:
                    registerExchange = new TopicExchange(exchange.name, exchange.durable, exchange.autoDelete, ExchangeEnum.arguments.get(exchange.name));
                    break;
                case ExchangeTypes.FANOUT:
                    registerExchange = new FanoutExchange(exchange.name, exchange.durable, exchange.autoDelete, ExchangeEnum.arguments.get(exchange.name));
                    break;
                case ExchangeTypes.HEADERS:
                    registerExchange = new HeadersExchange(exchange.name, exchange.durable, exchange.autoDelete, ExchangeEnum.arguments.get(exchange.name));
                    break;
                default:
                    break;
            }

            if (null != registerExchange) {
                // 生成一个Bean,交给容器管理
                registerBean(registerExchange.getName(), registerExchange);
            }
        });
        return null;
    }

    /**
     * 注册队列
     *
     * @return
     */
    @Bean(name = "registerQueue")
    public Object registerQueue() {
        QueueEnum.queueLists.forEach(queue -> {
            Queue registerQueue = new Queue(queue.name, queue.durable, queue.exclusive, queue.autoDelete,QueueEnum.arguments.get(queue.name));
            // 注册队列
            registerBean(registerQueue.getName(), registerQueue);
            String bindName = queue.name + queue.exchangeName + queue.routingKey;
            // 注册绑定
            registerBean(bindName, binding(queue));
        });
        return null;
    }

    /**
     * 项容器中注入一个单例bean
     *
     * @param beanName bean名称
     * @param bean bean对象
     */
    private void registerBean(String beanName, Object bean) {
        if(StringUtils.isEmpty(beanName)){
            return;
        }
        String name = beanName.replaceAll("\\.","");
        configurableListableBeanFactory.registerSingleton(name, bean);
    }

    private Binding binding(QueueEnum queueEnum) {
        return new Binding(queueEnum.name, Binding.DestinationType.QUEUE, queueEnum.exchangeName, queueEnum.routingKey, null);
    }

   /* *//**
     * 创建消息转化格式，转化为json格式
     * @return
     *//*
    //@Bean(name = "jackson2JsonMessageConverter")
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitMessagingTemplate myRabbitMessagingTemplate(){
        RabbitMessagingTemplate myRabbitMessagingTemplate = new RabbitMessagingTemplate();
        myRabbitMessagingTemplate.setAmqpMessageConverter(jackson2JsonMessageConverter());
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        myRabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return myRabbitMessagingTemplate;
    }*/

    @Bean("myRabbitTemplate")
    public RabbitTemplate myRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate myRabbitTemplate = new RabbitTemplate(connectionFactory);
        myRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 在发送消息时设置强制标志；仅适用于 returnCallback已提供的情况。
        myRabbitTemplate.setMandatory(true);
        return myRabbitTemplate;

    }

    /**
     * @Desc： 自定义消息监听工厂
     */
    /*@Bean(name = "myRabbitListenerFactory")
    public SimpleRabbitListenerContainerFactory mySimpleRabbitListenerContanerFactory(ConnectionFactory connectionFactory,SimpleRabbitListenerContainerFactoryConfigurer defaultFactoryConf){
        SimpleRabbitListenerContainerFactory myFactory = new SimpleRabbitListenerContainerFactory();
        myFactory.setConnectionFactory(connectionFactory);
        //Jackson2JsonMessageConverter jackson2JsonMessageConverter = (Jackson2JsonMessageConverter) configurableListableBeanFactory.getBean("jackson2JsonMessageConverter");
        myFactory.setMessageConverter(jackson2JsonMessageConverter());
        defaultFactoryConf.configure(myFactory,connectionFactory);

        return myFactory;
    }*/


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
      return;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

}
