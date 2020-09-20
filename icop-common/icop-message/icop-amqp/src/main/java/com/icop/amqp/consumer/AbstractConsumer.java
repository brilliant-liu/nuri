package com.icop.amqp.consumer;

import com.alibaba.fastjson.JSONObject;
import com.icop.amqp.entities.MessageContent;
import com.icop.base.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author: liukj
 * @date: 2020/8/27
 * @description： 消费者基类
 */
@Slf4j
public abstract class AbstractConsumer<T extends MessageContent,M extends com.icop.amqp.entities.Message<T>> extends MessageListener {

    public void consumer(Message message){
        MessageProperties messageProperties = message.getMessageProperties();
        String messageId = messageProperties.getMessageId();
        String correlationId = messageProperties.getCorrelationId();
        String messageStr = new String (message.getBody(), StandardCharsets.UTF_8);
        try{
            // 获取消息对象
            M messageBody = getInstance(messageStr);



        }catch (Exception e){
            log.error("消息消费失败，消息ID:{},消息correlationId：{}，消息内容：{}",messageId,correlationId,messageStr);
            //TODO,异常处理逻辑

        }


        return;
    }

    /**
     * 获取message对象的实例,
     * @param messageStr 消息内容，格式为Json格式
     * @return
     */
    abstract M getInstance(String messageStr);



}
